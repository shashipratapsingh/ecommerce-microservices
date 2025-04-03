package ApiGatewayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("auth-service", r -> r.path("/auth/**").uri("lb://AUTH-SERVICE"))
//				.route("user-service", r -> r.path("/user/**").uri("lb://USER-SERVICE"))
//				.route("product-service", r -> r.path("/product/**").uri("lb://PRODUCT-SERVICE"))
//				.route("order-service", r -> r.path("/order/**").uri("lb://ORDER-SERVICE"))
//				.route("inventory-service", r -> r.path("/inventory/**").uri("lb://INVENTORY-SERVICE"))
//				.build();
//	}

}
