package powerGenerationSim;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ControlCentrePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public int height = 60;
	
	public static int sliderValue = 0;
	
	public ControlCentrePanel(PowerGeneration pg, ViewPanel vp, int panelWidth, int panelHeight) {
		
		this.setSize(panelWidth, panelHeight);
		this.setLayout(null);
		this.setBackground(null);
		
		// JPanel containing stacked title and toggle buttons
		JPanel MSPanel = initMainStackPanel(panelWidth, panelHeight);
		this.add(MSPanel);
		
		JSlider windSpeedSlider = initWSSlider(pg, panelWidth, panelHeight);
		windSpeedSlider.setBounds(0,panelHeight/2 - 20,150,50);
		this.add(windSpeedSlider);
		
	}
	
	public static JPanel initMainStackPanel(int panelWidth, int panelHeight) {
		JPanel mainStackPanel = new JPanel();
		mainStackPanel.setLayout(new BoxLayout(mainStackPanel,
				BoxLayout.Y_AXIS));
		
		// Control Center Label
		JLabel title = new JLabel("CONTROL CENTRE");
		title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		mainStackPanel.add(title);
		
		// On/Off Switch
		JToggleButton onToggle = new JToggleButton("ON");
		onToggle.setSize(50,20);
		JToggleButton offToggle = new JToggleButton("OFF");
		offToggle.setSize(50,20);
		
		// Creating sub JPanel for on/off toggle buttons
		JPanel toggleButs = new JPanel();
		
		// Adding toggle buttons to sub-panel
		toggleButs.add(onToggle);
		toggleButs.add(offToggle);
		
		toggleButs.setSize(toggleButs.getMinimumSize());
		
		// Initializing toggle listener
		initToggleListener(onToggle,offToggle);
		
		// Adding sub-panel to main stack panel
		mainStackPanel.add(toggleButs);
		
		mainStackPanel.setSize(mainStackPanel.getMinimumSize());
		mainStackPanel.setLocation(panelWidth/2 - mainStackPanel.getWidth()/2,
				panelHeight / 2 - mainStackPanel.getHeight() / 2 - 10);
		
		return mainStackPanel;
	}
	
	public static void initToggleListener(JToggleButton onToggle,
			JToggleButton offToggle) {
		// Instantiate ItemListener for onToggle
		ItemListener toggleLIstener = new ItemListener() {
					
			public void itemStateChanged(ItemEvent e) {
						
				// Get state change (selected or deselected)
				int state = e.getStateChange();
						
				// Check for which button was pressed
				if (e.getSource() == onToggle) {
					// Change state of button based on state defined earlier
					onToggle.setSelected(state == ItemEvent.SELECTED);
					offToggle.setSelected(state != ItemEvent.SELECTED);
				} else {
					offToggle.setSelected(state == ItemEvent.SELECTED);
					onToggle.setSelected(state != ItemEvent.SELECTED);
				}

			}
					
		};
				
		// Attach listener to toggles
		onToggle.addItemListener(toggleLIstener);
		offToggle.addItemListener(toggleLIstener);

	}
	
	public static JSlider initWSSlider(PowerGeneration pg, int panelWidth, int panelHeight) {
		// Initializing wind speed slider
		JSlider windSpeedSlider = new JSlider();
		windSpeedSlider.setMinorTickSpacing(10);
		windSpeedSlider.setPaintTicks(true);
		windSpeedSlider.setPaintLabels(true);
		
		// Add event listener to slider
		windSpeedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// Check to see if slider is still being moved
				if(!windSpeedSlider.getValueIsAdjusting()) {
					// Assign current slider value to sliderValue variable
					sliderValue = (int)windSpeedSlider.getValue();
					pg.setWindSpeed(sliderValue);
				}
			}
		});

		
		return windSpeedSlider;
	}
	

}
