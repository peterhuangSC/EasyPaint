package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.*;

import Main.Screens.*;

public class AppScreen extends JPanel implements ActionListener{
	private static final String COLOUR_CHOOSER = "Colour Chooser";
	private static final long serialVersionUID = 1L;

	// rows, cols, hgap, vgap
	BoxLayout bxlayout = new BoxLayout(this, BoxLayout.X_AXIS);

	ToolkitScreen toolkitPanel;
	PaletteScreen palettePanel;
	BrushScreen brushPanel;

	CanvasScreen drawingPanel;

	public static JButton chooserButton = new JButton("Chooser");

	public AppScreen() {
		setLayout(bxlayout);
		setPreferredSize(new Dimension(PainterPlist.SCREENWIDTH, PainterPlist.SCREENHEIGHT));
		setFocusable(true);
		setBackground(Color.GRAY);
		//setOpaque(false);

		setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));

		JPanel editorPanel = new JPanel();

		editorPanel.setBackground(Color.lightGray);
		//editorPanel.setOpaque(false);
		editorPanel.setPreferredSize(new Dimension(PainterPlist.SIDEBARWIDTH, PainterPlist.SCREENHEIGHT));
		add(editorPanel);
		add(drawingPanel = new CanvasScreen());
		
		JPanel chooserPanel = new JPanel();

		// editor panel
		BoxLayout innerBoxLayout = new BoxLayout(editorPanel, BoxLayout.Y_AXIS);
		editorPanel.setLayout(innerBoxLayout);

		// chooser panel
		chooserPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		chooserPanel.setBackground(Color.WHITE);
		chooserPanel.setPreferredSize(new Dimension(PainterPlist.SIDEBARWIDTH, (int) (PainterPlist.SCREENHEIGHT * 0.05)));

		// adding all of the panels
		editorPanel.add(toolkitPanel = new ToolkitScreen());
		editorPanel.add(palettePanel = new PaletteScreen());
		editorPanel.add(chooserPanel);
		editorPanel.add(brushPanel = new BrushScreen());

		// -----------------Chooser Panel-----------------//
		chooserPanel.setLayout(new GridLayout(1, 1));
		chooserPanel.add(chooserButton);
		chooserButton.setActionCommand(COLOUR_CHOOSER);
		chooserButton.addActionListener(this);
		// -----------------Chooser Panel-----------------//
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// any additional code here
	}

	public void actionPerformed(ActionEvent ae) {
		String type = ae.getActionCommand();

		System.out.print("Current Colour" + PainterPlist.getColor().toString());

		if (type.equals(COLOUR_CHOOSER)) {
			Color originalColor = PainterPlist.getColor();
			try {
				Color newColor = JColorChooser.showDialog(this, "Choose your colour", originalColor);
				PainterPlist.setColor(newColor);
			} catch (NullPointerException npe) {
				npe.printStackTrace();
				PainterPlist.setColor(originalColor);
			}

			if (PainterPlist.getColor() == null) PainterPlist.setColor(originalColor);

			chooserButton.setForeground(PainterPlist.getColor());
		} else {

		}
	}
}
