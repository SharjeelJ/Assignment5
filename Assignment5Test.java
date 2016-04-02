/**
 * Assignment 4
 * Course: CPSC 233
 * Prof: Manzara
 * UCID: 30002229 & 30008424
 * TA session: 03
 * @author Asjad Hassan Malick & Sharjeel Junaid
 */

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class Assignment5Test 
{
	public static void main(String args[])
	{
		//THE COLOR CONSTANTS
		final Color RED = new Color(188,0,0);
		final Color GREEN = new Color(45,189,16);
		final Color TEMP = new Color(102,102,255);
		final Color SOIL = new Color(255,153,51);
		final Color HUMID = new Color(69,167,174);
		final Color GREY = new Color(195,195,195);
		
		//GUI FRAME SETUP
		final JFrame frame = new JFrame("GREENHOUSE SIMULATOR");
		
		//GUI Panel setup
		final JTabbedPane panel = new JTabbedPane();
		
		//Contains the active labels, tells which system is active
		final JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(4,1));
		
		//To be put on second tab panel, will have ranges
		final JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(3,1));
		
		//Tab for initial values
		final JPanel firstTabPanel = new JPanel();
		firstTabPanel.setLayout(new BorderLayout());
		
		//Tab that contains basically everything else
		final JPanel secondTabPanel = new JPanel();
		secondTabPanel.setLayout(new BorderLayout());
		
		//**********************************************
		//WEST PANEL
		//**********************************************
		
		//Create the label for the furnace, make it red
		final JLabel furnaceActivatedLabel = new JLabel(" FURNACE: NOT ACTIVE");
		furnaceActivatedLabel.setOpaque(true);
		furnaceActivatedLabel.setBackground(RED);
		
		//Create the label for the air conditioner (AC), make it red
		final JLabel ACActivatedLabel = new JLabel(" AIR CONDITIONER: NOT ACTIVE");
		ACActivatedLabel.setOpaque(true);
		ACActivatedLabel.setBackground(RED);
		
		//Create the label for the humidifier, make it red
		final JLabel humidActivatedLabel = new JLabel(" HUMIDIFIER: NOT ACTIVE");
		humidActivatedLabel.setOpaque(true);
		humidActivatedLabel.setBackground(RED);
		
		//Create the label for the sprinkler, make it red
		final JLabel sprinklerActivatedLabel = new JLabel(" SPRINKLER: NOT ACTIVE");
		sprinklerActivatedLabel.setOpaque(true);
		sprinklerActivatedLabel.setBackground(RED);
		
		//ADD THE LABELS TO THE PANEL
		westPanel.add(furnaceActivatedLabel);
		westPanel.add(ACActivatedLabel);
		westPanel.add(humidActivatedLabel);
		westPanel.add(sprinklerActivatedLabel);
		
		//**********************************************
		//NORTH PANEL
		//**********************************************
		
		//Temperature settings
		final JPanel temperaturePanel = new JPanel();
		temperaturePanel.setLayout(new GridLayout(2,1));
		final JLabel tempLabel = new JLabel("Temperature Settings");
		tempLabel.setOpaque(true);
		tempLabel.setBackground(TEMP);
		
		final JPanel temperatureSettings = new JPanel();
		temperatureSettings.setLayout(new GridLayout(1,3));
		
		final JLabel tempRangeLabel = new JLabel("Target Temperature: ");
		tempRangeLabel.setOpaque(true);
		tempRangeLabel.setBackground(TEMP);
		
		final JTextField tempRangeField = new JTextField(2);
		
		final JLabel tempPlusMin = new JLabel("+ or -   3 degrees Celsius");
		tempPlusMin.setOpaque(true);
		tempPlusMin.setBackground(TEMP);
		
		temperatureSettings.add(tempRangeLabel);
		temperatureSettings.add(tempRangeField);
		temperatureSettings.add(tempPlusMin);
		
		temperaturePanel.add(tempLabel);
		temperaturePanel.add(temperatureSettings);
		
		
		// Soil settings
		final JPanel soilPanel = new JPanel();
		soilPanel.setLayout(new GridLayout(2,1));
		final JLabel soilLabel = new JLabel("Soil Moisture Settings");
		soilLabel.setOpaque(true);
		soilLabel.setBackground(SOIL);
		
		final JPanel soilSettings = new JPanel();
		soilSettings.setLayout(new GridLayout(1,5));
		
		final JLabel soilRangeLabel = new JLabel("Target Moisture: ");
		soilRangeLabel.setOpaque(true);
		soilRangeLabel.setBackground(SOIL);
		
		final JTextField soilRangeField = new JTextField(2);
		final JTextField soilRangeField2 = new JTextField(2);
		
		final JLabel soilPer1 = new JLabel("%          to ");
		soilPer1.setOpaque(true);
		soilPer1.setBackground(SOIL);
		
		final JLabel soilPer2 = new JLabel("%");
		soilPer2.setOpaque(true);
		soilPer2.setBackground(SOIL);
		
		soilSettings.add(soilRangeLabel);
		soilSettings.add(soilRangeField);
		soilSettings.add(soilPer1);
		soilSettings.add(soilRangeField2);
		soilSettings.add(soilPer2);
		
		soilPanel.add(soilLabel);
		soilPanel.add(soilSettings);
		
		
		//Humidity Settings
		final JPanel humidPanel = new JPanel();
		humidPanel.setLayout(new GridLayout(2,1));
		final JLabel humidLabel = new JLabel("Humidity Settings");
		humidLabel.setOpaque(true);
		humidLabel.setBackground(HUMID);
		
		final JPanel humidSettings = new JPanel();
		humidSettings.setLayout(new GridLayout(1,5));
		
		final JLabel humidRangeLabel = new JLabel("Target Humidity: ");
		humidRangeLabel.setOpaque(true);
		humidRangeLabel.setBackground(HUMID);
		
		final JTextField humidRangeField = new JTextField(2);
		final JTextField humidRangeField2 = new JTextField(2);
		
		final JLabel humidPer1 = new JLabel("%          to ");
		humidPer1.setOpaque(true);
		humidPer1.setBackground(HUMID);
		
		final JLabel humidPer2 = new JLabel("%");
		humidPer2.setOpaque(true);
		humidPer2.setBackground(HUMID);
		
		humidSettings.add(humidRangeLabel);
		humidSettings.add(humidRangeField);
		humidSettings.add(humidPer1);
		humidSettings.add(humidRangeField2);
		humidSettings.add(humidPer2);
		
		humidPanel.add(humidLabel);
		humidPanel.add(humidSettings);
		
		northPanel.add(temperaturePanel);
		northPanel.add(soilPanel);
		northPanel.add(humidPanel);
		
		//**********************************************
		//TAB 1
		//**********************************************
		
		//Panel that is to be placed on the tab
		final JPanel GHPanel = new JPanel();
		GHPanel.setLayout(new GridLayout(10,2));
		
		// Label to describe the functions of tab
		final JLabel GHLabel = new JLabel("Initial Greenhouse settings");
		Font font = GHLabel.getFont();
		Font italicFont = new Font(font.getName(), Font.ITALIC, font.getSize());
		GHLabel.setFont(italicFont);
		GHLabel.setOpaque(true);
		GHLabel.setBackground(GREEN);
		
		//Label initializations for temperature
		final JLabel heatingRateLabel = new JLabel("Rate of Heating (Celsius per minute):");
		
		final JLabel coolingRateLabel = new JLabel("Rate of Cooling (Celsius per minute):");
		
		final JLabel ambientTempRateLabel = new JLabel("Rate of ambient temperature "
				+ "(Celsius per minute):"); 
		
		final JLabel initialGHTempLabel = new JLabel(" Initial air Temperature: "); 
		initialGHTempLabel.setOpaque(true);
		initialGHTempLabel.setBackground(TEMP);

		//Field initializations for temperatures settings
		final JTextField heatingRateField = new JTextField(2);
		final JTextField coolingRateField = new JTextField(2);
		final JTextField ambientTempRateField = new JTextField(2);
		
		final JTextField initialGHTempField = new JTextField(2);
		initialGHTempField.setBackground(GREY);
		
		//ADD EVERYTHING TO THE MAIN PANEL
		GHPanel.add(initialGHTempLabel);
		GHPanel.add(initialGHTempField);
		GHPanel.add(heatingRateLabel);
		GHPanel.add(heatingRateField);
		GHPanel.add(coolingRateLabel);
		GHPanel.add(coolingRateField);
		GHPanel.add(ambientTempRateLabel);
		GHPanel.add(ambientTempRateField);
		
		//Label for soil settings
		final JLabel initialGHSoilLabel = new JLabel(" Initial Soil Moisture:"); 
		initialGHSoilLabel.setOpaque(true);
		initialGHSoilLabel.setBackground(SOIL);
		
		final JLabel moistureRateLabel = new JLabel("Sprinkler moisturizing rate (% per minute):");
		final JLabel dryingRateLabel = new JLabel("Ambient drying rate (% per minute):");
		
		//Fields for soil settings
		final JTextField initialGHSoilField = new JTextField(2);
		initialGHSoilField.setBackground(GREY);
		
		final JTextField moistureRateField = new JTextField(2);
		final JTextField dryingRateField = new JTextField(2);
		
		//ADD THEM TO THE PANEL
		GHPanel.add(initialGHSoilLabel);
		GHPanel.add(initialGHSoilField);
		GHPanel.add(moistureRateLabel);
		GHPanel.add(moistureRateField);
		GHPanel.add(dryingRateLabel);
		GHPanel.add(dryingRateField);
		
		//Humidifier setting labels
		final JLabel initialGHHumidLabel = new JLabel(" Initial Air Humidity:"); 
		initialGHHumidLabel.setOpaque(true);
		initialGHHumidLabel.setBackground(HUMID);
		
		final JLabel HumidRateLabel = new JLabel("Humidifier Humidity rate (% per minute):");
		final JLabel aridRateLabel = new JLabel("Ambient aridity rate (% per minute):");
		
		//Humidifier settings text fields
		final JTextField initialGHHumidField = new JTextField(2);
		initialGHHumidField.setBackground(GREY);
		
		final JTextField HumidRateField = new JTextField(2);
		final JTextField aridRateField = new JTextField(2);
		
		//ADD THEM TO THE MAIN PANEL
		GHPanel.add(initialGHHumidLabel);
		GHPanel.add(initialGHHumidField);
		GHPanel.add(HumidRateLabel);
		GHPanel.add(HumidRateField);
		GHPanel.add(aridRateLabel);
		GHPanel.add(aridRateField);
		
		//Add label to the top, the rest to the center
		firstTabPanel.add(GHLabel, BorderLayout.NORTH);
		firstTabPanel.add(GHPanel, BorderLayout.CENTER);
		
		//CREATE THE TAB
		panel.addTab("INIITAL VALUES",firstTabPanel);
		
		//**********************************************
		//SOUTH PANEL
		//**********************************************
		
		//4 buttons
		final JButton simulation = new JButton("RUN SIMULATION");
		final JButton load = new JButton("Load saved simulation");
		final JButton save = new JButton("Save current simulation");
		final JButton stop = new JButton("STOP SIMULATION");
		
		//Panel for sliders relating to temp
		final JPanel tempSliderPanel = new JPanel();
		tempSliderPanel.setLayout(new GridLayout(2,1));
		
		//Label the slider
		final JLabel tempSliderLabel = new JLabel("Temperature update time");
		
		//Cretae and set the slider for temperature
		final JSlider tempSlider = new JSlider(0,20);
		tempSlider.setMajorTickSpacing(5);
		tempSlider.setMinorTickSpacing(1);
		tempSlider.setPaintTicks(true);
		tempSlider.setPaintLabels(true);
		
		//Add label and slider to panel
		tempSliderPanel.add(tempSliderLabel);
		tempSliderPanel.add(tempSlider);
		
		//Create the same slider setup for the soil
		final JPanel soilSliderPanel = new JPanel();
		soilSliderPanel.setLayout(new GridLayout(2,1));
		
		final JLabel soilSliderLabel = new JLabel("Soil Moisture update time");
		
		final JSlider soilSlider = new JSlider(0,20);
		soilSlider.setMajorTickSpacing(5);
		soilSlider.setMinorTickSpacing(1);
		soilSlider.setPaintTicks(true);
		soilSlider.setPaintLabels(true);
		
		soilSliderPanel.add(soilSliderLabel);
		soilSliderPanel.add(soilSlider);
		
		//Then do the same for humidifier
		final JPanel humidSliderPanel = new JPanel();
		humidSliderPanel.setLayout(new GridLayout(2,1));
		
		final JLabel humidSliderLabel = new JLabel("Humidity update time");
		
		final JSlider humidSlider = new JSlider(0,20);
		humidSlider.setMajorTickSpacing(5);
		humidSlider.setMinorTickSpacing(1);
		humidSlider.setPaintTicks(true);
		humidSlider.setPaintLabels(true);
		
		humidSliderPanel.add(humidSliderLabel);
		humidSliderPanel.add(humidSlider);
		
		//Create a panel to organize all the sliders
		final JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridLayout(3,1));
		
		//Add the sliders to the panel
		sliderPanel.add(tempSliderPanel);
		sliderPanel.add(soilSliderPanel);
		sliderPanel.add(humidSliderPanel);
		
		//Cretae a panel for the buttons
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,1));
		
		//Add the buttons
		buttonPanel.add(simulation);
		buttonPanel.add(load);
		buttonPanel.add(save);
		buttonPanel.add(stop);
		
		//Create a panel to show the current data in the form of label
		final JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(3,2));
		
		//Current temperature, color it
		final JLabel currentTempLabel = new JLabel("CURRENT TEMPERATURE (CELSIUS):");
		currentTempLabel.setOpaque(true);
		currentTempLabel.setBackground(GREY);
		
		final JLabel currentTempLabelAmount = new JLabel("0");
		currentTempLabelAmount.setOpaque(true);
		currentTempLabelAmount.setBackground(GREY);
		
		//current soil, dont color
		final JLabel currentSoilLabel = new JLabel("CURRENT SOIL MOISTURE (%)");
		final JLabel currentSoilLabelAmount = new JLabel("0");
		
		//Current Humid, color
		final JLabel currentHumidLabel = new JLabel("CURRENT HUMIDITY (%)");
		currentHumidLabel.setOpaque(true);
		currentHumidLabel.setBackground(GREY);
		
		final JLabel currentHumidLabelAmount = new JLabel("0");
		currentHumidLabelAmount.setOpaque(true);
		currentHumidLabelAmount.setBackground(GREY);
		
		//Add the labels in the correct order
		labelPanel.add(currentTempLabel);
		labelPanel.add(currentTempLabelAmount);
		
		labelPanel.add(currentSoilLabel);
		labelPanel.add(currentSoilLabelAmount);
		
		labelPanel.add(currentHumidLabel);
		labelPanel.add(currentHumidLabelAmount);
		
		//ADD EVERYTHING TO SECOND TAB PANEL
		secondTabPanel.add(northPanel, BorderLayout.NORTH);
		secondTabPanel.add(sliderPanel, BorderLayout.CENTER);
		secondTabPanel.add(westPanel, BorderLayout.WEST);
		secondTabPanel.add(buttonPanel, BorderLayout.EAST);
		secondTabPanel.add(labelPanel, BorderLayout.SOUTH);
		
		//CREATE SECOND TAB
		panel.addTab("CONTROL SETTINGS",secondTabPanel);
		
		//SHARJEEL THIS SHIT IS FOR YOU, MAKE WHATEVER LOCAL VARIABLES YOU WISH
		class SlidingClickingListener implements ActionListener, ChangeListener
		{
			//Booleans telling whether a machine is on or not
			private boolean ac = false, furn = false, spk = false, hum = false;
			
			//METHOD TO SWITCH THE COLORS AND ACTIVE STATUS OF MACHINES
			private void colorSwitch()
			{
				if(ac)
				{
					ACActivatedLabel.setBackground(GREEN);
					ACActivatedLabel.setText("ACTIVE");
				}
				else
				{
					ACActivatedLabel.setBackground(RED);
					ACActivatedLabel.setText("NOT ACTIVE");
				}
				
				if(furn)
				{
					furnaceActivatedLabel.setBackground(GREEN);
					furnaceActivatedLabel.setText("ACTIVE");
				}
				else
				{
					furnaceActivatedLabel.setBackground(RED);
					furnaceActivatedLabel.setText("NOT ACTIVE");
				}
				
				if(spk)
				{
					sprinklerActivatedLabel.setBackground(GREEN);
					sprinklerActivatedLabel.setText("ACTIVE");
				}
				else
				{
					sprinklerActivatedLabel.setBackground(RED);
					sprinklerActivatedLabel.setText("NOT ACTIVE");
				}
				
				if(spk)
				{
					sprinklerActivatedLabel.setBackground(GREEN);
					sprinklerActivatedLabel.setText("ACTIVE");
				}
				else
				{
					sprinklerActivatedLabel.setBackground(RED);
					sprinklerActivatedLabel.setText("NOT ACTIVE");
				}
				
				if(hum)
				{
					humidActivatedLabel.setBackground(GREEN);
					humidActivatedLabel.setText("ACTIVE");
				}
				else
				{
					humidActivatedLabel.setBackground(RED);
					humidActivatedLabel.setText("NOT ACTIVE");
				}
					
			}
			
			public void actionPerformed(ActionEvent bc) 
			{
				if(bc.getSource().equals(simulation))
				{
					//DO SIMULATION THREADS HERE
				}
				
				if(bc.getSource().equals(load))
				{
					//DO LOADING OF FILES IN HERE
				}
				
				if(bc.getSource().equals(save))
				{
					//DO SAVING OF FILES HERE
				}
				if(bc.getSource().equals(stop))
				{
					//STOP THREADS HERE
				}
			}

			public void stateChanged(ChangeEvent sl) 
			{
				JSlider srcSlider = (JSlider)sl.getSource();
				
				if(srcSlider.getValueIsAdjusting() == false)
				{
					if(srcSlider.equals(tempSlider))
					{
						//SAVE TEMP NOTE THEY INPUT SECONDS. CONVERT TO MILLI!
					}
					
					if(srcSlider.equals(soilSlider))
					{
						//SAVE SOIL NOTE THEY INPUT SECONDS. CONVERT TO MILLI!
					}
					
					if(srcSlider.equals(humidSlider))
					{
						//SAVE HUMID NOTE THEY INPUT SECONDS. CONVERT TO MILLI!
					}
				}
			}
		}
		
		//Make ultimate listener
		SlidingClickingListener lis = new SlidingClickingListener();
		
		//Add the lis to the buttons
		simulation.addActionListener(lis);
		load.addActionListener(lis);
		save.addActionListener(lis);
		stop.addActionListener(lis);
		
		//add the lis to the sliders
		tempSlider.addChangeListener(lis);
		soilSlider.addChangeListener(lis);
		humidSlider.addChangeListener(lis);
		
		//set up frame
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(590,470);
		frame.setVisible(true);
	}
}
