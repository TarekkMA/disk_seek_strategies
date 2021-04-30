package com.seek;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static final int HEAD_POSITION = 15;
    public static final int TIME_PER_TRACK = 1;

    public static void main(String[] args) {
        List<Integer> requestSequence = Arrays.asList(15, 4, 40, 11, 35, 7, 14);

        List<SeekStrategy> strategies = Arrays.asList(
                new FCFS(),
                new SSTF()
        );

        for (SeekStrategy strategy : strategies) {
            List<Integer> responseSequence = strategy.order(requestSequence, HEAD_POSITION);
            int seekTime = getTime(responseSequence, HEAD_POSITION, TIME_PER_TRACK);
            float avgTime = (float) seekTime / (responseSequence.size() - 1);
            System.out.println("*** " + strategy.getClass().getSimpleName() + " Strategy ***");
            System.out.println("Sequence    : " + responseSequence.toString());
            System.out.println("Total Time  : " + seekTime + "ms");
            System.out.println("Average Time: " + avgTime + "ms");
        }
    }

    static int getTime(List<Integer> sequence, int currentHeadPos, int timePerTrack) {
        int time = 0;
        for (Integer track : sequence) {
            time += Math.abs(currentHeadPos - track) * timePerTrack;
            currentHeadPos = track;
        }
        return time;
    }
}
