package map;

import java.awt.Color;

public enum color {
	BLACK(Color.BLACK), YELLOW(Color.YELLOW), RED(Color.RED), ORANGE(Color.ORANGE), WHITE(Color.WHITE),
	BLUE(Color.BLUE), CYAN(Color.CYAN), GREEN(Color.GREEN);
	Color myColor;

	color(Color c) {
		myColor = c;
	}

	public Color getMyColor() {
		return myColor;
	}
}
