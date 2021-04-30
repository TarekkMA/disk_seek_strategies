package com.seek;

public class DiskConfig {
    public final int headPosition;
    public final int timePerTrack;
    public final int minTrack;
    public final int maxTrack;

    public DiskConfig(int headPosition, int timePerTrack, int minTrack, int maxTrack) {
        this.headPosition = headPosition;
        this.timePerTrack = timePerTrack;
        this.minTrack = minTrack;
        this.maxTrack = maxTrack;
    }
}
