package powerGenerationSim;

public class PowerGeneration {
	
	// Declaring input variables
	public boolean status = false;
	public String mode = "GEN";
	public int windSpeed = 0;
	
	// Declaring output variables
	public double outputVolts = 0.0;
	public double outputAmps = 0.0;
	
	public void setWindSpeed(int newWindSpeed) {
		windSpeed = newWindSpeed;
		
	}
}
