package com.project.conway_game_of_life;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -4962335884122083971L;
	
	private Grid g;
	
	boolean isDebug = false;
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	public MainFrame() {
		setWindow();
		setVisible(true);
		
		while(!isDebug) {
			try {
				Thread.sleep(70);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			g.repaint();
		}
	}
	
	private void setWindow() {
		setUndecorated(true);
		setTitle("Conway's Game of Life");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		
		int scrnWdth = (int) (screen.getWidth() / 2);
		int scrnHght = (int) (screen.getHeight() / 2);
		
		setGrid(scrnWdth, scrnHght);
		
		pack();
		
		int posX = (int) ((screen.getWidth() - getWidth()) / 2);
		int posY = (int) ((screen.getHeight() - getHeight()) / 2);
		setLocation(posX, posY);
	}
	
	private void setGrid(int width, int height) {
		int resolution = 25;
		
		g = new Grid(resolution, width, height);
		g.setPreferredSize(new Dimension(width, height));
		g.setFocusable(true);
		g.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 27) System.exit(0);
				else if (isDebug && e.getKeyCode() == 32) g.repaint();
			};
		});

		add(g);
	}
}
