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
import java.awt.*;
import java.text.DecimalFormat;

public class GreenHouseThread extends Thread {
    // Colour constants
    final Color RED = new Color(188, 0, 0);
    final Color GREEN = new Color(45, 189, 16);

    // Thread objects for each different part of the greenhouse
    private GreenHouseEnvironment greenhouseEnvironment;
    private TempThread temperatureController;
    private HumidThread humidityController;
    private SoilThread soilMoistureController;

    // JLabels for each of the GUI's current greenhouse readings / elements
    private JLabel currentTempReading;
    private JLabel currentSoilReading;
    private JLabel currentHumidityReading;
    private JLabel currentFurnaceStatus;
    private JLabel currentACStatus;
    private JLabel currentSprinklerStatus;
    private JLabel currentHumidifierStatus;


    /**
     * The constructor for the greenhouse thread
     *
     * @param thread1     greenhouse environment thread that will store all the synchronized data
     * @param thread2     temperature thread
     * @param thread3     humidity thread
     * @param thread4     soil moisture thread
     * @param cT          JLabel from the GUI for the current temperature reading
     * @param cS          JLabel from the GUI for the current soil moisture reading
     * @param cH          JLabel from the GUI for the current humidity reading
     * @param acLabel     JLabel from the GUI for displaying whether the AC is currently on or off
     * @param furnLabel   JLabel from the GUI for displaying whether the AC is currently on or off
     * @param sprinkLabel JLabel from the GUI for displaying whether the sprinkler is currently on or off
     * @param humidLabel  JLabel from the GUI for displaying whether the humidifier is currently on or off
     */
    public GreenHouseThread(GreenHouseEnvironment thread1, TempThread thread2, HumidThread thread3, SoilThread thread4, JLabel cT, JLabel cS, JLabel cH, JLabel acLabel, JLabel furnLabel, JLabel sprinkLabel, JLabel humidLabel) {
        greenhouseEnvironment = thread1;
        temperatureController = thread2;
        humidityController = thread3;
        soilMoistureController = thread4;
        currentTempReading = cT;
        currentSoilReading = cS;
        currentHumidityReading = cH;
        currentACStatus = acLabel;
        currentFurnaceStatus = furnLabel;
        currentSprinklerStatus = sprinkLabel;
        currentHumidifierStatus = humidLabel;
    }

    /**
     * Method to enable running all the subthreads
     */
    public void runSubThreads() {
        greenhouseEnvironment.runSubThreads = true;
        start();
    }

    /**
     * Method to disable running all the subthreads
     */
    public void disableSubThreads() {
        greenhouseEnvironment.runSubThreads = false;
    }

    public void run() {
        temperatureController.start();
        humidityController.start();
        soilMoistureController.start();

        // Keeps the GUI's elements updated as long as the simulation is still running
        while (greenhouseEnvironment.runSubThreads == true) {
            // Initialize decimal formatter to strip down to only a certain number of decimal places
            DecimalFormat decimals = new DecimalFormat("#.##");
            decimals.format(greenhouseEnvironment.temperature());
            // Updates the Temperature elements in the GUI
            currentTempReading.setText(decimals.format(greenhouseEnvironment.temperature()).toString());
            if (temperatureController.isFurnON()) {
                currentFurnaceStatus.setBackground(GREEN);
                currentFurnaceStatus.setText(" FURNACE: ACTIVE");
            } else {
                currentFurnaceStatus.setBackground(RED);
                currentFurnaceStatus.setText(" FURNACE: INACTIVE");
            }
            if (temperatureController.isACON()) {
                currentACStatus.setBackground(GREEN);
                currentACStatus.setText(" AIR CONDITIONER: ACTIVE");
            } else {
                currentACStatus.setBackground(RED);
                currentACStatus.setText(" AIR CONDITIONER: INACTIVE");
            }

            // Updates the Sprinkler elements in the GUI
            currentSoilReading.setText(decimals.format(greenhouseEnvironment.soilMoisture()).toString());
            if (soilMoistureController.isSprinkON()) {
                currentSprinklerStatus.setBackground(GREEN);
                currentSprinklerStatus.setText(" SPRINKLER: ACTIVE");
            } else {
                currentSprinklerStatus.setBackground(RED);
                currentSprinklerStatus.setText(" SPRINKLER: INACTIVE");
            }

            // Updates the Humidifier elements in the GUI
            currentHumidityReading.setText(decimals.format(greenhouseEnvironment.humidity()).toString());
            if (humidityController.isHumidON()) {
                currentHumidifierStatus.setBackground(GREEN);
                currentHumidifierStatus.setText(" HUMIDIFIER: ACTIVE");
            } else {
                currentHumidifierStatus.setBackground(RED);
                currentHumidifierStatus.setText(" HUMIDIFIER: INACTIVE");
            }
        }
        // Disables all the greenhouse elements when the thread has been given the stop command
        currentFurnaceStatus.setBackground(RED);
        currentFurnaceStatus.setText(" FURNACE: INACTIVE");
        currentACStatus.setBackground(RED);
        currentACStatus.setText(" AIR CONDITIONER: INACTIVE");
        currentSprinklerStatus.setBackground(RED);
        currentSprinklerStatus.setText(" SPRINKLER: INACTIVE");
        currentHumidifierStatus.setBackground(RED);
        currentHumidifierStatus.setText(" HUMIDIFIER: INACTIVE");

        return;
    }

}
