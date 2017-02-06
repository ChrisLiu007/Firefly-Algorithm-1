package com.operatingSystem.firefly;

import java.util.ArrayList;

/**
 * Created by surag on 11/14/2016.
 * <p>
 * FJSP constrains
 * 1. The operation sequence for each job is prescribed and the
 * appointed process order must be followed.
 * 2. Each machine can process only one operation at a time.
 * 3. Each operation must be processed on an appointed
 * machine.
 */
public class FJSP {

    private enum Algo {FIREFLY, RANDOM, FCFS, SRTF}

    private ArrayList<Job> jobs;
    private ArrayList<Machine> machines;

    /*
     * Makespan (Cm) of the jobs, i.e. the completion time of all
     * jobs
     * 2. Maximal machine workload (Wm), i.e. the maximum
     * working time spent on any machine
     * 3. Total workload of the machines (Wt) which represent the
     * total working time over all machines.
     */
    private int makespan;
    private int maximalMachineworkLoad;
    private int totalMachineWorkload;

    public FJSP(ArrayList<Job> jobs, ArrayList<Machine> machines) {
        this.jobs = jobs;
        this.machines = machines;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public void setMachines(ArrayList<Machine> machines) {
        this.machines = machines;
    }

    public int getMakespan() {
        return makespan;
    }

    public void setMakespan(int makespan) {
        this.makespan = makespan;
    }

    public int getMaximalMachineworkLoad() {
        return maximalMachineworkLoad;
    }

    public void setMaximalMachineworkLoad(int maximalMachineworkLoad) {
        this.maximalMachineworkLoad = maximalMachineworkLoad;
    }

    public int getTotalMachineWorkload() {
        return totalMachineWorkload;
    }

    public void setTotalMachineWorkload(int totalMachineWorkload) {
        this.totalMachineWorkload = totalMachineWorkload;
    }

    public int getNumberOfoperations() {
        int numOfOperations = 0;
        for (Job j : jobs) {
            numOfOperations += j.getOperations().size();
        }
        return numOfOperations;
    }

//    @Override
//    public String toString() {
//        return "FJSP{" +
//                "jobs=" + jobs.toString() +
//                ", machines=" + machines.toString() +
//                ", makespan=" + makespan +
//                ", maximalMachineworkLoad=" + maximalMachineworkLoad +
//                ", totalMachineWorkload=" + totalMachineWorkload +
//                '}';
//    }
}
