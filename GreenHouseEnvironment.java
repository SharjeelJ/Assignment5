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

import java.util.ArrayList;

public class GreenHouseEnvironment {
    // The shared values
    private double temperature = 0;
    private double soilMoisture = 0;
    private double humidity = 0;
    protected double tempChangeRate = 11;
    protected double humidChangeRate = 11;
    protected double soilChangeRate = 11;
    protected boolean runSubThreads = false;
    protected ArrayList <String> logData = new ArrayList();


    /**
     * Constructor for an environment
     *
     * @param t The initial temperature
     * @param s The initial soil moisture
     * @param h The initial humidity
     */
    public GreenHouseEnvironment(double t, double s, double h) {
        temperature = t;
        soilMoisture = s;
        humidity = h;
    }

    /**
     * Returns current temperature
     *
     * @return double - temperature
     */
    public double temperature() {
        return temperature;
    }

    /**
     * Returns current soilMoisture
     *
     * @return double - soilMoisture
     */
    public double soilMoisture() {
        return soilMoisture;
    }

    /**
     * Returns current humidity
     *
     * @return double - humidity
     */
    public double humidity() {
        return humidity;
    }

    /**
     * Updates temperature
     *
     * @param t the new temperature
     */
    public synchronized void changeTemp(double t) {
        temperature = t;
    }

    /**
     * Updates soil moisture
     *
     * @param s the new soil moisture
     */
    public synchronized void changeSoil(double s) {
        soilMoisture = s;
    }

    /**
     * Updates humidity
     *
     * @param h the new humidity
     */
    public synchronized void changeHumidity(double h) {
        humidity = h;
    }

}
