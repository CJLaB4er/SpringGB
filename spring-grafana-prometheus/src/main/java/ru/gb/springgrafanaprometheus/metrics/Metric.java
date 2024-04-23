package metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Metric {

    private final AtomicInteger myNumber;
    private final Counter myCounter;

    public Metric(MeterRegistry meterRegistry) {
        myNumber = meterRegistry.gauge("customNumber", new AtomicInteger());
        myCounter = meterRegistry.counter("customCounter");
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void sheduling() {
        int random = (int) (Math.random() * 100);
        myNumber.set(random);
        myCounter.increment();
    }
}
