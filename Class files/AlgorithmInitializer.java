package com.operatingSystem.firefly;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by aijaz on 11/19/16.
 */
public class AlgorithmInitializer {

    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        try {
            Scanner s = new Scanner(inputFile);
            int numMachines = s.nextInt();
            int numJobs = s.nextInt();
            ArrayList<Integer> numOps = new ArrayList<Integer>();
            for (int i = 0; i < numJobs; i++) {
                numOps.add(s.nextInt());
            }
            ArrayList<Machine> machines = new ArrayList<Machine>();
            for (int i = 0; i < numMachines; i++) {
                Machine m = new Machine();
                machines.add(m);
            }
            System.out.println(numMachines + numJobs + numOps.toString());
            ArrayList<Job> jobs = new ArrayList<>();
            for (int i = 0; i < numJobs; i++) {
                ArrayList<Operation> operations = new ArrayList<Operation>();
                for (int j = 0; j < numOps.get(i); j++) {
                    HashMap<Machine, Integer> machineProcessTime = new HashMap<Machine, Integer>();
                    for (int k = 0; k < numMachines; k++) {
                        int mtime = s.nextInt();
//                        System.out.println(mtime);
                        if(mtime != 0) {
                            machineProcessTime.put(machines.get(k), mtime);
                        }
                    }
//                    System.out.println(machineProcessTime);
                    Operation o = new Operation(machineProcessTime);
                    operations.add(o);
                }
//                System.out.println(operations.size());
                Job j = new Job(operations);
//                System.out.println(j.getJobId());
                jobs.add(j);
            }
            for (Job j : jobs) {
                System.out.println(j.getJobId());
                System.out.println(j.getOperations().size());
                for (Operation o : j.getOperations()
                        ) {
                    System.out.println(o.getOperationId());
                    for (Machine m : o.getMachineProcessTime().keySet()
                            ) {
                        System.out.print(o.getMachineProcessTime().get(m));
                    }
                    System.out.println();
                }
            }
            for (Machine m : machines) {
                System.out.println(m);
            }
            FJSP fjsp = new FJSP(jobs, machines);
//            FireflyAlgo.schedule(fjsp);
            SRJFAlgo.schedule(fjsp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
