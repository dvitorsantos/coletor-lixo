package org.example.Agents;

import org.example.Field;

public class ReactiveAgent extends Agent {
    private String orientation = "WALKING_RIGHT";

    public ReactiveAgent(String identifier, int current_x, int current_y) {
        super(identifier, current_x, current_y);
    }

    public void decide(Field field) {
        String info = field.info(this.getCurrent_x(), this.getCurrent_y());

        if (isTrash(info)) {
            this.collect(field);
        } else {
            this.walk();
        }
    }

    public void walk() {
        if (this.orientation.equals("TURNING_RIGHT")) {
            this.orientation = "WALKING_RIGHT";
        }

        if (this.orientation.equals("TURNING_LEFT")) {
            this.orientation = "WALKING_LEFT";
        }

        if (getCurrent_y() == 19 && !this.orientation.equals("WALKING_LEFT")) {
            this.move_down();
            this.orientation = "TURNING_LEFT";
        }

        if (getCurrent_y() == 0 && !this.orientation.equals("WALKING_RIGHT")) {
            this.move_down();
            this.orientation = "TURNING_RIGHT";
        }

        if (this.orientation.equals("WALKING_RIGHT")) {
            this.move_right();
        }

        if (this.orientation.equals("WALKING_LEFT")) {
            this.move_left();
        }
    }
}
