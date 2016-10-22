package com.dilatush.carvezilla.demo.ledrunners;

import com.dilatush.carvezilla.demo.leds.LED;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public class Pulse implements LEDRunner {

    private final int onMs;
    private final int offMs;
    private final int brightness;


    public Pulse( final int _periodMs, final float _dutyCycle, final int _brightness ) {

        if( _periodMs < 20 || _dutyCycle < 0.01 || _brightness < 1 || _brightness > 100 )
            throw new IllegalArgumentException( "Period or duty cycle too small, or brightness out of range" );

        onMs = Math.round( _dutyCycle * _periodMs );
        offMs = _periodMs - onMs;
        brightness = _brightness;
    }


    public Pulse( final int _periodMs, final float _dutyCycle ) {
        this( _periodMs, _dutyCycle, 100 );
    }


    public Pulse( final int _periodMs ) {
        this( _periodMs, 0.5f, 100 );
    }


    @Override
    public void run( final LED _led ) throws InterruptedException {

        _led.brightness( brightness );
        Thread.sleep( onMs );
        _led.brightness( 0 );
        Thread.sleep( offMs );
    }
}
