package com.seek;

import java.util.List;

public interface SeekStrategy {
    List<Integer> order(List<Integer> sequence, int headPosition);
}
