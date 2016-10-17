package com.dilatush.carvezilla.demo.leds;

import com.pi4j.io.gpio.GpioController;

/**
 * Implements an LED with variable brightness and a linear transition from one brightness to another.
 *
 * @author Tom Dilatush  tom@dilatush.com
 */
public class LinearTransitionLED extends VariableLED implements LED {

    private final int transitionTicks;

    private int fromBrightness;
    private int toBrightness;
    private int transitionTick;



    public LinearTransitionLED( final GpioController _gpio, final int _pinNumber, final int _transitionMs ) {
        super( _gpio, _pinNumber );
        transitionTicks = _transitionMs / TICK_MS;
        transitionTick = -1;  // show that we're NOT in a transition...
    }


    @Override
    public void brightness( final int _percent ) {
        fromBrightness = toBrightness;
        toBrightness = Math.min( 100, Math.max( 0, _percent ) );
        transitionTick = 0;  // show that we've just started a transition...
    }


    @Override
    protected int transition( final int _brightness ) {

        // if we're done, finish cleanly...
        if( transitionTicks == transitionTick ) {
            transitionTick = -1;
            return toBrightness;
        }

        // otherwise, compute our intermediate brightness...
        transitionTick++;
        return fromBrightness + (toBrightness - fromBrightness) * transitionTick / transitionTicks;
    }
}
