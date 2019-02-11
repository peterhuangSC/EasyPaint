package Main.Screens;

import javax.swing.*;

import Main.AppScreen;

import java.awt.event.*;
import java.awt.*;

public class PaletteScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
  
    private final int BORDERSIZE = 5;

    GridLayout glayout = new GridLayout(3, 2, 1, 1);

    //Colour buttons
	static JButton colourButton1 = new JButton();
	static JButton colourButton2 = new JButton();
	static JButton colourButton3 = new JButton();
	static JButton colourButton4 = new JButton();
	static JButton colourButton5 = new JButton();
    static JButton colourButton6 = new JButton();

    public void init() {
        //setBackground(Color.ORANGE);
        //setOpaque(false);
		setPreferredSize(new Dimension(PainterPlist.SIDEBARWIDTH, (int) (PainterPlist.SCREENHEIGHT * 0.35)));
        
        setLayout(glayout);
        
        initColourButtons();

		add(colourButton1);
		add(colourButton2);
		add(colourButton3);
		add(colourButton4);
		add(colourButton5);
		add(colourButton6);
    }


    public PaletteScreen() {
        init();
    }


    public void initColourButtons() {
		//make sure colour is displayed correctly! right now it's not as a NORMAL button
        colourButton1.setBackground(Color.BLUE);
		colourButton2.setBackground(Color.RED);
		colourButton3.setBackground(Color.ORANGE);
		colourButton4.setBackground(Color.YELLOW);
		colourButton5.setBackground(Color.GREEN);
        colourButton6.setBackground(Color.PINK);
        
        colourButton1.setOpaque(true);
        colourButton2.setOpaque(true);
        colourButton3.setOpaque(true);
        colourButton4.setOpaque(true);
        colourButton5.setOpaque(true);
        colourButton6.setOpaque(true);

		colourButton1.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));
		colourButton2.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));
		colourButton3.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));
		colourButton4.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));
		colourButton5.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));
		colourButton6.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDERSIZE));

		clearColourBorders("none");

		colourButton1.setActionCommand("Colour Blue");
		colourButton2.setActionCommand("Colour Red");
		colourButton3.setActionCommand("Colour Orange");
		colourButton4.setActionCommand("Colour Yellow");
		colourButton5.setActionCommand("Colour Green");
		colourButton6.setActionCommand("Colour Pink");

		colourButton1.addActionListener(this);
		colourButton2.addActionListener(this);
		colourButton3.addActionListener(this);
		colourButton4.addActionListener(this);
		colourButton5.addActionListener(this);
		colourButton6.addActionListener(this);

		//repaint();
    }

    
    public static void clearColourBorders(String paintSpecific) {
		colourButton1.setBorderPainted(false);
		colourButton2.setBorderPainted(false);
		colourButton3.setBorderPainted(false);
		colourButton4.setBorderPainted(false);
		colourButton5.setBorderPainted(false);
		colourButton6.setBorderPainted(false);

		if (paintSpecific.equals("Colour Blue")) colourButton1.setBorderPainted(true);
		else if (paintSpecific.equals("Colour Red")) colourButton2.setBorderPainted(true);
		else if (paintSpecific.equals("Colour Orange")) colourButton3.setBorderPainted(true);
		else if (paintSpecific.equals("Colour Yellow")) colourButton4.setBorderPainted(true);
		else if (paintSpecific.equals("Colour Green")) colourButton5.setBorderPainted(true);
		else if (paintSpecific.equals("Colour Pink")) colourButton6.setBorderPainted(true);
		else System.out.println("No colour selected");
	}


    public void actionPerformed(ActionEvent ae) {
        String type = ae.getActionCommand();

        if (type.equals("Colour Blue")) {
            clearColourBorders(type);
            PainterPlist.setColor(Color.BLUE);
            AppScreen.chooserButton.setForeground(Color.BLUE);
		} else if (type.equals("Colour Red")) {
            clearColourBorders(type);
            PainterPlist.setColor(Color.RED);
            AppScreen.chooserButton.setForeground(Color.RED);
		} else if (type.equals("Colour Orange")) {
            clearColourBorders(type);
            PainterPlist.setColor(Color.ORANGE);
            AppScreen.chooserButton.setForeground(Color.ORANGE);
		} else if (type.equals("Colour Yellow")) {
            clearColourBorders(type);
            PainterPlist.setColor(Color.YELLOW);
            AppScreen.chooserButton.setForeground(Color.YELLOW);
		} else if (type.equals("Colour Green")) {
            clearColourBorders(type);
            PainterPlist.setColor(Color.GREEN);
            AppScreen.chooserButton.setForeground(Color.GREEN);
		} else if (type.equals("Colour Pink")) {
            clearColourBorders(type);
            PainterPlist.setColor(Color.PINK);
            AppScreen.chooserButton.setForeground(Color.PINK);
		} else {

        }
    }
}