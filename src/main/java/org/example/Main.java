package org.example;

import org.example.Agents.ReactiveAgent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Field field = new Field();
        ReactiveAgent a = new ReactiveAgent("A", 0, 0);
        field.spawn(a);
        while (true) {
            a.decide(field.info(a.getCurrent_x(), a.getCurrent_y() + 1));
            field.update();
            Thread.sleep(1000);
        }

    }
}