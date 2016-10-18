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
