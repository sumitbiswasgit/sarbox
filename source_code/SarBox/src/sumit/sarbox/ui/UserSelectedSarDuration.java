package sumit.sarbox.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class UserSelectedSarDuration extends javax.swing.JDialog
{
    private String strStart = "";
    private String strEnd = "";
    private String strGlobalRelativeDateTimeFormat = "";
    
    public boolean boolSuccess = false;
    public String strUserSelectedSarStartTimeStamp = "";
    public String strUserSelectedSarEndTimeStamp = "";
    
    /*Text Area Error Message*/
    JTextArea textAreaErrorMessage = new JTextArea();
    
    public UserSelectedSarDuration(String strArgStart, String strArgEnd, String strArgGlobalRelativeDateTimeFormat)
    {
        initComponents();
        
        /*Text Area Error Message*/
        textAreaErrorMessage.setColumns(40);
        textAreaErrorMessage.setRows(7);
        textAreaErrorMessage.setLineWrap(true);
        textAreaErrorMessage.setWrapStyleWord(true);
        textAreaErrorMessage.setEditable(false);
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getRootPane().setDefaultButton(btnOK);
        
        strStart = strArgStart;
        strEnd = strArgEnd;
        strGlobalRelativeDateTimeFormat = strArgGlobalRelativeDateTimeFormat;
        
        formettedtxtSelectiveRelativeStartValue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(strGlobalRelativeDateTimeFormat))));
        formettedtxtSelectiveRelativeEndValue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(strGlobalRelativeDateTimeFormat))));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngroupDuration = new javax.swing.ButtonGroup();
        btnCancel = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        panelActualRelativeTimeStamp = new javax.swing.JPanel();
        lblActualRelativeStart = new javax.swing.JLabel();
        lblActualRelativeEnd = new javax.swing.JLabel();
        lblActualRelativeDuration = new javax.swing.JLabel();
        lblActualRelativeDurationValue = new javax.swing.JLabel();
        lblActualRelativeEndValue = new javax.swing.JLabel();
        lblActualRelativeStartValue = new javax.swing.JLabel();
        panelSelectiveRelativeTimeStamp = new javax.swing.JPanel();
        lblSelectiveRelativeStart = new javax.swing.JLabel();
        lblSelectiveRelativeEnd = new javax.swing.JLabel();
        lblSelectiveRelativeDuration = new javax.swing.JLabel();
        lblSelectiveRelativeDurationValue = new javax.swing.JLabel();
        formettedtxtSelectiveRelativeStartValue = new javax.swing.JFormattedTextField();
        formettedtxtSelectiveRelativeEndValue = new javax.swing.JFormattedTextField();
        radioWholeDuration = new javax.swing.JRadioButton();
        radioSelectiveDuration = new javax.swing.JRadioButton();
        lblNote = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("User Selected Sar Duration");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        panelActualRelativeTimeStamp.setBorder(javax.swing.BorderFactory.createTitledBorder("Actual Relative Timestamp"));

        lblActualRelativeStart.setText("Start");

        lblActualRelativeEnd.setText("End");

        lblActualRelativeDuration.setText("Duration");

        javax.swing.GroupLayout panelActualRelativeTimeStampLayout = new javax.swing.GroupLayout(panelActualRelativeTimeStamp);
        panelActualRelativeTimeStamp.setLayout(panelActualRelativeTimeStampLayout);
        panelActualRelativeTimeStampLayout.setHorizontalGroup(
            panelActualRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActualRelativeTimeStampLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelActualRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblActualRelativeDuration)
                    .addComponent(lblActualRelativeEnd)
                    .addComponent(lblActualRelativeStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelActualRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblActualRelativeDurationValue, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(lblActualRelativeEndValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblActualRelativeStartValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelActualRelativeTimeStampLayout.setVerticalGroup(
            panelActualRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActualRelativeTimeStampLayout.createSequentialGroup()
                .addGroup(panelActualRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActualRelativeStart)
                    .addComponent(lblActualRelativeStartValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelActualRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActualRelativeEnd)
                    .addComponent(lblActualRelativeEndValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelActualRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActualRelativeDuration)
                    .addComponent(lblActualRelativeDurationValue)))
        );

        panelSelectiveRelativeTimeStamp.setBorder(javax.swing.BorderFactory.createTitledBorder("Selective Relative Timestamp"));

        lblSelectiveRelativeStart.setText("Start");

        lblSelectiveRelativeEnd.setText("End");

        lblSelectiveRelativeDuration.setText("Duration");

        javax.swing.GroupLayout panelSelectiveRelativeTimeStampLayout = new javax.swing.GroupLayout(panelSelectiveRelativeTimeStamp);
        panelSelectiveRelativeTimeStamp.setLayout(panelSelectiveRelativeTimeStampLayout);
        panelSelectiveRelativeTimeStampLayout.setHorizontalGroup(
            panelSelectiveRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSelectiveRelativeTimeStampLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSelectiveRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSelectiveRelativeDuration)
                    .addComponent(lblSelectiveRelativeEnd)
                    .addComponent(lblSelectiveRelativeStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSelectiveRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formettedtxtSelectiveRelativeStartValue)
                    .addGroup(panelSelectiveRelativeTimeStampLayout.createSequentialGroup()
                        .addComponent(lblSelectiveRelativeDurationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(formettedtxtSelectiveRelativeEndValue))
                .addContainerGap())
        );
        panelSelectiveRelativeTimeStampLayout.setVerticalGroup(
            panelSelectiveRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSelectiveRelativeTimeStampLayout.createSequentialGroup()
                .addGroup(panelSelectiveRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectiveRelativeStart)
                    .addComponent(formettedtxtSelectiveRelativeStartValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSelectiveRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(formettedtxtSelectiveRelativeEndValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelectiveRelativeEnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSelectiveRelativeTimeStampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectiveRelativeDuration)
                    .addComponent(lblSelectiveRelativeDurationValue)))
        );

        btngroupDuration.add(radioWholeDuration);
        radioWholeDuration.setText("Whole Duration");
        radioWholeDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioWholeDurationActionPerformed(evt);
            }
        });

        btngroupDuration.add(radioSelectiveDuration);
        radioSelectiveDuration.setText("Selective Duration");
        radioSelectiveDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSelectiveDurationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioWholeDuration)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioSelectiveDuration))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnOK)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnApply)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCancel))
                                .addComponent(panelSelectiveRelativeTimeStamp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelActualRelativeTimeStamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelActualRelativeTimeStamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSelectiveRelativeTimeStamp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioWholeDuration)
                    .addComponent(radioSelectiveDuration))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNote)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnApply)
                    .addComponent(btnOK))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try
        {
            boolSuccess = false;
            
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.dispose();
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        try
        {
            SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat(strGlobalRelativeDateTimeFormat);
            Date objDateStart = objSimpleDateFormat.parse(formettedtxtSelectiveRelativeStartValue.getText());
            Date objDateEnd = objSimpleDateFormat.parse(formettedtxtSelectiveRelativeEndValue.getText());
            long longDifference = objDateEnd.getTime() - objDateStart.getTime();
            if(longDifference <= 0)
            {
                throw new Exception("Selective Start is greater than or equal to Selective End timestamp");
            }
            objDateStart = objSimpleDateFormat.parse(lblActualRelativeStartValue.getText());
            objDateEnd = objSimpleDateFormat.parse(formettedtxtSelectiveRelativeStartValue.getText());
            longDifference = objDateEnd.getTime() - objDateStart.getTime();
            if(longDifference < 0)
            {
                throw new Exception("Selective Start is less than Actual Start timestamp");
            }
            objDateStart = objSimpleDateFormat.parse(formettedtxtSelectiveRelativeEndValue.getText());
            objDateEnd = objSimpleDateFormat.parse(lblActualRelativeEndValue.getText());
            longDifference = objDateEnd.getTime() - objDateStart.getTime();
            if(longDifference < 0)
            {
                throw new Exception("Selective End is greater than Actual End timestamp");
            }
            
            objSimpleDateFormat = new SimpleDateFormat(strGlobalRelativeDateTimeFormat);
            objDateStart = objSimpleDateFormat.parse(formettedtxtSelectiveRelativeStartValue.getText());
            objDateEnd = objSimpleDateFormat.parse(formettedtxtSelectiveRelativeEndValue.getText());
            longDifference = objDateEnd.getTime() - objDateStart.getTime();
            
            int intSeconds = (int) (longDifference / 1000) % 60 ;
            int intMinutes = (int) ((longDifference / (1000*60)) % 60);
            int intHours   = (int) ((longDifference / (1000*60*60)) % 24);
            lblSelectiveRelativeDurationValue.setText(intHours + ":" + intMinutes + ":" + intSeconds);
            
            strUserSelectedSarStartTimeStamp = formettedtxtSelectiveRelativeStartValue.getText();
            strUserSelectedSarEndTimeStamp = formettedtxtSelectiveRelativeEndValue.getText();
            boolSuccess = true;
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnApplyActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        try
        {
            btnApplyActionPerformed(null);
            
            if(boolSuccess == true)
            {
                this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                this.dispose();
            }
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try
        {
            lblActualRelativeStartValue.setText(strStart);
            lblActualRelativeEndValue.setText(strEnd);
            lblNote.setText("<html>Date time format is <b>" + strGlobalRelativeDateTimeFormat + "</b></html>");
            
            SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat(strGlobalRelativeDateTimeFormat);
            Date objDateStart = objSimpleDateFormat.parse(lblActualRelativeStartValue.getText());
            Date objDateEnd = objSimpleDateFormat.parse(lblActualRelativeEndValue.getText());
            long longDifference = objDateEnd.getTime() - objDateStart.getTime();
            
            int intSeconds = (int) (longDifference / 1000) % 60 ;
            int intMinutes = (int) ((longDifference / (1000*60)) % 60);
            int intHours   = (int) ((longDifference / (1000*60*60)) % 24);
            lblActualRelativeDurationValue.setText(intHours + ":" + intMinutes + ":" + intSeconds);
            
            radioWholeDuration.setSelected(true);
            radioWholeDurationActionPerformed(null);
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowOpened

    private void radioWholeDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioWholeDurationActionPerformed
        try
        {
            formettedtxtSelectiveRelativeStartValue.setEnabled(false);
            formettedtxtSelectiveRelativeEndValue.setEnabled(false);
            
            formettedtxtSelectiveRelativeStartValue.setText(lblActualRelativeStartValue.getText());
            formettedtxtSelectiveRelativeEndValue.setText(lblActualRelativeEndValue.getText());
            
            lblSelectiveRelativeDurationValue.setText(lblActualRelativeDurationValue.getText());
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_radioWholeDurationActionPerformed

    private void radioSelectiveDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSelectiveDurationActionPerformed
        try
        {
            formettedtxtSelectiveRelativeStartValue.setEnabled(true);
            formettedtxtSelectiveRelativeEndValue.setEnabled(true);
            
            formettedtxtSelectiveRelativeStartValue.setText(lblActualRelativeStartValue.getText());
            formettedtxtSelectiveRelativeEndValue.setText(lblActualRelativeEndValue.getText());
            
            lblSelectiveRelativeDurationValue.setText("");
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_radioSelectiveDurationActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.ButtonGroup btngroupDuration;
    private javax.swing.JFormattedTextField formettedtxtSelectiveRelativeEndValue;
    private javax.swing.JFormattedTextField formettedtxtSelectiveRelativeStartValue;
    private javax.swing.JLabel lblActualRelativeDuration;
    private javax.swing.JLabel lblActualRelativeDurationValue;
    private javax.swing.JLabel lblActualRelativeEnd;
    private javax.swing.JLabel lblActualRelativeEndValue;
    private javax.swing.JLabel lblActualRelativeStart;
    private javax.swing.JLabel lblActualRelativeStartValue;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblSelectiveRelativeDuration;
    private javax.swing.JLabel lblSelectiveRelativeDurationValue;
    private javax.swing.JLabel lblSelectiveRelativeEnd;
    private javax.swing.JLabel lblSelectiveRelativeStart;
    private javax.swing.JPanel panelActualRelativeTimeStamp;
    private javax.swing.JPanel panelSelectiveRelativeTimeStamp;
    private javax.swing.JRadioButton radioSelectiveDuration;
    private javax.swing.JRadioButton radioWholeDuration;
    // End of variables declaration//GEN-END:variables
}
