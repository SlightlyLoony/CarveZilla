package com.dilatush.carvezilla.demo.ledrunners;

import com.dilatush.carvezilla.demo.leds.LED;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public class Burst implements LEDRunner {

    private final LEDRunner runner;
    private final int number;


    public Burst( final LEDRunner _runner, final int _number ) {

        if( _runner == null || _number < 1 )
            throw new IllegalArgumentException( "Invalid LED runner or number of cycles is less than one" );
        runner = _runner;
        number = _number;
    }

    @Override
    public void run( final LED _led ) throws InterruptedException {

        for( int i = 0; i < number; i++ ) {
            runner.run( _led );
        }
    }
}
