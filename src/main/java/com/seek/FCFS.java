package com.seek;

import java.util.ArrayList;
import java.util.List;

public class FCFS implements SeekStrategy {
    @Override
    public List<Integer> order(List<Integer> sequence, int headPosition) {
        List<Integer> res = new ArrayList<>(sequence);
        if (res.get(0) != headPosition) {
            res.add(0, headPosition);
        }
        return res;
    }
}
