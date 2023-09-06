package org.example;

import org.example.Agents.ReactiveAgent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Field field = new Field();
        ReactiveAgent a = new ReactiveAgent("A", 0, 0);
        field.spawn(a);
        while (true) {
            a.decide(field);
            field.update();
            Thread.sleep(500);
        }

    }
}