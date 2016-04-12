package Assignment5;

/**
 * Assignment 5
 * Course: CPSC 233
 * Prof: Manzara
 * UCID: 30002229 & 30008424
 * TA session: 03
 *
 * @author Asjad Hassan Malick & Sharjeel Junaid
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class GreenHouseGUI {
    public static void main(String args[]) {
        //THE COLOR CONSTANTS
        final Color RED = new Color(188, 0, 0);
        final Color GREEN = new Color(45, 189, 16);
        final Color TEMP = new Color(102, 102, 255);
        final Color SOIL = new Color(255, 153, 51);
        final Color HUMID = new Color(69, 167, 174);
        final Color GREY = new Color(195, 195, 195);

        //GUI FRAME SETUP
        final JFrame frame = new JFrame("GREENHOUSE SIMULATOR");

        //GUI Panel setup
        final JTabbedPane panel = new JTabbedPane();

        //Contains the active labels, tells which system is active
        final JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(4, 1));

        //To be put on second tab panel, will have ranges
        final JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(3, 1));

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
        temperaturePanel.setLayout(new GridLayout(2, 1));
        final JLabel tempLabel = new JLabel("Temperature Settings");
        tempLabel.setOpaque(true);
        tempLabel.setBackground(TEMP);

        final JPanel temperatureSettings = new JPanel();
        temperatureSettings.setLayout(new GridLayout(1, 3));

        final JLabel tempRangeLabel = new JLabel("Target Temperature: ");
        tempRangeLabel.setOpaque(true);
        tempRangeLabel.setBackground(TEMP);

        final JTextField tempRangeField = new JTextField(2);

        final JLabel tempPlusMin = new JLabel("+/-   3 degrees Celsius");
        tempPlusMin.setOpaque(true);
        tempPlusMin.setBackground(TEMP);

        temperatureSettings.add(tempRangeLabel);
        temperatureSettings.add(tempRangeField);
        temperatureSettings.add(tempPlusMin);

        temperaturePanel.add(tempLabel);
        temperaturePanel.add(temperatureSettings);


        // Soil settings
        final JPanel soilPanel = new JPanel();
        soilPanel.setLayout(new GridLayout(2, 1));
        final JLabel soilLabel = new JLabel("Soil Moisture Settings");
        soilLabel.setOpaque(true);
        soilLabel.setBackground(SOIL);

        final JPanel soilSettings = new JPanel();
        soilSettings.setLayout(new GridLayout(1, 5));

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
        humidPanel.setLayout(new GridLayout(2, 1));
        final JLabel humidLabel = new JLabel("Humidity Settings");
        humidLabel.setOpaque(true);
        humidLabel.setBackground(HUMID);

        final JPanel humidSettings = new JPanel();
        humidSettings.setLayout(new GridLayout(1, 5));

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
        GHPanel.setLayout(new GridLayout(10, 2));

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

        final JTextField humidRateField = new JTextField(2);
        final JTextField aridRateField = new JTextField(2);

        //ADD THEM TO THE MAIN PANEL
        GHPanel.add(initialGHHumidLabel);
        GHPanel.add(initialGHHumidField);
        GHPanel.add(HumidRateLabel);
        GHPanel.add(humidRateField);
        GHPanel.add(aridRateLabel);
        GHPanel.add(aridRateField);

        //Add label to the top, the rest to the center
        firstTabPanel.add(GHLabel, BorderLayout.NORTH);
        firstTabPanel.add(GHPanel, BorderLayout.CENTER);

        //CREATE THE TAB
        panel.addTab("INITIAL VALUES", firstTabPanel);

        //**********************************************
        //SOUTH PANEL
        //**********************************************

        //4 buttons
        final JButton simulation = new JButton("RUN SIMULATION");
        final JButton loadf = new JButton("Load saved simulation");
        final JButton savef = new JButton("Save current simulation");
        final JButton stop = new JButton("STOP SIMULATION");
        stop.setEnabled(false);

        //Panel for sliders relating to temp
        final JPanel tempSliderPanel = new JPanel();
        tempSliderPanel.setLayout(new GridLayout(2, 1));

        //Label the slider
        final JLabel tempSliderLabel = new JLabel("Temperature update time (seconds)");

        //Create and set the slider for temperature
        final JSlider tempSlider = new JSlider(1, 21);
        tempSlider.setMajorTickSpacing(5);
        tempSlider.setMinorTickSpacing(1);
        tempSlider.setPaintTicks(true);
        tempSlider.setPaintLabels(true);

        //Add label and slider to panel
        tempSliderPanel.add(tempSliderLabel);
        tempSliderPanel.add(tempSlider);

        //Create the same slider setup for the soil
        final JPanel soilSliderPanel = new JPanel();
        soilSliderPanel.setLayout(new GridLayout(2, 1));

        final JLabel soilSliderLabel = new JLabel("Soil Moisture update time (seconds)");

        final JSlider soilSlider = new JSlider(1, 21);
        soilSlider.setMajorTickSpacing(5);
        soilSlider.setMinorTickSpacing(1);
        soilSlider.setPaintTicks(true);
        soilSlider.setPaintLabels(true);

        soilSliderPanel.add(soilSliderLabel);
        soilSliderPanel.add(soilSlider);

        //Then do the same for humidifier
        final JPanel humidSliderPanel = new JPanel();
        humidSliderPanel.setLayout(new GridLayout(2, 1));

        final JLabel humidSliderLabel = new JLabel("Humidity update time (seconds)");

        final JSlider humidSlider = new JSlider(1, 21);
        humidSlider.setMajorTickSpacing(5);
        humidSlider.setMinorTickSpacing(1);
        humidSlider.setPaintTicks(true);
        humidSlider.setPaintLabels(true);

        humidSliderPanel.add(humidSliderLabel);
        humidSliderPanel.add(humidSlider);

        //Create a panel to organize all the sliders
        final JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(3, 1));

        //Add the sliders to the panel
        sliderPanel.add(tempSliderPanel);
        sliderPanel.add(soilSliderPanel);
        sliderPanel.add(humidSliderPanel);

        //Create a panel for the buttons
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        //Add the buttons
        buttonPanel.add(simulation);
        buttonPanel.add(loadf);
        buttonPanel.add(savef);
        buttonPanel.add(stop);

        //Create a panel to show the current data in the form of label
        final JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(3, 2));

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
        panel.addTab("CONTROL SETTINGS", secondTabPanel);

        //Saving frame
        final JFrame saveFrame = new JFrame();
        final JPanel savePanel = new JPanel();
        savePanel.setLayout(new GridLayout(3, 1));

        final JLabel saveLabel = new JLabel("Name of file to save");
        final JTextField saveField = new JTextField();
        final JButton save = new JButton("SAVE");

        savePanel.add(saveLabel);
        savePanel.add(saveField);
        savePanel.add(save);

        saveFrame.add(savePanel);

        saveFrame.setSize(150, 150);
        saveFrame.setVisible(false);

        //Load frame
        final JFrame loadFrame = new JFrame();
        final JPanel loadPanel = new JPanel();
        loadPanel.setLayout(new GridLayout(3, 1));

        final JLabel loadLabel = new JLabel("Name of file to load");
        final JTextField loadField = new JTextField();
        final JButton load = new JButton("LOAD");

        loadPanel.add(loadLabel);
        loadPanel.add(loadField);
        loadPanel.add(load);

        loadFrame.add(loadPanel);

        loadFrame.setSize(150, 150);
        loadFrame.setVisible(false);

        // Sets the default initial values for the settings
        initialGHTempField.setText("20");
        heatingRateField.setText("5");
        coolingRateField.setText("5");
        ambientTempRateField.setText("5");
        initialGHSoilField.setText("20");
        moistureRateField.setText("5");
        dryingRateField.setText("5");
        initialGHHumidField.setText("35");
        humidRateField.setText("5");
        aridRateField.setText("5");

        class SlidingClickingListener implements ActionListener, ChangeListener {
            private GreenHouseEnvironment GHE;
            private GreenHouseThread GThread;
            private TempThread tempSensor;
            private SoilThread soilSensor;
            private HumidThread humidSensor;

            // Stores the sliders update time values
            private double tempChangeRate = tempSlider.getValue();
            private double soilChangeRate = soilSlider.getValue();
            private double humidChangeRate = humidSlider.getValue();


            public void actionPerformed(ActionEvent bc) {
                if (bc.getSource().equals(simulation)) {

                    //disable and enable certain fields and buttons
                    simulation.setEnabled(false);
                    stop.setEnabled(true);
                    savef.setEnabled(false);
                    loadf.setEnabled(false);

                    tempRangeField.setEditable(false);

                    soilRangeField.setEditable(false);
                    soilRangeField2.setEditable(false);

                    humidRangeField.setEditable(false);
                    humidRangeField2.setEditable(false);
                    tempRangeField.setEditable(false);

                    initialGHTempField.setEditable(false);
                    heatingRateField.setEditable(false);
                    coolingRateField.setEditable(false);
                    ambientTempRateField.setEditable(false);

                    initialGHSoilField.setEditable(false);
                    moistureRateField.setEditable(false);
                    dryingRateField.setEditable(false);

                    initialGHHumidField.setEditable(false);
                    humidRateField.setEditable(false);
                    aridRateField.setEditable(false);

                    //Initialize the GHE
                    double initialTemp = Double.parseDouble(initialGHTempField.getText());
                    double initialSoil = Double.parseDouble(initialGHSoilField.getText());
                    double initialHumid = Double.parseDouble(initialGHHumidField.getText());

                    GHE = new GreenHouseEnvironment(initialTemp, initialSoil, initialHumid);

                    //Initialize the temperature thread
                    double furnaceRate = Double.parseDouble(heatingRateField.getText());
                    double coolingRate = Double.parseDouble(coolingRateField.getText()) * -1;
                    double upperLimitTemp = Double.parseDouble(tempRangeField.getText()) + 3;
                    double lowerLimitTemp = Double.parseDouble(tempRangeField.getText()) - 3;
                    double ambientTemp = Double.parseDouble(ambientTempRateField.getText());

                    tempSensor = new TempThread(upperLimitTemp, lowerLimitTemp, furnaceRate,
                            coolingRate, ambientTemp, GHE);

                    //Initialize the soil thread
                    double moistureRate = Double.parseDouble(moistureRateField.getText());
                    double upperLimitSoil = Double.parseDouble(soilRangeField2.getText());
                    double lowerLimitSoil = Double.parseDouble(soilRangeField.getText());
                    double ambientSoil = (Double.parseDouble(dryingRateField.getText())) * -1; //negate it

                    soilSensor = new SoilThread(upperLimitSoil, lowerLimitSoil, moistureRate, ambientSoil, GHE);

                    //Initialize the humid thread
                    double humidRate = Double.parseDouble(humidRateField.getText());
                    double upperLimitHumid = Double.parseDouble(humidRangeField2.getText());
                    double lowerLimitHumid = Double.parseDouble(humidRangeField.getText());
                    double ambientArid = (Double.parseDouble(aridRateField.getText())) * -1;

                    humidSensor = new HumidThread(upperLimitHumid, lowerLimitHumid, humidRate, ambientArid, GHE);

                    //initialize the GreenHouse thread
                    GThread = new GreenHouseThread(GHE, tempSensor, humidSensor, soilSensor, currentTempLabelAmount, currentSoilLabelAmount, currentHumidLabelAmount, ACActivatedLabel, furnaceActivatedLabel, sprinklerActivatedLabel, humidActivatedLabel);
                    GHE.tempChangeRate = tempChangeRate;
                    GHE.soilChangeRate = soilChangeRate;
                    GHE.humidChangeRate = humidChangeRate;

                    // Starts the Threads
                    GThread.runSubThreads();
                }

                if (bc.getSource().equals(loadf)) {
                    loadFrame.setVisible(true);
                    // TODO Get the last saved simulation's file name and automatically have it selected at the default file to load
                }

                if (bc.getSource().equals(savef)) {
                    saveFrame.setVisible(true);
                    // TODO Get the last saved simulation's file name and increment the file index counter
                }
                if (bc.getSource().equals(stop)) {
                    simulation.setEnabled(true);
                    stop.setEnabled(false);
                    savef.setEnabled(true);
                    loadf.setEnabled(true);

                    tempRangeField.setEditable(true);

                    soilRangeField.setEditable(true);
                    soilRangeField2.setEditable(true);

                    humidRangeField.setEditable(true);
                    humidRangeField2.setEditable(true);
                    tempRangeField.setEditable(true);

                    initialGHTempField.setEditable(true);
                    heatingRateField.setEditable(true);
                    coolingRateField.setEditable(true);
                    ambientTempRateField.setEditable(true);

                    initialGHSoilField.setEditable(true);
                    moistureRateField.setEditable(true);
                    dryingRateField.setEditable(true);

                    initialGHHumidField.setEditable(true);
                    humidRateField.setEditable(true);
                    aridRateField.setEditable(true);

                    //Stops Threads
                    GThread.disableSubThreads();
                }

                if (bc.getSource().equals(load)) {
                    loadFrame.setVisible(false);
                    // TODO Loading simulation
                }

                if (bc.getSource().equals(save)) {
                    saveFrame.setVisible(false);
                    // TODO Saving simulation
                    try {
                        // File Path Of The File To Append The Data To
                        String finalFile = "Simulation Log.txt";

                        // Initializes file writing utility
                        FileWriter fileWriter = null;

                        fileWriter = new FileWriter(finalFile);
                        // Loops through and prints out the logged data from the ArrayList to the log file
                        for (int counter = 0; counter < GHE.logData.size(); counter++) {
                            // Appends the data to the file
                            fileWriter.write("\n" + GHE.logData.get(counter));
                        }
                        // Closes the file writing utility
                        fileWriter.close();
                    } catch (IOException e) {
                    }
                }

            }

            public void stateChanged(ChangeEvent sl) {
                JSlider srcSlider = (JSlider) sl.getSource();

                // Updates the thread update frequencies based on the slider values
                if (srcSlider.getValueIsAdjusting() == false) {
                    if (srcSlider.equals(tempSlider)) {
                        if (GHE == null) {
                            tempChangeRate = tempSlider.getValue();
                        } else {
                            GHE.tempChangeRate = tempSlider.getValue();
                        }
                    }
                    if (srcSlider.equals(soilSlider)) {
                        if (GHE == null) {
                            soilChangeRate = soilSlider.getValue();
                        } else {
                            GHE.soilChangeRate = soilSlider.getValue();
                        }
                    }
                    if (srcSlider.equals(humidSlider)) {
                        if (GHE == null) {
                            humidChangeRate = humidSlider.getValue();
                        } else {
                            GHE.humidChangeRate = humidSlider.getValue();
                        }
                    }
                }
            }
        }

        //Make ultimate listener
        SlidingClickingListener lis = new SlidingClickingListener();

        //Add the lis to the buttons
        simulation.addActionListener(lis);
        loadf.addActionListener(lis);
        savef.addActionListener(lis);
        stop.addActionListener(lis);
        load.addActionListener(lis);
        save.addActionListener(lis);

        //add the lis to the sliders
        tempSlider.addChangeListener(lis);
        soilSlider.addChangeListener(lis);
        humidSlider.addChangeListener(lis);

        //set up frame
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(590, 470);
        frame.setVisible(true);
    }
}
