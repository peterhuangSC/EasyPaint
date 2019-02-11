package Main.Screens;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class BrushScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
   
    private final int BORDERSIZE = 3;

    GridLayout glayout = new GridLayout(4, 1, 1, 1);

    //Thickness buttons
	static JRadioButton thickness1 = new JRadioButton("Thickness 1");
	static JRadioButton thickness2 = new JRadioButton("Thickness 2");
	static JRadioButton thickness3 = new JRadioButton("Thickness 3");
    static JRadioButton thickness4 = new JRadioButton("Thickness 4");


    public void init() {
        //setBackground(Color.RED);
		setPreferredSize(new Dimension(PainterPlist.SIDEBARWIDTH, (int) (PainterPlist.SCREENHEIGHT * 0.25)));

        setLayout(glayout);
        initThicknessButtons();

        //use image icons

        add(thickness1);
        add(thickness2);
        add(thickness3);
        add(thickness4);
    }


    public BrushScreen() {
        init();
    }


    public void initThicknessButtons() {
		ImageIcon thick1xImg = new ImageIcon();
		ImageIcon thick2xImg = new ImageIcon();
		ImageIcon thick3xImg = new ImageIcon();
		ImageIcon thick4xImg = new ImageIcon();
	
		try {
			thick1xImg = new ImageIcon("src/Resources/line1x.png");
			thick2xImg = new ImageIcon("src/Resources/line2x.png");
			thick3xImg = new ImageIcon("src/Resources/line3x.png");
			thick4xImg = new ImageIcon("src/Resources/line4x.png");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		thickness1 = new JRadioButton(thick1xImg);
		thickness1.setBorderPainted(true);

		thickness2 = new JRadioButton(thick2xImg);
		thickness2.setBorderPainted(true);

		thickness3 = new JRadioButton(thick3xImg);
		thickness3.setBorderPainted(true);

		thickness4 = new JRadioButton(thick4xImg);
        thickness4.setBorderPainted(true);

        clearBorders("none");
        
        thickness1.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));
		thickness2.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));
		thickness3.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));
		thickness4.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));

		thickness1.setActionCommand("Thickness 1x");
		thickness2.setActionCommand("Thickness 2x");
		thickness3.setActionCommand("Thickness 3x");
        thickness4.setActionCommand("Thickness 4x");

		thickness1.addActionListener(this);
		thickness2.addActionListener(this);
		thickness3.addActionListener(this);
		thickness4.addActionListener(this);

		ButtonGroup thicknessButtons = new ButtonGroup();
		thicknessButtons.add(thickness1);
		thicknessButtons.add(thickness2);
		thicknessButtons.add(thickness3);
		thicknessButtons.add(thickness4);
	}


    public static void clearBorders(String paintSpecific) {
		thickness1.setBorderPainted(false);
		thickness2.setBorderPainted(false);
		thickness3.setBorderPainted(false);
		thickness4.setBorderPainted(false);

		if (paintSpecific.equals("Thickness 1x")) thickness1.setBorderPainted(true);
		else if (paintSpecific.equals("Thickness 2x")) thickness2.setBorderPainted(true);
		else if (paintSpecific.equals("Thickness 3x")) thickness3.setBorderPainted(true);
		else if (paintSpecific.equals("Thickness 4x")) thickness4.setBorderPainted(true);
		else System.out.println("No colour selected");
	}


    public void actionPerformed(ActionEvent ae) {
        String type = ae.getActionCommand();
        System.out.println(type);

        if (type.equals("Thickness 1x")) {
            clearBorders(type);
            PainterPlist.setThickness(1);
		} else if (type.equals("Thickness 2x")) {
            clearBorders(type);
            PainterPlist.setThickness(3);
		} else if (type.equals("Thickness 3x")) {
            clearBorders(type);
            PainterPlist.setThickness(9);
		} else if (type.equals("Thickness 4x")) {
            clearBorders(type);
            PainterPlist.setThickness(27);
		} else {

		}
    }
}