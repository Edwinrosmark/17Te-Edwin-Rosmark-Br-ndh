/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gameoflife;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import java.awt.event.*;

/**
 *
 * @author Edwin
 */
public class Gameoflife {

    private int worldWidth = 40;
    private int worldHeight = 20;
    private char[][] world = new char[worldWidth][worldHeight];

    public Gameoflife() {
        initWorld();
        setSeed();
        startGame();
    }

    private void initWorld() {
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldHeight; j++) {
                world[i][j] = '.';
            }
        }
    }

    private void setSeed() {
        // Fyll en rektangel mitt på spelplanen.
        for (int i = worldWidth / 2 - 1; i < worldWidth / 2 + 2; i++) {
            for (int j = worldHeight / 2 - 1; j < worldHeight / 2 + 2; j++) {
                world[i][j] = 'X';
            }

        }
    }

    private int checkSurroundings(int xPos, int yPos) {

        int countneighbours = 0;
        for (int x = xPos -1 ; x <= xPos +1; x++){
            for (int y = yPos -1; y <= yPos +1; y++) {
               if (!(x == xPos && y == yPos)){
                  if (world[x][y] == 'X') {
                     countneighbours++;
        }   
        }        
            }
        }
        return 0;
    }

    private void updateWorld() {
        // Skapa en temorär värld.
        char[][] newWorld = new char[worldWidth][worldHeight];

        /* Gå igenom den gamla världen, punkt för punkt och tillämpa reglerna.
    
Any live cell with fewer than two live neighbors dies, as if by underpopulation.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by overpopulation.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    
    
         */
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldHeight; j++) {
                newWorld[i][j] = '.';

                // Räkna antalet grannar
                //
                int neighbours = checkSurroundings(i, j);

                if (neighbours < 2) {
                    newWorld[i][j] = '.';
                }

                if (neighbours > 2 && neighbours < 4) {
                    newWorld[i][j] = 'X';
                }

                if (neighbours > 3) {
                    newWorld[i][j] = '.';
                    {
                    }

                    if (neighbours == 3 && world[i][j] == '.') {
                        newWorld[i][j] = 'X';
                    }
                }
            }
        }
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldHeight; j++) {
                world[i][j] = newWorld[i][j];
            }
        }
    }

    private void printWorld() {
        System.out.println("=========================================");
        for (int i = 0; i < this.worldHeight; i++) {
            for (int j = 0; j < this.worldWidth; j++) {
                System.out.print(world[j][i]);
            }
            System.out.println();
        }
    }

    private void startGame() {
        printWorld();
        while (true) {
            try {
                Thread.sleep(1000);
                updateWorld();
                printWorld();
            } catch (InterruptedException ex) {
                Logger.getLogger(Gameoflife.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Gameoflife();
    }

    
    
    
    
    
    
    
}
