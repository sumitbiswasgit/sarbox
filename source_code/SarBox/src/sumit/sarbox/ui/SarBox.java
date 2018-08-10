package sumit.sarbox.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.jfree.chart.ChartPanel;
import sumit.sarbox.common.PdfUtil;
import sumit.sarbox.method.CommonMethod;
import sumit.sarbox.method.UiMethod;

public class SarBox extends javax.swing.JFrame 
{

    // <editor-fold defaultstate="collapsed" desc="Global Variable declaration and Assigning">
    
    /*Global FilePath and Direcotry*/
    String strCurrentWorkingDirectory = "";
    String StrBinDirectory = "";
    String strSarCounterCollectionFilePath = "";
    String strSarConfigFilePath = "";
    String strSarCounterCollectionHelpFilePath = "";
    String strSelectedSarFilePath = "";
    String strSelectedSarDirectory = "";
    
    /*Global Class Object*/
    CommonMethod objCommonMethod;
    UiMethod objUiMethod;
    
    /*Global Variable*/
    String strGlobalRelativeDateTimeFormat = "dd-MM-yyyy HH:mm:ss";
    int intGlobalTruncate;
    int intGlobalInterval;
    int intGlobalActualDateTimeLength;
    ArrayList objALSarFileContent = new ArrayList();
    ArrayList objALPredefinedSarCounterCollection = new ArrayList();
    ArrayList objALSarCounterCollection = new ArrayList();
    ArrayList objALSelectedTimestampSarContent = new ArrayList();
    ArrayList objALSelectedCounterSarContent = new ArrayList();
    String strGlobalCounter = "";
    String strGlobalCounterNames = "";
    String strGlobalSeriesName = "";
    JPanel jPanelChartPanel = new ChartPanel(null);
    
    /*Text Area Error Message*/
    JTextArea textAreaErrorMessage = new JTextArea();
    
    // </editor-fold>
    
    public SarBox()
    {
        initComponents();
        
        /*Text Area Error Message*/
        textAreaErrorMessage.setColumns(40);
        textAreaErrorMessage.setRows(7);
        textAreaErrorMessage.setLineWrap(true);
        textAreaErrorMessage.setWrapStyleWord(true);
        textAreaErrorMessage.setEditable(false);
        
        /*Maximize the JFrame*/
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        
        /*Tree Selection Listener*/
        treeSarCounterCollection.addTreeSelectionListener(new TreeSelectionListener()
        {
            public void valueChanged(TreeSelectionEvent e)
            {
                treeSarCounterCollectionSelection(e);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSarCounterCollection = new javax.swing.JPanel();
        scrollSarCounterCollection = new javax.swing.JScrollPane();
        treeSarCounterCollection = new javax.swing.JTree();
        panelSarCounterName = new javax.swing.JPanel();
        scrollSarCounterName = new javax.swing.JScrollPane();
        tableSarCounterName = new javax.swing.JTable();
        panelSarGraph = new javax.swing.JPanel();
        progressBar = new javax.swing.JProgressBar();
        menubarSarBox = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuitemOpenSarFile = new javax.swing.JMenuItem();
        menuitemCloseSarFile = new javax.swing.JMenuItem();
        menuExport = new javax.swing.JMenu();
        menuitemExportRawParsedData = new javax.swing.JMenuItem();
        menuitemExportSelectedTimestampParsedData = new javax.swing.JMenuItem();
        menuitemExportSarDataForSelectedCounter = new javax.swing.JMenuItem();
        menuitemExportSelectedCounterMetricValues = new javax.swing.JMenuItem();
        menuSeparatorExport = new javax.swing.JPopupMenu.Separator();
        menuitemExportPDF = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuitemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SarBox : Sar Parser Tool");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        panelSarCounterCollection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Counter_Collection");
        treeSarCounterCollection.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        scrollSarCounterCollection.setViewportView(treeSarCounterCollection);

        javax.swing.GroupLayout panelSarCounterCollectionLayout = new javax.swing.GroupLayout(panelSarCounterCollection);
        panelSarCounterCollection.setLayout(panelSarCounterCollectionLayout);
        panelSarCounterCollectionLayout.setHorizontalGroup(
            panelSarCounterCollectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollSarCounterCollection, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
        );
        panelSarCounterCollectionLayout.setVerticalGroup(
            panelSarCounterCollectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollSarCounterCollection, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
        );

        panelSarCounterName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableSarCounterName.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CounterName", "Min", "Max", "Sum", "Avg"
            }
        ));
        scrollSarCounterName.setViewportView(tableSarCounterName);

