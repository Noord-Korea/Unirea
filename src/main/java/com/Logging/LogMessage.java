package com.Logging;

public class LogMessage {

    private String messageText;

    public LogLevel getLogLevel() {
        return logLevel;
    }

    private LogLevel logLevel;

    public String getMessageText() {
        return messageText;
    }

    public LogMessage(String messageText, LogLevel logLevel)
    {
        this.messageText = messageText;
        this.logLevel = logLevel;
    }
}
