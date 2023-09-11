package org.example;

import org.example.Agents.ModelAgent;
import org.example.Agents.ObjectiveAgent;
import org.example.Agents.SimpleReactiveAgent;
import org.example.Agents.UtilityAgent;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Field field = new Field();
		SimpleReactiveAgent simpleReactive = new SimpleReactiveAgent("A", 0, 0);
		ModelAgent model = new ModelAgent("B", 0, 19);
		ObjectiveAgent objective = new ObjectiveAgent("C", 0, 0, field);
		UtilityAgent utility = new UtilityAgent("D", 0, 0, field);
		
		//field.spawn(simpleReactive);
		field.spawn(model);
		//field.spawn(objective);
		field.spawn(utility);

		while (true) {
			if(!field.hasTrash()) {
				break;
			}
			//simpleReactive.decide(field);
			model.decide(field);
			//objective.decide(field);
			utility.decide(field);
			field.update();
			Thread.sleep(50);
		}

	}
	
}