        javax.swing.GroupLayout panelSarCounterNameLayout = new javax.swing.GroupLayout(panelSarCounterName);
        panelSarCounterName.setLayout(panelSarCounterNameLayout);
        panelSarCounterNameLayout.setHorizontalGroup(
            panelSarCounterNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollSarCounterName, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        panelSarCounterNameLayout.setVerticalGroup(
            panelSarCounterNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollSarCounterName, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
        );

        panelSarGraph.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelSarGraphLayout = new javax.swing.GroupLayout(panelSarGraph);
        panelSarGraph.setLayout(panelSarGraphLayout);
        panelSarGraphLayout.setHorizontalGroup(
            panelSarGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelSarGraphLayout.setVerticalGroup(
            panelSarGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        menuFile.setText("File");

        menuitemOpenSarFile.setText("Open Sar File");
        menuitemOpenSarFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemOpenSarFileActionPerformed(evt);
            }
        });
        menuFile.add(menuitemOpenSarFile);

        menuitemCloseSarFile.setText("Close Sar File");
        menuitemCloseSarFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemCloseSarFileActionPerformed(evt);
            }
        });
        menuFile.add(menuitemCloseSarFile);

        menubarSarBox.add(menuFile);

        menuExport.setText("Export");

        menuitemExportRawParsedData.setText("Export Raw Parsed Data");
        menuitemExportRawParsedData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemExportRawParsedDataActionPerformed(evt);
            }
        });
        menuExport.add(menuitemExportRawParsedData);

        menuitemExportSelectedTimestampParsedData.setText("Export Selected Timestamp Parsed Data");
        menuitemExportSelectedTimestampParsedData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemExportSelectedTimestampParsedDataActionPerformed(evt);
            }
        });
        menuExport.add(menuitemExportSelectedTimestampParsedData);

        menuitemExportSarDataForSelectedCounter.setText("Export Sar Data For Selected Counter");
        menuitemExportSarDataForSelectedCounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemExportSarDataForSelectedCounterActionPerformed(evt);
            }
        });
        menuExport.add(menuitemExportSarDataForSelectedCounter);

        menuitemExportSelectedCounterMetricValues.setText("Export Selected Counter Metric Values");
        menuitemExportSelectedCounterMetricValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemExportSelectedCounterMetricValuesActionPerformed(evt);
            }
        });
        menuExport.add(menuitemExportSelectedCounterMetricValues);
        menuExport.add(menuSeparatorExport);

        menuitemExportPDF.setText("Export as PDF");
        menuitemExportPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemExportPDFActionPerformed(evt);
            }
        });
        menuExport.add(menuitemExportPDF);

        menubarSarBox.add(menuExport);

        menuHelp.setText("Help");

        menuitemAbout.setText("About");
        menuitemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemAboutActionPerformed(evt);
            }
        });
        menuHelp.add(menuitemAbout);

        menubarSarBox.add(menuHelp);

        setJMenuBar(menubarSarBox);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelSarCounterCollection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelSarCounterName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelSarGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelSarCounterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelSarGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelSarCounterCollection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // <editor-fold defaultstate="collapsed" desc="menuitemOpenSarFileActionPerformed">
    private void menuitemOpenSarFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemOpenSarFileActionPerformed
        try
        {
            boolean boolSuccess;
            
            /*Initialize All Values*/
            InitializeAllValues();

            /*Read from Sar Config to get the open dialog directory if it exists*/
            if(objCommonMethod.checkDirectoryFileExists(strSarConfigFilePath) == true)
            {
                ArrayList objALConfig = new ArrayList();

                objALConfig = objCommonMethod.readFile(strSarConfigFilePath, -1);

                for(int i=0; i<objALConfig.size(); i++)
                {
                    if(objALConfig.get(i).toString().startsWith("SelectedSarDirectory=") == true)
                    {
                        strSelectedSarDirectory = objALConfig.get(i).toString().replace("SelectedSarDirectory=", "");
                        
                        break;
                    }
                }
            }
            
            /*Show Open Sar File Dialog*/
            JFileChooser fileopen = new JFileChooser();
            FileFilter filterIni = new FileNameExtensionFilter("txt file", "txt");
            FileFilter filterLog = new FileNameExtensionFilter("log file", "log");
            fileopen.addChoosableFileFilter(filterIni);
            fileopen.addChoosableFileFilter(filterLog);
            
            fileopen.setCurrentDirectory(new File(strSelectedSarDirectory));
            fileopen.setDialogTitle("Open Sar File");
            
            int ret = fileopen.showDialog(this, "Open");

            if (ret == JFileChooser.APPROVE_OPTION)
            {
                /*Clear All Values*/
                menuitemCloseSarFileActionPerformed(null);
                
                /*Initialize All Values*/
                InitializeAllValues();
                
                File file = fileopen.getSelectedFile();
                strSelectedSarFilePath = file.toString();
                
                setTitle("SarBox : Sar Parser Tool " + strSelectedSarFilePath);
                
                boolSuccess = false;
                boolSuccess = objUiMethod.getUserSetting(strSelectedSarFilePath, strSarConfigFilePath);
                
                /*If User Settings was canceled then Close Sar File*/
                if(boolSuccess == false)
                {
                    menuitemCloseSarFileActionPerformed(null);
                }
                else
                {
                    intGlobalTruncate = objUiMethod.intGlobalTruncate;
                    intGlobalInterval = objUiMethod.intGlobalInterval;
                    intGlobalActualDateTimeLength = objUiMethod.intGlobalActualDateTimeLength;
                    
                    objALSarFileContent = objCommonMethod.readSarFile(strSelectedSarFilePath, intGlobalTruncate, intGlobalInterval, strGlobalRelativeDateTimeFormat, intGlobalActualDateTimeLength);
                    
                    objALPredefinedSarCounterCollection = objCommonMethod.readFile(strSarCounterCollectionFilePath, -1);
                    objALPredefinedSarCounterCollection = objCommonMethod.getSarCounterCollectionPredefinedInArrayList(objALPredefinedSarCounterCollection, strSarCounterCollectionHelpFilePath);
                    
                    objALSarCounterCollection = objCommonMethod.getSarCounterCollectionInArrayList(objALSarFileContent, objALPredefinedSarCounterCollection, strGlobalRelativeDateTimeFormat);
                    
                    objUiMethod.addSarCounterCollectionToTree(treeSarCounterCollection, objALSarCounterCollection);
                    
                    boolSuccess = false;
                    boolSuccess = objUiMethod.getUserSelectedSarDuration(objALSarFileContent, strGlobalRelativeDateTimeFormat);
                    
                    if(boolSuccess == false)
                    {
                        menuitemCloseSarFileActionPerformed(null);
                    }
                    else
                    {
                        String strUserSelectedSarStartTimeStamp = objUiMethod.strUserSelectedSarStartTimeStamp;
                        String strUserSelectedSarEndTimeStamp = objUiMethod.strUserSelectedSarEndTimeStamp;
                        
                        objALSelectedTimestampSarContent = objCommonMethod.getSelectedSarTimeStampContent(objALSarFileContent, strUserSelectedSarStartTimeStamp, strUserSelectedSarEndTimeStamp, strGlobalRelativeDateTimeFormat);
                    }
                }
            }

//            objCommonMethod = null;
//            objUiMethod = null;
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
            
            menuitemCloseSarFileActionPerformed(null);
        }
    }//GEN-LAST:event_menuitemOpenSarFileActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="menuitemCloseSarFileActionPerformed">
    private void menuitemCloseSarFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemCloseSarFileActionPerformed
        try
        {
//            /*Clear FilePath and Direcotry*/
//            strCurrentWorkingDirectory = "";
//            StrBinDirectory = "";
//            strSarCounterCollectionFilePath = "";
//            strSarConfigFilePath = "";
//            strSarCounterCollectionHelpFilePath = "";
//            strSelectedSarFilePath = "";
    
            /*Clear Class Objects*/
//            objCommonMethod = null;
//            objUiMethod = null;
            
            /*Clear Global variable*/
//            intGlobalTruncate = 0;
//            intGlobalInterval = 0;
//            intGlobalActualDateTimeLength = 0;
            objALSarFileContent = null;
            objALPredefinedSarCounterCollection = null;
            objALSarCounterCollection = null;
            objALSelectedTimestampSarContent = null;
            objALSelectedCounterSarContent = null;
            
            javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Counter_Collection");
            treeSarCounterCollection.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
            
            progressBar.setValue(0);
            
            panelSarGraph.removeAll();
            panelSarGraph.updateUI();
            
            tableSarCounterName.setModel(new DefaultTableModel(
                new Object [][] { },
                new String [] 
                {
                    "CounterName", "Min", "Max", "Sum", "Avg"
                }
            ));
            
            /*Reset Frame Title Text*/
            setTitle("SarBox : Sar Parser Tool");
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuitemCloseSarFileActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="InitializeAllValues">
    private void InitializeAllValues()
    {
        try
        {
            /*Assign all initial Directory and File name*/
            strCurrentWorkingDirectory = new File (".").getCanonicalPath();
            StrBinDirectory = strCurrentWorkingDirectory + File.separator + "Bin";
            strSarCounterCollectionFilePath = StrBinDirectory + File.separator + "SarCounterCollection.dat";
            strSarConfigFilePath = StrBinDirectory + File.separator + "SarConfig.dat";
            strSarCounterCollectionHelpFilePath = StrBinDirectory + File.separator + "SarCounterCollectionHelp.dat";
            
            /*Assign Class Objects*/
            objCommonMethod = new CommonMethod();
            objUiMethod = new UiMethod();
            
            /*Create Bin Folder if it is not created*/
            if(objCommonMethod.checkDirectoryFileExists(StrBinDirectory) == false)
            {
                objCommonMethod.createDirectory(StrBinDirectory);
            }
            
            /*Create Sar CounterCollection file inside Bin Folder if it is not created*/
            if(objCommonMethod.checkDirectoryFileExists(strSarCounterCollectionFilePath) == false)
            {
                objCommonMethod.writeSarCounterCollectionToFile(strSarCounterCollectionFilePath);
            }
            
            /*Create Sar CounterCollection Help file inside Bin Folder if it is not created*/
            if(objCommonMethod.checkDirectoryFileExists(strSarCounterCollectionHelpFilePath) == false)
            {
                objCommonMethod.writeSarCounterCollectionHelpToFile(strSarCounterCollectionHelpFilePath);
            }
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="menuitemExportRawParsedDataActionPerformed">
    private void menuitemExportRawParsedDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemExportRawParsedDataActionPerformed
        try
        {
            String strFileName = "";
            String strSarContentLine = "";
            //String strSarDateTime = "";
            //String strSarRemainingLine = "";
                
            if(objALSarFileContent != null)
            {
                if(objALSarFileContent.size() > 0)
                {
                    JFileChooser filesave = new JFileChooser();
                    FileFilter filterCsv = new FileNameExtensionFilter("csv files", "csv");
                    filesave.addChoosableFileFilter(filterCsv);
                    filesave.setDialogTitle("Export Parsed Sar Data");

                    filesave.setCurrentDirectory(new File(strSelectedSarFilePath));

                    int ret = filesave.showDialog(this, "Export");

                    if (ret == JFileChooser.APPROVE_OPTION)
                    {
                        File file = filesave.getSelectedFile();
                        strFileName = file.toString();
                        if(objCommonMethod.checkDirectoryFileExists(strFileName) == true)
                        {
                            int more = JOptionPane.YES_OPTION;
                            more = JOptionPane.showConfirmDialog(null, strFileName + " already exists." + System.getProperty("line.separator") + "Do you want to replace the file?", "SarBox", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

                            if(more == JOptionPane.YES_OPTION)
                            {
                                FileWriter fstream = new FileWriter(strFileName);
                                BufferedWriter out = new BufferedWriter(fstream);

                                for(int i=0; i<objALSarFileContent.size(); i++)
                                {
                                    strSarContentLine = objALSarFileContent.get(i).toString();

                                    if(strSarContentLine.length() > strGlobalRelativeDateTimeFormat.length())
                                    {
                                        //strSarDateTime = strSarContentLine.substring(0, strGlobalRelativeDateTimeFormat.length());
                                        //strSarRemainingLine = strSarContentLine.substring(strGlobalRelativeDateTimeFormat.length(), strSarContentLine.length());

                                        out.write(strSarContentLine.substring(0, strGlobalRelativeDateTimeFormat.length()) + strSarContentLine.substring(strGlobalRelativeDateTimeFormat.length(), strSarContentLine.length()).replace(" ", ",") + System.getProperty("line.separator"));
                                    }
                                    else
                                    {
                                        out.write(strSarContentLine + System.getProperty("line.separator"));
                                    }
                                }

                                out.close();
                            }
                        }
                        else
                        {
                            FileWriter fstream = new FileWriter(strFileName);
                            BufferedWriter out = new BufferedWriter(fstream);

                            for(int i=0; i<objALSarFileContent.size(); i++)
                            {
                                strSarContentLine = objALSarFileContent.get(i).toString();

                                if(strSarContentLine.length() > strGlobalRelativeDateTimeFormat.length())
                                {
                                    //strSarDateTime = strSarContentLine.substring(0, strGlobalRelativeDateTimeFormat.length());
                                    //strSarRemainingLine = strSarContentLine.substring(strGlobalRelativeDateTimeFormat.length(), strSarContentLine.length());

                                    out.write(strSarContentLine.substring(0, strGlobalRelativeDateTimeFormat.length()) + strSarContentLine.substring(strGlobalRelativeDateTimeFormat.length(), strSarContentLine.length()).replace(" ", ",") + System.getProperty("line.separator"));
                                }
                                else
                                {
                                    out.write(strSarContentLine + System.getProperty("line.separator"));
                                }
                            }

                            out.close();
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "No values to export", "SarBox", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No values to export", "SarBox", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuitemExportRawParsedDataActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="menuitemExportSelectedTimestampParsedDataActionPerformed">
    private void menuitemExportSelectedTimestampParsedDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemExportSelectedTimestampParsedDataActionPerformed
        try
        {
            String strFileName = "";
            String strSarContentLine = "";
                
            if(objALSelectedTimestampSarContent != null)
            {
                if(objALSelectedTimestampSarContent.size() > 0)
                {
                    JFileChooser filesave = new JFileChooser();
                    FileFilter filterCsv = new FileNameExtensionFilter("csv files", "csv");
                    filesave.addChoosableFileFilter(filterCsv);
                    filesave.setDialogTitle("Export Parsed Sar Data");

                    filesave.setCurrentDirectory(new File(strSelectedSarFilePath));

                    int ret = filesave.showDialog(this, "Export");

                    if (ret == JFileChooser.APPROVE_OPTION)
                    {
                        File file = filesave.getSelectedFile();
                        strFileName = file.toString();
                        if(objCommonMethod.checkDirectoryFileExists(strFileName) == true)
                        {
                            int more = JOptionPane.YES_OPTION;
                            more = JOptionPane.showConfirmDialog(null, strFileName + " already exists." + System.getProperty("line.separator") + "Do you want to replace the file?", "SarBox", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

                            if(more == JOptionPane.YES_OPTION)
                            {
                                FileWriter fstream = new FileWriter(strFileName);
                                BufferedWriter out = new BufferedWriter(fstream);

                                for(int i=0; i<objALSelectedTimestampSarContent.size(); i++)
                                {
                                    strSarContentLine = objALSelectedTimestampSarContent.get(i).toString();

                                    if(strSarContentLine.length() > strGlobalRelativeDateTimeFormat.length())
                                    {
                                        out.write(strSarContentLine.substring(0, strGlobalRelativeDateTimeFormat.length()) + strSarContentLine.substring(strGlobalRelativeDateTimeFormat.length(), strSarContentLine.length()).replace(" ", ",") + System.getProperty("line.separator"));
                                    }
                                    else
                                    {
                                        out.write(strSarContentLine + System.getProperty("line.separator"));
                                    }
                                }

                                out.close();
                            }
                        }
                        else
                        {
                            FileWriter fstream = new FileWriter(strFileName);
                            BufferedWriter out = new BufferedWriter(fstream);

                            for(int i=0; i<objALSelectedTimestampSarContent.size(); i++)
                            {
                                strSarContentLine = objALSelectedTimestampSarContent.get(i).toString();

                                if(strSarContentLine.length() > strGlobalRelativeDateTimeFormat.length())
                                {
                                    out.write(strSarContentLine.substring(0, strGlobalRelativeDateTimeFormat.length()) + strSarContentLine.substring(strGlobalRelativeDateTimeFormat.length(), strSarContentLine.length()).replace(" ", ",") + System.getProperty("line.separator"));
                                }
                                else
                                {
                                    out.write(strSarContentLine + System.getProperty("line.separator"));
                                }
                            }

                            out.close();
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "No values to export", "SarBox", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No values to export", "SarBox", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuitemExportSelectedTimestampParsedDataActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="formComponentResized">
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        try
        {
            if(objALSelectedCounterSarContent != null)
            {
                if(objALSelectedCounterSarContent.size() != 0)
                {
                    objUiMethod.showGraphInPanel(panelSarGraph, jPanelChartPanel, objALSelectedCounterSarContent, strGlobalCounterNames, strGlobalRelativeDateTimeFormat, strGlobalRelativeDateTimeFormat.length(), strGlobalCounter, getSize().width, getSize().height);
                }
            }
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formComponentResized
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="menuitemExportSarDataForSelectedCounterActionPerformed">
    private void menuitemExportSarDataForSelectedCounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemExportSarDataForSelectedCounterActionPerformed
        try
        {
            String strFileName = "";
            String strSarContentLine = "";
                
            if(objALSelectedCounterSarContent != null)
            {
                if(objALSelectedCounterSarContent.size() > 0)
                {
                    JFileChooser filesave = new JFileChooser();
                    FileFilter filterCsv = new FileNameExtensionFilter("csv files", "csv");
                    filesave.addChoosableFileFilter(filterCsv);
                    filesave.setDialogTitle("Export Selected Counter Sar Data");

                    filesave.setCurrentDirectory(new File(strSelectedSarFilePath));

                    int ret = filesave.showDialog(this, "Export");

                    if (ret == JFileChooser.APPROVE_OPTION)
                    {
                        File file = filesave.getSelectedFile();
                        strFileName = file.toString();
                        if(objCommonMethod.checkDirectoryFileExists(strFileName) == true)
                        {
                            int more = JOptionPane.YES_OPTION;
                            more = JOptionPane.showConfirmDialog(null, strFileName + " already exists." + System.getProperty("line.separator") + "Do you want to replace the file?", "SarBox", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

                            if(more == JOptionPane.YES_OPTION)
                            {
                                FileWriter fstream = new FileWriter(strFileName);
                                BufferedWriter out = new BufferedWriter(fstream);
                                
                                if(strGlobalSeriesName.compareTo("") != 0)
                                {
                                    out.write(strGlobalCounter + " [" + strGlobalSeriesName + "]" + System.getProperty("line.separator"));
                                    out.write("Date," + strGlobalCounterNames.replace(" ", ",") + System.getProperty("line.separator"));
                                }
                                else
                                {
                                    out.write(strGlobalCounter + System.getProperty("line.separator"));
                                    out.write("Date," + strGlobalCounterNames.replace(" ", ",") + System.getProperty("line.separator"));
                                }

                                for(int i=0; i<objALSelectedCounterSarContent.size(); i++)
                                {
                                    strSarContentLine = objALSelectedCounterSarContent.get(i).toString();

                                    if(strSarContentLine.length() > strGlobalRelativeDateTimeFormat.length())
                                    {
                                        out.write(strSarContentLine.substring(0, strGlobalRelativeDateTimeFormat.length()) + strSarContentLine.substring(strGlobalRelativeDateTimeFormat.length(), strSarContentLine.length()).replace(" ", ",") + System.getProperty("line.separator"));
                                    }
                                    else
                                    {
                                        out.write(strSarContentLine + System.getProperty("line.separator"));
                                    }
                                }

                                out.close();
                            }
                        }
                        else
                        {
                            FileWriter fstream = new FileWriter(strFileName);
                            BufferedWriter out = new BufferedWriter(fstream);
                            
                            if(strGlobalSeriesName.compareTo("") != 0)
                            {
                                out.write(strGlobalCounter + " [" + strGlobalSeriesName + "]" + System.getProperty("line.separator"));
                                out.write("Date," + strGlobalCounterNames.replace(" ", ",") + System.getProperty("line.separator"));
                            }
                            else
                            {
                                out.write(strGlobalCounter + System.getProperty("line.separator"));
                                out.write("Date," + strGlobalCounterNames.replace(" ", ",") + System.getProperty("line.separator"));
                            }

                            for(int i=0; i<objALSelectedCounterSarContent.size(); i++)
                            {
                                strSarContentLine = objALSelectedCounterSarContent.get(i).toString();

                                if(strSarContentLine.length() > strGlobalRelativeDateTimeFormat.length())
                                {
                                    out.write(strSarContentLine.substring(0, strGlobalRelativeDateTimeFormat.length()) + strSarContentLine.substring(strGlobalRelativeDateTimeFormat.length(), strSarContentLine.length()).replace(" ", ",") + System.getProperty("line.separator"));
                                }
                                else
                                {
                                    out.write(strSarContentLine + System.getProperty("line.separator"));
                                }
                            }

                            out.close();
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "No values to export", "SarBox", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No values to export", "SarBox", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuitemExportSarDataForSelectedCounterActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="menuitemExportSelectedCounterMetricValuesActionPerformed">
    private void menuitemExportSelectedCounterMetricValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemExportSelectedCounterMetricValuesActionPerformed
        try
        {
            String strFileName = "";
            String strSarContentLine = "";
            String strTableColumnValue = "";
            String strTableRowValue = "";
                
            if(objALSelectedCounterSarContent != null)
            {
                if(objALSelectedCounterSarContent.size() > 0)
                {
                    JFileChooser filesave = new JFileChooser();
                    FileFilter filterCsv = new FileNameExtensionFilter("csv files", "csv");
                    filesave.addChoosableFileFilter(filterCsv);
                    filesave.setDialogTitle("Export Selected Counter Sar Data");

                    filesave.setCurrentDirectory(new File(strSelectedSarFilePath));

                    int ret = filesave.showDialog(this, "Export");

                    if (ret == JFileChooser.APPROVE_OPTION)
                    {
                        File file = filesave.getSelectedFile();
                        strFileName = file.toString();
                        if(objCommonMethod.checkDirectoryFileExists(strFileName) == true)
                        {
                            int more = JOptionPane.YES_OPTION;
                            more = JOptionPane.showConfirmDialog(null, strFileName + " already exists." + System.getProperty("line.separator") + "Do you want to replace the file?", "SarBox", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

                            if(more == JOptionPane.YES_OPTION)
                            {
                                FileWriter fstream = new FileWriter(strFileName);
                                BufferedWriter out = new BufferedWriter(fstream);
                                
                                DefaultTableModel dtm = (DefaultTableModel)tableSarCounterName.getModel();
                                int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
                                
                                for (int i = 0 ; i < nCol ; i++)
                                {
                                    strTableColumnValue = strTableColumnValue + " " + dtm.getColumnName(i);
                                }
                                strTableColumnValue = strTableColumnValue.trim().replace(" ", ",");
                                out.write(strTableColumnValue + System.getProperty("line.separator"));
                                
                                for (int i = 0 ; i < nRow ; i++)
                                {
                                    strTableRowValue = "";
                                    for (int j = 0 ; j < nCol ; j++)
                                    {
                                        strTableRowValue = strTableRowValue + " " + dtm.getValueAt(i, j);
                                    }
                                    strTableRowValue = strTableRowValue.trim().replace(" ", ",");
                                    out.write(strTableRowValue + System.getProperty("line.separator"));
                                }
                                
                                out.close();
                            }
                        }
                        else
                        {
                            FileWriter fstream = new FileWriter(strFileName);
                            BufferedWriter out = new BufferedWriter(fstream);

                            DefaultTableModel dtm = (DefaultTableModel)tableSarCounterName.getModel();
                            int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();

                            for (int i = 0 ; i < nCol ; i++)
                            {
                                strTableColumnValue = strTableColumnValue + " " + dtm.getColumnName(i);
                            }
                            strTableColumnValue = strTableColumnValue.trim().replace(" ", ",");
                            out.write(strTableColumnValue + System.getProperty("line.separator"));

                            for (int i = 0 ; i < nRow ; i++)
                            {
                                strTableRowValue = "";
                                for (int j = 0 ; j < nCol ; j++)
                                {
                                    strTableRowValue = strTableRowValue + " " + dtm.getValueAt(i, j);
                                }
                                strTableRowValue = strTableRowValue.trim().replace(" ", ",");
                                out.write(strTableRowValue + System.getProperty("line.separator"));
                            }

                            out.close();
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "No values to export", "SarBox", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No values to export", "SarBox", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuitemExportSelectedCounterMetricValuesActionPerformed

    private void menuitemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemAboutActionPerformed
        try
        {
            About objAbout = new About();
            objAbout.setVisible(true);
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuitemAboutActionPerformed

    private void menuitemExportPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemExportPDFActionPerformed
        try
        {
            File pdfFile = null;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export SarBox output to pdf");
            FileFilter filter = new FileNameExtensionFilter("PDF document (*.pdf)","pdf");
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new File(strSelectedSarDirectory));
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                if (fileChooser.getSelectedFile().toString().endsWith(".pdf") == false) {
                    pdfFile = new File(fileChooser.getSelectedFile() + ".pdf");
                } else {
                    pdfFile = new File(fileChooser.getSelectedFile().toString());
                }
                
                PdfUtil objPdfUtil = new PdfUtil();
                
                progressBar.setValue(0);
            
                objPdfUtil.exportSarOutputInPdf(pdfFile.getAbsolutePath(), treeSarCounterCollection, progressBar, objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat, strSelectedSarFilePath);
            }

        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuitemExportPDFActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="treeSarCounterCollectionSelection">
    private void treeSarCounterCollectionSelection(TreeSelectionEvent e)
    {
        boolean boolShowMetricfalg = false;
        String strSelectedCounter = "";
        String strSelectedCounterNames = "";
        String strSelectedSeriesName = "";
        
        objALSelectedCounterSarContent = null;
        strGlobalCounter = "";
        strGlobalCounterNames = "";
        strGlobalSeriesName = "";
        
        panelSarGraph.removeAll();
        panelSarGraph.updateUI();
        tableSarCounterName.setModel(new DefaultTableModel(
                new Object [][] { },
                new String [] 
                {
                    "CounterName", "Min", "Max", "Sum", "Avg"
                }
            ));
        
        try
        {
            if(treeSarCounterCollection.getSelectionPath() != null)
            {
                if(treeSarCounterCollection.getSelectionPath().getPathCount() == 2)
                {
                    strSelectedCounter = treeSarCounterCollection.getSelectionPath().getLastPathComponent().toString();
                    
                    for(int i=0; i<objALSarCounterCollection.size(); i++)
                    {
                        if(((ArrayList)objALSarCounterCollection.get(i)).get(0).toString().split(" ")[0].compareTo(strSelectedCounter) == 0)
                        {
                            if(((ArrayList)((ArrayList)objALSarCounterCollection.get(i)).get(1)).size() == 0)
                            {
                                strSelectedCounterNames = ((ArrayList)objALSarCounterCollection.get(i)).get(0).toString().substring(strSelectedCounter.length(), ((ArrayList)objALSarCounterCollection.get(i)).get(0).toString().length()).trim();

                                objALSelectedCounterSarContent = objCommonMethod.getSarContentForSelectedCounter(objALSelectedTimestampSarContent, strSelectedCounterNames);
                                
                                strGlobalCounter = strSelectedCounter;
                                strGlobalCounterNames = strSelectedCounterNames;
                                
                                objUiMethod.showGraphInPanel(panelSarGraph, jPanelChartPanel, objALSelectedCounterSarContent, strGlobalCounterNames, strGlobalRelativeDateTimeFormat, strGlobalRelativeDateTimeFormat.length(), strGlobalCounter, getSize().width, getSize().height);
                                
                                boolShowMetricfalg = true;
                            }
                            
                            break;
                        }
                    }
                }
                else if(treeSarCounterCollection.getSelectionPath().getPathCount() == 3)
                {
                    strSelectedCounter = treeSarCounterCollection.getSelectionPath().getPath()[1].toString();
                    strSelectedSeriesName = treeSarCounterCollection.getSelectionPath().getPath()[2].toString();
                    
                    for(int i=0; i<objALSarCounterCollection.size(); i++)
                    {
                        if(((ArrayList)objALSarCounterCollection.get(i)).get(0).toString().split(" ")[0].compareTo(strSelectedCounter) == 0)
                        {
                            strSelectedCounterNames = ((ArrayList)objALSarCounterCollection.get(i)).get(0).toString().substring(strSelectedCounter.length(), ((ArrayList)objALSarCounterCollection.get(i)).get(0).toString().length()).trim();
                            
                            objALSelectedCounterSarContent = objCommonMethod.getSarContentForSelectedCounter(objALSelectedTimestampSarContent, strSelectedCounterNames, strSelectedSeriesName, strGlobalRelativeDateTimeFormat);
                            
                            strGlobalSeriesName = strSelectedSeriesName;
                            strGlobalCounter = strSelectedCounter;
                            strGlobalCounterNames = strSelectedCounterNames.substring(strSelectedCounterNames.split(" ")[0].length(), strSelectedCounterNames.length()).trim();
                            
                            objUiMethod.showGraphInPanel(panelSarGraph, jPanelChartPanel, objALSelectedCounterSarContent, strGlobalCounterNames, strGlobalRelativeDateTimeFormat, strGlobalRelativeDateTimeFormat.length(), strGlobalCounter + " [" + strGlobalSeriesName + "]", getSize().width, getSize().height);
                            
                            boolShowMetricfalg = true;
                            
                            break;
                        }
                    }
                }
            }
            
            if((objUiMethod != null) && (boolShowMetricfalg == true))
            {
                boolShowMetricfalg = false;
                DecimalFormat objDecimalFormat = new DecimalFormat("#.###");
                String[] strArrayCounterName = objUiMethod.strArrayCounterName;
                double[] doubleArrayMin = objUiMethod.doubleArrayMin;
                double[] doubleArrayMax = objUiMethod.doubleArrayMax;
                double[] doubleArraySum = objUiMethod.doubleArraySum;
                double[] doubleArrayAvg = objUiMethod.doubleArrayAvg;
                Object[][] objectCounterMetric = new Object[strArrayCounterName.length][5];

                for(int i=0; i<strArrayCounterName.length; i++)
                {
                    objectCounterMetric[i][0] = strArrayCounterName[i];
                    objectCounterMetric[i][1] = objDecimalFormat.format(doubleArrayMin[i]);
                    objectCounterMetric[i][2] = objDecimalFormat.format(doubleArrayMax[i]);
                    objectCounterMetric[i][3] = objDecimalFormat.format(doubleArraySum[i]);
                    objectCounterMetric[i][4] = objDecimalFormat.format(doubleArrayAvg[i]);
                }

                tableSarCounterName.setModel(new DefaultTableModel(
                    objectCounterMetric,
                    new String [] {"CounterName", "Min", "Max", "Sum", "Avg"}));
            }
        }
        catch (Exception ex)
        {
            textAreaErrorMessage.setText(ex.getMessage());
            JOptionPane.showMessageDialog(this, new JScrollPane( textAreaErrorMessage ), "SarBox Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // </editor-fold>
    
    public static void main(String args[])
    {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SarBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SarBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SarBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SarBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() {
                new SarBox().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu menuExport;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JPopupMenu.Separator menuSeparatorExport;
    private javax.swing.JMenuBar menubarSarBox;
    private javax.swing.JMenuItem menuitemAbout;
    private javax.swing.JMenuItem menuitemCloseSarFile;
    private javax.swing.JMenuItem menuitemExportPDF;
    private javax.swing.JMenuItem menuitemExportRawParsedData;
    private javax.swing.JMenuItem menuitemExportSarDataForSelectedCounter;
    private javax.swing.JMenuItem menuitemExportSelectedCounterMetricValues;
    private javax.swing.JMenuItem menuitemExportSelectedTimestampParsedData;
    private javax.swing.JMenuItem menuitemOpenSarFile;
    private javax.swing.JPanel panelSarCounterCollection;
    private javax.swing.JPanel panelSarCounterName;
    private javax.swing.JPanel panelSarGraph;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JScrollPane scrollSarCounterCollection;
    private javax.swing.JScrollPane scrollSarCounterName;
    private javax.swing.JTable tableSarCounterName;
    private javax.swing.JTree treeSarCounterCollection;
    // End of variables declaration//GEN-END:variables
}
