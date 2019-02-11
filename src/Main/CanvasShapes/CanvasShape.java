package Main.CanvasShapes;

import java.awt.Color;
import java.awt.Point;

public class CanvasShape {

    protected Integer type = 0;

    protected int thickness = 1;
    protected Point startPoint = new Point(0, 0);
    protected Point endPoint = new Point(0, 0);
    protected Color colour = Color.BLACK;

    //for ovals and rectangles
    protected int width = 0;
    protected int height = 0;

    public boolean isFilled = false;

    public CanvasShape () {

    }

    public Integer getType() {
        return type;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thicc) {
        thickness = thicc;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point sp) {
        startPoint = sp;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point ep) {
        endPoint = ep;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color c) {
        colour = c;
    }

    //ovals and rectangles
    public int getHeight() { return height; }
    public void setHeight(int h) { height = h; }
    public int getWidth() { return width; }
    public void setWidth(int w) { width = w; }

    public boolean hitTestLine(int mouseX, int mouseY) { return false; }
    public boolean hitTestOval(int mouseX, int mouseY) { return false; }
    public boolean hitTestRect(int mouseX, int mouseY) { return false; }

    public boolean hitInnerOval(int mouseX, int mouseY) { return false; }
    public boolean hitInnerRect(int mouseX, int mouseY) { return false; }

}