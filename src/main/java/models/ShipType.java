/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Indicate type of ship.
 * @author Nico Kuijpers
 */
public enum ShipType {
    AIRCRAFTCARRIER,  // Aircraft carrier (size 5)
    BATTLESHIP,       // Battle ship (size 4)
    CRUISER,          // Cruister (size 3)
    SUBMARINE,        // Submarine (size 3)
    MINESWEEPER;      // Mine sweeper (size 2)
}
