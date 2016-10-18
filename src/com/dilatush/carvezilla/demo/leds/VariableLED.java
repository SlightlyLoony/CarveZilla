package com.dilatush.carvezilla.demo.leds;

import com.pi4j.io.gpio.*;

/**
 * Represents an LED connected to a GPIO pin whose brightness can be controlled.  The LED can have a configurable brightness (0 - 100%).  The
 * brightness is controlled by pulsing the LED at 50 cycles per second, and controlling the width of the "on" pulse.
 *
 * @author Tom Dilatush  tom@dilatush.com
 */
public class VariableLED implements LED {

    protected static final int TICK_MS = 20;
    protected static final int TICK_TEN_THOU = TICK_MS * 10;
    protected static final int TEN_THOU_PER_INC = TICK_TEN_THOU / 100;

    private GpioPinDigitalOutput pin;
    private PWM pwm;


    public VariableLED( final GpioController _gpio, final int _pinNumber ) {

        Pin pinSpec = RaspiPin.getPinByAddress( _pinNumber );
        pin = _gpio.provisionDigitalOutputPin( pinSpec, PinState.LOW );
        pin.setShutdownOptions( false, PinState.LOW );

        pwm = new PWM();
        pwm.start();
    }


    @Override
    public void brightness( final int _percent ) {
        pwm.setBrightness( Math.min( 100, Math.max( 0, _percent ) ) );
    }


    protected int transition( final int _brightness ) {
        return _brightness;
    }


    /**
     * This thread implements a pulse-width modulation scheme to control the LED's brightness.  The pulses are begun on 0.02 second intervals (50
     * pulses per second), and the width is varied from 0 to 0.02 seconds, in 0.0002 second increments.
     */
    private class PWM extends Thread {

        private int brightness;
        private boolean on;

        PWM() {
            setName( "PWM" + pin );
            setDaemon( true );
            brightness = 0;
            on = false;
        }

        @Override
        public void run() {

            try {

                // do this forever, basically...
                while( !interrupted() ) {

                    // if the LED is currently on...
                    if( on ) {
                        on = false;
                        int tenthou = TICK_TEN_THOU - brightness * TEN_THOU_PER_INC;
                        if( tenthou > 0 )
                            pin.setState( PinState.LOW );
                        wait( tenthou );
                    }

                    else {
                        brightness = transition( brightness );
                        on = true;
                        if( brightness > 5 )  // we ignore the lowest brightnesses, as they are relatively unstable and don't look good...
                            pin.setState( PinState.HIGH );
                        wait( brightness * TEN_THOU_PER_INC );
                    }
                }
            }
            catch( InterruptedException _e ) {
                // naught to do here, we just want to kill the thread...
            }
        }


        private void wait( final int _tenthou ) throws InterruptedException {
            if( _tenthou <= 0 )
                return;

            int millis = _tenthou / 10;
            int nanos = 100000 * (_tenthou - millis * 10);
            sleep( millis, nanos );
        }


        private synchronized void setBrightness( final int _brightness ) {
            brightness = _brightness;
        }
    }

}
