package com.operatingSystem.firefly;

import java.util.*;

/**
 * Created by aijaz on 11/28/16.
 */
public class SRJFAlgo {

    private static FJSP fjsp;
    public static ArrayList<Job> jobs = null;
    public static ArrayList<Machine> machines = null;

    public static GanttChart schedule(FJSP fjsp) {
        jobs = fjsp.getJobs();
        machines = fjsp.getMachines();

        jobs = retainShortestOperationsInJob(jobs);
        // after the above step there should be only one machine - process time entry for each operations
        int operationIndex = 0;
        while (!allOperationsProcessed(jobs)) {
            // assign a operation in each job sequentially
            ArrayList<Operation> curOps = new ArrayList<>();
            for (Job j : jobs) {
                if (!j.isProcessed()) {
                    curOps.add(j.getOperations().get(operationIndex));
                }
            }
            // now got all the operations that needs to be scheduled in the current iteration
            // find the job with the shortest processing time and assign the machine that it needs
            int readPhase = curOps.size();
            int loopingIndex = 0;
            while (!curOps.isEmpty()) {
                Operation o = findOperationWithSmallestProcessingTime(curOps);
                scheduleOperation(o,machines);
                curOps.remove(o);
            }
            operationIndex++;
        }
        GanttChart g = GanttChart.createGanttChart(jobs,machines);
        return g;
    }

    private static boolean scheduleOperation(Operation o, ArrayList<Machine> machines) {
        Machine currMachine =  o.getMachineProcessTime().entrySet().iterator().next().getKey();
        int processingTime = o.getMachineProcessTime().entrySet().iterator().next().getValue();
        int startTime = currMachine.getCurrentTime();
        o.setStartTime(startTime);
        currMachine.addOperation(o,processingTime);
        o.setMachineThatExecutedTheOperation(currMachine);
        o.setProcessed(true);
        return true;
    }

    private static Operation findOperationWithSmallestProcessingTime(ArrayList<Operation> ops) {
        Operation smallestProcessingTimeOperation = ops.get(0);
        HashMap<Machine, Integer> curMap = smallestProcessingTimeOperation.getMachineProcessTime();
        Map.Entry<Machine, Integer> smallestTimeOperationEntry = curMap.entrySet().iterator().next();
        for (int i = 1; i < ops.size(); i++) {
            Map.Entry<Machine, Integer> currentTimeOperationEntry = ops.get(i).getMachineProcessTime().entrySet().iterator().next();
            if (smallestTimeOperationEntry.getValue() > currentTimeOperationEntry.getValue()) {
                smallestTimeOperationEntry = currentTimeOperationEntry;
                smallestProcessingTimeOperation = ops.get(i);
            }
        }
        return smallestProcessingTimeOperation;
    }

    private static ArrayList<Job> retainShortestOperationsInJob(ArrayList<Job> jobs) {
        for (int i = 0; i < jobs.size(); i++) {
            ArrayList<Operation> ops = jobs.get(i).getOperations();
            // only retain the shortest time for each operation
            for (int j = 0; j < ops.size(); j++) {
                HashMap<Machine, Integer> map = ops.get(j).getMachineProcessTime();
                // find the machine with minimum processing time
                Machine leastProcessingTimeMachine = null;
                int minimumProcessingTime = Integer.MAX_VALUE;
                for (Machine m : map.keySet()) {
                    if (minimumProcessingTime > map.get(m)) {
                        leastProcessingTimeMachine = m;
                        minimumProcessingTime = map.get(m);
                    }
                }

                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (leastProcessingTimeMachine != pair.getKey()) {
                        it.remove();
                    }
                }
            }
        }
        // do a check whether the details were removed
        return jobs;
    }

    private static boolean allOperationsProcessed(ArrayList<Job> jobs) {
        for (Job j : jobs) {
            if (!j.isProcessed()) {
                return false;
            }
        }
        return true;
    }
}
