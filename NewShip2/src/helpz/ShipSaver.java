package helpz;

import java.io.*;

import ship.Ship;

public class ShipSaver {
    public static void saveShip(Ship ship, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(ship);
            System.out.println("Ship saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving ship: " + e.getMessage());
        }
    }

    public static Ship loadShip(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Ship ship = (Ship) in.readObject();
            System.out.println("Ship loaded successfully!");
            return ship;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading ship: " + e.getMessage());
            return null;
        }
    }
}

//// IMPLEMENTATION
//public class Main {
//    public static void main(String[] args) {
//        // Example ship creation
//        Hull hull = new Hull(); // Assuming Hull constructor exists
//        Crew crew = new Crew(); // Assuming Crew constructor exists
//        Ship ship = new Ship("USS Enterprise", hull, crew);
//        
//        // Save the ship
//        ShipSaver.saveShip(ship, "shipData.ser");
//
//        // Later load the ship back into the program
//        Ship loadedShip = ShipSaver.loadShip("shipData.ser");
//        if (loadedShip != null) {
//            System.out.println("Loaded ship name: " + loadedShip.getName());
//        }
//    }
//}
