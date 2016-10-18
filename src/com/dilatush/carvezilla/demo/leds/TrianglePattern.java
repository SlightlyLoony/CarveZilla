package com.dilatush.carvezilla.demo.leds;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public class TrianglePattern implements BrightnessPattern {

    private int periodMs;
    private long startTimeMs;

    public TrianglePattern(final int _periodMs ) {
        periodMs = _periodMs;
        startTimeMs = System.currentTimeMillis();
    }


    @Override
    public double brightness() {
        double cp = 1.0 * ((System.currentTimeMillis() - startTimeMs) % periodMs) / (periodMs - 1);
        return 2.0 * ((cp < 0.5) ? cp : 1.0 - cp);
    }
}
