package com.project.conway_game_of_life;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Grid extends JPanel {
	private static final long serialVersionUID = 1369751877879272282L;
	private boolean firstGen = true;

	private int rows;
	private int columns;
	private int resolution;
	private Cell[][] cells;
	
	public Grid(int resolution, int width, int height) {
		this.resolution = resolution;
		columns = width / resolution;
		rows = height / resolution;
		
		cells = new Cell[rows][columns];
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		assessValues(cells);
		if (firstGen) firstGen = false;
		
		paintCells(cells, g);
		
	}

	
	private void assessValues(Cell[][] cells) {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				if (firstGen) {
					cells[x][y] = new Cell();
					
					cells[x][y].setValue(Math.random() > 0.5 ? 1 : 0);
				}else{
					int neighbors = countNeighbors(cells, x, y);
					//RULES
					if (cells[x][y].getValue() == 1) {
						if (neighbors < 2) cells[x][y].setValue(0);
						else if (neighbors <= 3) cells[x][y].setValue(1);
						else cells[x][y].setValue(0);
					} else if (neighbors == 3) cells[x][y].setValue(1);
					
//					//RULES v2
//					if (cells[x][y].getValue() == 1) {
//						if (neighbors >= 1 || neighbors <= 4 ) cells[x][y].setValue(1);
//						else if (neighbors > 4) cells[x][y].setValue(0);
//						else cells[x][y].setValue(2);
//					}else cells[x][y].setValue(1);
					
				}
			}
		}
	}

	private void paintCells(Cell[][] cells, Graphics g) {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				if (cells[x][y].getValue() == 1) g.setColor(Color.black);
				else g.setColor(Color.white);
				
				g.fillRect(y * resolution, x * resolution, resolution, resolution);
			}
		}
	}
	
	private int countNeighbors(Cell[][] cells, int row, int column) {
		int neighbors = 0;
		
		for (int x = row - 1; x < row + 2; x++) {
			for (int y = column - 1; y < column + 2; y++) {
				try {
					if (x == row && y == column) continue;
					
					neighbors += cells[x][y].getValue();
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
		
		return neighbors;
	}
}
