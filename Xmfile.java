/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Phase1;

/**
 *
 * @author SHEREF ZEDAN
 */

import dsgui.DsGui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFileChooser;

/**
 *
 * @author oy
 */
public class Xmfile {

    private String address , content , output_file_address = ""  , Foldar_address ;
    private String[] str;
    public String opener() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        address = filename;
        return filename;
    } // O(1)

    public void reader() throws FileNotFoundException {
        content = "";
        File file = new File(address);
        Scanner scan = new Scanner(file);
       //scan.nextLine();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if(line.length()>5 && line.substring(0, 5).equals("<?xml")) continue;
            content = content.concat(line.trim() + "\n");
        }
        str = content.split("\n");
    } // O(n) where n is number of lines in file

    
    

    
    public void create_output_file(String filename){
        String [] arr = filename.split("\\\\");
        Foldar_address = "";
        for(int i=0 ; i<arr.length-1 ; ++i)
            Foldar_address += arr[i] + "\\";
        output_file_address += Foldar_address + "XML-EDITOR-OUTPUT.xml";
        System.out.println(output_file_address);     
    }

}
