/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ratesweb.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Static class for simulation of DB, saving and reading from file
 * @author zdene
 */
public class FilesDB {
    /***
     * name of file
     */
    private static String nameOfFile = "ratesDB.txt";
    
    /***
     * Save data to file
     * @param data 
     */
    public static void saveToFile(String data) {
        File file = new File(nameOfFile); //initialize File object and passing path as argument  
        boolean result;
        try {
            result = file.createNewFile();  //creates a new file  
            if (result) // test if successfully created a new file  
            {
                System.out.println("file created " + file.getCanonicalPath()); //returns the path string  
            } else {
                System.out.println("File already exist at location: " + file.getCanonicalPath());
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Write in file
            bw.write(data);

            // Close connection
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();    //prints exception if any  
        }
    }
    
    /***
     * Read data from file
     * @return 
     */
    public static String readFromFile()
    {
        String data = "";
        try {
            data =  Files.readString(Paths.get(nameOfFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            return data;
        }
    }
    
}
