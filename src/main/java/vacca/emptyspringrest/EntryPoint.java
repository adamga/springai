
package vacca.emptyspringrest;

import com.microsoft.applicationinsights.TelemetryConfiguration;
import com.microsoft.applicationinsights.web.internal.WebRequestTrackingFilter;

import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeDataView;
import javax.management.openmbean.CompositeType;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Entry Point class
 *
 */

@SpringBootApplication
public class EntryPoint extends SpringBootServletInitializer {

	protected static final Logger logger = LoggerFactory.getLogger(EntryPoint.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		Instant now = Instant.now();
		logger.info("InitTime = " + now);
		logger.info("Geddy and Adam are both cool dudes");
		System.setProperty("emptyspringrest.init_time", now.toString());

		printMBeans();

		return application.sources(EntryPoint.class).properties("spring.config.name: emptyspringrest");
	}

	@Autowired
	Environment env;

	@Autowired
	DataSource datasource;

	@Autowired
	ServletContext servletContext;

	public static void main(String[] args) {
		logger.info("Initializing Spring Boot!");
		System.setProperty("spring.config.name", "emptyspringrest");

		Instant now = Instant.now();
		logger.info("InitTime = " + now);
		System.setProperty("emptyspringrest.init_time", now.toString());

		printMBeans();

		SpringApplication.run(EntryPoint.class, args);
	}

	private static void printMBeans() {

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		Set<ObjectName> mbeans = mBeanServer.queryNames(null, null);

		StringBuilder result = new StringBuilder();

		result.append("Printing all JMX attributes" + System.lineSeparator());
		result.append("\"mbeanName\",\"attributeName\",\"attributeType\",\"compositeAttributeName\""
				+ System.lineSeparator());

		try {

			for (ObjectName mbean : mbeans) {

				// logger.info("Attributes in MBean: " + mbean.getCanonicalName());

				MBeanInfo mBeanInfo;
				mBeanInfo = mBeanServer.getMBeanInfo(mbean);
				// logger.info("Printing info for mBeanInfo : " + mBeanInfo.toString());
				MBeanAttributeInfo[] attributes = mBeanInfo.getAttributes();

				for (MBeanAttributeInfo attribute : attributes) {
					String attributeType = attribute.getType();

					if ("javax.management.openmbean.CompositeData".equals(attributeType)) {
						CompositeData compositeData = (CompositeData) mBeanServer.getAttribute(mbean,
								attribute.getName());
						if (compositeData != null) {
							CompositeType compositeType = compositeData.getCompositeType();
							Set<String> keys = compositeType.keySet();
							for (String key : keys) {
								// logger.info("Attribute: " + attribute.getName() + " ( type: " + attributeType
								// + " )" + ", key: " + key);
								result.append("\"" + mbean.getCanonicalName() + "\",\"" + attribute.getName() + "\",\""
										+ attributeType + "\",\"" + key + "\"" + System.lineSeparator());
							}
						}
					} else {

						// logger.info("Attribute: " + attribute.getName() + " ( type: " + attributeType
						// + " )");
						result.append("\"" + mbean.getCanonicalName() + "\",\"" + attribute.getName() + "\",\""
								+ attributeType + "\",\"n/a\"" + System.lineSeparator());
					}

				}

			}

			System.out.println(result.toString());

		} catch (IntrospectionException | InstanceNotFoundException | ReflectionException | AttributeNotFoundException
				| MBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
