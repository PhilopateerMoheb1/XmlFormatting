
import static Phase1.Compressor.compress;
import static Phase1.Compressor.expand;
import Phase1.Deformatter;
import Phase1.Formatter;
import static Phase1.JSON_Converter.converter;
import Phase1.User;
import Phase1.XMLChecker;
import Phase1.Xmfile;
import phase2.classesGenerator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.swing.JFileChooser;
import phase2.Vizualizer;

/**
 *
 * @author SHEREF ZEDAN
 *
 * @fix&Edits Ghaith Bassam Zaza
 * @support Undo &Redo Mina Mounir Farid
 */
public class dsgui extends javax.swing.JFrame {

    User[] users;
    /**
     * Creates new form dsgui
     */
    Xmfile x1;
    XMLChecker checker;
    Stack undo , redo ,inputStack,outputStack;
    public dsgui() {
        initComponents();
        undo = new Stack() ;
        redo =  new Stack() ;
        inputStack = new Stack() ;
        outputStack = new Stack() ;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        scrollbar1 = new java.awt.Scrollbar();
        TOJSON = new javax.swing.JButton();
        EXPAND = new javax.swing.JButton();
        FORMAT = new javax.swing.JButton();
        OPEN = new javax.swing.JButton();
        COMPRESS = new javax.swing.JButton();
        CORRECT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        CHECK = new javax.swing.JButton();
        MINIFY = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        Analysis = new javax.swing.JButton();
        clear1 = new javax.swing.JButton();
        clear2 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("XML EDITOR");
        setResizable(false);

        TOJSON.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TOJSON.setForeground(new java.awt.Color(51, 51, 255));
        TOJSON.setText("TO JSON");
        TOJSON.setEnabled(false);
        TOJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TOJSONActionPerformed(evt);
            }
        });

        EXPAND.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EXPAND.setForeground(new java.awt.Color(51, 51, 255));
        EXPAND.setText("EXPAND");
        EXPAND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXPANDActionPerformed(evt);
            }
        });

        FORMAT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        FORMAT.setForeground(new java.awt.Color(51, 51, 255));
        FORMAT.setText("FORMAT");
        FORMAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FORMATActionPerformed(evt);
            }
        });

        OPEN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        OPEN.setForeground(new java.awt.Color(51, 51, 255));
        OPEN.setText("OPEN");
        OPEN.setPreferredSize(new java.awt.Dimension(70, 30));
        OPEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OPENActionPerformed(evt);
            }
        });

        COMPRESS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        COMPRESS.setForeground(new java.awt.Color(51, 51, 255));
        COMPRESS.setText("COMPRESS");
        COMPRESS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMPRESSActionPerformed(evt);
            }
        });

        CORRECT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CORRECT.setForeground(new java.awt.Color(51, 51, 255));
        CORRECT.setText("CORRECT");
        CORRECT.setEnabled(false);
        CORRECT.setMaximumSize(new java.awt.Dimension(70, 30));
        CORRECT.setMinimumSize(new java.awt.Dimension(70, 30));
        CORRECT.setPreferredSize(new java.awt.Dimension(70, 30));
        CORRECT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CORRECTActionPerformed(evt);
            }
        });

        inputArea.setColumns(20);
        inputArea.setRows(5);
        inputArea.setSelectedTextColor(new java.awt.Color(0, 204, 204));
        inputArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputAreaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(inputArea);

        outputArea.setEditable(false);
        outputArea.setColumns(20);
        outputArea.setRows(5);
        jScrollPane3.setViewportView(outputArea);

        CHECK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CHECK.setForeground(new java.awt.Color(51, 51, 255));
        CHECK.setText("CHECK");
        CHECK.setMaximumSize(new java.awt.Dimension(70, 30));
        CHECK.setMinimumSize(new java.awt.Dimension(70, 30));
        CHECK.setPreferredSize(new java.awt.Dimension(70, 30));
        CHECK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHECKActionPerformed(evt);
            }
        });

        MINIFY.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MINIFY.setForeground(new java.awt.Color(51, 51, 255));
        MINIFY.setText("MINIFY");
        MINIFY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MINIFYActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear.setForeground(new java.awt.Color(51, 51, 255));
        clear.setText("CLEAR");
        clear.setMaximumSize(new java.awt.Dimension(70, 30));
        clear.setMinimumSize(new java.awt.Dimension(70, 30));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        Analysis.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Analysis.setForeground(new java.awt.Color(51, 51, 255));
        Analysis.setText("ANALYSE");
        Analysis.setEnabled(false);
        Analysis.setMaximumSize(new java.awt.Dimension(70, 30));
        Analysis.setMinimumSize(new java.awt.Dimension(70, 30));
        Analysis.setPreferredSize(new java.awt.Dimension(70, 30));
        Analysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnalysisActionPerformed(evt);
            }
        });

        clear1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear1.setForeground(new java.awt.Color(51, 51, 255));
        clear1.setText("REDO");
        clear1.setMaximumSize(new java.awt.Dimension(70, 30));
        clear1.setMinimumSize(new java.awt.Dimension(70, 30));
        clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoActionPerformed(evt);
            }
        });

        clear2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear2.setForeground(new java.awt.Color(51, 51, 255));
        clear2.setText("UNDO");
        clear2.setMaximumSize(new java.awt.Dimension(70, 30));
        clear2.setMinimumSize(new java.awt.Dimension(70, 30));
        clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(CORRECT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(FORMAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TOJSON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CHECK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(COMPRESS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(MINIFY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EXPAND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(OPEN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Analysis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(clear1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clear2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(OPEN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CHECK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CORRECT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MINIFY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(COMPRESS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EXPAND, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FORMAT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TOJSON, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Analysis, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clear2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clear1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Analysis.getAccessibleContext().setAccessibleName("analysis");

        pack();
    }// </editor-fold>                        

    private void OPENActionPerformed(java.awt.event.ActionEvent evt) {                                     
        undo.push("open");
        outputArea.setText(null);
        OPEN.setText("OPEN FILE");
        try {
            // TODO add your handling code here:
            x1 = new Xmfile();
            String filename = x1.opener();
            x1.reader();
            inputArea.setText("");
            outputArea.append("file: " + filename + " opened successfully!\n");
            outputArea.setForeground(Color.decode("#5EBA7D"));
            //       System.out.println(filename);
            x1.create_output_file(filename);
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            // scan.nextLine();
            while (scan.hasNextLine()) {
                inputArea.append(scan.nextLine() + "\n");
            }
            inputArea.requestFocus();
            CORRECT.setEnabled(false);
            Analysis.setEnabled(false);
            TOJSON.setEnabled(false);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(dsgui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ne) {
            outputArea.append("no file selected");
        }


    }                                    
       
    private void FORMATActionPerformed(java.awt.event.ActionEvent evt) {                                       
        undo.push("format");
        outputArea.setText(null);

        String s = inputArea.getText();
        inputStack.push(s);
        if (s == null || s.isBlank()) {
            outputArea.append("please insert text to format\n");
            outputArea.setForeground(Color.red);
            return;
        }
        String v = Formatter.format(s);
        // System.out.println(v);
        outputArea.append("formatted successfully!\n");
        outputArea.setForeground(Color.decode("#5EBA7D"));
        inputArea.setText(v);
    }                                      

    private void COMPRESSActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            undo.push("compress");
            outputArea.setText(null);
            String str = inputArea.getText();
            if (str == null || str.isBlank()) {
                outputArea.append("please insert text to compress\n");
                return;
            }
            JFileChooser fc = new JFileChooser();
            fc.showSaveDialog(jPanel1);
            File file = fc.getSelectedFile();
            file = new File(file.toString() + ".z");
            inputStack.push(file) ;
            String com = compress(str);
            FileWriter fw;

            fw = new FileWriter(file);
            fw.write(com);
            fw.close();
            outputArea.append("file compressed to " + file.getName() + " successfully\nold file size: " + str.length() + " bytes\ncompressed size: " + com.length() + " bytes \n");
            outputArea.setForeground(Color.decode("#5EBA7D"));
        } catch (IOException ex) {
            Logger.getLogger(dsgui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StringIndexOutOfBoundsException e) {
            outputArea.append("compresion failed please make sure your code does not contain any non ascii chars");
            outputArea.setForeground(Color.red);
        }

    }                                        

    private void CORRECTActionPerformed(java.awt.event.ActionEvent evt) {                                        
        outputArea.setText(null);
        undo.push("correct") ;
        try {
            String[] v = null;
            checker.Check();
            inputStack.push(inputArea.getText());
            checker.correct();
            int i = checker.getErrorCount();
            v = checker.getErrors();
            outputArea.append("number of errors  = " + i + "\n");
            for (int j = 0; j < i; j++) {

                outputArea.append(v[j] + "\n");
            }
            inputArea.setText(null);
            inputArea.append(checker.getCorrectXML());
            outputArea.append("XML corrected\n");
            outputArea.setForeground(Color.decode("#5EBA7D"));
            CORRECT.setEnabled(false);
            TOJSON.setEnabled(true);
            Analysis.setEnabled(true);
        } catch (Exception e) {
            outputArea.append("xml CANNOT be corrected!");
            outputArea.setForeground(Color.red);
        }
    }                                       

    private void EXPANDActionPerformed(java.awt.event.ActionEvent evt) {                                       
        undo.push("expand");
        outputArea.setText(null);
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(jPanel1);
        File file = fc.getSelectedFile();
        if (file == null) {
            return;
        }
        String[] name = file.getName().split("\\.");
        if (!name[name.length - 1].equals("z")) {
            outputArea.append("invalide extention");
            outputArea.setForeground(Color.red);;
            return;
        }
        String com;
        try {

            com = new String(Files.readAllBytes(file.toPath()));
            System.out.println(com);
            inputArea.setText(expand(com));
            outputArea.append("expanded " + file.getName() + " successfully\n");
            outputArea.setForeground(Color.decode("#5EBA7D"));
            CORRECT.setEnabled(false);
            TOJSON.setEnabled(false);
            Analysis.setEnabled(false);
        } catch (IOException ex) {
            Logger.getLogger(dsgui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StringIndexOutOfBoundsException e) {
            outputArea.append("expansion failed your file might be damaged.");
             outputArea.setForeground(Color.red);
        }

    }                                      

    private void TOJSONActionPerformed(java.awt.event.ActionEvent evt) {                                       
        undo.push("TOJSON");
        outputArea.setText(null);
        String v = inputArea.getText();
        inputStack.push(v) ;
        if (v == null || v.isBlank()) {
            outputArea.append("please insert text to convert\n");
            return;
        }
        String d = converter(checker).toString();

        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(jPanel1);
        File file = fc.getSelectedFile();
        
        if (file == null) {
            return;
        }
        file = new File(file.toString() + ".json");
        inputStack.push(file);
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            fw.write(d);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(dsgui.class.getName()).log(Level.SEVERE, null, ex);
        }
        outputArea.append("file converted to " + file.getName() + " successfully\n");
        outputArea.setForeground(Color.decode("#5EBA7D"));
    }                                      

    private void CHECKActionPerformed(java.awt.event.ActionEvent evt) {                                      
        undo.add("check");
        outputArea.setText(null);
        if (inputArea.getText().isBlank()) {
            outputArea.append("no text found!");
            outputArea.setForeground(Color.red);
            return;
        }
        try {
            checker = new XMLChecker(inputArea.getText());
            checker.Check();
            CORRECT.setEnabled(true);
            if (checker.isCorrect() == true) {
                outputArea.append("XML is correct!\n");
                outputArea.setForeground(Color.decode("#5EBA7D"));
                TOJSON.setEnabled(true);
                Analysis.setEnabled(true);
                CORRECT.setEnabled(false);
            } else if (checker.isCorrect() == false) {
                outputArea.append("XML is not correct!\n");
                outputArea.setForeground(Color.red);
                CORRECT.setEnabled(true);
            }

        } catch (IllegalArgumentException e) {
            outputArea.append("XML is not correct\n");
            outputArea.setForeground(Color.red);
        }
    }                                     
   
    private void MINIFYActionPerformed(java.awt.event.ActionEvent evt) {                                       
        undo.add("minify") ;
       outputArea.setText(null);
        String c = inputArea.getText();
        inputStack.push(c) ;
        if (c == null || c.isBlank()) {
            outputArea.append("please insert text to minify\n");
            outputArea.setForeground(Color.red);
            return;
        }
        c = Deformatter.deformate(c);
        outputArea.append("xml minified successfully\n");
        inputArea.setText(c);
        outputArea.setForeground(Color.decode("#5EBA7D"));
    }                                      
    private void clearCommand()
    { 
        inputStack.push(inputArea.getText());
        outputStack.push(outputArea.getText());
        inputArea.setText(null);
        outputArea.setText(null);
    }
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {                                      
        undo.add("clear");
        clearCommand();
    }                                     

    private void AnalysisActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            outputArea.setText(null);
            XMLChecker generate = new XMLChecker(Deformatter.deformate(inputArea.getText()));
            generate.Check();
            generate.correct();
            XMLChecker generate2 = new XMLChecker(Deformatter.deformate(generate.getCorrectXML()));
            classesGenerator v = new classesGenerator();
            ArrayList user1 = v.generate(generate2);
            users = (User[]) user1.toArray(User[]::new);
            boolean generframe = generate2.isCorrect();

            while (generframe) {
                generframe = false;
                part2 i = new part2(users);
                i.setLocationRelativeTo(null);
                i.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                i.show();
            }
        } catch (IllegalArgumentException e) {
            outputArea.append("the format of the code cannot be analysed!");
            outputArea.setForeground(Color.red);
        }
    }                                        

    private void inputAreaKeyPressed(java.awt.event.KeyEvent evt) {                                     
       
        CORRECT.setEnabled(false);
        Analysis.setEnabled(false);
        TOJSON.setEnabled(false);
    }                                    

    private void redoActionPerformed(java.awt.event.ActionEvent evt) {                                     
          try
          { 
              String command = (String) (redo.pop());
            undo.push(command);
            switch(command)
            { 
                case "format": 
                    FORMATActionPerformed(evt);
                    break;
                case "minify":
                    MINIFYActionPerformed(evt);
                    break ;
                case "correct":
                    CORRECTActionPerformed(evt);
                    break;
                case "check":
                    CHECKActionPerformed(evt);
                    break;
                case "open":
                    OPENActionPerformed(evt);
                    break;
                case "expand":
                    EXPANDActionPerformed(evt);
                    break;
                case "clear":
                    clearActionPerformed(evt);
                    break;
                case "TOJSON":
                    TOJSONActionPerformed(evt);
                    break;
                case "compressed":
                    TOJSONActionPerformed(evt);
                    break;
                default:
                    inputArea.setText((String)(inputStack.pop()));
            }
          }catch(RuntimeException e)
          { 
            outputArea.setText("redo operation is not possible yet\n");
            outputArea.setForeground(Color.red);
          }
            
    }                                    

    private void undoActionPerformed(java.awt.event.ActionEvent evt) {                                     
        try
        { 
            String command = (String) (undo.pop());
            redo.push(command);
        switch(command)
        { 
            case "format":
            case "minify":
            case "correct":    
               inputArea.setText((String)(inputStack.pop()));
               outputArea.setText(null);
                break;
            case "check": 
                outputArea.setText(null);
                TOJSON.setEnabled(false);
                Analysis.setEnabled(false);
                CORRECT.setEnabled(false);
                break ;
            case "open":
            case "expand":    
                clearCommand() ;
                break ;
            case "clear":
                inputArea.setText((String)(inputStack.pop()));
                outputArea.setText((String)(outputStack.pop()));
                break;
            case "TOJSON":
                File f = (File)(inputStack.pop());
                inputArea.setText((String)(inputStack.pop()));
                outputArea.setText(null);
                              
                if(f.delete()) 
                { 
                    outputArea.setText("JSON file deleted\n");
                    outputArea.setForeground(Color.green);
                }
                else
                { 
                   outputArea.setText("JSON file deletion failed\n");
                    outputArea.setForeground(Color.red); 
                }
                break;
            case "compress":
                 File fi = (File)(inputStack.pop());
                 if(fi.delete()) 
                { 
                    outputArea.setText("compressed file deleted\n");
                    outputArea.setForeground(Color.green);
                }
                else
                { 
                   outputArea.setText("compressed file deletion failed\n");
                    outputArea.setForeground(Color.red); 
                }
                break;
                         
        }
        }
        
        catch(RuntimeException e)
        { 
            outputArea.setText("undo operation is not possible yet\n");
            outputArea.setForeground(Color.red);
        }
        
        
    }                                    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dsgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dsgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dsgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dsgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        dsgui gui = new dsgui();
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setLocationRelativeTo(null);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton Analysis;
    private javax.swing.JButton CHECK;
    private javax.swing.JButton COMPRESS;
    private javax.swing.JButton CORRECT;
    private javax.swing.JButton EXPAND;
    private javax.swing.JButton FORMAT;
    private javax.swing.JButton MINIFY;
    private javax.swing.JButton OPEN;
    private javax.swing.JButton TOJSON;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clear;
    private javax.swing.JButton clear1;
    private javax.swing.JButton clear2;
    private javax.swing.JTextArea inputArea;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextArea outputArea;
    private java.awt.Scrollbar scrollbar1;
    // End of variables declaration                   
    private javax.swing.JButton search;
    private javax.swing.JButton GetMutul;
    private javax.swing.JButton GetAnalysis;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JButton Visuallizer;

    private void openfileActionperformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

}
