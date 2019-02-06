/**
 * Game of Life Exercise 
 * View.java
 * Purpose: Give the user a visual output of how the Game of Life is evolving.
 * 
 * @author William Robbie McMillan
 * @version 1.0 07/02/19
 */
package GameOfLife.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame implements ActionListener {

	JButton cell;
	JButton pause;
	JButton resume;
	JButton restart;
	JPanel gridFrame;
	String gridSize;
	Model model;
	Timer timer;

	public View() {

		model = new Model();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocation(100, 100);
		setTitle("BBC Game of Life Challenge");
		setResizable(false);

		viewInitialGridPanel();
		viewEvolution();
		buttonPanel();

	}

	/*
	 * Method to create the initial grid and show it in a GUI using a gridlayout and
	 * JPanel.
	 */

	public void viewInitialGridPanel() {

		gridFrame = new JPanel();
		
		gridFrame.setLayout(new GridLayout(16, 16));

		model.generateInitialGrid();

		Cell[][] cellGrid = model.getInitialGrid(); // Get the initial grid from the model class.
		for (int i = 0; i < cellGrid.length; i++) {
			for (int j = 0; j < cellGrid.length; j++) {
				cell = new JButton();

				if (cellGrid[i][j].isAlive()) { // Colour each JButton black or white based on its status.
					cell.setBackground(Color.black);
				}
				gridFrame.add(cell); // Add JButton to JPanel.

			}

			this.add(gridFrame);

		}

	}

	/*
	 * Method to generate the evolved grid and update the gridFrame
	 * JPanel with the new grid.
	 */

	public void viewEvolution() {

		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) { //

				gridFrame.removeAll(); // remove JPanels contents.
				model.documentNeighbours(); // call methods from model class.
				model.generateEvolution();

				Cell[][] cellGrid = model.getEvolution(); // get newly updated cellGrid.

				for (int i = 0; i < cellGrid.length; i++) {
					for (int j = 0; j < cellGrid.length; j++) {

						cell = new JButton();

						if (cellGrid[i][j].getStatus() == 1) {
							cell.setBackground(Color.black);
						}

						gridFrame.add(cell);

					}

				}
				gridFrame.revalidate(); // refresh gridFrame with new contents.
				gridFrame.repaint();

			}

		};

		timer = new Timer(2000, taskPerformer); // tells to the actionlistener to repeat contents every 2000 ms.

		timer.start();

	}

	/*
	 * Method to the create the button panel.
	 */

	public void buttonPanel() {

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout());

		pause = new JButton("Pause");
		resume = new JButton("Resume");
		restart = new JButton("Restart");
		pause.addActionListener(this);
		resume.addActionListener(this);
		restart.addActionListener(this);
		buttonPanel.add(pause);
		buttonPanel.add(resume);
		buttonPanel.add(restart);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}


	/*
	 * pause: pause the output 
	 * resume: restart the timer 
	 * restart: start again with a
	 * new initial grid.
	 */

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pause) {
			timer.stop();
		} else if (e.getSource() == resume) {
			timer.restart();
		} else if (e.getSource() == restart) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			viewInitialGridPanel();
			viewEvolution();

		}
	}

}
