package cloudgateway.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Configuration
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("r1", r -> r.path("/students")
                        .uri("lb://exp-ms1"))
                .route("r2", r -> r.path("/students/getID")
                        .uri("lb://exp-ms1"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
