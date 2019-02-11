package Main.CanvasShapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.CubicCurve2D;

public class CanvasOval extends CanvasShape {
    public Ellipse2D myOval;
    public Ellipse2D smallerOval;
    //public boolean isFilled;

    public CanvasOval(Point start, int widthIn, int heightIn, int thicc, Color lineColour) {
        this.type = 2;
        this.startPoint = start;
        this.width = widthIn;
        this.height = heightIn;
        this.thickness = thicc;
        this.colour = lineColour;

        this.endPoint = new Point(startPoint.x + width, startPoint.y + height);

        //bigger ellipse for thickness
        this.myOval = new Ellipse2D.Double(start.x - thicc/2, start.y - thicc/2, widthIn + thicc, heightIn + thicc);
        this.smallerOval = new Ellipse2D.Double(start.x + thicc, start.y + thicc, widthIn - 2*thicc, heightIn - 2*thicc);

        isFilled = false;
    }

    public CanvasOval(int startX, int startY, int widthIn, int heightIn, int thicc, Color lineColour) {
        this.type = 2;
        this.startPoint = new Point(startX, startY);
        this.width = widthIn;
        this.height = heightIn;
        this.thickness = thicc;
        this.colour = lineColour;

        this.endPoint = new Point(startX + width, startY + height);

        this.myOval = new Ellipse2D.Double(startX - thicc/2, startY - thicc/2, widthIn + thicc, heightIn + thicc);
        this.smallerOval = new Ellipse2D.Double(startX + thicc, startY + thicc, widthIn - 2*thicc, heightIn - 2*thicc);

        isFilled = false;
    }

    public CanvasOval(int startX, int startY, int widthIn, int heightIn, int thicc, Color lineColour, boolean fill) {
        this(startX, startY, widthIn, heightIn, thicc, lineColour);
        isFilled = true;
    }

    public boolean hitTestOval(int mouseX, int mouseY) { 
        Point2D mouse2DPoint = new Point2D.Double(mouseX, mouseY);

        if (myOval.contains(mouse2DPoint)) {
            if (smallerOval.contains(mouse2DPoint) && !isFilled) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean hitInnerOval(int mouseX, int mouseY) {
        Point2D mouse2DPoint = new Point2D.Double(mouseX, mouseY);

        if (smallerOval.contains(mouse2DPoint)) {
            return true;
        } else {
            return false;
        }
    }
}