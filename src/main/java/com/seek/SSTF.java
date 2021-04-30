package com.seek;

import java.util.*;
import java.util.stream.Collectors;

public class SSTF implements SeekStrategy {
    @Override
    public List<Integer> order(List<Integer> sequence, DiskConfig config) {
        int headPosition = config.headPosition;
        sequence = new ArrayList<>(sequence);
        List<Integer> res = new ArrayList<>();
        Set<Integer> accessed = new HashSet<>();
        if (sequence.get(0) != headPosition) {
            sequence.add(0, headPosition);
        }
        accessed.add(sequence.get(0));
        for (int i = 0; i < sequence.size(); i++) {
            res.add(headPosition);
            int nextMinTrack = findNextMinTrack(headPosition, sequence, accessed);
            if (nextMinTrack != -1) {
                accessed.add(nextMinTrack);
                headPosition = nextMinTrack;
            }
        }
        return res;
    }

    private int findNextMinTrack(int currentTrack, List<Integer> sequence, Set<Integer> accessed) {
        List<TrackCost> cost = sequence.stream()
                .map(track -> new TrackCost(track, Math.abs(track - currentTrack)))
                .collect(Collectors.toList());
        TrackCost minTrackCost = cost.stream()
                .filter(c -> !accessed.contains(c.track))
                .min(Comparator.comparingInt(c -> c.cost))
                .orElse(null);
        if (minTrackCost == null) {
            return -1;
        }
        return minTrackCost.track;
    }

    public static class TrackCost {
        public final int track;
        public final int cost;

        private TrackCost(int track, int cost) {
            this.track = track;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "TrackCost{" +
                    "track=" + track +
                    ", cost=" + cost +
                    '}';
        }
    }
}
