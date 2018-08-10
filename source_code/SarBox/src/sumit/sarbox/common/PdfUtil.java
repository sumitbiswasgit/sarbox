package sumit.sarbox.common;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.jfree.chart.JFreeChart;
import sumit.sarbox.method.CommonMethod;
import sumit.sarbox.method.UiMethod;

/**
 *
 * @author SUMIBISW
 */
public class PdfUtil {
    
    JTree treeSarCounterCollection;
    JProgressBar progressBar;
    
    Document document = new Document(PageSize.A4, 20, 20, 50, 25);
    PdfWriter writer = null;
    
    //String FILE = "D:\\MyWork\\NetBeansProjects\\SarBox\\SarBoxPdf.pdf";
    String FILE = "";
    Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.RED);
    Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    Font previewBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.ITALIC, BaseColor.LIGHT_GRAY);
    Font headerFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.DARK_GRAY);
    Font headerFontChild = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.LIGHT_GRAY);
    Font footerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.LIGHT_GRAY);
    Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
    Font tableContentFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLUE);
    
    public void exportSarOutputInPdf(String pdfFile, JTree objTreeSarCounterCollection, JProgressBar objProgressBar, ArrayList objALSarCounterCollection, ArrayList objALSelectedTimestampSarContent, String strGlobalRelativeDateTimeFormat, String strSelectedSarFilePath) throws Exception {
        
        try {
            FILE = pdfFile;
            
            treeSarCounterCollection = objTreeSarCounterCollection;
            progressBar = objProgressBar;
            
            //int width = 500;
            //int height = 400;
            
            writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
            
            // add header and footer
            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
            writer.setPageEvent(event);
            
            document.open();
            
            addMetaData(document);
            
            addMainPage(document, strSelectedSarFilePath);
            
            writeChart(objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat);
            //writeChartThread(objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat);
            //writeChartMultiThread(objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat);
            
            //HeaderFooterPageEvent event = new HeaderFooterPageEvent();
            //writer.setPageEvent(event);
            
        } catch (Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.exportSarOutputInPdf which tries to export sar output in PDF." + "\n" + ex);
        } finally {
            document.close();
            
            openPDF(FILE);
        }
    }
    
    private JFreeChart getSarChartAndWriteTable(ArrayList objALSarCounterCollection, ArrayList objALSelectedTimestampSarContent, String strGlobalRelativeDateTimeFormat) throws Exception {
        try {
            
            JFreeChart chart = null;
            
            boolean boolShowMetricfalg = false;
            String strSelectedCounter = "";
            String strSelectedCounterNames = "";
            String strSelectedSeriesName = "";
            
            ArrayList objALSelectedCounterSarContent = null;
            String strGlobalCounter = "";
            String strGlobalCounterNames = "";
            String strGlobalSeriesName = "";
            
            CommonMethod objCommonMethod = new CommonMethod();
            UiMethod objUiMethod = new UiMethod();
            
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
                                
                                chart = objUiMethod.getGraphForPdf(objALSelectedCounterSarContent, strGlobalCounterNames, strGlobalRelativeDateTimeFormat, strGlobalRelativeDateTimeFormat.length(), strGlobalCounter, 500, 400);
                                
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
                            
                            chart = objUiMethod.getGraphForPdf(objALSelectedCounterSarContent, strGlobalCounterNames, strGlobalRelativeDateTimeFormat, strGlobalRelativeDateTimeFormat.length(), strGlobalCounter + " [" + strGlobalSeriesName + "]", 500, 400);
                            
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
                
                writeTable(document, objectCounterMetric);
            }
            
            return chart;

        } catch (Exception ex) {
            
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.getSarChartAndWriteTable which tries to get chart and calculate and writes statistic table in PDF." + "\n" + ex);
        }
    }
    
    private void openPDF(String pdfFileName) throws Exception
    {
        try {
            
            File pdfFile = new File(pdfFileName);
            
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(pdfFile);
                } else {
                        throw new Exception("Awt Desktop is not supported to open exported PDF file");
                }

                } else {
                        throw new Exception("Exported PDF file does not exists" + pdfFileName);
                }
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.openPDF which tries to open exported PDF." + "\n" + ex);
        }
    }

    private void addEmptyLine(Paragraph paragraph, int number) throws Exception {
        try {
            for (int i = 0; i < number; i++) {
                paragraph.add(new Paragraph(" "));
            }
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.addEmptyLine which tries to write empty line in PDF." + "\n" + ex);
        }
    }
    
    private void addMetaData(Document document) throws Exception {
        try {
            document.addTitle("SarBox");
            document.addSubject("Using SarBox to export sar report");
            document.addKeywords("SarBox, Java, jFreeChart, iText, PDF");
            document.addAuthor("Sumit Biswas <sumitsushil@gmail.com>");
            document.addCreator("SarBox");
            document.addCreationDate();
            document.addHeader("SarBox", "Export Sar Log Output");
            document.addLanguage("US");
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.addMetaData which tries to write metadata in PDF." + "\n" + ex);
        }
    }
    
    private void addMainPage(Document document, String strSelectedSarFilePath) throws DocumentException, Exception {
        try {
            Paragraph preface = new Paragraph();

            // Add one empty line
            addEmptyLine(preface, 10);
            // Write a big header
            preface.add(new Paragraph("SAR report using SarBox", catFont));

            addEmptyLine(preface, 1);
            // Will create: Report generated by: _name, _date
            preface.add(new Paragraph(
                    "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    smallBold));

            addEmptyLine(preface, 1);

            preface.add(new Paragraph("Preview of selected Sar log : " + strSelectedSarFilePath, smallBold));

            CommonMethod objCommonMethod = new CommonMethod();
            ArrayList objALSarPreview = objCommonMethod.readFile(strSelectedSarFilePath, 5);
            String sarPreview = "";
            for(int i=0; i<objALSarPreview.size(); i++)
            {
                sarPreview = sarPreview + objALSarPreview.get(i).toString() + "\n";
            }
            sarPreview = sarPreview + "...";
            preface.add(new Paragraph(sarPreview, previewBold));

            document.add(preface);
            // Start a new page
            document.newPage();
        } catch(DocumentException ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.addMainPage which tries to write first main page in PDF." + "\n" + ex);
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.addMainPage which tries to write first main page in PDF." + "\n" + ex);
        }
    }
    
    private void addPageCategory(Document document, String content) throws DocumentException, Exception {
        try {
            Paragraph preface = new Paragraph();

            preface.add(new Paragraph(content, subFont));
            preface.setAlignment(Element.ALIGN_CENTER);

            // Add one empty line
            addEmptyLine(preface, 1);

            document.add(preface);
        } catch(DocumentException ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.addPageCategory which tries to write each page category describing chart and statistic table in PDF." + "\n" + ex);
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.addPageCategory which tries to write each page category describing chart and statistic table in PDF." + "\n" + ex);
        }
    }
    
    private void writeChartMultiThread(final ArrayList objALSarCounterCollection, final ArrayList objALSelectedTimestampSarContent, final String strGlobalRelativeDateTimeFormat) throws DocumentException, Exception {
        try {
            
            progressBar.setMinimum(0);
            progressBar.setMaximum(10);
            progressBar.setStringPainted(true);
            
            final int progress = 0;
            
            // Runs outside of the Swing UI thread
            new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i <= 10; i++) {

                        //progress = i;
                        // Runs inside of the Swing UI thread
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                //progressBar.setValue(i);
                            }
                        });

                        progressBar.setValue(i);

                        try {
                            java.lang.Thread.sleep(1000);
                        }
                        catch(InterruptedException e) { }
                        catch(Exception e) { }
                    } 
                }
            }).start();
            
            document.close();
            
            openPDF(FILE);
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.writeChartMultiThread which tries to write in each page chart in PDF working in thread." + "\n" + ex);
        }
    }
    
    private void writeChartThread(final ArrayList objALSarCounterCollection, final ArrayList objALSelectedTimestampSarContent, final String strGlobalRelativeDateTimeFormat) throws DocumentException, Exception {
        try {
            
            final TreeNode rootNode  = (TreeNode)treeSarCounterCollection.getModel().getRoot();
            final int totalTreeChildCount = rootNode.getChildCount();
            
            //DefaultTreeModel model = (DefaultTreeModel) treeSarCounterCollection.getModel();
            //DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            //int totalTreeChildCount = root.getLeafCount();
            
            progressBar.setMinimum(0);
            progressBar.setMaximum(totalTreeChildCount);
            progressBar.setStringPainted(true);
            
            SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    try {
                    // Simulate doing something useful.
                    //for (int i = 0; i <= 10; i++) {
                    //    Thread.sleep(1000);
                    //    //progressBar.setValue(i);
                    //    
                    //    publish(i);
                    //}
                    
                    //TreeNode rootNode  = (TreeNode)treeSarCounterCollection.getModel().getRoot();
                    //TreePath path1 = new TreePath(rootNode);
                    
                    for(int i=0; i<rootNode.getChildCount(); i++) {
                        TreeNode child1 = rootNode.getChildAt(i);

                        DefaultMutableTreeNode node1 = (DefaultMutableTreeNode) child1;//.getParent();
                        TreePath path2 = new TreePath(node1.getPath());
                        if(node1.isLeaf() == true) {

                            try {
                                treeSarCounterCollection.setSelectionPath(path2);
                                treeSarCounterCollection.scrollPathToVisible(path2);

                                addPageCategory(document, child1.toString());
                            } catch (Exception ex) {
                                writeError(document, ex.getMessage());
                            }

                            /*PdfContentByte contentByte = writer.getDirectContent();
                            PdfTemplate template = contentByte.createTemplate(width, height);
                            Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
                            Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);

                            JFreeChart chart = getSarChart(objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat);

                            chart.draw(graphics2d, rectangle2d);

                            graphics2d.dispose();
                            contentByte.addTemplate(template, 0, 0);*/

                            try {
                                JFreeChart chart = getSarChartAndWriteTable(objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat);

                                //BufferedImage bufferedImage = chart.createBufferedImage(width, height);
                                BufferedImage bufferedImage = chart.createBufferedImage((int)document.getPageSize().getWidth()-40, 400);
                                Image image = Image.getInstance(writer, bufferedImage, 1.0f);
                                document.add(image);
                            } catch (Exception ex)
                            {
                                writeError(document, ex.getMessage());
                            }

                            // Start a new page
                            document.newPage();

                        } else {
                            treeSarCounterCollection.expandPath(path2);
                        }
                        for(int j=0; j<child1.getChildCount(); j++) {
                            TreeNode child2 = child1.getChildAt(j);
                            DefaultMutableTreeNode node2 = (DefaultMutableTreeNode) child2;
                            TreePath path3 = new TreePath(node2.getPath());

                            try {
                                treeSarCounterCollection.setSelectionPath(path3);
                                treeSarCounterCollection.scrollPathToVisible(path3);

                                addPageCategory(document, child2.getParent().toString() + " -> " + child2.toString());
                            } catch (Exception ex) {
                                writeError(document, ex.getMessage());
                            }

                            try {
                                JFreeChart chart = getSarChartAndWriteTable(objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat);

                                BufferedImage bufferedImage = chart.createBufferedImage((int)document.getPageSize().getWidth()-40, 400);
                                Image image = Image.getInstance(writer, bufferedImage, 1.0f);
                                document.add(image);
                            } catch (Exception ex)
                            {
                                writeError(document, ex.getMessage());
                            }

                            // Start a new page
                            document.newPage();

                        }
                        
                        publish(i);
                    }

                    // Here we can return some object of whatever type
                    // we specified for the first template parameter.
                    // (in this case we're auto-boxing 'true').
                    return true;
                    } catch (Exception ex) {
                        return false;
                    }
                }
                
                // Can safely update the GUI from this method.
                protected void done() {
    
                    boolean status;
                    try {
                        // Retrieve the return value of doInBackground.
                        status = get();
                        String statusvalue = "Completed with status: " + status;
                        
                        progressBar.setValue(totalTreeChildCount);
                        
                        document.close();
                        
                        openPDF(FILE);
                    } catch (InterruptedException e) {
                        // This is thrown if the thread's interrupted.
                    } catch (ExecutionException e) {
                        // This is thrown if we throw an exception
                        // from doInBackground.
                    } catch (Exception ex) {
                        
                    }
                }
                
                @Override
                // Can safely update the GUI from this method.
                protected void process(List<Integer> chunks) {
                    try {
                        // Here we receive the values that we publish().
                        // They may come grouped in chunks.
                        int mostRecentValue = chunks.get(chunks.size()-1);

                        //countLabel1.setText(Integer.toString(mostRecentValue));
                        progressBar.setValue(mostRecentValue);
                    } catch (Exception ex) {
                        
                    }
                }
            };

           worker.execute();
            
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.writeChartThread which tries to write in each page chart in PDF working in thread." + "\n" + ex);
        }
    }
    
    private void writeChart(ArrayList objALSarCounterCollection, ArrayList objALSelectedTimestampSarContent, String strGlobalRelativeDateTimeFormat) throws DocumentException, Exception {
        try {
            // Get Root Node from the model ...
            TreeNode rootNode  = (TreeNode)treeSarCounterCollection.getModel().getRoot();
            // ... and its path in the tree from the tree.
            TreePath path1 = new TreePath(rootNode);
            //System.out.println(path1);
            //addPageContent(document, path1.toString());
            //abcd.setSelectionPath(path1);
            //abcd.scrollPathToVisible(path1);
            for(int i=0; i<rootNode.getChildCount(); i++) {
                TreeNode child1 = rootNode.getChildAt(i);
                //System.out.println(child);
                //path = path.pathByAddingChild(child);
                //DefaultMutableTreeNode node1 = null;
                //if (child.isLeaf()) 
                DefaultMutableTreeNode node1 = (DefaultMutableTreeNode) child1;//.getParent();
                TreePath path2 = new TreePath(node1.getPath());
                if(node1.isLeaf() == true) {

                    //System.out.println(path2);

                    treeSarCounterCollection.setSelectionPath(path2);
                    treeSarCounterCollection.scrollPathToVisible(path2);

                    addPageCategory(document, child1.toString());

                    /*PdfContentByte contentByte = writer.getDirectContent();
                    PdfTemplate template = contentByte.createTemplate(width, height);
                    Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
                    Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);

                    JFreeChart chart = getSarChart(objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat);

                    chart.draw(graphics2d, rectangle2d);

                    graphics2d.dispose();
                    contentByte.addTemplate(template, 0, 0);*/
                    
                    try {
                        JFreeChart chart = getSarChartAndWriteTable(objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat);

                        //BufferedImage bufferedImage = chart.createBufferedImage(width, height);
                        BufferedImage bufferedImage = chart.createBufferedImage((int)document.getPageSize().getWidth()-40, 400);
                        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
                        document.add(image);
                    } catch (Exception ex)
                    {
                        writeError(document, ex.getMessage());
                    }

                    // Start a new page
                    document.newPage();

                    //addPageContent(document, path2.toString() + "->" + child1);
                    //addPageContent(document, child1.toString());

                    //createTable(document);

                    //abcd.setSelectionPath(path2);
                    //abcd.scrollPathToVisible(path2);
                } else {
                    treeSarCounterCollection.expandPath(path2);
                }
                for(int j=0; j<child1.getChildCount(); j++) {
                    TreeNode child2 = child1.getChildAt(j);
                    DefaultMutableTreeNode node2 = (DefaultMutableTreeNode) child2;
                    TreePath path3 = new TreePath(node2.getPath());
                    //System.out.println(path3);

                    treeSarCounterCollection.setSelectionPath(path3);
                    treeSarCounterCollection.scrollPathToVisible(path3);

                    //addPageCategory(document, path3.toString());
                    addPageCategory(document, child2.getParent().toString() + " -> " + child2.toString());
                    
                    try {
                        JFreeChart chart = getSarChartAndWriteTable(objALSarCounterCollection, objALSelectedTimestampSarContent, strGlobalRelativeDateTimeFormat);

                        //BufferedImage bufferedImage = chart.createBufferedImage(width, height);
                        BufferedImage bufferedImage = chart.createBufferedImage((int)document.getPageSize().getWidth()-40, 400);
                        Image image = Image.getInstance(writer, bufferedImage, 1.0f);
                        document.add(image);
                    } catch (Exception ex)
                    {
                        writeError(document, ex.getMessage());
                    }

                    // Start a new page
                    document.newPage();

                    //addPageContent(document, path3.toString());

                    //createTable(document);

                    //abcd.setSelectionPath(path3);
                    //abcd.scrollPathToVisible(path3);
                }
            }
        } catch(DocumentException ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.writeChart which tries to write in each page chart in PDF." + "\n" + ex);
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.writeChart which tries to write in each page chart in PDF." + "\n" + ex);
        }
    }
    
    private void writeTable(Document document, Object[][] objectCounterMetric) throws BadElementException, DocumentException, Exception {
        try {
            PdfPTable table = new PdfPTable(5);

            PdfPCell c1 = new PdfPCell(new Phrase("CounterName", tableHeaderFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Min", tableHeaderFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Max", tableHeaderFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Sum", tableHeaderFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Avg", tableHeaderFont));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            table.setHeaderRows(1);

            for(int i=0; i<objectCounterMetric.length; i++) {
                for(int j=0; j<objectCounterMetric[i].length; j++) {
                    table.addCell(new Phrase(objectCounterMetric[i][j].toString(), tableContentFont));
                }
            }

            document.add(table);
            
        } catch(BadElementException ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.writeTable which tries to write in each page statistic table in PDF." + "\n" + ex);
        } catch(DocumentException ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.writeTable which tries to write in each page statistic table in PDF." + "\n" + ex);
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.writeTable which tries to write in each page statistic table in PDF." + "\n" + ex);
        }

    }
    
    private void writeError(Document document, String errorMessage) throws DocumentException, Exception {
        try {
            
            Paragraph preface = new Paragraph();

            preface.add(new Paragraph("Error getting details. " + errorMessage, redFont));

            document.add(preface);
            
        } catch(DocumentException ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.writeError which tries to write message in page which has error in PDF." + "\n" + ex);
        } catch(Exception ex) {
            throw new Exception("Error in sumit.sarbox.common.PdfUtil.writeError which tries to write message in page which has error in PDF." + "\n" + ex);
        }

    }
    
    private class HeaderFooterPageEvent extends PdfPageEventHelper {

        public void onStartPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("SarBox", headerFont), 50, 810, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Get statistics and charts of each counter in SAR log", headerFontChild), 23, 800, 0);
            //ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 550, 800, 0);
        }

        public void onEndPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Sumit Biswas <sumitsushil@gmail.com>", footerFont), 110, 30, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("page " + document.getPageNumber(), footerFont), 550, 30, 0);
        }

    }
    
}
