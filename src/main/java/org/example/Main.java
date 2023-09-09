package org.example;

import org.example.Agents.ModelAgent;
import org.example.Agents.ObjectiveAgent;
import org.example.Agents.SimpleReactiveAgent;
import org.example.Agents.UtilityAgent;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Field field = new Field();
		SimpleReactiveAgent a = new SimpleReactiveAgent("A", 0, 0);
		ModelAgent b = new ModelAgent("B", 0, 0);
		ObjectiveAgent c = new ObjectiveAgent("C", 0, 19, field);
		UtilityAgent d = new UtilityAgent("D", 0, 0, field);

		field.spawn(c);
		field.spawn(d);

		while (true) {
			c.decide(field);
			d.decide(field);
			field.update();
			Thread.sleep(1000);
		}

	}
}