package org.example;

import org.example.Agents.Agent;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private final int rows = 20;
    private final int columns = 20;
    private String[][] field = new String[rows][columns];

    private ArrayList<Agent> agents;

    public Field() {
        this.agents = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int randomValue = random.nextInt(10); // Gere um valor aleatório entre 0 e 2
                String randomChar;

                // Mapeie o valor aleatório para '*' ou '#' ou espaço vazio
                switch (randomValue) {
                    case 0:
                        randomChar = String.valueOf('*');
                        break;
                    case 1:
                        randomChar = String.valueOf('#');
                        break;
                    default:
                        randomChar = String.valueOf(' ');
                        break;
                }

                field[i][j] = randomChar;
            }
        }
    }

    public void print() {
        agents.forEach(agent -> {
            System.out.println("Agent " + agent.getIdentifier() + " with " + agent.getPoints() + " points.");
        });
        System.out.println("========================================");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
            	System.out.print(field[i][j] + " ");
                for (Agent agent: agents) {
                    if (agent.getCurrent_x() == i && agent.getCurrent_y() == j) {
                        System.out.print(agent.getIdentifier() + " ");
                    } 
                }

            }
            System.out.println();
        }
        System.out.println("========================================");
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
        this.field[x][y] = " ";
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
