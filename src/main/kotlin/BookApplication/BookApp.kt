package BookApplication

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication(scanBasePackages = ["BookApplication"])
@EnableJpaRepositories(basePackages = ["BookApplication"])
@EntityScan(basePackages = ["BookApplication"])
@EnableSwagger2 //needed to enable Swagger
class BookApp {

    /*
        Bean used to configure Swagger documentation
     */
    @Bean
    fun swaggerApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("API for REST Books")
                .description("Get info on your favorite book")
                .version("1.0")
                .build()
    }
}

/*
    If you run this directly, you can then check the Swagger documentation at:

    http://localhost:8080/newsrest/api/swagger-ui.html

 */
fun main(args: Array<String>) {
    SpringApplication.run(BookApp::class.java, *args)
}