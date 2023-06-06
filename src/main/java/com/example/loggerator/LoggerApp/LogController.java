package com.example.loggerator.LoggerApp;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@RestController
public class LogController {

    private List<String> logMessages = new ArrayList<>();
    private Object lock = new Object();

    @GetMapping("/logs")
    public List<String> searchLogs(
            @RequestParam(value = "code", required = false) Integer code,
            @RequestParam(value = "method", required = false) String method,
            @RequestParam(value = "user", required = false) String user) {

        List<String> filteredLogs = new ArrayList<>();

        for (String log : logMessages) {
            String[] logParts = log.split(" ");
            if ((code == null || Integer.parseInt(logParts[9]) == code)
                    && ( method == null || logParts[6].substring(1).equalsIgnoreCase(method))
                    && ( user == null || logParts[2].equalsIgnoreCase(user))) {
                filteredLogs.add(log);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss Z");

        filteredLogs.sort(Comparator.comparing(s -> LocalDateTime.parse(getDateTime((String) s), formatter)).reversed());

        return filteredLogs;
    }

    private static String getDateTime(String logString) {
        int startIndex = logString.indexOf("[") + 1;
        int endIndex = logString.indexOf("]");
        return logString.substring(startIndex, endIndex);
    }



    @PostMapping("/log")
    public void postLogMessage(@RequestBody String logMessage) throws ParseException {
        synchronized (lock) {
            logMessages = LogParser.parse(logMessage);
            lock.notifyAll();
        }
    }


}
