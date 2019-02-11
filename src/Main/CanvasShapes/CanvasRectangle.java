package Main.CanvasShapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CanvasRectangle extends CanvasShape {
    public Rectangle2D myRect;
    public Rectangle2D smallerRect;

    public CanvasRectangle(Point start, int widthIn, int heightIn, int thicc, Color lineColour) {
        this.type = 3;
        this.startPoint = start;
        this.width = widthIn;
        this.height = heightIn;
        this.thickness = thicc;
        this.colour = lineColour;

        this.endPoint = new Point(startPoint.x + width, startPoint.y + height);

        this.myRect = new Rectangle2D.Double(start.x - thicc/2, start.y - thicc/2, widthIn + thicc, heightIn + thicc);
        this.smallerRect = new Rectangle2D.Double(start.x + thicc, start.y + thicc, widthIn - 2*thicc, heightIn - 2*thicc);

        isFilled = false;
    }

    public CanvasRectangle(int startX, int startY, int widthIn, int heightIn, int thicc, Color lineColour) {
        this.type = 3;
        this.startPoint = new Point(startX, startY);
        this.width = widthIn;
        this.height = heightIn;
        this.thickness = thicc;
        this.colour = lineColour;

        this.endPoint = new Point(startX + width, startY + height);

        this.myRect = new Rectangle2D.Double(startX - thicc/2, startY - thicc/2, widthIn + thicc, heightIn + thicc);
        this.smallerRect = new Rectangle2D.Double(startX + thicc, startY + thicc, widthIn - 2*thicc, heightIn - 2*thicc);

        isFilled = false;
    }

    public CanvasRectangle(int startX, int startY, int widthIn, int heightIn, int thicc, Color lineColour, boolean fill) {
        this(startX, startY, widthIn, heightIn, thicc, lineColour);
        isFilled = true;
    }

    public boolean hitTestRect(int mouseX, int mouseY) { 
        Point2D mouse2DPoint = new Point2D.Double(mouseX, mouseY);

        if (myRect.contains(mouse2DPoint)) {
            if (smallerRect.contains(mouse2DPoint)) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean hitInnerRect(int mouseX, int mouseY) {
        Point2D mouse2DPoint = new Point2D.Double(mouseX, mouseY);

        if (smallerRect.contains(mouse2DPoint)) {
            return true;
        } else {
            return false;
        }
    }
}