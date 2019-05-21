package cn.jyd.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@RestController
public class SpringCloudMicroserviceZuul1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMicroserviceZuul1Application.class, args);
	}
	
	
	@RequestMapping(value="/exampleService/eureka")
	public String tiaoZhuan() {
		return null;		
	}

	@RequestMapping(value="/exampleService2/eureka2")
	public String tiaoZhuan2() {
		return "/exampleService2/eureka2";		
	}
}

