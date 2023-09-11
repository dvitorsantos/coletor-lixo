package org.example.Agents;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import org.example.Field;

public class ModelAgent extends Agent {
	private String[][] internalField = new String[20][20];
	private String orientation = "WALKING_DOWN_INI";
	private Integer stepCounter = 3;
	Deque<Integer[]> trashStack = new ArrayDeque<>(); // posiçoes dos lixos escaneados
	private String mapStatus = "NO_MAPPED";

	public ModelAgent(String identifier, int current_x, int current_y) {
		super(identifier, current_x, current_y);

	}
	
	public void decide(Field field) {
		if (mapStatus.equals("NO_MAPPED")) {
			toMap(field);
		} else {
			scanner(this.getCurrent_x(), this.getCurrent_y(), field);
			String info = field.info(this.getCurrent_x(), this.getCurrent_y());

			if (isTrash(info)) {
				updateCollect(field);
			} else {
				int[] nearestTrash = getNearestTrash();
				goToTrash(nearestTrash);
			}

		}
	}
	// função que dirige o mapeamento do mapa
	private void toMap(Field field) {
		try {
			String info = field.info(this.getCurrent_x(), this.getCurrent_y());

			if (isTrash(info)) {
				updateCollect(field);
			}
			else {
				scanner(this.getCurrent_x(), this.getCurrent_y(), field);
				walk();
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			this.move_up();
			mapStatus = "MAPPED";
		}
		
	}

//escaneia o mapa e atualiza a pilha de lixos;
	private void scanner(int i, int j, Field field) {
		int[][] offsets = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // posicoes(superior, inferior, esquerda,
		// direita)
		internalField[i][j] = field.info(i, j); // scaneia a posicao atual

		for (int[] offset : offsets) {
			int newRow = i + offset[0]; // guarda o valor de uma possivel linha a ser escaneada
			int newCol = j + offset[1]; // guarda o valor de uma possivel coluna a ser escaneada

			if (newRow >= 0 && newRow < 20 && newCol >= 0 && newCol < 20) { // verifica se a possivel combinação de linha e coluna é válida (se cabe no mapa)
				internalField[newRow][newCol] = field.info(newRow, newCol);
				// insere novos lixos que acabaram de ser detectados
				if (isTrash(internalField[newRow][newCol])) {
					Integer[] temp = { newRow, newCol };
					trashStack.push(temp);
				}
				// verifica se lixos anteriormente listados já foram removidos por outros agentes
				Iterator<Integer[]> iterator = trashStack.iterator();
				while (iterator.hasNext()) {
					Integer[] trash = iterator.next();
					if (trash[0].equals(newRow) && trash[1].equals(newCol) && internalField[newRow][newCol].equals(" ")) {
						iterator.remove(); // Remove o elemento de forma segura
					}
				}
			}
		}
	}

	public void walk() {
		if (this.orientation.equals("WALKING_DOWN_INI")) {
			this.move_down();
			if (this.getCurrent_x() == 1 && this.getCurrent_y() == 0)
				this.orientation = "WALKING_RIGHT";
			if (this.getCurrent_x() == 1 && this.getCurrent_y() == 19)
				this.orientation = "WALKING_LEFT";
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

	// retorna a distancia de um lixo para um agente
	private static double calculateDistance(double x1, double y1, double x2, double y2) {
		double differenceX = x2 - x1;
		double differenceY = y2 - y1;
		double distance = Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));
		return distance;
	}

	// retorna a posição do lixo mais próximo
	private int[] getNearestTrash() {
		int[] nearestTrash = new int[2];
		double menor = 999999f;
		for (Integer[] trash : trashStack) {
			double distance = calculateDistance(this.getCurrent_x(), this.getCurrent_y(), trash[0], trash[1]);
			if (distance < menor) {
				menor = distance;
				nearestTrash[0] = trash[0];
				nearestTrash[1] = trash[1];
			}
		}
		return nearestTrash;
	}

	private void goToTrash(int[] array) {
		if (this.getCurrent_x() != array[0]) {
			if (this.getCurrent_x() > array[0]) {
				this.move_up();
			} else
				this.move_down();
		} else if (this.getCurrent_y() != array[1]) {
			if (this.getCurrent_y() > array[1]) {
				this.move_left();
			} else {
				this.move_right();
			}
		}
	}

	private void updateCollect(Field field) {
		this.collect(field);

		// verifica se o lixo que acabou de ser comido estava na pilha de lixos
		// detectados, se sim, exclui-se da pilha

		Iterator<Integer[]> iterator = trashStack.iterator();
		while (iterator.hasNext()) {
			Integer[] trash = iterator.next();
			if (trash[0].equals(this.getCurrent_x()) && trash[1].equals(this.getCurrent_y())) {
				iterator.remove(); // Remove o elemento de forma segura
			}
		}

	}

}
