/**
 *
 * @author Ahmed Abdelmotelb 
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

public class Xmfile {

    private String address , content , output_file_address = ""  , Foldar_address , json_file_adress="";
    private String[] str;
    public ArrayList<String> labels;
    private boolean Is_compressed;

    public Xmfile() {
        Is_compressed = false;
        labels = new ArrayList<String>();
    }

    public Xmfile(String address) {
        this();
        this.address = address;
    }

    public void print() {
        for (String line : labels) {
            System.out.println(line);
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getAddress() {
        return address;
    }



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
    public void writer(String upadted_content, boolean yes) throws IOException {
        FileWriter w = new FileWriter(output_file_address);
        w.write(upadted_content);
        w.close();
        Is_compressed = yes;
    } // O(1)

    
    public void writer(String upadted_content, boolean yes  , String location ) throws IOException {
        FileWriter w = new FileWriter(location);
        w.write(upadted_content);
        w.close();
        Is_compressed = yes;
        
    }

    public void display(javax.swing.JTextArea jTextArea3) {
        jTextArea3.setText("");
        try {
            // TODO add your handling code here:    
            File file = new File(output_file_address);
            Scanner scan = new Scanner(file);
            // scan.nextLine();
            while (scan.hasNextLine()) {
                jTextArea3.append(scan.nextLine() + "\n");
            }
            jTextArea3.requestFocus();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DsGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // O(n) where n is number of lines in file
    
    
     public void display(javax.swing.JTextArea jTextArea3 , String location) {
        jTextArea3.setText("");
        try {
            // TODO add your handling code here:    
            File file = new File(location);
            Scanner scan = new Scanner(file);
            // scan.nextLine();
            while (scan.hasNextLine()) {
                jTextArea3.append(scan.nextLine() + "\n");
            }
            jTextArea3.requestFocus();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DsGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create_output_file(String filename){
        String [] arr = filename.split("\\\\");
        Foldar_address = "";
        for(int i=0 ; i<arr.length-1 ; ++i)
            Foldar_address += arr[i] + "\\";
        output_file_address += Foldar_address + "XML-EDITOR-OUTPUT.xml";
        json_file_adress += Foldar_address + "XML-EDITOR-OUTPUT.json";
        System.out.println(output_file_address);     
    }

}
