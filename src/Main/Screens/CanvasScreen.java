package Main.Screens;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import Main.AppScreen;
import Main.CanvasShapes.*;


public class CanvasScreen extends JPanel implements MouseListener, MouseMotionListener { //, KeyListener{

    private static final long serialVersionUID = 1L;
    
    Graphics2D g;

    private int startX, startY, currentX, currentY;//, endX, endY = 0;

    String myTool = "none";
    Color myColor = Color.BLACK;
    int thickness = 1;

    CanvasShape selectedItem;
    int selectedItemIndex = -1;

    public static List<CanvasShape> shapesList = new ArrayList<CanvasShape>();

    private static final String ESC = "escape";
    private Action escape = new AbstractAction(ESC) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(ESC);
        }
    };

    private class Unselect extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            setUnselected();
            System.out.println("escaped!!");
            repaint();
        }
    }

    public CanvasScreen() {
        init();
    }

    public void init() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(PainterPlist.SCREENWIDTH - PainterPlist.SIDEBARWIDTH, PainterPlist.SCREENHEIGHT));
        
        addMouseListener(this);
        addMouseMotionListener(this);

        this.requestFocusInWindow();
        this.setFocusable(true);
        this.requestFocus();
        //addKeyListener(this);

        selectedItem = null;

        //this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), ESC);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), ESC);
        this.getActionMap().put(ESC, new Unselect());
    }

    public void setUnselected() {
        PainterPlist.isSelected = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String myTool = PainterPlist.getTool();
        System.out.println("mouse pressed, current tool: " + myTool);

        try {
            currentX = e.getX();
            currentY = e.getY();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        String myTool = PainterPlist.getTool();
        System.out.println("mouse pressed, current tool: " + myTool);

        try {
            startX = e.getX();
            startY = e.getY();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        if (myTool.equals("Select Tool")) {
            //if start x and start y is in for each contains
            int listLen = shapesList.size();
            for (int i = listLen - 1; i >= 0; i--) {
                if (shapesList.get(i) instanceof CanvasLine) {
                    boolean hitTest = shapesList.get(i).hitTestLine(startX, startY);
                    if (hitTest == true) {
                        selectedItem = shapesList.get(i);
                        selectedItemIndex = i;
                        PainterPlist.isSelected = true;
                        PainterPlist.setThickness(selectedItem.getThickness());
                        BrushScreen.clearBorders(matchThickness(PainterPlist.getThickness()));
                        PainterPlist.setColor(selectedItem.getColour());
                        PaletteScreen.clearColourBorders(matchColour(PainterPlist.getColor()));
                        AppScreen.chooserButton.setForeground(PainterPlist.getColor());
                        break;
                    }
                } else if (shapesList.get(i) instanceof CanvasOval) {
                    boolean hitTest = false;
                    if (shapesList.get(i).isFilled == true) {
                        hitTest = shapesList.get(i).hitInnerOval(startX, startY);
                    } else {
                        hitTest = shapesList.get(i).hitTestOval(startX, startY);
                    }
                    if (hitTest == true) {
                        selectedItem = shapesList.get(i);
                        selectedItemIndex = i;
                        PainterPlist.isSelected = true;
                        PainterPlist.setThickness(selectedItem.getThickness());
                        BrushScreen.clearBorders(matchThickness(PainterPlist.getThickness()));
                        PainterPlist.setColor(selectedItem.getColour());
                        PaletteScreen.clearColourBorders(matchColour(PainterPlist.getColor()));
                        AppScreen.chooserButton.setForeground(PainterPlist.getColor());
                        break;
                    }
                } else if (shapesList.get(i) instanceof CanvasRectangle) {
                    boolean hitTest = false;
                    if (shapesList.get(i).isFilled == true) {
                        hitTest = shapesList.get(i).hitInnerRect(startX, startY);
                    } else {
                        hitTest = shapesList.get(i).hitTestRect(startX, startY);
                    }
                    if (hitTest == true) {
                        selectedItem = shapesList.get(i);
                        selectedItemIndex = i;
                        PainterPlist.isSelected = true;
                        PainterPlist.setThickness(selectedItem.getThickness());
                        BrushScreen.clearBorders(matchThickness(PainterPlist.getThickness()));
                        PainterPlist.setColor(selectedItem.getColour());
                        PaletteScreen.clearColourBorders(matchColour(PainterPlist.getColor()));
                        AppScreen.chooserButton.setForeground(PainterPlist.getColor());
                        break;
                    }
                }
                PainterPlist.isSelected = false;
            }
        } else if (myTool.equals("Fill Tool")) {
            int listLen = shapesList.size();
            for (int i = listLen - 1; i >= 0; i--) {
                if (shapesList.get(i) instanceof CanvasOval) {
                    boolean hitTest = shapesList.get(i).hitInnerOval(startX, startY);
                    if (hitTest == true) {
                        //fill in the inner
                        CanvasOval filler = new CanvasOval((int)shapesList.get(i).getStartPoint().getX(),
                                                           (int)shapesList.get(i).getStartPoint().getY(),
                                                           (int)shapesList.get(i).getWidth(),
                                                           (int)shapesList.get(i).getHeight(),
                                                           PainterPlist.getThickness(),
                                                           PainterPlist.getColor(), true);
                        
                        shapesList.add(i + 1, filler);
                        break;
                    }
                } else if (shapesList.get(i) instanceof CanvasRectangle) {
                    boolean hitTest = shapesList.get(i).hitInnerRect(startX, startY);
                    if (hitTest == true) {
                        //fill in the inner
                        CanvasRectangle filler = new CanvasRectangle((int)shapesList.get(i).getStartPoint().getX(),
                                                                     (int)shapesList.get(i).getStartPoint().getY(),
                                                                     (int)shapesList.get(i).getWidth(),
                                                                     (int)shapesList.get(i).getHeight(),
                                                                     PainterPlist.getThickness(),
                                                                     PainterPlist.getColor(), true);
                        
                        shapesList.add(i + 1, filler);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        String myTool = PainterPlist.getTool(); //consider making a separate static variable class
        //System.out.println("mouse dragged, current tool: " + myTool);

        try {
            currentX = e.getX();
            currentY = e.getY();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        //Erase Tool
        if (myTool.equals("Erase Tool")) {
            int listLen = shapesList.size();
            for (int i = listLen - 1; i >= 0; i--) {
                if (shapesList.get(i) instanceof CanvasLine) {
                    boolean hitTest = shapesList.get(i).hitTestLine(currentX, currentY);
                    if (hitTest == true) {
                        shapesList.remove(i);
                    } 
                } else if (shapesList.get(i) instanceof CanvasOval) {
                    boolean hitTest = shapesList.get(i).hitTestOval(currentX, currentY);
                    if (hitTest == true) {
                        shapesList.remove(i);
                    } 
                } else if (shapesList.get(i) instanceof CanvasRectangle) {
                    boolean hitTest = shapesList.get(i).hitTestRect(currentX, currentY);
                    if (hitTest == true) {
                        shapesList.remove(i);
                    } 
                }
                if (i < 0) break;
            }
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String myTool = PainterPlist.getTool(); //consider making a separate static variable class
        System.out.println("mouse dragged, current tool: " + myTool);

        try {
            currentX = e.getX();
            currentY = e.getY();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        if (PainterPlist.getTool().equals("Draw Line Tool")) {
            CanvasShape cLine = new CanvasLine(startX, startY, currentX, currentY, thickness, myColor);
            shapesList.add(cLine);
        } else if (PainterPlist.getTool().equals("Draw Circle Tool")) {
            CanvasShape cOval = new CanvasOval(Math.min(startX, currentX), Math.min(startY, currentY), 
                                               Math.abs(currentX - startX), Math.abs(currentY - startY), 
                                               thickness, myColor);
            shapesList.add(cOval);
        } else if (PainterPlist.getTool().equals("Draw Rectangle Tool")) {
            CanvasShape cRect = new CanvasRectangle(Math.min(startX, currentX), Math.min(startY, currentY), 
                                                    Math.abs(currentX - startX), Math.abs(currentY - startY), 
                                                    thickness, myColor);
            shapesList.add(cRect);
        }

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //on mouse pressed and on mouse released
    //mouse action listener

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        g = (Graphics2D) g1;

        myColor = PainterPlist.getColor();
        thickness = PainterPlist.getThickness();
        if (myColor == null) myColor = Color.CYAN; //fixed testing

        //pre-draw previous images and shapes

        int counter = 0;
        for (CanvasShape currentShape : shapesList) {
            g.setColor(currentShape.getColour());
            g.setStroke(new BasicStroke(currentShape.getThickness()));

            if (currentShape instanceof CanvasLine) { //LINE---------------------------------------------
                if (selectedItemIndex == counter && PainterPlist.isSelected) {
                    g.setColor(new Color(26, 255, 255));
                    g.setStroke(new BasicStroke((int)(currentShape.getThickness() * 1.3 + 2)));

                    //to show the line is selected
                    g.drawLine(currentShape.getStartPoint().x, currentShape.getStartPoint().y, 
                               currentShape.getEndPoint().x, currentShape.getEndPoint().y);

                    g.setColor(currentShape.getColour());
                    g.setStroke(new BasicStroke(currentShape.getThickness()));
                }

                g.drawLine(currentShape.getStartPoint().x, currentShape.getStartPoint().y, 
                           currentShape.getEndPoint().x, currentShape.getEndPoint().y);

            } else if (currentShape instanceof CanvasOval) { //OVAL--------------------------------------
                if (selectedItemIndex == counter && PainterPlist.isSelected) {
                    g.setColor(new Color(26, 255, 255));
                    g.setStroke(new BasicStroke((int)(currentShape.getThickness() * 1.3 + 2)));

                    //to show the oval is selected
                    g.drawOval(currentShape.getStartPoint().x, currentShape.getStartPoint().y, 
                           currentShape.getWidth(), currentShape.getHeight());

                    g.setColor(currentShape.getColour());
                    g.setStroke(new BasicStroke(currentShape.getThickness()));
                }
                
                if (currentShape.isFilled == false) {
                    g.drawOval(currentShape.getStartPoint().x, currentShape.getStartPoint().y, 
                            currentShape.getWidth(), currentShape.getHeight());
                } else {
                    g.fillOval(currentShape.getStartPoint().x, currentShape.getStartPoint().y, 
                            currentShape.getWidth(), currentShape.getHeight());
                }

            } else if (currentShape instanceof CanvasRectangle) { //RECT---------------------------------
                if (selectedItemIndex == counter && PainterPlist.isSelected) {
                    g.setColor(new Color(26, 255, 255));
                    g.setStroke(new BasicStroke((int)(currentShape.getThickness() * 1.3 + 2)));

                    //to show the rect is selected
                    g.drawRect(currentShape.getStartPoint().x, currentShape.getStartPoint().y, 
                           currentShape.getWidth(), currentShape.getHeight());

                    g.setColor(currentShape.getColour());
                    g.setStroke(new BasicStroke(currentShape.getThickness()));
                }

                if (currentShape.isFilled == false) {
                    g.drawRect(currentShape.getStartPoint().x, currentShape.getStartPoint().y, 
                               currentShape.getWidth(), currentShape.getHeight());
                } else {
                    g.fillRect(currentShape.getStartPoint().x, currentShape.getStartPoint().y, 
                               currentShape.getWidth(), currentShape.getHeight());
                }

            } else {
                //not a supported shape type, no draw
            }
            counter++;
        }
        
        if (PainterPlist.getTool().equals("Draw Line Tool")) {
            g.setColor(myColor);
            g.setStroke(new BasicStroke(thickness));
            g.drawLine(startX, startY, currentX, currentY); 
        } else if (PainterPlist.getTool().equals("Draw Circle Tool")) {
            g.setColor(myColor);
            g.setStroke(new BasicStroke(thickness));
            //g.drawOval(startX, startY, currentX - startX, currentY - startY);

            g.drawOval(Math.min(startX, currentX), Math.min(startY, currentY), 
                       Math.abs(currentX - startX), Math.abs(currentY - startY));
        } else if (PainterPlist.getTool().equals("Draw Rectangle Tool")) {
            g.setColor(myColor);
            g.setStroke(new BasicStroke(thickness));
            //g.drawRect(startX, startY, currentX - startX, currentY - startY);

            g.drawRect(Math.min(startX, currentX), Math.min(startY, currentY), 
                       Math.abs(currentX - startX), Math.abs(currentY - startY));
        } 
    }


    //MARK: Key listeners
    // @Override
    // public void keyTyped(KeyEvent e) {

    // }

    // @Override
    // public void keyPressed(KeyEvent ke) {
    //     //wrap this in a conditional for select tool?
    //     System.out.println("kEYPRESSSED " + PainterPlist.isSelected);
    //     if (PainterPlist.isSelected) {
    //         PainterPlist.isSelected = false;
    //     }
    //     System.out.println("kEYPRESSSED after " + PainterPlist.isSelected);
    // }

    // @Override
    // public void keyReleased(KeyEvent ke) {
    //     if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
    //         System.out.println("kEYPRESSSED " + PainterPlist.isSelected);
    //     if (PainterPlist.isSelected) {
    //         PainterPlist.isSelected = false;
    //     }
    //     System.out.println("kEYPRESSSED after " + PainterPlist.isSelected);
    //     }
    // }

    public String matchColour(Color inColour) {
        if (inColour == Color.BLUE) {
            return "Colour Blue";
        } else if (inColour == Color.RED) {
            return "Colour Red";
        } else if (inColour == Color.ORANGE) {
            return "Colour Orange";
        } else if (inColour == Color.YELLOW) {
            return "Colour Yellow";
        } else if (inColour == Color.GREEN) {
            return "Colour Green";
        } else if (inColour == Color.PINK) {
            return "Colour Pink";
        } else {
            return "none";
        }
    }

    public String matchThickness(int thick) {
        if (thick == 1) {
            return "Thickness 1x";
        } else if (thick == 3) {
            return "Thickness 2x";
        } else if (thick == 9) {
            return "Thickness 3x";
        } else if (thick == 27) {
            return "Thickness 4x";
        } else {
            return "none";
        }
    }
}

//when ESC is invoked selected is false
