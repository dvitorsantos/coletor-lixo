package org.example.Agents;

import org.example.Field;

public class Agent {
    private String identifier;
    private Integer current_x;
    private Integer current_y;
    private Integer last_x;
    private Integer last_y;
    private Integer points;

    public Agent(String identifier, int current_x, int current_y) {
        this.identifier = identifier;
        this.current_x = current_x;
        this.current_y = current_y;
        this.last_x = current_x;
        this.last_y = current_y;
        this.points = 0;
    }

    public void move_down() {
        this.last_x = current_x;
        this.current_x++;
    }

    public void move_up() {
        this.last_x = current_x;
        this.current_x--;
    }

    public void move_left() {
        this.last_y = current_y;
        this.current_y--;
    }

    public void move_right() {
        this.last_y = current_y;
        this.current_y++;
    }

    public void collect(String trash) {
        switch (trash) {
            case "*" -> this.points += 1;
            case "#" -> this.points += 3;
        }
    }

    public boolean isTrash(String info) {
        return info.equals("*") || info.equals("#");
    }

    public String getIdentifier() {
        return identifier;
    }

    public Integer getCurrent_x() {
        return current_x;
    }

    public Integer getCurrent_y() {
        return current_y;
    }

    public Integer getLast_x() {
        return last_x;
    }

    public Integer getLast_y() {
        return last_y;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
