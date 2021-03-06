package be.ugent.systemdesign.university.api_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	private String getEnvOrLocalhost(String envName) {
		String env = System.getenv(envName);
		if(env == null) {
			return "localhost";
		}
		return env;
    	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {        
	return builder.routes()
                .route(r -> r.path("/api/curriculum/**").uri(String.format("http://%s:2223/api/curriculum/", getEnvOrLocalhost("CURRICULUM_HOST"))))
                .route(r -> r.path("/api/faculty/**").uri(String.format("http://%s:2224/api/faculty/", getEnvOrLocalhost("FACULTY_HOST"))))
                .route(r -> r.path("/api/facultydb/**").uri(String.format("http://%s:2224/api/facultydb/", getEnvOrLocalhost("FACULTY_HOST"))))
                .route(r -> r.path("/api/learningplatform/**").uri(String.format("http://%s:2229/api/learningplatform/", getEnvOrLocalhost("LEARNINGPLATFORM_HOST"))))
                .route(r -> r.path("/api/registration/**").uri(String.format("http://%s:2227/api/registration/", getEnvOrLocalhost("REGISTRATION_HOST"))))
                .route(r -> r.path("/api/evaluation/**").uri(String.format("http://%s:2225/api/evaluation/", getEnvOrLocalhost("EVALUATION_HOST"))))
                .route(r -> r.path("/api/account/**").uri(String.format("http://%s:2226/api/account/", getEnvOrLocalhost("ACCOUNT_HOST"))))
                .route(r -> r.path("/api/invoice/**").uri(String.format("http://%s:2228/api/invoice/", getEnvOrLocalhost("INVOICE_HOST"))))
                .build();
        
    	}
}
