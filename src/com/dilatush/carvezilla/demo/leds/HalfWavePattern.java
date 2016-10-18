package com.dilatush.carvezilla.demo.leds;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public class HalfWavePattern implements BrightnessPattern {

    private int periodMs;
    private long startTimeMs;

    public HalfWavePattern( final int _periodMs ) {
        periodMs = _periodMs;
        startTimeMs = System.currentTimeMillis();
    }


    @Override
    public double brightness() {
        double radians = 2.0 * Math.PI * (System.currentTimeMillis() - startTimeMs) % periodMs / (periodMs - 1);
        return Math.max( 0, Math.sin( radians ) );
    }
}
