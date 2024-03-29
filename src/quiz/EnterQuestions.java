/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;
import java.util.*;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class EnterQuestions extends javax.swing.JFrame {

    /**
     * Creates new form EnterQuestions
     */
    public EnterQuestions() {
        initComponents(); 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setExtendedState(this.MAXIMIZED_BOTH);
    }
    void clearControls()
    {
        txtaQuestion.setText("");
        txtaExplanation.setText("");        
        txtChoiceA.setText("");
        txtChoiceB.setText("");
        txtChoiceC.setText("");
        txtChoiceD.setText("");
        cmbAnswer.setSelectedIndex(0);
    }
    boolean isClearControls()
    {
        if(txtaQuestion.getText().equals("") || txtChoiceA.getText().equals("") || txtChoiceB.getText().equals("") || txtChoiceC.getText().equals("") || txtChoiceD.getText().equals(""))
        { 
            return true;
        }        
        else 
            return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblQuestion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaQuestion = new javax.swing.JTextArea();
        lblExplanation = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaExplanation = new javax.swing.JTextArea();
        lblChoiceA = new javax.swing.JLabel();
        txtChoiceA = new javax.swing.JTextField();
        lblChoiceB = new javax.swing.JLabel();
        txtChoiceB = new javax.swing.JTextField();
        lblChoiceC = new javax.swing.JLabel();
        txtChoiceC = new javax.swing.JTextField();
        lblChoiceD = new javax.swing.JLabel();
        txtChoiceD = new javax.swing.JTextField();
        lblAnswer = new javax.swing.JLabel();
        cmbAnswer = new javax.swing.JComboBox<>();
        lblTest = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmbSubject = new javax.swing.JComboBox<>();
        lblSubject = new javax.swing.JLabel();
        cmbChapter = new javax.swing.JComboBox<>();
        lblChapter = new javax.swing.JLabel();
        lblSubTopic = new javax.swing.JLabel();
        cmbSubTopic = new javax.swing.JComboBox<>();
        btnModify = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("QUIZ");
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ENTER QUESTIONS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Question:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblQuestion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblQuestion.setText("Question :");

        txtaQuestion.setColumns(20);
        txtaQuestion.setRows(5);
        jScrollPane1.setViewportView(txtaQuestion);

        lblExplanation.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblExplanation.setText("Explanation:");

        txtaExplanation.setColumns(20);
        txtaExplanation.setRows(5);
        jScrollPane2.setViewportView(txtaExplanation);

        lblChoiceA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblChoiceA.setText("Choice A:");

        lblChoiceB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblChoiceB.setText("Choice B:");

        lblChoiceC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblChoiceC.setText("Choice c:");

        lblChoiceD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblChoiceD.setText("Choice D:");

        lblAnswer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAnswer.setText("Answer :");

        cmbAnswer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choice A", "Choice B", "Choice C", "Choice D" }));

        lblTest.setText("jLabel2");
        lblTest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTestMouseClicked(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExplanation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAnswer))
                        .addContainerGap(131, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblChoiceC)
                        .addGap(220, 220, 220)
                        .addComponent(lblChoiceD)
                        .addGap(18, 18, 18)
                        .addComponent(txtChoiceD, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTest)
                        .addGap(84, 84, 84))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtChoiceC, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblChoiceA)
                                .addGap(18, 18, 18)
                                .addComponent(txtChoiceA, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(lblChoiceB)
                        .addGap(18, 18, 18)
                        .addComponent(txtChoiceB, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(64, 64, 64))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuestion)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblExplanation)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChoiceA)
                        .addComponent(txtChoiceA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChoiceB)
                        .addComponent(txtChoiceB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChoiceC)
                        .addComponent(txtChoiceC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChoiceD)
                        .addComponent(txtChoiceD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTest)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAnswer)
                    .addComponent(cmbAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Questions From:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cmbSubject.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSubjectItemStateChanged(evt);
            }
        });
        cmbSubject.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSubjectFocusGained(evt);
            }
        });

        lblSubject.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSubject.setText("Subject :");

        cmbChapter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbChapterItemStateChanged(evt);
            }
        });

        lblChapter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblChapter.setText("Chapter :");

        lblSubTopic.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSubTopic.setText("SubTopic :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblChapter, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbChapter, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSubTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSubTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbChapter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblChapter)
                        .addComponent(lblSubTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbSubTopic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSubject)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnModify.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnModify.setText("MODIFY");

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSubjectFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSubjectFocusGained
        
        try
        {
            DBConnection ob = new DBConnection();
            String query = "SELECT DISTINCT subject FROM datatable";
            Statement stmt = ob.getDBConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                cmbSubject.addItem(rs.getString("subject"));
            }
            ob.getDBConnection().close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_cmbSubjectFocusGained

    private void cmbSubjectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSubjectItemStateChanged
        // TODO add your handling code here:
        //System.out.println(cmbSubject.getSelectedItem());        
        cmbChapter.removeAllItems();
        try
        {
            DBConnection ob = new DBConnection();
            
            String query = "SELECT DISTINCT chapter FROM datatable where subject='"+cmbSubject.getSelectedItem()+"'";
            Statement stmt = ob.getDBConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //cmbChapter.removeItemAt(0);
            while(rs.next())
            {
                cmbChapter.addItem(rs.getString("chapter"));
            }
            ob.getDBConnection().close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_cmbSubjectItemStateChanged

    private void cmbChapterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbChapterItemStateChanged
        // TODO add your handling code here:
        //System.out.println(cmbChapter.getSelectedItem());        
        cmbSubTopic.removeAllItems();
        try
        {
            DBConnection ob = new DBConnection();
            String query = "SELECT DISTINCT subtopic FROM datatable where chapter='"+cmbChapter.getSelectedItem()+"'";
            Statement stmt = ob.getDBConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //cmbChapter.removeItemAt(0);
            while(rs.next())
            {
                cmbSubTopic.addItem(rs.getString("subtopic"));
            }
            ob.getDBConnection().close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_cmbChapterItemStateChanged

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearControls();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String subjCode = cmbSubject.getSelectedItem().toString().substring(0,3); 
        String chaptCode = cmbChapter.getSelectedItem().toString().substring(0,3);
        String subTopicCode = cmbSubTopic.getSelectedItem().toString().substring(0,3);
        String qCode = subjCode+""+chaptCode+""+subTopicCode;
        if(isClearControls() == false)
        {
            try
            {
                DBConnection ob = new DBConnection();       
                String query = "INSERT INTO questionstable (qCode, question, explanation, choiceA, choiceB, choiceC, choiceD, answer) VALUES (?,?,?,?,?,?,?,?)";
            
                PreparedStatement preparedStmt = ob.getDBConnection().prepareStatement(query);
                preparedStmt.setString (1, qCode);
                preparedStmt.setString(2, txtaQuestion.getText());
                preparedStmt.setString(3, txtaExplanation.getText());
                preparedStmt.setString(4, txtChoiceA.getText());
                preparedStmt.setString(5, txtChoiceB.getText());
                preparedStmt.setString(6, txtChoiceC.getText());
                preparedStmt.setString(7, txtChoiceD.getText());
                preparedStmt.setString(8, cmbAnswer.getSelectedItem().toString());
            
                preparedStmt.execute();
                ob.getDBConnection().close();
                JOptionPane.showMessageDialog(rootPane, "Data Inserted Successfully");
                clearControls();
            }
            catch (HeadlessException | ClassNotFoundException | SQLException e)
            {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Fields Cannot be Blank.....");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.setVisible(false);
        Admin ob = new Admin();
        ob.setVisible(true);
    }//GEN-LAST:event_btnExitActionPerformed

    private void lblTestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTestMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lblTestMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser ob = new JFileChooser();
        int j=ob.showOpenDialog(this); 
        DBConnection conn = new DBConnection();      
        PreparedStatement pstmt;
        FileInputStream fs;
        File f;
        String filepath=null;
        if(j==JFileChooser.APPROVE_OPTION)
        {    
            f=ob.getSelectedFile();    
            filepath=f.getPath(); 
            try {
                fs=new FileInputStream(f);
                pstmt = conn.getDBConnection().prepareStatement("INSERT INTO imagetest VALUES(?)");
                pstmt.setBinaryStream(1,fs,(int)f.length());
                
                BufferedImage image = ImageIO.read(new File(filepath));
                lblTest.setIcon(new ImageIcon(image));
                
                pstmt.executeUpdate();
            } catch (ClassNotFoundException | SQLException | FileNotFoundException ex) {
                Logger.getLogger(PhotoTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PhotoTest.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(EnterQuestions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnterQuestions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnterQuestions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnterQuestions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnterQuestions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbAnswer;
    private javax.swing.JComboBox<String> cmbChapter;
    private javax.swing.JComboBox<String> cmbSubTopic;
    private javax.swing.JComboBox<String> cmbSubject;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnswer;
    private javax.swing.JLabel lblChapter;
    private javax.swing.JLabel lblChoiceA;
    private javax.swing.JLabel lblChoiceB;
    private javax.swing.JLabel lblChoiceC;
    private javax.swing.JLabel lblChoiceD;
    private javax.swing.JLabel lblExplanation;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JLabel lblSubTopic;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblTest;
    private javax.swing.JTextField txtChoiceA;
    private javax.swing.JTextField txtChoiceB;
    private javax.swing.JTextField txtChoiceC;
    private javax.swing.JTextField txtChoiceD;
    private javax.swing.JTextArea txtaExplanation;
    private javax.swing.JTextArea txtaQuestion;
    // End of variables declaration//GEN-END:variables

    private void setIcon(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
