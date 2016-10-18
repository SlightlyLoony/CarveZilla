package com.dilatush.carvezilla.demo;

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
        LED led0 = new PatternLED( gpio, 0, new HalfWavePattern( 2000 ) );
        LED led1 = new ExponentialTransitionLED( gpio, 1, 300 );

        Random random = new Random( System.currentTimeMillis() );
        boolean on = false;

        while( true ) {

            Thread.sleep( 1000 );

            led0.brightness( 100 );
            led1.brightness( on ? 0 : 100 );
            on = !on;
        }
    }
}
