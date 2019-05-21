package cn.jyd.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "consumer-1")
public interface FeignExampleService {
	
	@GetMapping(value="/user/get/{name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getUser(@PathVariable("name") String name);
}
