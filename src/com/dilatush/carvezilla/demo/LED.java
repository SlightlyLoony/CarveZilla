package com.dilatush.carvezilla.demo;

import com.pi4j.io.gpio.*;

/**
 * Represents an LED connected to a GPIO pin.
 *
 * @author Tom Dilatush  tom@dilatush.com
 */
public class LED {

    private GpioPinDigitalOutput pin;

    public LED( final GpioController _gpio, final int _pin ) {
        Pin pinSpec = RaspiPin.getPinByAddress( _pin );
        pin = _gpio.provisionDigitalOutputPin( pinSpec, PinState.LOW );
        pin.setShutdownOptions( false, PinState.LOW );
    }


    public void on() {
        pin.setState( PinState.HIGH );
    }


    public void off() {
        pin.setState( PinState.LOW );
    }
}
