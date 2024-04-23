package Metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MyMetrics {
    private final Counter counterIssues;
    private final Counter counterLimitBooks;

    private final MeterRegistry meterRegistry;

    public MyMetrics(MeterRegistry meterRegistry) {
        counterIssues = meterRegistry.counter("countIssues");
        this.counterLimitBooks = meterRegistry.counter("countLimitBooks");
        this.meterRegistry = meterRegistry;
    }

    public void addIssue() {
        counterIssues.increment();
    }

    public void addLimitBook() {
        counterLimitBooks.increment();
    }
}
