package com.dilatush.carvezilla.demo;

import com.dilatush.carvezilla.demo.ledrunners.Burst;
import com.dilatush.carvezilla.demo.ledrunners.Pulse;
import com.dilatush.carvezilla.demo.ledrunners.Sequence;
import com.dilatush.carvezilla.demo.leds.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import java.util.Random;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public class RunLED {

    public static void main( String[] args ) throws InterruptedException {

        GpioController gpio = GpioFactory.getInstance();

        LED led = new ExponentialTransitionLED( gpio, 0, 100 );
        Pulse p0Full = new Pulse( 200, 0.25f );
        Pulse p0Half = new Pulse( 200, 0.25f, 50 );
        Burst b0 = new Burst( p0Full, 3 );
        Burst b1 = new Burst( p0Half, 3 );
        Sequence s0 = new Sequence( b0, b1 );
        Burst b2 = new Burst( s0, 20 );
        b2.run( led );
        
        LED led0 = new PatternLED( gpio, 0, new HalfWavePattern( 500 ) );
        LED led1 = new PatternLED( gpio, 1, new TrianglePattern( 500 ) );

        Random random = new Random( System.currentTimeMillis() );
        boolean on = false;

        while( true ) {

            Thread.sleep( 1000 );

            led0.brightness( 100 );
            led1.brightness( 100 );
            on = !on;
        }
    }
}
