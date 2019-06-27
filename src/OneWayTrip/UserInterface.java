package OneWayTrip;

import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * UI generated using the built-in system of Netbeans and
 * modified by me to enact the behavior I wanted.
 * @author Alex Conway
 */
public class UserInterface extends javax.swing.JFrame {

    public UserInterface() {
        initComponents();
        instanceMap.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popWindow = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        textField = new javax.swing.JTextField();
        instanceMap = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        dungeonName = new javax.swing.JLabel();
        mapText = new javax.swing.JTextArea();
        mainPanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        menu = new javax.swing.JList<>(menuItems);
        jScrollPane5 = new javax.swing.JScrollPane();
        infoBox = new javax.swing.JTextArea();
        topPanel = new javax.swing.JPanel();
        eventLogPanel = new javax.swing.JScrollPane();
        eventLog = new javax.swing.JTextArea();
        statusInfo = new javax.swing.JScrollPane();
        userStats = new javax.swing.JTextArea();
        resourcePanel = new javax.swing.JPanel();
        xpBar = new javax.swing.JProgressBar();
        mpBar = new javax.swing.JProgressBar();
        spBar = new javax.swing.JProgressBar();
        hpBar = new javax.swing.JProgressBar();
        mpLabel = new javax.swing.JLabel();
        currSP = new javax.swing.JLabel();
        maxMP = new javax.swing.JLabel();
        maxSP = new javax.swing.JLabel();
        xpLabel = new javax.swing.JLabel();
        spLabel = new javax.swing.JLabel();
        maxHP = new javax.swing.JLabel();
        currMP = new javax.swing.JLabel();
        currHP = new javax.swing.JLabel();
        charName = new javax.swing.JLabel();
        lvlLabel = new javax.swing.JLabel();
        hpLabel = new javax.swing.JLabel();
        currXP = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        currLvl = new javax.swing.JLabel();

        popWindow.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        popWindow.setTitle("Entry Required");
        popWindow.setBackground(java.awt.Color.darkGray);
        popWindow.setLocation(new java.awt.Point(400, 200));
        popWindow.setSize(new java.awt.Dimension(250, 100));

        jPanel1.setBackground(java.awt.Color.darkGray);

        label.setBackground(java.awt.Color.darkGray);
        label.setForeground(java.awt.Color.white);
        label.setText(" ");

        textField.setBackground(java.awt.Color.darkGray);
        textField.setForeground(java.awt.Color.white);
        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textField, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout popWindowLayout = new javax.swing.GroupLayout(popWindow.getContentPane());
        popWindow.getContentPane().setLayout(popWindowLayout);
        popWindowLayout.setHorizontalGroup(
            popWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        popWindowLayout.setVerticalGroup(
            popWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        instanceMap.setBackground(java.awt.Color.darkGray);
        instanceMap.setForeground(java.awt.Color.white);
        instanceMap.setLocation(new java.awt.Point(900, 0));
        instanceMap.setSize(new java.awt.Dimension(275, 275));

        jPanel2.setBackground(java.awt.Color.darkGray);
        jPanel2.setForeground(java.awt.Color.white);

        dungeonName.setBackground(java.awt.Color.darkGray);
        dungeonName.setForeground(java.awt.Color.white);
        dungeonName.setText("Dungeon Map");

        mapText.setBackground(java.awt.Color.darkGray);
        mapText.setColumns(20);
        mapText.setForeground(java.awt.Color.white);
        mapText.setLineWrap(true);
        mapText.setRows(5);
        mapText.setWrapStyleWord(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mapText)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dungeonName)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dungeonName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapText, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout instanceMapLayout = new javax.swing.GroupLayout(instanceMap.getContentPane());
        instanceMap.getContentPane().setLayout(instanceMapLayout);
        instanceMapLayout.setHorizontalGroup(
            instanceMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        instanceMapLayout.setVerticalGroup(
            instanceMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.darkGray);
        setForeground(java.awt.Color.darkGray);

        mainPanel.setBackground(java.awt.Color.darkGray);
        mainPanel.setForeground(java.awt.Color.white);

        bottomPanel.setBackground(java.awt.Color.darkGray);
        bottomPanel.setForeground(java.awt.Color.white);

        jScrollPane4.setBackground(java.awt.Color.darkGray);
        jScrollPane4.setForeground(java.awt.Color.white);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        menu.setBackground(java.awt.Color.darkGray);
        menu.setForeground(java.awt.Color.white);
        menu.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lsm = menu.getSelectionModel();
        lsm.addListSelectionListener(new SelectionHandler());
        menu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menuKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(menu);

        jScrollPane5.setBackground(java.awt.Color.darkGray);
        jScrollPane5.setForeground(java.awt.Color.white);

        infoBox.setEditable(false);
        infoBox.setBackground(java.awt.Color.darkGray);
        infoBox.setColumns(20);
        infoBox.setForeground(java.awt.Color.white);
        infoBox.setLineWrap(true);
        infoBox.setRows(5);
        infoBox.setWrapStyleWord(true);
        infoBox.setBorder(null);
        jScrollPane5.setViewportView(infoBox);

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        topPanel.setBackground(java.awt.Color.darkGray);
        topPanel.setForeground(java.awt.Color.white);

        eventLogPanel.setBackground(java.awt.Color.darkGray);
        eventLogPanel.setForeground(java.awt.Color.white);
        eventLogPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        eventLogPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        eventLog.setEditable(false);
        eventLog.setBackground(java.awt.Color.darkGray);
        eventLog.setColumns(20);
        eventLog.setForeground(java.awt.Color.white);
        eventLog.setRows(5);
        eventLogPanel.setViewportView(eventLog);

        statusInfo.setBackground(java.awt.Color.darkGray);
        statusInfo.setForeground(java.awt.Color.white);
        statusInfo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        statusInfo.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        userStats.setEditable(false);
        userStats.setBackground(java.awt.Color.darkGray);
        userStats.setColumns(20);
        userStats.setForeground(java.awt.Color.white);
        userStats.setRows(5);
        statusInfo.setViewportView(userStats);

        resourcePanel.setBackground(java.awt.Color.darkGray);
        resourcePanel.setForeground(java.awt.Color.white);

        xpBar.setBackground(java.awt.Color.darkGray);
        xpBar.setForeground(java.awt.Color.yellow);

        mpBar.setBackground(java.awt.Color.darkGray);
        mpBar.setForeground(java.awt.Color.blue);

        spBar.setBackground(java.awt.Color.darkGray);
        spBar.setForeground(java.awt.Color.green);

        hpBar.setBackground(java.awt.Color.darkGray);
        hpBar.setForeground(java.awt.Color.red);
        hpBar.setBorder(null);

        mpLabel.setBackground(java.awt.Color.darkGray);
        mpLabel.setForeground(java.awt.Color.white);
        mpLabel.setText("MP:");

        currSP.setBackground(java.awt.Color.darkGray);
        currSP.setForeground(java.awt.Color.white);
        currSP.setText("100");

        maxMP.setBackground(java.awt.Color.darkGray);
        maxMP.setForeground(java.awt.Color.white);
        maxMP.setText("/100");

        maxSP.setBackground(java.awt.Color.darkGray);
        maxSP.setForeground(java.awt.Color.white);
        maxSP.setText("/100");

        xpLabel.setBackground(java.awt.Color.darkGray);
        xpLabel.setForeground(java.awt.Color.white);
        xpLabel.setText("XP:");

        spLabel.setBackground(java.awt.Color.darkGray);
        spLabel.setForeground(java.awt.Color.white);
        spLabel.setText("SP:");

        maxHP.setBackground(java.awt.Color.darkGray);
        maxHP.setForeground(java.awt.Color.white);
        maxHP.setText("/100");

        currMP.setBackground(java.awt.Color.darkGray);
        currMP.setForeground(java.awt.Color.white);
        currMP.setText("100");

        currHP.setBackground(java.awt.Color.darkGray);
        currHP.setForeground(java.awt.Color.white);
        currHP.setText("100");

        charName.setBackground(java.awt.Color.darkGray);
        charName.setForeground(java.awt.Color.white);
        charName.setText(" ");

        lvlLabel.setBackground(java.awt.Color.darkGray);
        lvlLabel.setForeground(java.awt.Color.white);
        lvlLabel.setText("Level:");

        hpLabel.setBackground(java.awt.Color.darkGray);
        hpLabel.setForeground(java.awt.Color.white);
        hpLabel.setText("HP:");

        currXP.setBackground(java.awt.Color.darkGray);
        currXP.setForeground(java.awt.Color.white);
        currXP.setText("0");

        jLabel14.setBackground(java.awt.Color.darkGray);
        jLabel14.setForeground(java.awt.Color.white);
        jLabel14.setText("/100%");

        currLvl.setBackground(java.awt.Color.darkGray);
        currLvl.setForeground(java.awt.Color.white);
        currLvl.setText(" ");

        javax.swing.GroupLayout resourcePanelLayout = new javax.swing.GroupLayout(resourcePanel);
        resourcePanel.setLayout(resourcePanelLayout);
        resourcePanelLayout.setHorizontalGroup(
            resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resourcePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resourcePanelLayout.createSequentialGroup()
                        .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(charName)
                            .addGroup(resourcePanelLayout.createSequentialGroup()
                                .addComponent(lvlLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currLvl)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(resourcePanelLayout.createSequentialGroup()
                        .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spLabel)
                            .addComponent(hpLabel)
                            .addComponent(xpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(xpBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hpBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mpBar, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(spBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(resourcePanelLayout.createSequentialGroup()
                            .addComponent(currHP)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(maxHP))
                        .addGroup(resourcePanelLayout.createSequentialGroup()
                            .addComponent(currSP)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(maxSP))
                        .addGroup(resourcePanelLayout.createSequentialGroup()
                            .addComponent(currMP)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(maxMP)))
                    .addGroup(resourcePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(currXP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addContainerGap())
        );
        resourcePanelLayout.setVerticalGroup(
            resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resourcePanelLayout.createSequentialGroup()
                .addComponent(charName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lvlLabel)
                    .addComponent(currLvl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hpLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hpBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currHP)
                        .addComponent(maxHP)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currMP)
                        .addComponent(maxMP))
                    .addComponent(mpBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mpLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maxSP)
                        .addComponent(currSP))
                    .addComponent(spLabel)
                    .addComponent(spBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currXP)
                        .addComponent(jLabel14))
                    .addGroup(resourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(xpBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(xpLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eventLogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusInfo)
                    .addComponent(resourcePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(resourcePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                    .addComponent(eventLogPanel)))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottomPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void menuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menuKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                //dispatchCommand();
                testMap();
        }   
    }//GEN-LAST:event_menuKeyPressed
    private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActionPerformed
        textEntry = textField.getText();
        eventLog.append("Is " + textEntry + " correct?\n");
        menuItems = game.getMenuText();
        menu.setListData(menuItems);
        infoItems = game.getDescText();
        popWindow.setVisible(false);
        UserInterface.this.setFocusable(true);
        menu.requestFocus();
    }//GEN-LAST:event_textFieldActionPerformed
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
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }    
    // <editor-fold defaultstate="collapsed" desc="UI Vars">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JLabel charName;
    private javax.swing.JLabel currHP;
    private javax.swing.JLabel currLvl;
    private javax.swing.JLabel currMP;
    private javax.swing.JLabel currSP;
    private javax.swing.JLabel currXP;
    private javax.swing.JLabel dungeonName;
    private javax.swing.JTextArea eventLog;
    private javax.swing.JScrollPane eventLogPanel;
    private javax.swing.JProgressBar hpBar;
    private javax.swing.JLabel hpLabel;
    private javax.swing.JTextArea infoBox;
    private javax.swing.JDialog instanceMap;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lvlLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextArea mapText;
    private javax.swing.JLabel maxHP;
    private javax.swing.JLabel maxMP;
    private javax.swing.JLabel maxSP;
    private javax.swing.JList<String> menu;
    private javax.swing.JProgressBar mpBar;
    private javax.swing.JLabel mpLabel;
    private javax.swing.JDialog popWindow;
    private javax.swing.JPanel resourcePanel;
    private javax.swing.JProgressBar spBar;
    private javax.swing.JLabel spLabel;
    private javax.swing.JScrollPane statusInfo;
    private javax.swing.JTextField textField;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextArea userStats;
    private javax.swing.JProgressBar xpBar;
    private javax.swing.JLabel xpLabel;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
    
    //My Vars
    private int menuIndex;
    private String[] menuItems = new String[] {"Continue", "New Game", "Load Game", "Options", "Exit"};;
    private String[] infoItems = new String[] {"Continue with the last played character.",
                                               "Start a new character.",
                                               "Load a specific character.",
                                               "Change options of the game.",
                                               "Exit the game."};
    private GameUser user = new GameUser();
    private GameEngine game = new GameEngine(user);
    private String textEntry;
    private javax.swing.ListSelectionModel lsm;
    private ResourceMonitor rm = new ResourceMonitor();
    private boolean rmNotSpooled = true;
    //private  ResourceMonitor rm;
    //My Methods and Classes
    /*
    This function will evaluate what menu item is selected at the time of pressing
    the enter key. It will then start the engine thread and wait for the engine to
    finish its work before continuing. If the engine determines that the UI must
    finish the desired task, the UI will run the process function and update itself.
    */
    private void dispatchCommand() {
        game.choice = menuItems[menuIndex];
        Thread t = new Thread(game);
        System.out.println("Starting engine thread.");
        t.start();
        try {
            System.out.println("Will wait for engine.");
            t.join();
            System.out.println("Waited for engine.");
        }
        catch (InterruptedException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(game.taskForUI){
            System.out.println("Engine indicated UI function.");
            processTask();
            System.out.println("Will listen for next command.");
        }
        else {
            eventLog.append(game.getLogText());
            menuItems = game.getMenuText();
            menu.setListData(menuItems);
            infoItems = game.getDescText();
            infoBox.setText("");
            System.out.println("Will listen for next command.");
        }
        //Logic for when to spool resource monitor thread
        if(user.getName() != null && rmNotSpooled){
            Thread r = new Thread(rm);
            r.start();
            rmNotSpooled = false;
        }
    }
    /*
    So far, the UI only needs to process when the engine needs a text based entry
    to finish its work. 
    */
    private void processTask() {
        System.out.println("Engine indicated UI function.");
        switch(game.gameState){
            case "Choosing name": {
                if(textEntry == null){
                    eventLog.append("Let's make you a character.\n");
                    eventLog.append("What will you be called?\n");
                    label.setText("Your name:");
                    UserInterface.this.setFocusable(false);
                    popWindow.setVisible(true);
                    popWindow.toFront();
                    textField.requestFocus();
                    System.out.println("Opened window for text entry");
                }
                else if(menuItems[menuIndex].compareTo("No") == 0){
                    label.setText("Your name:");
                    UserInterface.this.setFocusable(false);
                    popWindow.setVisible(true);
                    popWindow.toFront();
                    textField.requestFocus();
                    System.out.println("Opened window for text entry");
                }
                else {
                    user.setName(textEntry);
                    game.gameState = "Choosing class";
                    game.setLogT(game.gameState, null);
                    game.setMenu(game.gameState);
                    game.setDesc(game.gameState);
                    game.taskForUI = false;
                    textField.setText("");
                    textEntry = null;
                    eventLog.append(game.getLogText());
                    menuItems = game.getMenuText();
                    menu.setListData(menuItems);
                    infoItems = game.getDescText();
                }
                break;
            }
            case "Choosing stats": {
                if(textEntry == null){
                    eventLog.append(game.getLogText());
                    label.setText("Ten Dex Arc:");
                    UserInterface.this.setFocusable(false);
                    popWindow.setVisible(true);
                    popWindow.toFront();
                    textField.requestFocus();
                    System.out.println("Opened window for text entry");
                }
                else {
                    int ten, dex, arc;
                    String[] splitEntry;
                    splitEntry = textEntry.split(" " , 3);
                    ten = Integer.parseInt(splitEntry[0]);
                    dex = Integer.parseInt(splitEntry[1]);
                    arc = Integer.parseInt(splitEntry[2]);
                    if(ten + dex + arc != 3){
                        eventLog.append("Invalid stat spread. Please try again.\n");
                        label.setText("Ten Dex Arc:");
                        UserInterface.this.setFocusable(false);
                        popWindow.setVisible(true);
                        popWindow.toFront();
                        textField.requestFocus();
                    }
                    else if (menuItems[menuIndex].compareTo("No") == 0){
                        label.setText("Ten Dex Arc:");
                        UserInterface.this.setFocusable(false);
                        popWindow.setVisible(true);
                        popWindow.toFront();
                        textField.requestFocus();
                    }
                    else {
                        user.setAllAS(ten, dex, arc);
                        game.gameState = "Choosing weapon";
                        game.taskForUI = false;
                        textEntry = null;
                        textField.setText("");
                        game.setLogT(game.gameState, null);
                        game.setMenu(game.gameState);
                        game.setDesc(game.gameState);
                        eventLog.append(game.getLogText());
                        menuItems = game.getMenuText();
                        menu.setListData(menuItems);
                        infoItems = game.getDescText();   
                    }
                }
                break;
            }
        }
    }
    private void initCharData(){
        charName.setText(user.getName());
        currLvl.setText(Integer.toString(user.getLevel()));
        setResourceMax();
        updateResourceVals();
    }
    private void updateResourceVals(){
            hpBar.setValue(user.getHP());
            spBar.setValue(user.getSP());
            mpBar.setValue(user.getMP());
            xpBar.setValue(user.getExp());
            currHP.setText(Integer.toString(user.getHP()));
            currMP.setText(Integer.toString(user.getMP()));
            currSP.setText(Integer.toString(user.getSP()));
            currXP.setText(Integer.toString(user.getExp()));
    }
    private void setResourceMax(){
        maxHP.setText(Integer.toString(user.getMaxHP()));
        hpBar.setMaximum(user.getMaxHP());
        maxMP.setText(Integer.toString(user.getMaxMP()));
        mpBar.setMaximum(user.getMaxMP());
        maxSP.setText(Integer.toString(user.getMaxSP()));
        spBar.setMaximum(user.getMaxSP());
        
    }
    private void testMap(){
        mapText.setText("");
        Instance inst = new Instance();
        inst.generateRoomLayout();
        inst.combineAdjacentRooms(10, 10);
        inst.generateRoomStruct(10, 10);
        inst.generateConnections();
        inst.generateMap();
        
    }
    //This is needed to let the descriptions update when the menu is interacted with
    private class SelectionHandler implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for(int i = minIndex; i <= maxIndex; i++){
                if(lsm.isSelectedIndex(i)){
                    infoBox.setText(infoItems[i]);
                    menuIndex = i;
                    //eventLog.append(menuItems[i] + i + "\n");
                }
            }
        }
    }
    private class ResourceMonitor implements Runnable {
        @Override
        public void run() {
            initCharData();
            while(true){
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                updateResourceVals();
            }
        }
    }
}
