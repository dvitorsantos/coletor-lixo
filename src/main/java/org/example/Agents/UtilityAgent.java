package org.example.Agents;

import org.example.Field;

import java.util.ArrayList;

public class UtilityAgent extends Agent {
    ArrayList<int[]> objectives = new ArrayList<>();
    private int[] current_objective = null;

    public UtilityAgent(String identifier, int current_x, int current_y, Field field) {
        super(identifier, current_x, current_y);
        ArrayList<int[]> primary_objectives = new ArrayList<>();
        ArrayList<int[]> secondary_objectives = new ArrayList<>();

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                String info = field.info(x, y);

                int[] objective = new int[2];
                objective[0] = x;
                objective[1] = y;

                if (info.equals("#")) primary_objectives.add(objective);
                if (info.equals("*")) secondary_objectives.add(objective);
            }
        }

        objectives.addAll(primary_objectives);
        objectives.addAll(secondary_objectives);
    }

    public void decide(Field field) {
        this.current_objective = next_objective();

        int objective_x = current_objective[0];
        int objective_y = current_objective[1];

        if (this.getCurrent_x() < objective_x) this.move_down();
        else if (this.getCurrent_x() > objective_x) this.move_up();
        else if (this.getCurrent_y() < objective_y) this.move_right();
        else if (this.getCurrent_y() > objective_y) this.move_left();

        if (this.getCurrent_x().equals(objective_x) && this.getCurrent_y().equals(objective_y) ||
                field.info(getCurrent_x(), getCurrent_y()).equals("*") || field.info(getCurrent_x(), getCurrent_y()).equals("#") ) {
            this.collect(field);
            this.remove_objective(getCurrent_x(), getCurrent_y());
        }
    }

    public int[] next_objective() {
        return objectives.get(0);
    }

    public void remove_objective(int x, int y) {
        objectives.removeIf(objective -> objective[0] == x && objective[1] == y);
    }
}
