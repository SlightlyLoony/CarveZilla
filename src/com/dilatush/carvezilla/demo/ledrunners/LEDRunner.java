package com.dilatush.carvezilla.demo.ledrunners;

import com.dilatush.carvezilla.demo.leds.LED;

/**
 * @author Tom Dilatush  tom@dilatush.com
 */
public interface LEDRunner {

    public void run( final LED _led ) throws InterruptedException;
}
