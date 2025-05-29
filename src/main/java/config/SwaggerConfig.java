//package config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        Server server = new Server();
//        server.setUrl("https://library-springboot-api-production.up.railway.app");
//        server.setDescription("Production Server");
//
//        return new OpenAPI().servers(List.of(server));
//    }
//
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("default")
//                .pathsToMatch("/**")
//                .build();
//    }
//}
//
