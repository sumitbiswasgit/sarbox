package sumit.sarbox.ui;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class About extends javax.swing.JDialog
{
    
    /*Text Area Error Message*/
    JTextArea textAreaErrorMessage = new JTextArea();
    
    public About()
    {
        initComponents();
        
        /*Text Area Error Message*/
        textAreaErrorMessage.setColumns(40);
        textAreaErrorMessage.setRows(7);
        textAreaErrorMessage.setLineWrap(true);
        textAreaErrorMessage.setWrapStyleWord(true);
        textAreaErrorMessage.setEditable(false);
        
        this.setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnClose);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel1.setText("<html>\n<div align=\"center\"><h2>SarBox</h2></div>\n<p>SarBox Version 5.0 has been released on 10-Auguest-2018 by Sumit Biswas</p>\n<br>\n<p>&copy; Copyright 2011-2018</p>\n<br>\n<p>SarBox helps user to parse, analyze and export sar data. The sar command writes to standard output the contents of selected cumulative activity counters in the operating system, usually Unix</p>\n<br>\n<p>SarBox is a free application deveolped in Java(tm) platform.  It runs on the Java (JDK 1.8 or later).</p>\n<br>\n<p>I am not a lawer, so don't understand much about license. Meaning, SarBox is free for anyone. If you want to claim that SarBox is yours and put it on fire, I have no problem.</p>\n<p>I would appriciate anyone who wants to contribute and make changes to improve SarBox.</p>\n<p>I appreciate and very thankful to jfreechart and itext for their contribution.</p>\n<br>\n<p>This application includes library by http://www.jfree.org/jfreechart/ and https://itextpdf.com/</p>\n<br>\n<p>Anybody can contact me in sumitsushil@gmail.com</p>\n</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 427, Short.MAX_VALUE)
                        .addComponent(btnClose)
                        .addGap(205, 205, 205))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClose)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        try
        {
            this.dispose();
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
