package Main.CanvasShapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class CanvasLine extends CanvasShape {
    public Line2D myLine;

    public CanvasLine(Point start, Point end, int thicc, Color lineColour) {
        this.type = 1;

        this.startPoint = start;
        this.endPoint = end;
        this.thickness = thicc;
        this.colour = lineColour;

        this.myLine = new Line2D.Double(startPoint, endPoint);
    }

    public CanvasLine(int startX, int startY, int endX, int endY, int thicc, Color lineColour) {
        this.type = 1;

        this.startPoint = new Point(startX, startY);
        this.endPoint = new Point(endX, endY);
        this.thickness = thicc;
        this.colour = lineColour;

        this.myLine = new Line2D.Double(startPoint, endPoint);
    }


    //line's equation
    //y = mx + b
    //m = (y2-y1)/(x2-x1)

    // get distance using Java2D method
    //double d2 = Line2D.ptSegDist(P0.x, P0.y, P1.x, P1.y, M.x, M.y);

    public boolean hitTestLine(int mouseX, int mouseY) {
        Point2D mouse2DPoint = new Point2D.Double(mouseX, mouseY);

        double ptDistance = myLine.ptSegDist(mouse2DPoint);
        System.out.println("Distance to line: " + ptDistance);
        if (ptDistance <= thickness) {
            return true;
        } else {
            return false;
        }
    }
    
}