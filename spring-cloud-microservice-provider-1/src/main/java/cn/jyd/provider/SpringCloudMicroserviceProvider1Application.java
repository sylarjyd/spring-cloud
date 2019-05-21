package cn.jyd.provider;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class SpringCloudMicroserviceProvider1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMicroserviceProvider1Application.class, args);
	}

	@Autowired  
	private Environment env;
	
	@GetMapping(value="/user/get/{name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getUser(@PathVariable("name") String name) {
		String port = env.getProperty("server.port");
		return port+"======"+UUID.randomUUID().toString()+"======="+name;
	}
}

