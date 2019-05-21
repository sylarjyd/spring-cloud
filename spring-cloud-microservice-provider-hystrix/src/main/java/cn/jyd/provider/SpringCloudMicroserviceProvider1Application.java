package cn.jyd.provider;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableCircuitBreaker 
public class SpringCloudMicroserviceProvider1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMicroserviceProvider1Application.class, args);
	}

	@Autowired  
	private Environment env;
	
	@GetMapping(value="/user/get/{name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@HystrixCommand(fallbackMethod="getExceptionUser")
	public String getUser(@PathVariable("name") String name) {
		if("123".equals(name)) {
			throw new RuntimeException("123不存在!");
		}
		String port = env.getProperty("server.port");
		return port+"======"+UUID.randomUUID().toString()+"======="+name;
	}
	
	
	public String getExceptionUser(@PathVariable("name") String name) {
		return "123不存在,返回一个异常!";		
	}
	
	@Bean
	public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
		HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean<HystrixMetricsStreamServlet> servletRegistrationBean = new ServletRegistrationBean<>(hystrixMetricsStreamServlet);
		servletRegistrationBean.setLoadOnStartup(1);
		servletRegistrationBean.addUrlMappings("/hystrix.stream");
		servletRegistrationBean.setName("HystrixMetricsStreamServlet");
		return servletRegistrationBean;
	}
	
	
	
	
	
}

