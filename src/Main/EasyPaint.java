//EasyPaint.java
//Peter Huang
//CS349
//20608854

package Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import Main.AppScreen;
import Main.CanvasShapes.*;
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
			int state = jfc.showOpenDialog(this);
			File myFile = jfc.getSelectedFile();
			if(myFile != null && state == JFileChooser.APPROVE_OPTION) {
				System.out.println("You are opening the file: " + jfc.getSelectedFile().getName());

				List<CanvasShape> tempList = CanvasScreen.shapesList;
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(myFile));
					String line = reader.readLine(); //skip line 1
					line = reader.readLine();

					List<CanvasShape> newShapesList = new ArrayList<CanvasShape>();
					while (true) {
						line = reader.readLine();
						if (line.trim().equals("end")) break;

						String [] components = line.split(Pattern.quote("|"));
						int compLen = components.length;
						for (int j = 0; j < compLen; j++) {
							components[j] = components[j].trim();
						}

						String shapeType = components[0];
						int startX = Integer.parseInt(components[1]);
						int startY = Integer.parseInt(components[2]);
						int endX = Integer.parseInt(components[3]);
						int endY = Integer.parseInt(components[4]);
						int myWidth = Integer.parseInt(components[5]);
						int myHeight = Integer.parseInt(components[6]);
						int myThicc = Integer.parseInt(components[7]);
						Color myColour = new Color(Integer.parseInt(components[8]));
						boolean isFilledIn = false;
						if (components[9].equalsIgnoreCase("true")) isFilledIn = true;

						Point startPt = new Point(startX, startY);
						Point endPt = new Point(endX, endY);

						CanvasShape currentShape;// = new CurrentShape();
						if (shapeType.equals("CanvasLine")) {
							currentShape = new CanvasLine(startPt, endPt, myThicc, myColour);
							currentShape.isFilled = false;
							newShapesList.add(currentShape);
						} else if (shapeType.equals("CanvasOval")) {
							currentShape = new CanvasOval(startX, startY, myWidth, myHeight, myThicc, myColour);
							currentShape.isFilled = isFilledIn;
							newShapesList.add(currentShape);
						} else if (shapeType.equals("CanvasRectangle")) {
							currentShape = new CanvasRectangle(startX, startY, myWidth, myHeight, myThicc, myColour);
							currentShape.isFilled = isFilledIn;
							newShapesList.add(currentShape);
						}
						//don't add here because we have them all added above
					}
					CanvasScreen.shapesList.clear();
					CanvasScreen.shapesList.addAll(newShapesList);

					reader.close();

				} catch (IOException e) {
					e.printStackTrace();
					CanvasScreen.shapesList = tempList;
				} catch (ArrayIndexOutOfBoundsException e2) {
					e2.printStackTrace();
					CanvasScreen.shapesList = tempList;
				} catch (NumberFormatException e3) {
					e3.printStackTrace();
					CanvasScreen.shapesList = tempList;
				} catch (Exception e4) {
					//other exceptions
					e4.printStackTrace();
					CanvasScreen.shapesList = tempList;
				}

			}

			System.out.println("Load to be implemented");
			g.drawingPanel.repaint();
			g.drawingPanel.repaint();
		} else if (ae.getActionCommand().equals("Save")) {
			System.out.println("Save to be implemented");

			JFileChooser jfc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("EasyPaint Images", "ezp");
			jfc.setFileFilter(filter);
			int state = jfc.showSaveDialog(this);
			File myFile = new File(jfc.getSelectedFile() + ".ezp");
			//File myFile = new File(jfc.getSelectedFile() + ".txt");
			if (myFile != null) myFile.delete(); //overwrite
			if(state == JFileChooser.APPROVE_OPTION) {
				//System.out.println(jfc.getSelectedFile().getAbsolutePath());
				PrintWriter filewriter;
				try {
					BufferedWriter bfw = new BufferedWriter(new FileWriter(myFile));
					filewriter = new PrintWriter(bfw);
					filewriter.println("Easy Paint by Peter Huang");
					//System.out.println("Reached printing to " + jfc.getSelectedFile().getName());

					//int len = CanvasScreen.shapesList.size(); 
					for (CanvasShape cshape : CanvasScreen.shapesList) {
						String infoLine = "";
						if (cshape instanceof CanvasLine) {
							infoLine = "CanvasLine| ";
						} else if (cshape instanceof CanvasOval) {
							infoLine = "CanvasOval| ";
						} else if (cshape instanceof CanvasRectangle) {
							infoLine = "CanvasRectangle| ";
						} else {
							continue;
						}

						//startpoint(x,y), endpoint(x,y), width, height, thickness, colour, isFilled
						String linePt2 = cshape.getStartPoint().x + "| " + cshape.getStartPoint().y + "| " +
							cshape.getEndPoint().x + "| " + cshape.getEndPoint().y + "| " + cshape.getWidth() + "| " +
							cshape.getHeight() + "| " +	cshape.getThickness() + "| " + cshape.getColour().getRGB() + 
							"| " + cshape.isFilled;

						//cshape.getColour().getRGB();

						infoLine = infoLine.concat(linePt2);
						filewriter.println(infoLine);
					}
					filewriter.println("end");
					filewriter.close();
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
