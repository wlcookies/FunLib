package com.wlcookies.funlib;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String a = "$GPGGA,1,2,31";
        String replace = a.replace("GPGGA", "GNGGA");
        System.out.println(replace);
    }
}