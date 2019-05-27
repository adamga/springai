package vacca.emptyspringrest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import vacca.emptyspringrest.entity.AppInfo;

@RestController
@RequestMapping("/info")
public class AppInfoController {

	protected static final Logger logger = LoggerFactory.getLogger(AppInfo.class);

	@Autowired
	private ApplicationContext applicationContext;

	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AppInfo getAppInfo() throws Exception {
		logger.info("Received AppInfo GET request");
		
		BuildProperties warBuildProperties = applicationContext.getBean(BuildProperties.class);
		
		//get timestamp at deployment
		String deployedTimestamp = System.getProperty("emptyspringrest.init_time");
		
		String env = applicationContext.getEnvironment().getProperty("environment");
		String name = warBuildProperties.get("name");
		String version = warBuildProperties.get("version");
		String buildNumber = warBuildProperties.get("buildNumber");
		
		return new AppInfo(env, name, version, buildNumber, deployedTimestamp);
		
	}

}
