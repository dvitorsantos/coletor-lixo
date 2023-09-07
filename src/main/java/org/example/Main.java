package org.example;

import org.example.Agents.ModelAgent;
import org.example.Agents.ReactiveAgent;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Field field = new Field();
		ReactiveAgent a = new ReactiveAgent("A", 0, 0);
		ModelAgent ma = new ModelAgent("B", 0, 0);
		field.spawn(ma);

		while (true) {
			System.out.flush();
			System.out.print("\033[H\033[2J");
			// a.decide(field);
			ma.toMap(field);
			field.update();
			Thread.sleep(100);
			System.out.flush();
			System.out.print("\033[H\033[2J");

		}

	}
}