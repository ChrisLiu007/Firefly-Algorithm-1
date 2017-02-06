package com.operatingSystem.firefly;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by surag on 11/14/2016.
 */
public class Job {

    private static final AtomicInteger count = new AtomicInteger(0);
    private ArrayList<Operation> operations;
    private int jobId;


    public Job(ArrayList<Operation> operations) {
        this.operations = operations;
        this.jobId = count.incrementAndGet();

    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    public int getJobId() {
        return jobId;
    }


    // by overriding equals function we can compare two jobs in terms of the jobId and operations that it holds
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        if (getJobId() != job.getJobId()) return false;
        return getOperations().equals(job.getOperations());

    }

    @Override
    public int hashCode() {
        int result = getOperations().hashCode();
        result = 31 * result + getJobId();
        return result;
    }

    public boolean isProcessed() {
        for (Operation o : operations) {
            if (!o.isProcessed()) {
                return false;
            }
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "Job{" +
//                "jobId=" + jobId == null ? "null" : jobId +
//                ", operations=" + operations == null ? "null": operations.toString() +
//                '}';
//    }
}
