package powerGenerationSim;

import java.awt.*;
import javax.swing.JFrame;

public class MainViewController extends JFrame{

	private static final long serialVersionUID = 1L;
	
	// Creating Model Object
	PowerGeneration powerGenerationModel = new PowerGeneration();
	
	private final int CCHeight = 100;
	
	private final int width = 850, height = 500;
	
	// Initialize panels
	private ViewPanel viewPanel = new ViewPanel(powerGenerationModel);
	private ControlCentrePanel controlPanel = new ControlCentrePanel(powerGenerationModel,
			viewPanel, width, CCHeight);
	
	// Control Center Height

	public MainViewController() {
		
		// Create main frame
		this.setTitle("Power Generation Simulator");
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		
		// Center frame in screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2,
				dim.height/2-this.getSize().height/2);
		
		// Add Control Center Panel
		controlPanel.setLocation(0, height-CCHeight);
		this.add(controlPanel);
		
		// Add View Panel
		viewPanel.setBounds(0, 0, width, height - CCHeight);
		this.add(viewPanel);
		
		// Show Frame
		this.setLayout(null);
		this.setVisible(true);
		
		// Run main loop
		run(powerGenerationModel, viewPanel, controlPanel);
		
	}
	
	public static void run(PowerGeneration pg, ViewPanel vp, ControlCentrePanel cc) {
		int windSpeed = pg.windSpeed;
		while(true) {
			
			if (windSpeed != pg.windSpeed) {
				vp.changeWindSpeed(pg);
				windSpeed = pg.windSpeed;
			}
			
			vp.repaint();
		}
	}
	
}
