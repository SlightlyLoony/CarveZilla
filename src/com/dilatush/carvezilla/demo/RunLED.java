package com.dilatush.carvezilla.demo;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import java.util.Random;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public class RunLED {

    public static void main( String[] args ) throws InterruptedException {

        GpioController gpio = GpioFactory.getInstance();

        LED led0 = new LED( gpio, 0 );
        LED led1 = new LED( gpio, 1 );

        Random random = new Random( System.currentTimeMillis() );

        while( true ) {

            int wait = 100 + random.nextInt( 190 ) * 10;
            Thread.sleep( wait );

            boolean zero = random.nextBoolean();
            int bright = random.nextInt( 101 );
            if( zero )
                led0.bright( bright );
            else
                led1.bright( bright );
        }
    }
}
