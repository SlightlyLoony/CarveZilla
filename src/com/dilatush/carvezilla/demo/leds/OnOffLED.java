package com.dilatush.carvezilla.demo.leds;

import com.pi4j.io.gpio.*;

/**
 * Represents an LED connected to a GPIO pin that can be turned on (full brightness) or off.  Setting the brightness percentage to anything other
 * than zero will turn the LED fully on.
 *
 * @author Tom Dilatush  tom@dilatush.com
 */
public class OnOffLED implements LED {

    private GpioPinDigitalOutput pin;


    public OnOffLED( final GpioController _gpio, final int _pinNumber ) {

        Pin pinSpec = RaspiPin.getPinByAddress( _pinNumber );
        pin = _gpio.provisionDigitalOutputPin( pinSpec, PinState.LOW );
        pin.setShutdownOptions( false, PinState.LOW );
    }


    @Override
    public void brightness( final int _percent ) {
        if( _percent > 0 )
            pin.setState( PinState.HIGH );
        else
            pin.setState( PinState.LOW );
    }
}
