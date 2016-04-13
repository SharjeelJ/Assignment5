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
import java.io.*;

public class GreenHouseGUI {
    public static void main(String args[]) {
        // Colour constants
        final Color RED = new Color(188, 0, 0);
        final Color GREEN = new Color(45, 189, 16);
        final Color TEMP = new Color(102, 102, 255);
        final Color SOIL = new Color(255, 153, 51);
        final Color HUMID = new Color(69, 167, 174);
        final Color GREY = new Color(195, 195, 195);

        // GUI frame setup
        final JFrame frame = new JFrame("GREENHOUSE SIMULATOR");

        // GUI panel setup
        final JTabbedPane panel = new JTabbedPane();

        // Contains the active labels, tells which system is active
        final JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(4, 1));

        // To be put on second tab panel, will have ranges
        final JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(3, 1));

        // Tab for initial values
        final JPanel firstTabPanel = new JPanel();
        firstTabPanel.setLayout(new BorderLayout());

        // Tab that contains basically everything else (output readings, frequency slides, target ranges)
        final JPanel secondTabPanel = new JPanel();
        secondTabPanel.setLayout(new BorderLayout());

        //**********************************************
        // WEST PANEL
        //**********************************************

        // Create the label for the furnace, make it red
        final JLabel furnaceActivatedLabel = new JLabel(" FURNACE: INACTIVE");
        furnaceActivatedLabel.setOpaque(true);
        furnaceActivatedLabel.setBackground(RED);

        // Create the label for the air conditioner (AC), make it red
        final JLabel ACActivatedLabel = new JLabel(" AIR CONDITIONER: INACTIVE");
        ACActivatedLabel.setOpaque(true);
        ACActivatedLabel.setBackground(RED);

        // Create the label for the humidifier, make it red
        final JLabel humidActivatedLabel = new JLabel(" HUMIDIFIER: INACTIVE");
        humidActivatedLabel.setOpaque(true);
        humidActivatedLabel.setBackground(RED);

        // Create the label for the sprinkler, make it red
        final JLabel sprinklerActivatedLabel = new JLabel(" SPRINKLER: INACTIVE");
        sprinklerActivatedLabel.setOpaque(true);
        sprinklerActivatedLabel.setBackground(RED);

        // Add the labels to the panel
        westPanel.add(furnaceActivatedLabel);
        westPanel.add(ACActivatedLabel);
        westPanel.add(humidActivatedLabel);
        westPanel.add(sprinklerActivatedLabel);

        //**********************************************
        // NORTH PANEL
        //**********************************************

        // Temperature settings
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

        final JLabel tempPlusMin = new JLabel("+/-   3 Degrees Celsius");
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

        final JLabel soilPer1 = new JLabel("%          To ");
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


        // Humidity Settings
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

        final JLabel humidPer1 = new JLabel("%          To ");
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

        // Adds the newly created panels to the primary panel
        northPanel.add(temperaturePanel);
        northPanel.add(soilPanel);
        northPanel.add(humidPanel);

        //**********************************************
        // TAB 1
        //**********************************************

        // Panel that is to be placed on the tab
        final JPanel GHPanel = new JPanel();
        GHPanel.setLayout(new GridLayout(10, 2));

        // Label to describe the functions of the tab
        final JLabel GHLabel = new JLabel("Initial Greenhouse Settings");
        Font font = GHLabel.getFont();
        Font italicFont = new Font(font.getName(), Font.ITALIC, font.getSize());
        GHLabel.setFont(italicFont);
        GHLabel.setOpaque(true);
        GHLabel.setBackground(GREEN);

        // Label initializations for temperature
        final JLabel heatingRateLabel = new JLabel("Rate Of Heating (Celsius Per Minute):");

        final JLabel coolingRateLabel = new JLabel("Rate Of Cooling (Celsius Per Minute):");

        final JLabel ambientTempRateLabel = new JLabel("Rate Of Ambient Temperature " + "(Celsius Per Minute):");

        final JLabel initialGHTempLabel = new JLabel(" Initial Air Temperature:");
        initialGHTempLabel.setOpaque(true);
        initialGHTempLabel.setBackground(TEMP);

        // Field initializations for temperatures settings
        final JTextField heatingRateField = new JTextField(2);
        final JTextField coolingRateField = new JTextField(2);
        final JTextField ambientTempRateField = new JTextField(2);

        final JTextField initialGHTempField = new JTextField(2);
        initialGHTempField.setBackground(GREY);

        // Add everything to the main panel
        GHPanel.add(initialGHTempLabel);
        GHPanel.add(initialGHTempField);
        GHPanel.add(heatingRateLabel);
        GHPanel.add(heatingRateField);
        GHPanel.add(coolingRateLabel);
        GHPanel.add(coolingRateField);
        GHPanel.add(ambientTempRateLabel);
        GHPanel.add(ambientTempRateField);

        // Label for the soil settings
        final JLabel initialGHSoilLabel = new JLabel(" Initial Soil Moisture:");
        initialGHSoilLabel.setOpaque(true);
        initialGHSoilLabel.setBackground(SOIL);

        final JLabel moistureRateLabel = new JLabel("Sprinkler Moisturizing Rate (% Per Minute):");
        final JLabel dryingRateLabel = new JLabel("Ambient Drying Rate (% Per Minute):");

        // Fields for the soil settings
        final JTextField initialGHSoilField = new JTextField(2);
        initialGHSoilField.setBackground(GREY);

        final JTextField moistureRateField = new JTextField(2);
        final JTextField dryingRateField = new JTextField(2);

        // Add the newly created soil elements to the main panel
        GHPanel.add(initialGHSoilLabel);
        GHPanel.add(initialGHSoilField);
        GHPanel.add(moistureRateLabel);
        GHPanel.add(moistureRateField);
        GHPanel.add(dryingRateLabel);
        GHPanel.add(dryingRateField);

        // Humidifier setting labels
        final JLabel initialGHHumidLabel = new JLabel(" Initial Air Humidity:");
        initialGHHumidLabel.setOpaque(true);
        initialGHHumidLabel.setBackground(HUMID);

        final JLabel HumidRateLabel = new JLabel("Humidifier Humidity Rate (% Per Minute):");
        final JLabel aridRateLabel = new JLabel("Ambient Aridity Rate (% Per Minute):");

        // Humidifier settings text fields
        final JTextField initialGHHumidField = new JTextField(2);
        initialGHHumidField.setBackground(GREY);

        final JTextField humidRateField = new JTextField(2);
        final JTextField aridRateField = new JTextField(2);

        // Add the newly created humidity elements to the main panel
        GHPanel.add(initialGHHumidLabel);
        GHPanel.add(initialGHHumidField);
        GHPanel.add(HumidRateLabel);
        GHPanel.add(humidRateField);
        GHPanel.add(aridRateLabel);
        GHPanel.add(aridRateField);

        // Add a label to the top
        firstTabPanel.add(GHLabel, BorderLayout.NORTH);
        firstTabPanel.add(GHPanel, BorderLayout.CENTER);

        // Create the tab
        panel.addTab("INITIAL VALUES", firstTabPanel);

        //**********************************************
        // SOUTH PANEL
        //**********************************************

        // Create 4 buttons
        final JButton simulation = new JButton("RUN SIMULATION");
        final JButton loadf = new JButton("Load Saved Simulation");
        final JButton savef = new JButton("Save Current Simulation");
        final JButton stop = new JButton("STOP SIMULATION");
        stop.setEnabled(false);

        // Panel for sliders relating to the temperature
        final JPanel tempSliderPanel = new JPanel();
        tempSliderPanel.setLayout(new GridLayout(2, 1));

        // Label the slider
        final JLabel tempSliderLabel = new JLabel("Temperature Update Time (Seconds)");

        // Create and set the slider for temperature
        final JSlider tempSlider = new JSlider(1, 21);
        tempSlider.setMajorTickSpacing(5);
        tempSlider.setMinorTickSpacing(1);
        tempSlider.setPaintTicks(true);
        tempSlider.setPaintLabels(true);

        // Add label and slider to panel
        tempSliderPanel.add(tempSliderLabel);
        tempSliderPanel.add(tempSlider);

        // Create the same slider setup for the soil moisture
        final JPanel soilSliderPanel = new JPanel();
        soilSliderPanel.setLayout(new GridLayout(2, 1));

        final JLabel soilSliderLabel = new JLabel("Soil Moisture Update Time (Seconds)");

        final JSlider soilSlider = new JSlider(1, 21);
        soilSlider.setMajorTickSpacing(5);
        soilSlider.setMinorTickSpacing(1);
        soilSlider.setPaintTicks(true);
        soilSlider.setPaintLabels(true);

        soilSliderPanel.add(soilSliderLabel);
        soilSliderPanel.add(soilSlider);

        // Do the same for the humidity
        final JPanel humidSliderPanel = new JPanel();
        humidSliderPanel.setLayout(new GridLayout(2, 1));

        final JLabel humidSliderLabel = new JLabel("Humidity Update Time (Seconds)");

        final JSlider humidSlider = new JSlider(1, 21);
        humidSlider.setMajorTickSpacing(5);
        humidSlider.setMinorTickSpacing(1);
        humidSlider.setPaintTicks(true);
        humidSlider.setPaintLabels(true);

        humidSliderPanel.add(humidSliderLabel);
        humidSliderPanel.add(humidSlider);

        // Create a panel to organize all the sliders
        final JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(3, 1));

        // Add the sliders to the panel
        sliderPanel.add(tempSliderPanel);
        sliderPanel.add(soilSliderPanel);
        sliderPanel.add(humidSliderPanel);

        // Create a panel for the buttons
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        // Add the buttons to the panel
        buttonPanel.add(simulation);
        buttonPanel.add(loadf);
        buttonPanel.add(savef);
        buttonPanel.add(stop);

        // Create a panel to show the current data in the form of label
        final JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(3, 2));

        // Current temperature, color
        final JLabel currentTempLabel = new JLabel("CURRENT TEMPERATURE (CELSIUS):");
        currentTempLabel.setOpaque(true);
        currentTempLabel.setBackground(GREY);

        final JLabel currentTempLabelAmount = new JLabel("0");
        currentTempLabelAmount.setOpaque(true);
        currentTempLabelAmount.setBackground(GREY);

        // Current soil moisture, no color
        final JLabel currentSoilLabel = new JLabel("CURRENT SOIL MOISTURE (%)");
        final JLabel currentSoilLabelAmount = new JLabel("0");

        // Current humidity, color
        final JLabel currentHumidLabel = new JLabel("CURRENT HUMIDITY (%)");
        currentHumidLabel.setOpaque(true);
        currentHumidLabel.setBackground(GREY);

        final JLabel currentHumidLabelAmount = new JLabel("0");
        currentHumidLabelAmount.setOpaque(true);
        currentHumidLabelAmount.setBackground(GREY);

        // Add the labels in the correct order
        labelPanel.add(currentTempLabel);
        labelPanel.add(currentTempLabelAmount);

        labelPanel.add(currentSoilLabel);
        labelPanel.add(currentSoilLabelAmount);

        labelPanel.add(currentHumidLabel);
        labelPanel.add(currentHumidLabelAmount);

        // Add everything to the second tab panel
        secondTabPanel.add(northPanel, BorderLayout.NORTH);
        secondTabPanel.add(sliderPanel, BorderLayout.CENTER);
        secondTabPanel.add(westPanel, BorderLayout.WEST);
        secondTabPanel.add(buttonPanel, BorderLayout.EAST);
        secondTabPanel.add(labelPanel, BorderLayout.SOUTH);

        // Create a second tab
        panel.addTab("CONTROL SETTINGS", secondTabPanel);

        // Saving frame
        final JFrame saveFrame = new JFrame();
        final JPanel savePanel = new JPanel();
        savePanel.setLayout(new GridLayout(3, 1));

        final JLabel saveLabel = new JLabel("Name Of The Log File To Save");
        final JTextField saveField = new JTextField();
        final JButton save = new JButton("SAVE");

        savePanel.add(saveLabel);
        savePanel.add(saveField);
        savePanel.add(save);

        saveFrame.add(savePanel);

        saveFrame.setSize(150, 150);
        saveFrame.setVisible(false);

        // Load frame
        final JFrame loadFrame = new JFrame();
        final JPanel loadPanel = new JPanel();
        loadPanel.setLayout(new GridLayout(3, 1));

        final JLabel loadLabel = new JLabel("Name Of The Log File To Load");
        final JTextField loadField = new JTextField();
        final JButton load = new JButton("LOAD");

        loadPanel.add(loadLabel);
        loadPanel.add(loadField);
        loadPanel.add(load);

        loadFrame.add(loadPanel);

        loadFrame.setSize(150, 150);
        loadFrame.setVisible(false);

        // Sets the default initial values for the simulation
        tempRangeField.setText("33");
        initialGHTempField.setText("27.5");
        heatingRateField.setText("10");
        coolingRateField.setText("9.5");
        ambientTempRateField.setText("2.505");
        soilRangeField.setText("20");
        soilRangeField2.setText("25");
        initialGHSoilField.setText("25");
        moistureRateField.setText("12");
        dryingRateField.setText("6");
        humidRangeField.setText("30");
        humidRangeField2.setText("40");
        initialGHHumidField.setText("25");
        humidRateField.setText("10.5");
        aridRateField.setText("3.25");

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

                    // Disable and enable certain fields and buttons
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

                    // Initialize the GreenHouseEnvironment
                    double initialTemp = Double.parseDouble(initialGHTempField.getText());
                    double initialSoil = Double.parseDouble(initialGHSoilField.getText());
                    double initialHumid = Double.parseDouble(initialGHHumidField.getText());

                    GHE = new GreenHouseEnvironment(initialTemp, initialSoil, initialHumid);

                    // Initialize the temperature thread
                    double furnaceRate = Double.parseDouble(heatingRateField.getText());
                    double coolingRate = Double.parseDouble(coolingRateField.getText()) * -1;
                    double upperLimitTemp = Double.parseDouble(tempRangeField.getText()) + 3;
                    double lowerLimitTemp = Double.parseDouble(tempRangeField.getText()) - 3;
                    double ambientTemp = Double.parseDouble(ambientTempRateField.getText());

                    tempSensor = new TempThread(upperLimitTemp, lowerLimitTemp, furnaceRate,
                            coolingRate, ambientTemp, GHE);

                    // Initialize the soil moisture thread
                    double moistureRate = Double.parseDouble(moistureRateField.getText());
                    double upperLimitSoil = Double.parseDouble(soilRangeField2.getText());
                    double lowerLimitSoil = Double.parseDouble(soilRangeField.getText());
                    double ambientSoil = (Double.parseDouble(dryingRateField.getText())) * -1; //negate it

                    soilSensor = new SoilThread(upperLimitSoil, lowerLimitSoil, moistureRate, ambientSoil, GHE);

                    // Initialize the humidity thread
                    double humidRate = Double.parseDouble(humidRateField.getText());
                    double upperLimitHumid = Double.parseDouble(humidRangeField2.getText());
                    double lowerLimitHumid = Double.parseDouble(humidRangeField.getText());
                    double ambientArid = (Double.parseDouble(aridRateField.getText())) * -1;

                    humidSensor = new HumidThread(upperLimitHumid, lowerLimitHumid, humidRate, ambientArid, GHE);

                    // initialize the GreenHouse master thread to store all the threads & data
                    GThread = new GreenHouseThread(GHE, tempSensor, humidSensor, soilSensor, currentTempLabelAmount, currentSoilLabelAmount, currentHumidLabelAmount, ACActivatedLabel, furnaceActivatedLabel, sprinklerActivatedLabel, humidActivatedLabel);
                    GHE.tempChangeRate = tempChangeRate;
                    GHE.soilChangeRate = soilChangeRate;
                    GHE.humidChangeRate = humidChangeRate;

                    // Starts the threads
                    GThread.runSubThreads();
                }

                if (bc.getSource().equals(loadf)) {
                    loadFrame.setVisible(true);

                    // Creates a new empty placeholder file object in the current directory
                    File finalFileCheck = new File(".");

                    // Initialize a counter to keep track of the number of log files present in the current directory
                    int logFilesCounter = 0;

                    // Loops through the file names of all objects in the current directory and counts the number of pre-existing log files
                    for (int counter = 0; counter < finalFileCheck.listFiles().length; counter++) {
                        if (finalFileCheck.listFiles()[counter].getName().contains("Simulation Log"))
                            logFilesCounter++;
                    }

                    // Updates the load file name text box with the last found log file's name
                    loadField.setText("Simulation Log " + logFilesCounter + ".txt");
                }

                if (bc.getSource().equals(savef)) {
                    saveFrame.setVisible(true);

                    // Creates a new empty placeholder file object in the current directory
                    File finalFileCheck = new File(".");

                    // Initialize a counter to keep track of the number of log files present in the current directory
                    int logFilesCounter = 1;

                    // Loops through the file names of all objects in the current directory and counts the number of pre-existing log files
                    for (int counter = 0; counter < finalFileCheck.listFiles().length; counter++) {
                        if (finalFileCheck.listFiles()[counter].getName().contains("Simulation Log"))
                            logFilesCounter++;
                    }

                    // Updates the save file name text box with the last found log file's name incremented by 1
                    saveField.setText("Simulation Log " + logFilesCounter + ".txt");
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

                    // Stops the threads
                    GThread.disableSubThreads();
                }

                if (bc.getSource().equals(load)) {
                    loadFrame.setVisible(false);
                    try {
                        // Initializes file reading utility
                        FileReader fileImport = new FileReader(loadField.getText());
                        BufferedReader fileReader = new BufferedReader(fileImport);

                        // Loops through the data ArrayList and sets all the logged data to the simulation's settings (skips over / ignores irrelevant data)
                        for (int counter = 1; counter < 23; counter++) {
                            switch (counter) {
                                case 3:
                                    tempRangeField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 4:
                                    initialGHTempField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 5:
                                    coolingRateField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 6:
                                    heatingRateField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 7:
                                    ambientTempRateField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 8:
                                    tempSlider.setValue(Integer.valueOf(fileReader.readLine().substring(4)));
                                    break;
                                case 10:
                                    soilRangeField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 11:
                                    soilRangeField2.setText(fileReader.readLine().substring(4));
                                    break;
                                case 12:
                                    initialGHSoilField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 13:
                                    moistureRateField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 14:
                                    dryingRateField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 15:
                                    soilSlider.setValue(Integer.valueOf(fileReader.readLine().substring(4)));
                                    break;
                                case 17:
                                    humidRangeField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 18:
                                    humidRangeField2.setText(fileReader.readLine().substring(4));
                                    break;
                                case 19:
                                    initialGHHumidField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 20:
                                    humidRateField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 21:
                                    aridRateField.setText(fileReader.readLine().substring(4));
                                    break;
                                case 22:
                                    humidSlider.setValue(Integer.valueOf(fileReader.readLine().substring(4)));
                                    break;
                                default:
                                    fileReader.readLine();
                            }
                        }

                        // Closes the file writing utility
                        fileReader.close();
                    } catch (IOException e) {
                    } catch (NullPointerException e) {
                    }
                }

                if (bc.getSource().equals(save)) {
                    saveFrame.setVisible(false);
                    try {
                        // Initializes file writing utility
                        FileWriter fileWriter = new FileWriter(saveField.getText());

                        // Writes out all the simulation settings data
                        fileWriter.write("SIMULATION SETTINGS");

                        fileWriter.write("\nTEMPERATURE");
                        fileWriter.write("\n T: " + tempRangeField.getText());
                        fileWriter.write("\n I: " + initialGHTempField.getText());
                        fileWriter.write("\nCR: " + coolingRateField.getText());
                        fileWriter.write("\nHR: " + heatingRateField.getText());
                        fileWriter.write("\nAR: " + ambientTempRateField.getText());
                        fileWriter.write("\nUT: " + tempSlider.getValue());

                        fileWriter.write("\nSOIL MOISTURE");
                        fileWriter.write("\nT1: " + soilRangeField.getText());
                        fileWriter.write("\nT2: " + soilRangeField2.getText());
                        fileWriter.write("\n I: " + initialGHSoilField.getText());
                        fileWriter.write("\n R: " + moistureRateField.getText());
                        fileWriter.write("\nAR: " + dryingRateField.getText());
                        fileWriter.write("\nUT: " + soilSlider.getValue());

                        fileWriter.write("\nHUMIDITY");
                        fileWriter.write("\nT1: " + humidRangeField.getText());
                        fileWriter.write("\nT2: " + humidRangeField2.getText());
                        fileWriter.write("\n I: " + initialGHHumidField.getText());
                        fileWriter.write("\n R: " + humidRateField.getText());
                        fileWriter.write("\nAR: " + aridRateField.getText());
                        fileWriter.write("\nUT: " + humidSlider.getValue());

                        fileWriter.write("\n\nLOG DATA");

                        // Loops through the data ArrayList and writes out the all the logged data to the log file
                        for (int counter = 0; counter < GHE.logData.size(); counter++) {
                            fileWriter.write("\n" + GHE.logData.get(counter));
                        }

                        // Closes the file writing utility
                        fileWriter.close();
                    } catch (IOException e) {
                    } catch (NullPointerException e) {
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

        // Make ultimate listener
        SlidingClickingListener lis = new SlidingClickingListener();

        // Add the listener to the buttons
        simulation.addActionListener(lis);
        loadf.addActionListener(lis);
        savef.addActionListener(lis);
        stop.addActionListener(lis);
        load.addActionListener(lis);
        save.addActionListener(lis);

        // Add the listener to the sliders
        tempSlider.addChangeListener(lis);
        soilSlider.addChangeListener(lis);
        humidSlider.addChangeListener(lis);

        // Set up the main GUI frame
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(620, 470);
        frame.setVisible(true);
    }
}
