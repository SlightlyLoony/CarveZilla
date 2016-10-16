package com.dilatush.carvezilla.demo;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public class RunLED {

    public static void main( String[] args ) throws InterruptedException {

        GpioController gpio = GpioFactory.getInstance();

        LED led = new LED( gpio, 0 );

        while( true ) {

            led.on();
            Thread.sleep( 500 );
            led.off();
            Thread.sleep( 500 );
        }
    }
}
