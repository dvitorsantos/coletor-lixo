package org.example;

import org.example.Agents.Agent;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private final int rows = 20;
    private final int columns = 20;
    private int trashes = 0;
    private String[][] field = new String[rows][columns];
    private ArrayList<Agent> agents;

    public Field() {
        this.agents = new ArrayList<>();

        int low_trashes = 0;
        int high_trashes = 0;

        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int randomValue = random.nextInt(20);
                String randomChar;

                switch (randomValue) {
                    case 0:
                        if (low_trashes < 10) {
                            randomChar = String.valueOf('*');
                            low_trashes++;
                        } else {
                            randomChar = String.valueOf(' ');
                        }
                        break;
                    case 1:
                        if (high_trashes < 10) {
                            randomChar = String.valueOf('#');
                            high_trashes++;
                        } else {
                            randomChar = String.valueOf(' ');
                        }
                        break;
                    default:
                        randomChar = String.valueOf(' ');
                        break;
                }

                field[i][j] = randomChar;
            }
        }

        this.trashes = low_trashes + high_trashes;
    }

    public void print() {
        agents.forEach(agent -> {
            System.out.println("Agent " + agent.getIdentifier() + " with " + agent.getPoints() + " points.");
        });

        System.out.println(trashes + " trashes in game.");

        System.out.println("========================================");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
            	System.out.print(field[i][j]+" ");
                for (Agent agent: agents) {
                    if (agent.getCurrent_x() == i && agent.getCurrent_y() == j) {
                        System.out.print(agent.getIdentifier());
                    }
                }
            }
            System.out.println();
        }
        System.out.println("========================================");
    }
    
    public boolean hasTrash() {
        return trashes > 0;
    }

    public void update() {
        this.print();
    }

    public void spawn(Agent agent) {
        this.agents.add(agent);
    }

    public String info(int x, int y) {
        return this.field[x][y];
    }

    public void clean(int x, int y) {
        if (this.field[x][y].equals("*") || this.field[x][y].equals("#")) this.trashes--;
        this.field[x][y] = " ";
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    
    public String[][] getField(){
    	return field;
    }
}
