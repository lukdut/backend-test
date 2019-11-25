package com.assignment.backend.storage;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongSupplier;

public class IncrementalIdGenerator implements LongSupplier {

    private final AtomicLong counter = new AtomicLong();

    @Override
    public long getAsLong() {
        return counter.getAndIncrement();
    }
}
