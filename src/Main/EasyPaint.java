//EasyPaint.java
//Peter Huang
//CS349
//20608854

package Main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.io.File;
import Main.AppScreen;

public class EasyPaint extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	AppScreen g;
	Desktop desk = Desktop.getDesktop();

	public EasyPaint() {
		super("EasyPaint (zh3huang)");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem loadItem = new JMenuItem("Load");
		JMenuItem saveItem = new JMenuItem("Save");

		fileMenu.add(newItem);
		fileMenu.add(loadItem);
		fileMenu.add(saveItem);

		newItem.addActionListener(this);
		loadItem.addActionListener(this);
		saveItem.addActionListener(this);

		add(g = new AppScreen());

		JMenuBar menubar = new JMenuBar();
		menubar.add(fileMenu);

		setJMenuBar(menubar);

		//frame properties
		setSize(1024, 768);
		//pack();
		setResizable(false);
		setVisible(true);

		setLocationRelativeTo(null);
		setFocusable(true);
		requestFocus();
	}


	//overrides abstract method actionPerformed
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("New")) {
			/*
			try { desk.open(new File("User Manual.chm"));} catch (Exception e) {e.printStackTrace();}			
			file above is in same directory level as src folder
			*/
			System.out.println("New to be implemented");
		} else if (ae.getActionCommand().equals("Load")) {
			System.out.println("Load to be implemented");
		} else if (ae.getActionCommand().equals("Save")) {
			System.out.println("Save to be implemented");
		} else {

		}
	}


	public static void main (String [] args) {
		new EasyPaint();
	}
}