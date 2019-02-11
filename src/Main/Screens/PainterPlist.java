
package Main.Screens;

import java.awt.Color;

public class PainterPlist {

    public final static int SCREENWIDTH = 1024;
	public final static int SCREENHEIGHT = 768;
	public final static int SIDEBARWIDTH = SCREENWIDTH / 5;

    private static String myTool = "none";
    private static Color myColor = Color.BLACK;
    private static int myThickness = 1;

    public static boolean isSelected = false;
    
    public static String getTool() {
        return myTool;
    }

    public static Color getColor() {
        return myColor;
    }

    public static int getThickness() {
        return myThickness;
    }

    public static void setTool(String tool) {
        myTool = tool;
    }

    public static void setColor(Color colour) {
        myColor = colour;
    }

    public static void setThickness(int thickness) {
        myThickness = thickness;
    }

    public PainterPlist () {

    }
}