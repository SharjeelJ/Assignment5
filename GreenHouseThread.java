package Assignment5;

import javax.swing.*;

public class GreenHouseThread extends Thread {

    // Thread objects for each different part of the greenhouse
    private GreenHouseEnvironment greenhouseEnvironment;
    private TempThread temperatureController;
    private HumidThread humidityController;
    private SoilThread soilMoistureController;

    // JLabels for each of the GUI's current greenhouse readings
    private JLabel currentTempReading;
    private JLabel currentSoilReading;
    private JLabel currentHumidityReading;

    /**
     * The constructor for the greenhouse thread
     *
     * @param thread1 greenhouse environment thread that will store all the synchronized data
     * @param thread2 temperature thread
     * @param thread3 humidity thread
     * @param thread4 soil moisture thread
     * @param cT      JLabel from the GUI for the current temperature reading
     * @param cS      JLabel from the GUI for the current soil moisture reading
     * @param cH      JLabel from the GUI for the current humidity reading
     */
    public GreenHouseThread(GreenHouseEnvironment thread1, TempThread thread2, HumidThread thread3, SoilThread thread4, JLabel cT, JLabel cS, JLabel cH) {
        super();
        greenhouseEnvironment = thread1;
        temperatureController = thread2;
        humidityController = thread3;
        soilMoistureController = thread4;
        currentTempReading = cT;
        currentSoilReading = cS;
        currentHumidityReading = cH;
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
        // TODO Fix up the remaining threads & Update the GUI's elements
        temperatureController.start();
        //humidityController.start();
        //soilMoistureController.start();

        while (greenhouseEnvironment.runSubThreads == true) {
            currentTempReading.setText(Double.toString(greenhouseEnvironment.temperature()));
        }
        return;
    }

}
