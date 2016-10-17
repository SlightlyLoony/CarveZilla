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
        LED led1 = new LinearTransitionLED( gpio, 1, 250 );

        Random random = new Random( System.currentTimeMillis() );

        while( true ) {

            int wait = 500 + random.nextInt( 150 ) * 10;
            Thread.sleep( wait );

            boolean zero = random.nextBoolean();
            int bright = random.nextInt( 101 );
            if( zero )
                led0.brightness( bright );
            else
                led1.brightness( bright );
        }
    }
}
