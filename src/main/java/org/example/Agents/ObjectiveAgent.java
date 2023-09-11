package org.example.Agents;

import org.example.Field;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjectiveAgent extends Agent {
    private ArrayList<int[]> objectives = new ArrayList<>();
    private int[] current_objective = null;

    public ObjectiveAgent(String identifier, int current_x, int current_y, Field field) {
        super(identifier, current_x, current_y);
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                String info = field.info(x, y);
                if (info.equals("*") || info.equals("#")) {
                    int[] objective = new int[2];
                    objective[0] = x;
                    objective[1] = y;
                    this.objectives.add(objective);
                }
            }
        }
    }

    public void decide(Field field) {
        this.current_objective = next_objective();

        int objective_x = current_objective[0];
        int objective_y = current_objective[1];

        if (this.getCurrent_x() < objective_x) this.move_down();
        else if (this.getCurrent_x() > objective_x) this.move_up();
        else if (this.getCurrent_y() < objective_y) this.move_right();
        else if (this.getCurrent_y() > objective_y) this.move_left();

        if ((this.getCurrent_x().equals(objective_x) && this.getCurrent_y().equals(objective_y)) ||
                field.info(getCurrent_x(), getCurrent_y()).equals("*") || field.info(getCurrent_x(), getCurrent_y()).equals("#") ) {
            this.collect(field);
            this.remove_objective(getCurrent_x(), getCurrent_y());
        }
    }

    public void walk() {

    }

    public int[] next_objective() {
    	int[] nearestTrash = new int[2];
		double menor = 999999f;
		for (int[] trash : objectives) {
			double distance = calculateDistance(this.getCurrent_x(), this.getCurrent_y(), trash[0], trash[1]);
			if (distance < menor) {
				menor = distance;
				nearestTrash[0] = trash[0];
				nearestTrash[1] = trash[1];
			}
		}
		return nearestTrash;
    }

    public void remove_objective(int x, int y) {
        objectives.removeIf(objective -> objective[0] == x && objective[1] == y);
    }
    
    private static double calculateDistance(double x1, double y1, double x2, double y2) {
		double differenceX = x2 - x1;
		double differenceY = y2 - y1;
		double distance = Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));
		return distance;
	}
    
   

}
