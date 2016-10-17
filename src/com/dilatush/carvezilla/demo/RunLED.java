package com.dilatush.carvezilla.demo;

import com.dilatush.carvezilla.demo.leds.LED;
import com.dilatush.carvezilla.demo.leds.LinearTransitionLED;
import com.dilatush.carvezilla.demo.leds.VariableLED;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import java.util.Random;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public class RunLED {

    public static void main( String[] args ) throws InterruptedException {

        GpioController gpio = GpioFactory.getInstance();
        LED led0 = new VariableLED( gpio, 0 );
        LED led1 = new LinearTransitionLED( gpio, 1, 150 );

        Random random = new Random( System.currentTimeMillis() );
        boolean on = false;

        while( true ) {

            Thread.sleep( 500 );

            led0.brightness( random.nextInt( 101 ) );
            led1.brightness( on ? 0 : 100 );
            on = !on;
        }
    }
}
