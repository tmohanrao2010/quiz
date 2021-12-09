/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class StartQuizFinal extends javax.swing.JFrame {

    String answer;
    FileInputStream fs;
    File fQuestion,fChoiceA,fChoiceB,fChoiceC,fChoiceD;
    Statement stmt;
    ResultSet rs;
    int countCorrects=0, countIncorrects=0,nexts=0;
    int click=0;
    public StartQuizFinal(){
        initComponents(); 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        //this.setSize(dim.width, dim.height);
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
    }
    void setNoOfClick(int x)
    {
        click = x;
    } 
    void incrementClicks()
    {
        click++;
    }
    int getNoOfClicks()
    {
        return click;
    }
    void countCorrectMarks()
    {
        countCorrects++;
    }
    void countIncorrectMarks()
    {
        countIncorrects++;
    }
    int getcountCorrectMarks()
    {
        return countCorrects;
    }
    /*void setCountIncorrectMarks(int x)
    {
        countIncorrects = x;
    }*/
    int getCountIncorrectMarks()
    {
        return countIncorrects;
    }
    ResultSet getResultSet()
    {
        return rs;
    }
    void setAnswer(String str)
    {
        answer = str;
    }
    String getAnswer()
    {
        return answer;
    }
    /*String getAnswerResultSet() throws SQLException
    {
        answer = getResultSet().getString("answer");
        return answer;
    }*/
    void setNoOfNexts()
    {
        nexts++;
    }
    int getNoOfNexts()
    {
        return nexts;
    }
    public int getRows(ResultSet res)
    {
        int totalRows = 0;
        try {
            res.last();
            totalRows = res.getRow();
            res.beforeFirst();
        } 
        catch(SQLException ex)  {
            return 0;
        }
        return totalRows ;    
    }
    void loadResult()
    {
        String subjCode = cmbSubject.getSelectedItem().toString().substring(0,3); 
        String chaptCode = cmbChapter.getSelectedItem().toString().substring(0,3);
        String subTopicCode = cmbSubTopic.getSelectedItem().toString().substring(0,3);
        String qCode = subjCode+""+chaptCode+""+subTopicCode;
        
        DBConnection ob = new DBConnection();
        try
        {
            String query = "Select * from questionstable where qCode = '"+qCode+"' order by rand()";            
            stmt = ob.getDBConnection().createStatement();
            rs = stmt.executeQuery(query);
            btnLoad.setText("Loaded");
            btnLoad.setForeground(Color.RED);
            
            JOptionPane.showMessageDialog(rootPane, "Questions Loaded...");
            JOptionPane.showMessageDialog(rootPane, "Press Next....");
        }
        catch(SQLException e)
        {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartQuiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void clearControls()
    {
        Image defaultChoice = Toolkit.getDefaultToolkit().getImage("src/quiz/defaultChoice.jpg");
        lblBrowseA.setIcon(new ImageIcon(defaultChoice));
        lblBrowseB.setIcon(new ImageIcon(defaultChoice));
        lblBrowseC.setIcon(new ImageIcon(defaultChoice));
        lblBrowseD.setIcon(new ImageIcon(defaultChoice));
        defaultChoice = Toolkit.getDefaultToolkit().getImage("src/quiz/uploadQuestionHere.jpg");
        lblImageQuestion.setIcon(new ImageIcon(defaultChoice));
        txtaExplanation.setText("");
        pnlChoiceA.setBackground(Color.LIGHT_GRAY);
        pnlChoiceB.setBackground(Color.LIGHT_GRAY);
        pnlChoiceC.setBackground(Color.LIGHT_GRAY);
        pnlChoiceD.setBackground(Color.LIGHT_GRAY);
    }
    Boolean isClearControls()
    {
        String iconfilenameA = lblBrowseA.getIcon().toString();
        String fileNameA = iconfilenameA.substring(iconfilenameA.lastIndexOf("/"  ) + 1);
        String iconfilenameB = lblBrowseB.getIcon().toString();
        String fileNameB = iconfilenameB.substring(iconfilenameB.lastIndexOf("/"  ) + 1);
        String iconfilenameC = lblBrowseC.getIcon().toString();
        String fileNameC = iconfilenameC.substring(iconfilenameC.lastIndexOf("/"  ) + 1);
        String iconfilenameD = lblBrowseD.getIcon().toString();
        String fileNameD = iconfilenameD.substring(iconfilenameD.lastIndexOf("/"  ) + 1);
        if(fileNameA.equals("defaultChoice.jpg"))  
        { 
            JOptionPane.showMessageDialog(rootPane, "Please Upload Choice A");
            return true;
        } 
        if(fileNameB.equals("defaultChoice.jpg"))  
        { 
            JOptionPane.showMessageDialog(rootPane, "Please Upload Choice B");
            return true;
        }
        if(fileNameC.equals("defaultChoice.jpg"))  
        { 
            JOptionPane.showMessageDialog(rootPane, "Please Upload Choice C");
            return true;
        }
        if(fileNameD.equals("defaultChoice.jpg"))  
        { 
            JOptionPane.showMessageDialog(rootPane, "Please Upload Choice D");
            return true;
        }
        else
            return false;
    }
    void setFileQuestion(File f)
    {
        fQuestion = f;
    }
    File getFileQuestion()
    {
        return fQuestion;
    }
    void setFileChoiceA(File f)
    {
        fChoiceA = f;
    }
    File getFileChoiceA()
    {
        return fChoiceA;
    }
    void setFileChoiceB(File f)
    {
        fChoiceB = f;
    }
    File getFileChoiceB()
    {
        return fChoiceB;
    }
    void setFileChoiceC(File f)
    {
        fChoiceC = f;
    }
    File getFileChoiceC()
    {
        return fChoiceC;
    }
    void setFileChoiceD(File f)
    {
        fChoiceD = f;
    }
    File getFileChoiceD()
    {
        return fChoiceD;
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
        lblExplanation = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaExplanation = new javax.swing.JTextArea();
        lblChoiceA = new javax.swing.JLabel();
        lblChoiceB = new javax.swing.JLabel();
        lblChoiceC = new javax.swing.JLabel();
        lblChoiceD = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        pnlChoiceA = new javax.swing.JPanel();
        lblBrowseA = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        lblImageQuestion = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        pnlChoiceB = new javax.swing.JPanel();
        lblBrowseB = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        pnlChoiceC = new javax.swing.JPanel();
        lblBrowseC = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        pnlChoiceD = new javax.swing.JPanel();
        lblBrowseD = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblSubject = new javax.swing.JLabel();
        cmbSubject = new javax.swing.JComboBox<>();
        lblChapter = new javax.swing.JLabel();
        cmbChapter = new javax.swing.JComboBox<>();
        lblSubTopic = new javax.swing.JLabel();
        cmbSubTopic = new javax.swing.JComboBox<>();
        btnNext = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblSubTopic1 = new javax.swing.JLabel();
        lblCI = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUIZ");
        setBackground(new java.awt.Color(204, 0, 204));
        setForeground(new java.awt.Color(0, 0, 0));
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1024, 646));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ALL THE BEST");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lblQuestion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQuestion.setText("Question :");

        lblExplanation.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblExplanation.setText("Explanation:");

        txtaExplanation.setEditable(false);
        txtaExplanation.setBackground(new java.awt.Color(204, 204, 255));
        txtaExplanation.setColumns(20);
        txtaExplanation.setRows(5);
        txtaExplanation.setTabSize(1);
        txtaExplanation.setWrapStyleWord(true);
        txtaExplanation.setAutoscrolls(false);
        txtaExplanation.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane2.setViewportView(txtaExplanation);

        lblChoiceA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChoiceA.setText("Choice A:");

        lblChoiceB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChoiceB.setText("Choice B:");

        lblChoiceC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChoiceC.setText("Choice C:");

        lblChoiceD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChoiceD.setText("Choice D:");

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setForeground(new java.awt.Color(255, 255, 255));

        pnlChoiceA.setBackground(new java.awt.Color(204, 204, 204));

        lblBrowseA.setBackground(new java.awt.Color(153, 0, 153));
        lblBrowseA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quiz/defaultChoice.jpg"))); // NOI18N
        lblBrowseA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBrowseAMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlChoiceALayout = new javax.swing.GroupLayout(pnlChoiceA);
        pnlChoiceA.setLayout(pnlChoiceALayout);
        pnlChoiceALayout.setHorizontalGroup(
            pnlChoiceALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBrowseA, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlChoiceALayout.setVerticalGroup(
            pnlChoiceALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBrowseA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.setLayer(pnlChoiceA, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlChoiceA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlChoiceA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblImageQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quiz/uploadQuestionHere.jpg"))); // NOI18N

        jLayeredPane2.setLayer(lblImageQuestion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImageQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImageQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlChoiceB.setBackground(new java.awt.Color(204, 204, 204));

        lblBrowseB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quiz/defaultChoice.jpg"))); // NOI18N
        lblBrowseB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBrowseBMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlChoiceBLayout = new javax.swing.GroupLayout(pnlChoiceB);
        pnlChoiceB.setLayout(pnlChoiceBLayout);
        pnlChoiceBLayout.setHorizontalGroup(
            pnlChoiceBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBrowseB, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlChoiceBLayout.setVerticalGroup(
            pnlChoiceBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBrowseB, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane3.setLayer(pnlChoiceB, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlChoiceB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlChoiceB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChoiceC.setBackground(new java.awt.Color(204, 204, 204));

        lblBrowseC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quiz/defaultChoice.jpg"))); // NOI18N
        lblBrowseC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBrowseCMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlChoiceCLayout = new javax.swing.GroupLayout(pnlChoiceC);
        pnlChoiceC.setLayout(pnlChoiceCLayout);
        pnlChoiceCLayout.setHorizontalGroup(
            pnlChoiceCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBrowseC, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlChoiceCLayout.setVerticalGroup(
            pnlChoiceCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBrowseC, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane4.setLayer(pnlChoiceC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlChoiceC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlChoiceC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlChoiceD.setBackground(new java.awt.Color(204, 204, 204));

        lblBrowseD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quiz/defaultChoice.jpg"))); // NOI18N
        lblBrowseD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBrowseDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlChoiceDLayout = new javax.swing.GroupLayout(pnlChoiceD);
        pnlChoiceD.setLayout(pnlChoiceDLayout);
        pnlChoiceDLayout.setHorizontalGroup(
            pnlChoiceDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBrowseD, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlChoiceDLayout.setVerticalGroup(
            pnlChoiceDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChoiceDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBrowseD, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane5.setLayer(pnlChoiceD, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlChoiceD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlChoiceD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblExplanation)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1064, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblChoiceC)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(lblChoiceA)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblChoiceD)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblChoiceB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuestion)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblExplanation)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblChoiceB)
                            .addComponent(lblChoiceA)
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblChoiceC, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblChoiceD))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Questions From:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        lblSubject.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSubject.setText("Subject :");
        lblSubject.setMaximumSize(new java.awt.Dimension(75, 15));
        lblSubject.setMinimumSize(new java.awt.Dimension(75, 15));
        jPanel2.add(lblSubject);

        cmbSubject.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbSubject.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSubjectItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbSubject);

        lblChapter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChapter.setText("Chapter :");
        jPanel2.add(lblChapter);

        cmbChapter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbChapter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbChapterItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbChapter);

        lblSubTopic.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSubTopic.setText("SubTopic :");
        jPanel2.add(lblSubTopic);

        cmbSubTopic.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(cmbSubTopic);

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNext.setText("NEXT");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLoad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLoad.setText("LOAD");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
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

        lblSubTopic1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSubTopic1.setText("Correct / Incorrect :");

        lblCI.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCI.setText("C/I");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(215, 215, 215))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(468, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(377, 377, 377)
                .addComponent(lblSubTopic1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCI, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lblSubTopic1)
                    .addComponent(lblCI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSubjectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSubjectItemStateChanged
        // TODO add your handling code here:        
        cmbChapter.removeAllItems();
        try
        {
            DBConnection ob = new DBConnection();
            
            String query = "SELECT DISTINCT chapter FROM datatable where subject='"+cmbSubject.getSelectedItem()+"'";
            Statement stmt = ob.getDBConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                cmbChapter.addItem(rs.getString("chapter"));
            }
            ob.getDBConnection().close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_cmbSubjectItemStateChanged

    private void cmbChapterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbChapterItemStateChanged
               
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

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        loadResult();
        clearControls();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.setVisible(false);
        Admin ob = new Admin();
        ob.setVisible(true);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        FileOutputStream fos;
        File file;
        BufferedImage image;
	//if(getNoOfNexts() <= getRows(getResultSet()))
        //{
        try 
	{
	    byte b[];
	    Blob blob;
            //rset=ps.executeQuery();
            int i=1;
            setNoOfClick(0);
            setNoOfNexts();
            btnLoad.setText("Load");
            btnLoad.setForeground(Color.BLACK);
            pnlChoiceA.setBackground(Color.LIGHT_GRAY);
            pnlChoiceB.setBackground(Color.LIGHT_GRAY);
            pnlChoiceC.setBackground(Color.LIGHT_GRAY);
            pnlChoiceD.setBackground(Color.LIGHT_GRAY);
            clearControls();
	    if(getResultSet().next())
	    {
	    	blob=getResultSet().getBlob("question");
                b=blob.getBytes(1,(int)blob.length());
                file=new File("E://images/image"+i+".jpg");
                fos=new FileOutputStream(file);
                fos.write(b);
                image = ImageIO.read(file);
                lblImageQuestion.setIcon(new ImageIcon(image));
                
                blob=getResultSet().getBlob("choiceA");
                b=blob.getBytes(1,(int)blob.length());
                file=new File("E://images/image"+i+"A.jpg");
                fos=new FileOutputStream(file);
                fos.write(b);
                image = ImageIO.read(file);
                lblBrowseA.setIcon(new ImageIcon(image));
                
                blob=getResultSet().getBlob("choiceB");
                b=blob.getBytes(1,(int)blob.length());
                file=new File("E://images/image"+i+"B.jpg");
                fos=new FileOutputStream(file);
                fos.write(b);
                image = ImageIO.read(file);
                lblBrowseB.setIcon(new ImageIcon(image));
                
                blob=getResultSet().getBlob("choiceC");
                b=blob.getBytes(1,(int)blob.length());
                file=new File("E://images/image"+i+"C.jpg");
                fos=new FileOutputStream(file);
                fos.write(b);
                image = ImageIO.read(file);
                lblBrowseC.setIcon(new ImageIcon(image));
                
                blob=getResultSet().getBlob("choiceD");
                b=blob.getBytes(1,(int)blob.length());
                file=new File("E://images/image"+i+"D.jpg");
                fos=new FileOutputStream(file);
                fos.write(b);
                image = ImageIO.read(file);
                lblBrowseD.setIcon(new ImageIcon(image));
                i++;
                setAnswer(getResultSet().getString("answer"));
	    }
	} 
	catch (SQLException e) 
	{
	    System.err.println("Cannot connect ! ");
	}catch (IOException ex) {
            Logger.getLogger(PhotoTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //}
        //else
        //{
           //JOptionPane.showMessageDialog(rootPane, "Questions Over..."); 
        //}
    }//GEN-LAST:event_btnNextActionPerformed

    private void lblBrowseAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBrowseAMouseClicked
        System.out.println(getAnswer());
        if(getNoOfClicks() == 0 && getAnswer().equals("Choice A"))
        {
                pnlChoiceA.setBackground(Color.green);
                countCorrectMarks();
                lblCI.setText(getcountCorrectMarks()+" / "+getCountIncorrectMarks()+" = "+getRows(getResultSet()));
                incrementClicks();
        }
        else
        {
            if(getNoOfClicks() != 0)
            {
            JOptionPane.showMessageDialog(rootPane, "More than one Attempt is not allowed...");
            }
            else
            {
                countIncorrectMarks();
                pnlChoiceA.setBackground(Color.RED);
                lblCI.setText(getcountCorrectMarks()+" / "+getCountIncorrectMarks()+" = "+getRows(getResultSet()));
                incrementClicks();
                System.out.println("Incorrect is counted"+countIncorrects);
            }
            //lblCI.setText(getcountCorrectMarks()+" / "+getcountIncorrectMarks());
        }
    }//GEN-LAST:event_lblBrowseAMouseClicked

    private void lblBrowseBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBrowseBMouseClicked
        System.out.println(getAnswer());
        if(getNoOfClicks() == 0 && getAnswer().equals("Choice B"))
        {
            pnlChoiceB.setBackground(Color.green);
            countCorrectMarks();
            lblCI.setText(getcountCorrectMarks()+" / "+getCountIncorrectMarks()+" = "+getRows(getResultSet()));
            incrementClicks();
        }
        else
        {
            if(getNoOfClicks() != 0)
            {
            JOptionPane.showMessageDialog(rootPane, "More than one Attempt is not allowed...");
            }
            else
            {
                countIncorrectMarks();
                pnlChoiceB.setBackground(Color.RED);
                lblCI.setText(getcountCorrectMarks()+" / "+getCountIncorrectMarks()+" = "+getRows(getResultSet()));
                incrementClicks();
                System.out.println("Incorrect is counted"+countIncorrects);
                //lblCI.setText(getcountCorrectMarks()+" / "+getcountIncorrectMarks());
            }
        }
    }//GEN-LAST:event_lblBrowseBMouseClicked

    private void lblBrowseCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBrowseCMouseClicked
        System.out.println(getAnswer());
        if (getNoOfClicks() == 0 && getAnswer().equals("Choice C")) {
            pnlChoiceC.setBackground(Color.green);
            countCorrectMarks();
            lblCI.setText(getcountCorrectMarks()+" / "+getCountIncorrectMarks()+" = "+getRows(getResultSet()));
            incrementClicks();
        } 
        else 
        {
            if(getNoOfClicks() != 0)
            {
            JOptionPane.showMessageDialog(rootPane, "More than one Attempt is not allowed...");
            }
            else
            {
                countIncorrectMarks();
                pnlChoiceC.setBackground(Color.RED);
                lblCI.setText(getcountCorrectMarks()+" / "+getCountIncorrectMarks()+" = "+getRows(getResultSet()));
                incrementClicks();
                System.out.println("Incorrect is counted"+countIncorrects);
            }
        } 
    }//GEN-LAST:event_lblBrowseCMouseClicked

    private void lblBrowseDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBrowseDMouseClicked
        
        System.out.println(getAnswer());
        if(getNoOfClicks() == 0 && getAnswer().equals("Choice D"))
        {
            pnlChoiceD.setBackground(Color.green);
            countCorrectMarks();
            lblCI.setText(getcountCorrectMarks()+" / "+getCountIncorrectMarks()+" = "+getRows(getResultSet()));
            incrementClicks();
        }
        else
        {
            if(getNoOfClicks() != 0)
            {
            JOptionPane.showMessageDialog(rootPane, "More than one Attempt is not allowed...");
            }
            else
            {
                countIncorrectMarks();
                pnlChoiceD.setBackground(Color.RED);
                lblCI.setText(getcountCorrectMarks()+" / "+getCountIncorrectMarks()+" = "+getRows(getResultSet()));
                incrementClicks();
                System.out.println("Incorrect is counted"+countIncorrects);
            }
        }
    }//GEN-LAST:event_lblBrowseDMouseClicked

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartQuizFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new StartQuizFinal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnNext;
    private javax.swing.JComboBox<String> cmbChapter;
    private javax.swing.JComboBox<String> cmbSubTopic;
    private javax.swing.JComboBox<String> cmbSubject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBrowseA;
    private javax.swing.JLabel lblBrowseB;
    private javax.swing.JLabel lblBrowseC;
    private javax.swing.JLabel lblBrowseD;
    private javax.swing.JLabel lblCI;
    private javax.swing.JLabel lblChapter;
    private javax.swing.JLabel lblChoiceA;
    private javax.swing.JLabel lblChoiceB;
    private javax.swing.JLabel lblChoiceC;
    private javax.swing.JLabel lblChoiceD;
    private javax.swing.JLabel lblExplanation;
    private javax.swing.JLabel lblImageQuestion;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JLabel lblSubTopic;
    private javax.swing.JLabel lblSubTopic1;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JPanel pnlChoiceA;
    private javax.swing.JPanel pnlChoiceB;
    private javax.swing.JPanel pnlChoiceC;
    private javax.swing.JPanel pnlChoiceD;
    private javax.swing.JTextArea txtaExplanation;
    // End of variables declaration//GEN-END:variables

    private void setIcon(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
