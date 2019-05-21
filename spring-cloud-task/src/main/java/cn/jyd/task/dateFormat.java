package cn.jyd.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dateFormat {
	
	public static String format() {
		String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return localDateTime;
	}
	
	public static void main(String[] args) {
		System.out.println(dateFormat.format());
	}

}
