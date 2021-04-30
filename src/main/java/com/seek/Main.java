package com.seek;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static final int HEAD_POSITION = 15;
    public static final int TIME_PER_TRACK = 1;
    public static final int MIN_TRACK = 0;
    public static final int MAX_TRACK = 50;

    public static void main(String[] args) {
        final DiskConfig config = new DiskConfig(
                HEAD_POSITION, TIME_PER_TRACK,
                MIN_TRACK, MAX_TRACK
        );
        List<Integer> requestSequence = Arrays.asList(15, 4, 40, 11, 35, 7, 14);
        //requestSequence = Arrays.asList(82, 170, 43, 140, 24, 16, 190);

        List<SeekStrategy> strategies = Arrays.asList(
                new FCFS(),
                new SSTF(),
                new SCAN()
        );

        for (SeekStrategy strategy : strategies) {
            List<Integer> responseSequence = strategy.order(requestSequence, config);
            int seekTime = getTime(responseSequence, config);
            float avgTime = (float) seekTime / (responseSequence.size() - 1);
            System.out.println("*** " + strategy.getClass().getSimpleName() + " Strategy ***");
            System.out.println("Sequence    : " + responseSequence.toString());
            System.out.println("Total Time  : " + seekTime + "ms");
            System.out.println("Average Time: " + avgTime + "ms");
        }
    }

    static int getTime(List<Integer> sequence, DiskConfig config) {
        int time = 0;
        int currentHeadPos = config.headPosition;
        for (Integer track : sequence) {
            time += Math.abs(currentHeadPos - track) * config.timePerTrack;
            currentHeadPos = track;
        }
        return time;
    }
}
