package com.dilatush.carvezilla.demo.leds;

/**
 * Implemented by classes that represent LEDs on a Raspberry Pi.
 *
 * @author Tom Dilatush  tom@dilatush.com
 */
public interface LED {

    void brightness( final int _percent );


    default void off() {
        brightness( 0 );
    }


    default void on() {
        brightness( 100 );
    }
}
