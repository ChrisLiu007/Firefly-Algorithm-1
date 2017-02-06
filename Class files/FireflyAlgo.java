package com.operatingSystem.firefly;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by surag on 11/14/2016.
 */
public class FireflyAlgo {

    private static int NUMBER_OF_FIREFLY = 10;
    private static int NUMBER_OF_GENERATIONS = 100;
    private static FJSP fjsp;
    private static ArrayList<Firefly> population;
    public double beta0; // attractiveness when distance is 0
    public double gamma; // light-absorption coefficient
    public double alpha; // random movement coefficient
    public static int MAV = 0; // Machine Assignment Vector
    public static int OSV = 1; // Operation Scheduling Vector

    //    private double W1;
//    private double W2;
//    private double W3;
//    private double Fc;
//    private double f1;
//    private double f2;
//    private double f3;
//    private double[][] r;
//
//    private double calculateDistance();
//    private


    public static class Firefly {

        public static ArrayList<Job> jobs = null;
        public static ArrayList<Machine> machines = null;
        public int[] machineAssignmentVector;
        public int[] operationSchedulingVector;
        private double objectiveFuntionScore;

        public double getObjectiveFuntionScore() {
            computeObjectiveFunctionScore();
            return objectiveFuntionScore;
        }

        public void computeObjectiveFunctionScore() {

        }
    }


    public static GanttChart schedule(FJSP fjsp) {
        Firefly intialSequence = chooseIntialSequence(fjsp);
        ArrayList<Machine> machines = fjsp.getMachines();
        ArrayList<Job> jobs = fjsp.getJobs();
        ArrayList<Firefly> initialPopulation = getInitialPopulation(intialSequence);
        population = initialPopulation;
        for (int i = 1; i < NUMBER_OF_GENERATIONS; i++) {
            ArrayList<Double> objectiveFuntionScore = getObjectiveFunctionScore(population);
//            don't know exactly whether to rank the fireflies or not which means rearrangin the position of firefly
            updatePopulation(population);

        }

        GanttChart g = GanttChart.createGanttChart(jobs,machines);
        return null;
    }

    private static void updatePopulation(ArrayList<Firefly> population) {
        for (int i = 0; i < population.size(); i++) {
            for (int j = 0; j < population.size(); j++) {
                if (population.get(j).getObjectiveFuntionScore() > population.get(i).getObjectiveFuntionScore()) {
                    // move i towards j
                    population.set(i, move(population.get(i), population.get(j)));
                    population.get(i).computeObjectiveFunctionScore();
                }
            }
        }
    }

    private static Firefly move(Firefly i, Firefly j) {
        ArrayList<ArrayList<Integer>> dMAV = calculateDistance(i, j, MAV);
        ArrayList<ArrayList<Integer>> dOSV = calculateDistance(i, j, OSV);
        double betaRMAV = calculatebeta(dMAV.size());
        double betaROSV = calculatebeta(dOSV.size());
        ArrayList<Double> randMAV = calculateRand(dMAV.size());
        ArrayList<Double> randOSV = calculateRand(dOSV.size());

//        movement beta step
        for (int k = 0; k < randMAV.size(); k++) {
            if (randMAV.get(k) > betaRMAV) {
                ArrayList<Integer> temp = dMAV.get(k);
                i.machineAssignmentVector[temp.get(0)] = j.machineAssignmentVector[temp.get(0)];
            }
        }

        for (int k = 0; k < randOSV.size(); k++) {
            if (randOSV.get(k) > betaROSV) {
                ArrayList<Integer> temp = dMAV.get(k);
                // swap contents
            }
        }

        // alpha step here we rearrange the vectors such that it is valid one

        for (int k = 0; k < randMAV.size(); k++) {
            if (randMAV.get(k) > betaRMAV) {
                // lacal search to replace the value at index
            }
        }

        for (int k = 0; k < randOSV.size(); k++) {
            if (randOSV.get(k) > betaROSV) {
                // lacal search to replace the value at index
            }
        }

        return i;
    }

    private static ArrayList<Double> calculateRand(int size) {
        ArrayList<Double> rand = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            rand.add(Math.random());
        }
        return rand;
    }

    private static double calculatebeta(int r) {
        double result = Math.pow(r, 2);
        result = result;
        return 0;
    }

    // not completed
    private static ArrayList<ArrayList<Integer>> calculateDistance(Firefly i, Firefly j, int v) {
        // we shall find the distance by checking the number of position that are different
        ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();
        if (v == MAV) {
            for (int k = 0; k < i.machineAssignmentVector.length; k++) {
                if (i.machineAssignmentVector[k] != j.machineAssignmentVector[k]) {
                    ArrayList<Integer> entry = new ArrayList<>();
                    entry.add(k);
                    entry.add(i.machineAssignmentVector[k]);
                    entry.add(j.machineAssignmentVector[k]);
                    pairs.add(entry);
                }
            }
        } else {
            pairs.add(calculateSwaps(i.operationSchedulingVector, j.operationSchedulingVector));
        }
        return pairs;
    }

    private static ArrayList<Integer> calculateSwaps(int[] i, int[] j) {
        // for now retrun a constant
        ArrayList<Integer> pair = new ArrayList<>();
        pair.add(3);
        pair.add(2);
        pair.add(3);
        pair.add(4);
        pair.add(5);
        pair.add(5);
        pair.add(6);
        return pair;
    }

    private static ArrayList<Double> getObjectiveFunctionScore(ArrayList<Firefly> population) {
        return null;
    }

    private static ArrayList<Firefly> getInitialPopulation(Firefly initialSequence) {

        return null;
    }

    private static Firefly chooseIntialSequence(FJSP fjsp) {
        SRJFAlgo.schedule(fjsp);
        ArrayList<Job> jobs = SRJFAlgo.jobs;
        ArrayList<Machine> machines = SRJFAlgo.machines;
        return null;
    }
    // will be the the implementation of algorithm
}