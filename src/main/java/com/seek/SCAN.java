package com.seek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SCAN implements SeekStrategy {
    @Override
    public List<Integer> order(List<Integer> sequence, DiskConfig config) {
        sequence = new ArrayList<>(sequence);
        final List<Integer> res = new ArrayList<>();
        final boolean up = sequence.get(0) >= config.headPosition;
        if (sequence.get(0) != config.headPosition) {
            sequence.add(0, config.headPosition);
        }
        Collections.sort(sequence);
        if (up) {
            goUp(config, sequence, res, true);
            if (res.get(res.size() -1) != config.maxTrack) {
                res.add(config.maxTrack);
            }
            goDown(config, sequence, res, false);
        } else {
            goDown(config, sequence, res, true);
            if (res.get(res.size() -1) != config.minTrack) {
                res.add(config.minTrack);
            }
            goUp(config, sequence, res, false);
        }
        return res;
    }

    private void goUp(DiskConfig config, List<Integer> input, List<Integer> res, boolean includeHead) {
        int startTrack = config.headPosition;
        if (!includeHead) {
            startTrack++;
        }
        for (int track = startTrack; track <= config.maxTrack; track++) {
            if (input.contains(track)) {
                res.add(track);
            }
        }
    }


    private void goDown(DiskConfig config, List<Integer> input, List<Integer> res, boolean includeHead) {
        int startTrack = config.headPosition;
        if (!includeHead) {
            startTrack--;
        }
        for (int track = startTrack; track >= config.minTrack; track--) {
            if (input.contains(track)) {
                res.add(track);
            }
        }
    }
}
