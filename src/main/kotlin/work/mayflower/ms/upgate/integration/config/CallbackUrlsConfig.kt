package work.mayflower.ms.upgate.integration.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "callback-urls")
data class CallbackUrlsConfig (var success: String, var failure: String)