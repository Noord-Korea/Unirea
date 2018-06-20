package com.logging;

import java.util.List;

public interface ILogger {

    void log(String message, LogLevel logLevel);

    void log(Exception ex);

    LogMessage getLastLog();

    List<LogMessage> getMessages();

}
