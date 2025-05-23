/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.FlatDarkLaf;
import core.controllers.FlightController;
import core.controllers.LocationController;
import core.controllers.PassengerController;
import core.controllers.PlaneController;
import core.views.AirportFrame;
import javax.swing.UIManager;


public class Main {
    public static void main(String args[]) {
       
        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                LocationController.loadLocationsFromJson();
                PlaneController.loadPlanesFromJson();
                PassengerController.loadPassengersFromJson();
                FlightController.loadFlightsFromJson();
                
                new AirportFrame().setVisible(true);
            }
        });
    }
}
