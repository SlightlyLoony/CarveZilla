package com.dilatush.carvezilla.demo.ledrunners;

import com.dilatush.carvezilla.demo.leds.LED;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public class Sequence implements LEDRunner {

    private final LEDRunner[] runners;


    public Sequence( final LEDRunner... _runners ) {

        if( _runners == null || _runners.length == 0 )
            throw new IllegalArgumentException( "Missing LED runners" );

        runners = _runners;
    }


    @Override
    public void run( final LED _led ) throws InterruptedException {

        for( LEDRunner runner : runners ) {
            runner.run( _led );
        }
    }
}
