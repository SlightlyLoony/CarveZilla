package com.dilatush.carvezilla.demo;

import com.pi4j.io.gpio.*;

/**
 * Represents an LED connected to a GPIO pin.  The LED can have a configurable brightness (0 - 100%).
 *
 * @author Tom Dilatush  tom@dilatush.com
 */
public class LED {

    private GpioPinDigitalOutput pin;
    private volatile int brightness;
    private PWM pwm;

    public LED( final GpioController _gpio, final int _pin ) {

        Pin pinSpec = RaspiPin.getPinByAddress( _pin );
        pin = _gpio.provisionDigitalOutputPin( pinSpec, PinState.LOW );
        pin.setShutdownOptions( false, PinState.LOW );
        brightness = 0;

        pwm = new PWM();
        pwm.start();
    }


    public void bright( final int _brightness ) {
        brightness = Math.min( 100, Math.max( 0, _brightness ) );
    }


    public void on() {
        brightness = 100;
    }


    public void off() {
        brightness = 0;
    }


    /**
     * This thread implements a pulse-width modulation scheme to control the LED's brightness.  The pulses are begun on 0.02 second intervals (50
     * pulse per second), and the width is varied from 0 to 0.02 seconds, in 0.0002 second increments.
     */
    private class PWM extends Thread {

        private boolean on = false;
        private int lastBrightness;

        public PWM() {
            setName( "PWM" + pin );
            setDaemon( true );
        }

        @Override
        public void run() {

            try {
                while( !interrupted() ) {

                    if( on ) {
                        pin.setState( PinState.LOW );
                        on = false;
                        int tenthou = 200 - lastBrightness * 2;
                        if( tenthou > 0 ) {
                            wait( tenthou );
                        }
                    }

                    else {
                        pin.setState( PinState.HIGH );
                        on = true;
                        lastBrightness = brightness;
                        if( lastBrightness > 0 ) {
                            wait( lastBrightness * 2 );
                        }
                    }
                }
            }
            catch( InterruptedException _e ) {
                // naught to do here, we just want to kill the thread...
            }
        }


        private void wait( final int tenthou ) throws InterruptedException {
            int millis = tenthou / 10;
            int nanos = 100000 * (tenthou - millis * 10);
            sleep( millis, nanos );
        }
    }
}
