package fr.kainovaii.blogspring.Component;

import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdGenerator {

    private final long workerId = 1L;       // Set your worker ID (0–31)
    private final long datacenterId = 1L;   // Set your datacenter ID (0–31)
    private final long twepoch = 1288834974657L;

    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & 4095;
            if (sequence == 0) {
                while ((timestamp = System.currentTimeMillis()) <= lastTimestamp) {}
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << 22)
                | (datacenterId << 17)
                | (workerId << 12)
                | sequence;
    }
}
