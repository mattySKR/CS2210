import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Board extends JComponent {

	public Board() {
	}

	public void paint(Graphics display) {
		display.setColor(Color.lightGray);
	}
}
