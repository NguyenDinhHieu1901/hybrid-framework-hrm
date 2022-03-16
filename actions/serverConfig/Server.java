package serverConfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:${env}.properties" })
public interface Server extends Config {

	@Key("app.url")
	String applicationUrl();

	@Key("app.username")
	String appUsername();

	@Key("app.password")
	String appPassword();
}
