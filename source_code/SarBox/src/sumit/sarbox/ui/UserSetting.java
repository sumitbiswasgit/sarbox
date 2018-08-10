package sumit.sarbox.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import sumit.sarbox.method.CommonMethod;

public class UserSetting extends javax.swing.JDialog
{
    CommonMethod objCommonMethod = new CommonMethod();
    private ArrayList objALSarPreview = new ArrayList();
    private String strSelectedSarFilePath = "";
    private String strSarConfigFilePath = "";
    public boolean boolSuccess = false;
    
    public int intGlobalTruncate;
    public int intGlobalInterval;
    public int intGlobalActualDateTimeLength;
    
    /*Text Area Error Message*/
    JTextArea textAreaErrorMessage = new JTextArea();
    
    public UserSetting(String strArgSelectedSarFilePath, String strArgSarConfigFilePath)
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

        strSelectedSarFilePath = strArgSelectedSarFilePath;
        strSarConfigFilePath = strArgSarConfigFilePath;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPreview = new javax.swing.JPanel();
        scrollPreview = new javax.swing.JScrollPane();
        txtareaPreview = new javax.swing.JTextArea();
        btnApply = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        panelTruncate = new javax.swing.JPanel();
        lblTruncate1 = new javax.swing.JLabel();
        txtTruncate = new javax.swing.JTextField();
        lblTruncate2 = new javax.swing.JLabel();
        panelDateTimeFormat = new javax.swing.JPanel();
        cmbDateTimeFormat = new javax.swing.JComboBox();
        lblDateTimeFormat = new javax.swing.JLabel();
        panelSarInterval = new javax.swing.JPanel();
        lblInterval1 = new javax.swing.JLabel();
        txtInterval = new javax.swing.JTextField();
        lblInterval2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("User Setting");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelPreview.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));

        txtareaPreview.setColumns(20);
        txtareaPreview.setEditable(false);
        txtareaPreview.setRows(5);
        scrollPreview.setViewportView(txtareaPreview);

        javax.swing.GroupLayout panelPreviewLayout = new javax.swing.GroupLayout(panelPreview);
        panelPreview.setLayout(panelPreviewLayout);
        panelPreviewLayout.setHorizontalGroup(
            panelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );
        panelPreviewLayout.setVerticalGroup(
            panelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        );

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

        panelTruncate.setBorder(javax.swing.BorderFactory.createTitledBorder("Truncate Lines"));

        lblTruncate1.setText("Truncate / Remove first");

        lblTruncate2.setText("line(s) which provides sar information");

        javax.swing.GroupLayout panelTruncateLayout = new javax.swing.GroupLayout(panelTruncate);
        panelTruncate.setLayout(panelTruncateLayout);
        panelTruncateLayout.setHorizontalGroup(
            panelTruncateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTruncateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTruncate1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTruncate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTruncate2)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        panelTruncateLayout.setVerticalGroup(
            panelTruncateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTruncateLayout.createSequentialGroup()
                .addGroup(panelTruncateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTruncate1)
                    .addComponent(txtTruncate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTruncate2))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        panelDateTimeFormat.setBorder(javax.swing.BorderFactory.createTitledBorder("Date Time Format"));

        cmbDateTimeFormat.setEditable(true);
        cmbDateTimeFormat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dd/MM/yyyy hh:mm:ss a", "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy", "hh:mm:ss a", "HH:mm:ss" }));

        javax.swing.GroupLayout panelDateTimeFormatLayout = new javax.swing.GroupLayout(panelDateTimeFormat);
        panelDateTimeFormat.setLayout(panelDateTimeFormatLayout);
        panelDateTimeFormatLayout.setHorizontalGroup(
            panelDateTimeFormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDateTimeFormatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbDateTimeFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDateTimeFormat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDateTimeFormatLayout.setVerticalGroup(
            panelDateTimeFormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDateTimeFormatLayout.createSequentialGroup()
                .addGroup(panelDateTimeFormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDateTimeFormatLayout.createSequentialGroup()
                        .addComponent(cmbDateTimeFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblDateTimeFormat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelSarInterval.setBorder(javax.swing.BorderFactory.createTitledBorder("Interval"));

        lblInterval1.setText("Interval between each set of sar data is ");

        lblInterval2.setText("(sec)");

        javax.swing.GroupLayout panelSarIntervalLayout = new javax.swing.GroupLayout(panelSarInterval);
        panelSarInterval.setLayout(panelSarIntervalLayout);
        panelSarIntervalLayout.setHorizontalGroup(
            panelSarIntervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSarIntervalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInterval1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInterval2)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        panelSarIntervalLayout.setVerticalGroup(
            panelSarIntervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSarIntervalLayout.createSequentialGroup()
                .addGroup(panelSarIntervalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInterval1)
                    .addComponent(txtInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInterval2))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jLabel2.setText("<html><p><b>Note:</b> Sar data will be converted from <u>absolute timestamp</u> to <u>relative timestamp</u></p>");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
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
                        .addComponent(btnOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApply)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addComponent(panelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDateTimeFormat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTruncate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSarInterval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTruncate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDateTimeFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSarInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOK)
                            .addComponent(btnApply)
                            .addComponent(btnCancel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        try
        {
            btnApplyActionPerformed(null);
            
            if(boolSuccess == true)
            {
                int more = JOptionPane.YES_OPTION;
                more = JOptionPane.showConfirmDialog(null, "Are you sure all settings are correct and SarBox can proceed with parsing?", "SarBox", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if(more == JOptionPane.YES_OPTION)
                {
                    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    this.dispose();
                }
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
            objALSarPreview = objCommonMethod.readFile(strSelectedSarFilePath, 5);
            
            for(int i=0; i<objALSarPreview.size(); i++)
            {
                txtareaPreview.append(objALSarPreview.get(i).toString() + "\n");
            }
            
            txtTruncate.setText("1");
            txtInterval.setText("1");
            
            setConfigDataFromFile(strSarConfigFilePath);
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        try
        {
            int intTruncate = Integer.parseInt(txtTruncate.getText());
            int intInterval = Integer.parseInt(txtInterval.getText());
            SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat(cmbDateTimeFormat.getSelectedItem().toString());
            int intDateTimeFormatLength = cmbDateTimeFormat.getSelectedItem().toString().split(" ").length;
            String strDateTime = "";
            Date objDate = null;
            
            lblDateTimeFormat.setText("");
            if(Integer.parseInt(txtTruncate.getText()) < 0)
            {
                throw new Exception("Truncate should have value 0 or higher");
            }
            if(Integer.parseInt(txtInterval.getText()) < 1)
            {
                throw new Exception("Interval should have value 1 or higher");
            }
            
            for(int i=intTruncate; i<objALSarPreview.size(); i++)
            {
                String strSarLine = objALSarPreview.get(i).toString();
                
                if(strSarLine.compareTo("") != 0)
                {
                    String[] strArraySarLine = strSarLine.split(" ");
                    
                    if(strArraySarLine.length > intDateTimeFormatLength)
                    {
                        for(int j=0; j<intDateTimeFormatLength; j++)
                        {
                            strDateTime = strDateTime + strArraySarLine[j] + " ";
                        }
                        strDateTime = strDateTime.trim();
                        
                        objDate = objSimpleDateFormat.parse(strDateTime);
                        lblDateTimeFormat.setText(objDate.toString());
                        
                        break;
                    }
                }
            }
            
            writeConfigDataToFile(strSelectedSarFilePath, strSarConfigFilePath, intTruncate, cmbDateTimeFormat.getSelectedItem().toString(), intInterval);
            
            intGlobalTruncate = intTruncate;
            intGlobalInterval = intInterval;
            intGlobalActualDateTimeLength = strDateTime.length();
            
            boolSuccess = true;
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnApplyActionPerformed

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

    private void writeConfigDataToFile(String strSelectedSarFilePath, String strSarConfigFilePath, int intTruncate, String DateTimeFormat, int intInterval) throws Exception
    {
        File file = new File(strSarConfigFilePath);
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
            
        try
        {
            bw.write("SelectedSarDirectory=" + new File(strSelectedSarFilePath).getParent().toString() + System.getProperty("line.separator"));
            bw.write("TruncateLine=" + intTruncate + System.getProperty("line.separator"));
            bw.write("DateTimeFormat=" + DateTimeFormat + System.getProperty("line.separator"));
            bw.write("Interval=" + intInterval + System.getProperty("line.separator"));
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            bw.close();
        }
    }
    
    private void setConfigDataFromFile(String strSarConfigFilePath) throws Exception
    {
        try
        {
            if(objCommonMethod.checkDirectoryFileExists(strSarConfigFilePath) == true)
            {
                ArrayList objALConfig = new ArrayList();
                
                objALConfig = objCommonMethod.readFile(strSarConfigFilePath, -1);
                
                for(int i=0; i<objALConfig.size(); i++)
                {
                    if(objALConfig.get(i).toString().startsWith("TruncateLine=") == true)
                    {
                        txtTruncate.setText(objALConfig.get(i).toString().replace("TruncateLine=", ""));
                    }
                    else if(objALConfig.get(i).toString().startsWith("DateTimeFormat=") == true)
                    {
                        cmbDateTimeFormat.setSelectedItem(objALConfig.get(i).toString().replace("DateTimeFormat=", ""));
                    }
                    else if(objALConfig.get(i).toString().startsWith("Interval=") == true)
                    {
                        txtInterval.setText(objALConfig.get(i).toString().replace("Interval=", ""));
                    }
                }
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox cmbDateTimeFormat;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblDateTimeFormat;
    private javax.swing.JLabel lblInterval1;
    private javax.swing.JLabel lblInterval2;
    private javax.swing.JLabel lblTruncate1;
    private javax.swing.JLabel lblTruncate2;
    private javax.swing.JPanel panelDateTimeFormat;
    private javax.swing.JPanel panelPreview;
    private javax.swing.JPanel panelSarInterval;
    private javax.swing.JPanel panelTruncate;
    private javax.swing.JScrollPane scrollPreview;
    private javax.swing.JTextField txtInterval;
    private javax.swing.JTextField txtTruncate;
    private javax.swing.JTextArea txtareaPreview;
    // End of variables declaration//GEN-END:variables
}
