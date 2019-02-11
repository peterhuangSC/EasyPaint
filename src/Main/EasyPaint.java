//EasyPaint.java
//Peter Huang
//CS349
//20608854

package Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Main.AppScreen;
import Main.Screens.CanvasScreen;

public class EasyPaint extends JFrame implements ActionListener {

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

		// frame properties
		setSize(1024, 768);
		// pack();
		setResizable(false);
		setVisible(true);

		setLocationRelativeTo(null);
		setFocusable(true);
		requestFocus();
	}

	/*
	 * try { desk.open(new File("User Manual.chm"));} catch (Exception e)
	 * {e.printStackTrace();} file above is in same directory level as src folder
	 */

	// overrides abstract method actionPerformed
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("New")) {
			CanvasScreen.shapesList.clear();
			g.drawingPanel.repaint();
			g.drawingPanel.repaint();
			g.drawingPanel.revalidate();
			g.drawingPanel.removeAll();
			// g = new AppScreen();
			System.out.println("New File!");
		} else if (ae.getActionCommand().equals("Load")) {
			JFileChooser jfc = new JFileChooser();
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("EasyPaint Images", "ezp");
			jfc.setFileFilter(filter);
			int returnVal = jfc.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You are opening the file: " + jfc.getSelectedFile().getName());
			}

			System.out.println("Load to be implemented");
		} else if (ae.getActionCommand().equals("Save")) {
			System.out.println("Save to be implemented");

			JFileChooser jfc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("EasyPaint Images", "ezp");
			jfc.setFileFilter(filter);
			int returnVal = jfc.showSaveDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You are saving the file: " + jfc.getSelectedFile().getName());
			
				PrintWriter printOut;
				try {
					printOut = new PrintWriter(new FileWriter("sampleFile.txt"));
					printOut.println();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {

		}
	}


	public static void main (String [] args) {
		new EasyPaint();
	}
}
