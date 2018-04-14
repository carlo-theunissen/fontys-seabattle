/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Indicate the result of a shot.
 * @author Nico Kuijpers
 */
public enum ShotType {
    MISSED,   // Shot missed
    HIT,      // Shot hit
    SUNK,     // PlaceShip sunk
    ALLSUNK;  // All ships sunk
}
