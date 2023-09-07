package org.example.Agents;

import org.example.Field;

public class ModelAgent extends Agent {
	private String[][] internalField = new String[20][20];
	private String orientation = "WALKING_DOWN_INI";
	private Integer stepCounter = 3;

	public ModelAgent(String identifier, int current_x, int current_y) {
		super(identifier, current_x, current_y);

	}

	public void toMap(Field field) {
		String info = field.info(this.getCurrent_x(), this.getCurrent_y());

		if (isTrash(info)) {
			this.collect(info);
			field.clean(this.getCurrent_x(), this.getCurrent_y());
		} else {
			scanner(this.getCurrent_x(), this.getCurrent_y(), field);
			walk();
		}

	}

	public void scanner(int i, int j, Field field) {
		int[][] offsets = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // posicoes(superior, inferior, esquerda,
																		// direita)

		internalField[i][j] = field.info(i, j); // scaneia a posicao atual

		for (int[] offset : offsets) {
			int newRow = i + offset[0]; // guarda o valor de uma possivel linha a ser escaneada
			int newCol = j + offset[1]; // guarda o valor de uma possivel coluna a ser escaneada

			if (newRow >= 0 && newRow < 20 && newCol >= 0 && newCol < 20) { // verifica se a possivel combinação de
																			// linha e coluna é válida (se cabe no mapa)
				internalField[newRow][newCol] = field.info(newRow, newCol);
			}
		}
	}

	public void walk() {

		if (this.orientation.equals("WALKING_DOWN_INI")) {
			this.move_down();
			this.orientation = "WALKING_RIGHT";
			return;
		}

		if (this.orientation.equals("WALKING_DOWN")) {
			if (stepCounter > 0) {
				this.move_down();
				stepCounter--;
			} else {
				if (this.getCurrent_y() == 0 && !this.orientation.equals("WALKING_RIGHT")) {
					this.orientation = "WALKING_RIGHT";
					stepCounter = 3;
				}

				if (this.getCurrent_y() == 19 && !this.orientation.equals("WALKING_LEFT")) {
					this.orientation = "WALKING_LEFT";
					stepCounter = 3;
				}
			}
		}

		if (this.getCurrent_y() == 19 && !this.orientation.equals("WALKING_LEFT")) {
			this.orientation = "WALKING_DOWN";
		}

		if (this.getCurrent_y() == 0 && !this.orientation.equals("WALKING_RIGHT")) {
			this.orientation = "WALKING_DOWN";
		}

		if (this.orientation.equals("WALKING_RIGHT")) {
			this.move_right();
		}

		if (this.orientation.equals("WALKING_LEFT")) {
			this.move_left();
		}

	}

	public void getInternalField() {// Só para ver se tá imprimindo o mapa interno que foi mapeado
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				System.out.print(internalField[i][j] + " ");
			}
			System.out.println();
		}
	}

}
