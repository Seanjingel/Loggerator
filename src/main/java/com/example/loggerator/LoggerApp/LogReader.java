package com.example.loggerator.LoggerApp;

import java.util.List;

public class LogReader implements Runnable {
    private List<LogMessage> logMessages;
    private Object lock;

    public LogReader(List<LogMessage> logMessages, Object lock) {
        this.logMessages = logMessages;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    lock.wait(); // Wait until notified about a new log message
                } catch (InterruptedException e) {
                    // Handle interruption if needed
                    Thread.currentThread().interrupt();
                    return;
                }

                // Process new log messages
                for (LogMessage logMessage : logMessages) {
                    // Perform desired operations with the log message
                    System.out.println(logMessage.getMessage());
                }

                // Clear processed log messages
                logMessages.clear();
            }
        }
    }
}

