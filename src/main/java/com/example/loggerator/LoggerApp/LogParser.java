package com.example.loggerator.LoggerApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogParser {


    public static List<String> parse(String input) {
        List<String> logEntries = new ArrayList<>();

        // Split the input string into individual log messages
        String[] logLines = input.split("\n");

        for (String logLine : logLines) {
//            LogEntry logEntry = parseLogLine(logLine);
            if (logLine != null) {
                logEntries.add(logLine);
            }
        }

        return logEntries;
    }

    private static LogEntry parseLogLine(String logLine) {
        // Split the log line by spaces
        String[] logParts = logLine.split(" ");

        // Check if the log line has the expected number of parts
        if (logParts.length < 10) {
            // Invalid log line format
            return null;
        }

        // Extract the IP address, user, timestamp, method, resource, HTTP version, status code, and response size from the log line
        String ipAddress = logParts[0];
        String user = logParts[2];
        String timestampString = logParts[3]+" "+logParts[4]+" "+logParts[5]; // Removing the '[' and ']' characters
        String method = logParts[6]; // Removing the leading '"' character
        String resource = logParts[7];
        String httpVersion = logParts[8]; // Removing the trailing '"' character
        int statusCode = Integer.parseInt(logParts[9]);
        String size = logParts[10];
        int responseSize = Integer.parseInt(size.replace("\r",""));
        timestampString =  timestampString.substring(1, timestampString.length() - 1);

        Date timestamp = parseTimestamp(timestampString);

        // Create and return a new LogEntry instance
        LogEntry logEntry = new LogEntry();
        logEntry.setIpAddress(ipAddress);
        logEntry.setUser(user);
        logEntry.setTimestamp(timestamp);
        logEntry.setMethod(method);
        logEntry.setResource(resource);
        logEntry.setHttpVersion(httpVersion);
        logEntry.setStatusCode(statusCode);
        logEntry.setResponseSize(responseSize);
        return logEntry;
    }

    private static Date parseTimestamp(String timestampString) {
            String TIMESTAMP_FORMAT = "dd/MMM/yyyy HH:mm:ss Z";


            SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
            try {
                return dateFormat.parse(timestampString);
            } catch (ParseException e) {
                // Handle parsing exception if needed
                e.printStackTrace();
            }
            return null;

    }
}

