package vacca.emptyspringrest.entity;

import java.io.Serializable;

public class AppInfo implements Serializable{

	private static final long serialVersionUID = -8705544117231558711L;

	private String environment;
	private String name;
	private String version;
	private String buildNumber;
	private String deployedTimestamp;


	public AppInfo(String environment, String name, String version, String buildNumber, String deployedTimestamp) {
		super();
		this.environment = environment;
		this.name = name;
		this.version = version;
		this.buildNumber = buildNumber;
		this.deployedTimestamp = deployedTimestamp;
	}
	
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	public String getDeployedTimestamp() {
		return deployedTimestamp;
	}
	public void setDeployedTimestamp(String deployedTimestamp) {
		this.deployedTimestamp = deployedTimestamp;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildNumber == null) ? 0 : buildNumber.hashCode());
		result = prime * result + ((environment == null) ? 0 : environment.hashCode());
		result = prime * result + ((deployedTimestamp == null) ? 0 : deployedTimestamp.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppInfo other = (AppInfo) obj;
		if (buildNumber == null) {
			if (other.buildNumber != null)
				return false;
		} else if (!buildNumber.equals(other.buildNumber))
			return false;
		if (environment == null) {
			if (other.environment != null)
				return false;
		} else if (!environment.equals(other.environment))
			return false;
		if (deployedTimestamp == null) {
			if (other.deployedTimestamp != null)
				return false;
		} else if (!deployedTimestamp.equals(other.deployedTimestamp))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}



}
