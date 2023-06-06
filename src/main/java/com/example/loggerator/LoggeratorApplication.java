package com.example.loggerator;

import com.example.loggerator.LoggerApp.LogMessage;
import com.example.loggerator.LoggerApp.LogReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LoggeratorApplication {

	public static void main(String[] args) {

		SpringApplication.run(LoggeratorApplication.class, args);
		System.out.println("Application Started!");

		// Start log reader thread
		List<LogMessage> logMessages = new ArrayList<>();
		Object lock = new Object();
		LogReader logReader = new LogReader(logMessages, lock);
		Thread logReaderThread = new Thread(logReader);
		logReaderThread.start();
	}

}
