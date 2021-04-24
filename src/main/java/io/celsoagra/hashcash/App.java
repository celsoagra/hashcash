package io.celsoagra.hashcash;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.time.StopWatch;

public class App {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        StopWatch stopWatch = StopWatch.create();
        stopWatch.start();
        System.out.println(String.format("mined: %s", new Block("hashcash algorithm").mine(4)));
        stopWatch.stop();
        System.out.format("milisec: %s", stopWatch.getTime());
    }

}
