package engine;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2813600722503606037L;

	private Image bgImage;
	
	public ImagePanel(Image bg){
		bgImage = bg;
	}
	
	public Image getBgImage() {
		return bgImage;
	}

	public void setBgImage(Image bgImage) {
		this.bgImage = bgImage;
	}

	@Override
	  protected void paintComponent(Graphics g) {

		int height = this.getHeight();
		int width = this.getWidth();
		
		ImageIcon image = new ImageIcon(bgImage.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		Image i = image.getImage();
		
	    super.paintComponent(g);
	        g.drawImage(i, 0, 0, null);
	}
}
