package com.operatingSystem.firefly;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by surag on 11/14/2016.
 */
public class Operation {

    private static final AtomicInteger count = new AtomicInteger(0);
    /**
     * Each operation Oij (i=1,2,…,n;
     * j=1,2,…,ni) of job (Ji) can be processed by one machine mij
     * in the set of eligible machines Mij. Pijk denotes the processing
     * time of operation Oij on machine k∈Mij.
     */

    // DS to store the processing time of each machine for a particular job
    private int operationId;
    private Job job;
    private HashMap<Machine, Integer> machineProcessTime;
    private Machine machineThatExecutedTheOperation;
    private boolean processed;
    private int startTime;

    public Operation(HashMap<Machine, Integer> machineProcessTime, Job job) {
        this.machineProcessTime = machineProcessTime;
        this.job = job;
        this.operationId = count.incrementAndGet();
        this.processed = false;
    }

    public Operation(HashMap<Machine, Integer> machineProcessTime) {
        this.machineProcessTime = machineProcessTime;
        this.operationId = count.incrementAndGet();
    }

    public HashMap<Machine, Integer> getMachineProcessTime() {
        return machineProcessTime;
    }

    public void setMachineProcessTime(HashMap<Machine, Integer> machineProcessTime) {
        this.machineProcessTime = machineProcessTime;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Machine getMachineThatExecutedTheOperation() {
        return machineThatExecutedTheOperation;
    }

    public void setMachineThatExecutedTheOperation(Machine machineThatExecutedTheOperation) {
        this.machineThatExecutedTheOperation = machineThatExecutedTheOperation;
    }

    public int getOperationId() {
        return operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;

        Operation operation = (Operation) o;

        if (getOperationId() != operation.getOperationId()) return false;
        if (getMachineProcessTime() != null ? !getMachineProcessTime().equals(operation.getMachineProcessTime()) : operation.getMachineProcessTime() != null)
            return false;
        return getJob().equals(operation.getJob());

    }

//    @Override
//    public int hashCode() {
//        int result = getMachineProcessTime() != null ? getMachineProcessTime().hashCode() : 0;
//        result = 31 * result + getJob().hashCode();
//        result = 31 * result + getOperationId();
//        return result;
//    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }


    //might have to change this in future
//    @Override
//    public String toString() {
//        return "Operation{" +
//                "operationId=" + operationId +
//                ", job=" + job.getJobId() == null ? "null" : job.getJobId() +
//                ", machineProcessTime=" + machineProcessTime == null ? "null"  : machineProcessTime.toString() +
//                ", machineThatExecutedTheOperation=" + (machineThatExecutedTheOperation == null ? "null" : machineThatExecutedTheOperation) +
//                '}';
//    }
}
