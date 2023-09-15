package com.example.log4j2demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Log4j2DemoApplication implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(Log4j2DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) {
		ThreadContext.put("Debugging", "Data");
		ThreadContext.push("NDC Data");
		logger.debug("Debugging log");
		ThreadContext.clearAll();

		ThreadContext.put("Info", "Data");
		ThreadContext.push("Info log NDC Data");
		logger.info("Info log");
		ThreadContext.clearAll();

		ThreadContext.put("Warn", "Data");
		ThreadContext.push("Warning NDC Data");
		logger.warn("Hey, This is a warning!");
		ThreadContext.clearAll();

		ThreadContext.push("This will be appended to both error and fatal logs");
		logger.error("Oops! We have an Error. OK");
		logger.fatal("Damn! Fatal error. Please fix me.");
		ThreadContext.clearAll();
	}
}
