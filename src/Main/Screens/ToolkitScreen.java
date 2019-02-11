package Main.Screens;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class ToolkitScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    GridLayout glayout = new GridLayout(3, 2, 1, 1);

    //Toolkit buttons
	JRadioButton selectTool = new JRadioButton();
	JRadioButton eraseTool = new JRadioButton();
	JRadioButton drawLineTool = new JRadioButton();
	JRadioButton drawCircleTool = new JRadioButton();
	JRadioButton drawRectTool = new JRadioButton();
    JRadioButton fillTool = new JRadioButton();
    
    ButtonGroup toolkitButtons = new ButtonGroup();

    public void init() {
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(PainterPlist.SIDEBARWIDTH, (int) (PainterPlist.SCREENHEIGHT * 0.35)));
        
		setLayout(glayout);

        initToolkitButtons();

		add(selectTool);
		add(eraseTool);
		add(drawLineTool);
		add(drawCircleTool);
		add(drawRectTool);
		add(fillTool);
    }

    public ToolkitScreen() {
        init();
    }

    public void initToolkitButtons() {
		ImageIcon mousePointerImg = new ImageIcon();
		ImageIcon eraserImg = new ImageIcon();
		ImageIcon drawlineImg = new ImageIcon();
		ImageIcon drawcircleImg = new ImageIcon();
		ImageIcon drawrectImg = new ImageIcon();
		ImageIcon paintBucketImg = new ImageIcon();

		//ImageIcon mousePointerImgSel = new ImageIcon();

		try {
			mousePointerImg = new ImageIcon("src/Resources/MousePointer3.png");
			eraserImg = new ImageIcon("src/Resources/Eraser.png");
			drawlineImg = new ImageIcon("src/Resources/line.png");
			drawcircleImg = new ImageIcon("src/Resources/circle.png");
			drawrectImg = new ImageIcon("src/Resources/rectangle.png");
			paintBucketImg = new ImageIcon("src/Resources/paintbucket.png");

			//mousePointerImgSel = new ImageIcon("src/Resources/MousePointerSelected.png");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		selectTool = new JRadioButton(mousePointerImg);
		selectTool.setBorderPainted(true);
		selectTool.setAlignmentX(Component.CENTER_ALIGNMENT);

		eraseTool = new JRadioButton(eraserImg);
		eraseTool.setBorderPainted(true);

		drawLineTool = new JRadioButton(drawlineImg);
		drawLineTool.setBorderPainted(true);

		drawCircleTool = new JRadioButton(drawcircleImg);
		drawCircleTool.setBorderPainted(true);

		drawRectTool = new JRadioButton(drawrectImg);
		drawRectTool.setBorderPainted(true);

		fillTool = new JRadioButton(paintBucketImg);
		fillTool.setBorderPainted(true);

		selectTool.setActionCommand("Select Tool");
		eraseTool.setActionCommand("Erase Tool");
		drawLineTool.setActionCommand("Draw Line Tool");
		drawCircleTool.setActionCommand("Draw Circle Tool");
		drawRectTool.setActionCommand("Draw Rectangle Tool");
		fillTool.setActionCommand("Fill Tool");

		selectTool.addActionListener(this);
		eraseTool.addActionListener(this);
		drawLineTool.addActionListener(this);
		drawCircleTool.addActionListener(this);
		drawRectTool.addActionListener(this);
		fillTool.addActionListener(this);
		
		toolkitButtons.add(selectTool);
		toolkitButtons.add(eraseTool);
		toolkitButtons.add(drawLineTool);
		toolkitButtons.add(drawCircleTool);
		toolkitButtons.add(drawRectTool);
		toolkitButtons.add(fillTool);
	}


	//MARK: Action section
    public void actionPerformed(ActionEvent ae) {
        String type = ae.getActionCommand();
		System.out.println(type);

		PainterPlist.setTool(type);

        if (type.equals("Select Tool")) {
			System.out.println("Select tool selected!");

		} else if (type.equals("Erase Tool")) {
			PainterPlist.isSelected = false;
			
		} else if (type.equals("Draw Line Tool")) {
			PainterPlist.isSelected = false;
		
		} else if (type.equals("Draw Circle Tool")) {
			PainterPlist.isSelected = false;
		
		} else if (type.equals("Draw Rectangle Tool")) {
			PainterPlist.isSelected = false;
		
		} else if (type.equals("Fill Tool")) {
			PainterPlist.isSelected = false;

			//set up disabling later?
			//can fill rectangles and ovals, not lines
			//probably drawRect but fill, so if hittestinternal, fill it in with the internal hit test coordinates
			//insert within the arrayList at correct places so stacked works - do this 1 behind the normal border rect/oval

		} else {
			PainterPlist.setTool("no tool selected");
			PainterPlist.isSelected = false;
        }
	}
	

}