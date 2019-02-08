package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.Desktop;
import java.awt.*;

public class AppScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	
	final int SCREENWIDTH = 1024;
	final int SCREENHEIGHT = 768;
	final int SIDEBARWIDTH = SCREENWIDTH / 5;

	//rows, cols, hgap, vgap
	GridLayout glayout = new GridLayout(3, 2, 1, 1);
	GridLayout glayout2 = new GridLayout(4, 1, 1, 1);
	BoxLayout bxlayout = new BoxLayout(this, BoxLayout.X_AXIS);

	Graphics2D g;

	public AppScreen() {
		init();
		setLayout(bxlayout);
		setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
		setFocusable(true);
		setBackground(Color.GRAY);

		setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		
		JPanel editorPanel = new JPanel();
		JPanel drawingPanel = new JPanel();
		
		editorPanel.setBackground(Color.lightGray);
		editorPanel.setPreferredSize(new Dimension(SIDEBARWIDTH, SCREENHEIGHT));
		add(editorPanel);

		drawingPanel.setBackground(Color.white);
		drawingPanel.setPreferredSize(new Dimension(SCREENWIDTH - SIDEBARWIDTH, SCREENHEIGHT));
		add(drawingPanel);

	
		JPanel toolkitPanel = new JPanel();
		JPanel palettePanel = new JPanel();
		JPanel chooserPanel = new JPanel();
		JPanel brushPanel = new JPanel();

		

		//-----------------Editor Panel-----------------//
		BoxLayout innerBoxLayout = new BoxLayout(editorPanel, BoxLayout.Y_AXIS);
		editorPanel.setLayout(innerBoxLayout);
		
		toolkitPanel.setBackground(Color.CYAN);
		toolkitPanel.setPreferredSize(new Dimension(SIDEBARWIDTH, (int) (SCREENHEIGHT * 0.35)));
		editorPanel.add(toolkitPanel);

		palettePanel.setBackground(Color.ORANGE);
		palettePanel.setPreferredSize(new Dimension(SIDEBARWIDTH, (int) (SCREENHEIGHT * 0.35)));
		editorPanel.add(palettePanel);

		chooserPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		chooserPanel.setBackground(Color.WHITE);
		chooserPanel.setPreferredSize(new Dimension(SIDEBARWIDTH, (int) (SCREENHEIGHT * 0.05)));
		editorPanel.add(chooserPanel);

		brushPanel.setBackground(Color.RED);
		brushPanel.setPreferredSize(new Dimension(SIDEBARWIDTH, (int) (SCREENHEIGHT * 0.25)));
		editorPanel.add(brushPanel);
		//-----------------Editor Panel-----------------//

		//-----------------Toolkit Panel-----------------//
		toolkitPanel.setLayout(glayout);

		/*Image img = icon.getImage("/Resources/MousePointer.png") ;  
 		Image newimg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
   		icon = new ImageIcon( newimg );*/

		JButton selectTool = new JButton(new ImageIcon("/Resources/MousePointer.png"));
		JButton eraseTool = new JButton();
		JButton drawLineTool = new JButton();
		JButton drawCircleTool = new JButton();
		JButton drawRectTool = new JButton();
		JButton fillTool = new JButton();

		toolkitPanel.add(selectTool);
		toolkitPanel.add(eraseTool);
		toolkitPanel.add(drawLineTool);
		toolkitPanel.add(drawCircleTool);
		toolkitPanel.add(drawRectTool);
		toolkitPanel.add(fillTool);

		//-----------------Toolkit Panel-----------------//

		//-----------------Chooser Panel-----------------//
		chooserPanel.setLayout(new GridLayout(1, 1));
		JButton chooserButton = new JButton("Chooser");
		chooserPanel.add(chooserButton);
		//-----------------Chooser Panel-----------------//

		//-----------------Palette Panel-----------------//
		palettePanel.setLayout(glayout);

		JButton colourButton1 = new JButton();
		colourButton1.setBackground(Color.BLUE);

		JButton colourButton2 = new JButton();
		colourButton2.setBackground(Color.RED);

		JButton colourButton3 = new JButton();
		colourButton3.setBackground(Color.ORANGE);

		JButton colourButton4 = new JButton();
		colourButton4.setBackground(Color.YELLOW);

		JButton colourButton5 = new JButton();
		colourButton5.setBackground(Color.GREEN);

		JButton colourButton6 = new JButton();
		colourButton6.setBackground(Color.PINK);

		palettePanel.add(colourButton1);
		palettePanel.add(colourButton2);
		palettePanel.add(colourButton3);
		palettePanel.add(colourButton4);
		palettePanel.add(colourButton5);
		palettePanel.add(colourButton6);
		//-----------------Palette Panel-----------------//

		//-----------------Brush Panel-----------------//
		brushPanel.setLayout(glayout2);

		//use image icons
		JButton thickness1 = new JButton("Thickness 1");
		JButton thickness2 = new JButton("Thickness 2");
		JButton thickness3 = new JButton("Thickness 3");
		JButton thickness4 = new JButton("Thickness 4");

		brushPanel.add(thickness1);
		brushPanel.add(thickness2);
		brushPanel.add(thickness3);
		brushPanel.add(thickness4);
		//-----------------Brush Panel-----------------//
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//any additional code here
	}

	public void init() {
		//running = true;
	}
}