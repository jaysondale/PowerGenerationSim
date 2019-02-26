package powerGenerationSim;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ViewPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	
	private static int windSpeed = 0;
	
	private static double rotationAngle = Math.toRadians(5);

	public ViewPanel(PowerGeneration pg) {
		this.setBackground(Color.white);
		this.setVisible(true);
		
		// Get windmill blades image
		try {
			image = ImageIO.read(new File((this.getClass().getResource("/imgs/blades.jpg")).getFile()));
		} catch (IOException ex) {
			System.out.println("File not found");
		}
		
		windSpeed = pg.windSpeed;
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		if (windSpeed > 0) {
			AffineTransform tx = AffineTransform.getRotateInstance(rotationAngle,
					image.getWidth()/2, image.getHeight()/2);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			g.drawImage(op.filter(image, null), 20, 10, null);
			System.out.println(rotationAngle);
			if (rotationAngle >= Math.PI * 2) {
				rotationAngle = 0;
			}
			rotationAngle += Math.toRadians(5);
			try {
				Thread.sleep(1000/(windSpeed));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			g.drawImage(image, 20, 10, this);
			rotationAngle = 0;
		}
	}
	
	public void changeWindSpeed(PowerGeneration pg) {
		windSpeed = pg.windSpeed;
	}
	
}
