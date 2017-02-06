package com.operatingSystem.firefly;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by surag on 11/14/2016.
 */
public class Machine {

    private static final AtomicInteger count = new AtomicInteger(0);
    HashSet<Operation> operations = new HashSet<Operation>();
    private int machineId;
    private int currentTime;

    public HashSet<Operation> getOperations() {
        return operations;
    }

    public void setOperations(HashSet<Operation> operations) {
        this.operations = operations;
    }

    public int getMachineId() {
        return machineId;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    private void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public boolean updateCurrentTime(int time) {
        int tempTime = time + this.currentTime;
        setCurrentTime((tempTime));
        return true;
    }

    public Machine() {
        this.machineId = count.incrementAndGet();
//        this.operations = new HashSet<>();
        this.currentTime = 0;
    }

    public boolean addOperation(Operation o, int processingTime) {
        this.operations.add(o);
        updateCurrentTime(processingTime);
        return true;
    }
}
