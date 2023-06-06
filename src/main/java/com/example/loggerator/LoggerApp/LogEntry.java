package com.example.loggerator.LoggerApp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogEntry {
    private String ipAddress;
    private String user;
    private Date timestamp;
    private String method;
    private String resource;
    private String httpVersion;
    private int statusCode;
    private int responseSize;
}
