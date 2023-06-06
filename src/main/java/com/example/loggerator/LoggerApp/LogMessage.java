package com.example.loggerator.LoggerApp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
@Getter
public class LogMessage {
    private String message;
}

