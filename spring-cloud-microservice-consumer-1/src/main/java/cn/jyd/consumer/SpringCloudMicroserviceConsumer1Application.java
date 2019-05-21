package cn.jyd.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
@RibbonClient(name="provider-1", configuration=MySelfRule.class)
public class SpringCloudMicroserviceConsumer1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMicroserviceConsumer1Application.class, args);
	}	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private FeignExampleService feignExampleService;
	
	@GetMapping(value="/user/get/{name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getUser(@PathVariable("name") String name) {
		return feignExampleService.getUser(name);		
	}
	
	@GetMapping(value="/user/get2/{name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getUser2(@PathVariable("name") String name) {
		return restTemplate.getForObject("http://provider-1/user/get/"+name,String.class);		
	}
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();		
	}
	
	

}

