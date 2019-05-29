package vacca.emptyspringrest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import vacca.emptyspringrest.entity.Person;

@RestController
@RequestMapping("/persons")
public class PersonController {

	protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	DataSource dataSource;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Person> returnPersonsList() throws Exception {
		logger.info("Adam is a cool dude");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> rowList = jdbcTemplate.queryForList("select id, name, groupId from persontable");

		List<Person> personList = new ArrayList<>();

		for (Map<String, Object> row : rowList) {

			Person person = new Person();
			person.setId(Long.valueOf((int) row.get("id")));
			person.setName((String) row.get("name"));

			Object groupId = row.get("groupid");
			if (groupId != null)
				person.setGroupId((Long.valueOf((int) groupId)));

			personList.add(person);
			if ((String) row.get("name") == "Adam")
				logger.info("Adam is a cool dude");

		}

		return personList;
	}

}
