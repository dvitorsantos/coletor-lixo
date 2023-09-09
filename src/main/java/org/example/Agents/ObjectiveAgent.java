package org.example.Agents;

import org.example.Field;

import java.util.ArrayList;

public class ObjectiveAgent extends Agent {
    private ArrayList<int[]> objectives;

    public ObjectiveAgent(String identifier, int current_x, int current_y, Field field) {
        super(identifier, current_x, current_y);
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 19; y++) {
                System.out.println(field.info(x, y));
            }
        }
    }

    public void decide() {

    }

    public void walk() {

    }
}
