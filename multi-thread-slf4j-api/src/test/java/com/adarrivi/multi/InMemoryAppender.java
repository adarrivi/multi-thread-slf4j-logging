package com.adarrivi.multi;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Log4j appender that stores the logging information in an accessible list.
 * Useful to analyze the contents of the logs
 */
public class InMemoryAppender extends AppenderSkeleton {

    private List<LoggingEvent> buffer = new ArrayList<>();

    public InMemoryAppender() {
    }

    protected void append(LoggingEvent loggingEvent) {
        if (buffer != null) {
            buffer.add(loggingEvent);
        }
    }

    public void close() {
        buffer.clear();
    }

    public boolean requiresLayout() {
        return true;
    }

    public void activateOptions() {
        this.buffer = new ArrayList<>();
    }

    public List<String> getLogLines() {
        Layout layout = getLayout();
        return buffer.stream().map(loggingEvent -> layout.format(loggingEvent)).collect(Collectors.toList());
    }

}