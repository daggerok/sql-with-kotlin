package daggerok.sqlwithkotlin

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(SpringDatasourceProperties::class)
class SpringDatasourcePropertiesConfig

@ConstructorBinding
@ConfigurationProperties("spring.datasource")
data class SpringDatasourceProperties(
    val url: String = "",
    val user: String = "",
    val password: String = "",
    val driver: String = "",
)
