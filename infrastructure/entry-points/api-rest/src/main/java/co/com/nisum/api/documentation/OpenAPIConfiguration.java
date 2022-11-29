package co.com.nisum.api.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Users registration and authentication API",
                description = "Nisum technical test. API for registration and authentication of users " +
                        "with endpoints to register, login, delete and get users.",
                contact = @Contact(
                        name = "Anderson Vega",
                        email = "anderson.vegacastillo@gmail.com"
                ),
                license = @License(
                        name = "Nisum Licence",
                        url = "www.nisum.com")),
        servers = @Server(url = "http://localhost:8080"
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER)
public class OpenAPIConfiguration {
}
