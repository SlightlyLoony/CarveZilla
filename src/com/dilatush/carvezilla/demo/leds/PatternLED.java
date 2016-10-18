package com.dilatush.carvezilla.demo.leds;

import com.pi4j.io.gpio.GpioController;

/**
 * Implements an LED whose brightness is the product of the set brightness and an associated brightness pattern over time.
 *
 * @author Tom Dilatush  tom@dilatush.com
 */
public class PatternLED extends VariableLED implements LED {

    private BrightnessPattern pattern;
    private int brightness;


    public PatternLED( final GpioController _gpio, final int _pinNumber, final BrightnessPattern _pattern ) {
        super( _gpio, _pinNumber );
        pattern = _pattern;
        brightness = 0;
    }


    @Override
    public void brightness( final int _percent ) {
        brightness = _percent;
    }


    @Override
    protected int transition( final int _brightness ) {
        return Math.max( 0, Math.min( 1, (int) Math.round( pattern.brightness() * brightness / 100.0 ) ) );
    }
}
