package sumit.sarbox.method;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import sumit.sarbox.ui.UserSelectedSarDuration;
import sumit.sarbox.ui.UserSetting;

public class UiMethod
{
    public int intGlobalTruncate;
    public int intGlobalInterval;
    public int intGlobalActualDateTimeLength;
    
    public String strUserSelectedSarStartTimeStamp = "";
    public String strUserSelectedSarEndTimeStamp = "";
    
    public double[] doubleArrayMin;
    public double[] doubleArrayMax;
    public double[] doubleArraySum;
    public double[] doubleArrayAvg;
    public String[] strArrayCounterName;
        
    // <editor-fold defaultstate="collapsed" desc="Get User Setting from UI">
    public boolean getUserSetting(String strSelectedSarFilePath, String strSarConfigFilePath) throws Exception
    {
        boolean boolSuccess;
        
        try
        {
            UserSetting objUserSetting = new UserSetting(strSelectedSarFilePath, strSarConfigFilePath);
            objUserSetting.setVisible(true);
            
            boolSuccess = objUserSetting.boolSuccess;
            
            intGlobalTruncate = objUserSetting.intGlobalTruncate;
            intGlobalInterval = objUserSetting.intGlobalInterval;
            intGlobalActualDateTimeLength = objUserSetting.intGlobalActualDateTimeLength;

            return boolSuccess;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.UiMethod.getUserSetting which tries to get User Setting from UI." + "\n" + ex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Show Sar Counter Collection in Tree">
    public void addSarCounterCollectionToTree(JTree treeSar, ArrayList objALSarCounterCollectionSet) throws Exception
    {
        try
        {
            DefaultMutableTreeNode[] treeNode = new DefaultMutableTreeNode[objALSarCounterCollectionSet.size() + 1];
            DefaultMutableTreeNode[] treeSubNode;
            
            treeNode[0] = new DefaultMutableTreeNode("Counter_Collection");
            
            for(int i=0; i<objALSarCounterCollectionSet.size(); i++)
            {
                treeNode[i+1] = new DefaultMutableTreeNode(((ArrayList)objALSarCounterCollectionSet.get(i)).get(0).toString().split(" ")[0]);
                
                if(((ArrayList)((ArrayList)objALSarCounterCollectionSet.get(i)).get(1)).size() > 0)
                {
                    treeSubNode = new DefaultMutableTreeNode[((ArrayList)((ArrayList)objALSarCounterCollectionSet.get(i)).get(1)).size()];
                    
                    for(int j=0; j<((ArrayList)((ArrayList)objALSarCounterCollectionSet.get(i)).get(1)).size(); j++)
                    {
                        treeSubNode[j] = new DefaultMutableTreeNode(((ArrayList)((ArrayList)objALSarCounterCollectionSet.get(i)).get(1)).get(j).toString());
                        treeNode[i+1].add(treeSubNode[j]);
                    }
                }
                
                treeNode[0].add(treeNode[i+1]);
            }
            
            treeSar.setModel(new javax.swing.tree.DefaultTreeModel(treeNode[0]));
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.UiMethod.addSarCounterCollectionToTree which tries to show Sar Counter Collection in Tree." + "\n" + ex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get User Selected Sar Duration from UI">
    public boolean getUserSelectedSarDuration(ArrayList objALSarFileContent, String strGlobalRelativeDateTimeFormat) throws Exception
    {
        boolean boolSuccess;
        
        try
        {
            String strStart = "";
            String strEnd = "";
            
            for(int i=0; i<objALSarFileContent.size(); i++)
            {
                if(objALSarFileContent.get(i).toString().length() > strGlobalRelativeDateTimeFormat.length())
                {
                    strStart = objALSarFileContent.get(i).toString().substring(0, strGlobalRelativeDateTimeFormat.length());
                    break;
                }
            }
            
            for(int i=objALSarFileContent.size() - 1; i>0; i--)
            {
                if(objALSarFileContent.get(i).toString().length() > strGlobalRelativeDateTimeFormat.length())
                {
                    strEnd = objALSarFileContent.get(i).toString().substring(0, strGlobalRelativeDateTimeFormat.length());
                    break;
                }
            }
            
            UserSelectedSarDuration objUserSelectedSarDuration = new UserSelectedSarDuration(strStart, strEnd, strGlobalRelativeDateTimeFormat);
            objUserSelectedSarDuration.setVisible(true);
            
            boolSuccess = objUserSelectedSarDuration.boolSuccess;
            strUserSelectedSarStartTimeStamp = objUserSelectedSarDuration.strUserSelectedSarStartTimeStamp;
            strUserSelectedSarEndTimeStamp = objUserSelectedSarDuration.strUserSelectedSarEndTimeStamp;
            
            return boolSuccess;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.UiMethod.getUserSelectedSarDuration which tries to get User Selected Sar Duration from UI." + "\n" + ex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Show Graph In Panel">
    public void showGraphInPanel(JPanel panelSarGraph, JPanel jPanelChartPanel, ArrayList objALSarContentForSelectedHeader, String strSelectedSarHeader, String strDateTimeFormat, int intDateTimeFormatLength, String strChartName, int intWidth, int intHeight) throws Exception
    {
        try
        {
            //panelSarGraph.setSize(panelSarGraph.getSize().width, intHeight - 200);
            jPanelChartPanel.setSize(panelSarGraph.getSize());
            
            if(objALSarContentForSelectedHeader.size() > 0)
            {
                panelSarGraph.removeAll();
                //JFreeChart chart = createChart1(createDataset1(objALSarContentForSelectedHeader, strSelectedSarHeader, intDateTimeFormatLength), strChartName);
                JFreeChart chart = createChart(createDataset(objALSarContentForSelectedHeader, strSelectedSarHeader, strDateTimeFormat, intDateTimeFormatLength), strDateTimeFormat, strChartName);
                jPanelChartPanel = new ChartPanel(chart);
                jPanelChartPanel.setSize(panelSarGraph.getSize());
                panelSarGraph.add(jPanelChartPanel);
                panelSarGraph.updateUI();
            }
            else
            {
                panelSarGraph.removeAll();
                panelSarGraph.updateUI();
            }
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.UiMethod.showGraphInPanel which tries to show Graph In Panel." + "\n" + ex);
        }
    }
    
    public JFreeChart getGraphForPdf(ArrayList objALSarContentForSelectedHeader, String strSelectedSarHeader, String strDateTimeFormat, int intDateTimeFormatLength, String strChartName, int intWidth, int intHeight) throws Exception
    {
        try
        {
            JFreeChart chart = null;
            //panelSarGraph.setSize(panelSarGraph.getSize().width, intHeight - 200);
            //jPanelChartPanel.setSize(panelSarGraph.getSize());
            
            if(objALSarContentForSelectedHeader.size() > 0)
            {
                //panelSarGraph.removeAll();
                //JFreeChart chart = createChart1(createDataset1(objALSarContentForSelectedHeader, strSelectedSarHeader, intDateTimeFormatLength), strChartName);
                chart = createChart(createDataset(objALSarContentForSelectedHeader, strSelectedSarHeader, strDateTimeFormat, intDateTimeFormatLength), strDateTimeFormat, strChartName);
                //jPanelChartPanel = new ChartPanel(chart);
                //jPanelChartPanel.setSize(panelSarGraph.getSize());
                //panelSarGraph.add(jPanelChartPanel);
                //panelSarGraph.updateUI();
            }
            else
            {
                //panelSarGraph.removeAll();
                //panelSarGraph.updateUI();
            }
            
            return chart;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.UiMethod.getGraphForPdf which tries to get graph for exporting into PDF." + "\n" + ex);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Create Dataset for Line Graph using Date">
    private JFreeChart createChart(XYDataset dataset, String strDateTimeFormat, String strChartName)throws Exception
    {
        try
        {
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
            strChartName, // title
            "Date Time", // x-axis label
            "Value", // y-axis label
            dataset, // data
            true, // create legend?
            true, // generate tooltips?
            false // generate URLs?
            );
            chart.setBackgroundPaint(Color.white);
            XYPlot plot = (XYPlot) chart.getPlot();
            //plot.setBackgroundPaint(Color.lightGray);
            plot.setBackgroundPaint(Color.white);
            //plot.setDomainGridlinePaint(Color.white);
            plot.setDomainGridlinePaint(Color.lightGray);
            //plot.setRangeGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.lightGray);
            plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            XYItemRenderer r = plot.getRenderer();
            if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            }
            DateAxis axis = (DateAxis) plot.getDomainAxis();
            //axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
            //axis.setDateFormatOverride(new SimpleDateFormat(strDateTimeFormat));
            //axis.setDateFormatOverride(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
            axis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
            return chart;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.UiMethod.createChart which tries to create Dataset for Line Chart." + "\n" + ex);
        }
    }
    
    private XYDataset createDataset(ArrayList objALSarContentForSelectedHeader, String strSelectedSarHeader, String strDateTimeFormat, int intDateTimeFormatLength) throws Exception
    {
        String errorLine = "";
        
        try
        {
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            DateFormat formatter = new SimpleDateFormat(strDateTimeFormat);
            String[] strArraySelectedSarHeader = strSelectedSarHeader.split(" ");
            TimeSeries[] objArrayTimeSeries = new TimeSeries[strArraySelectedSarHeader.length];
            
            doubleArrayMin = new double[strArraySelectedSarHeader.length];
            doubleArrayMax = new double[strArraySelectedSarHeader.length];
            doubleArraySum = new double[strArraySelectedSarHeader.length];
            doubleArrayAvg = new double[strArraySelectedSarHeader.length];
            strArrayCounterName = new String[strArraySelectedSarHeader.length];
    
            int intSarHeaderCount = 0;
            String strDateTime = "";
            String[] strCounterValues;
            Date objDate;
            boolean boolMetricStartFalg = true;
            
            for(int i=0; i<strArraySelectedSarHeader.length; i++)
            {
                objArrayTimeSeries[i] = new TimeSeries(strArraySelectedSarHeader[i], FixedMillisecond.class);
                
                strArrayCounterName[i] = strArraySelectedSarHeader[i];
            }
            
            for(int i=0; i<objALSarContentForSelectedHeader.size(); i++)
            {
                if(objALSarContentForSelectedHeader.get(i).toString().length() > intDateTimeFormatLength)
                {
                    strDateTime = "";
                    strDateTime = objALSarContentForSelectedHeader.get(i).toString().substring(0, intDateTimeFormatLength);
                    strDateTime = strDateTime.trim();
                    objDate = (Date)formatter.parse(strDateTime);
                    strCounterValues = objALSarContentForSelectedHeader.get(i).toString().substring(intDateTimeFormatLength, objALSarContentForSelectedHeader.get(i).toString().length()).trim().split(" ");
                    intSarHeaderCount = 0;
                    
                    errorLine = String.join("\t", strCounterValues);
                    
                    for(int j=0; j<strCounterValues.length; j++)
                    {
                        objArrayTimeSeries[j].add(new FixedMillisecond(objDate), Double.parseDouble(strCounterValues[j]));
                        
                        if(boolMetricStartFalg == true)
                        {
                            boolMetricStartFalg = false;
                            for(int k=0; k<strCounterValues.length; k++)
                            {
                                doubleArrayMin[k] = Double.parseDouble(strCounterValues[k]);
                                doubleArrayMax[k] = Double.parseDouble(strCounterValues[k]);
                            }
                        }
                        
                        if(doubleArrayMin[j] > Double.parseDouble(strCounterValues[j]))
                        {
                            doubleArrayMin[j] = Double.parseDouble(strCounterValues[j]);
                        }
                        if(doubleArrayMax[j] < Double.parseDouble(strCounterValues[j]))
                        {
                            doubleArrayMax[j] = Double.parseDouble(strCounterValues[j]);
                        }
                        
                        doubleArraySum[j] = doubleArraySum[j] + Double.parseDouble(strCounterValues[j]);
                    }
                }
            }
            
            for(int i=0; i<strArraySelectedSarHeader.length; i++)
            {
                doubleArrayAvg[i] = doubleArraySum[i]/objALSarContentForSelectedHeader.size();
            }
            
            for(int i=0; i<strArraySelectedSarHeader.length; i++)
            {
                dataset.addSeries(objArrayTimeSeries[i]);
            }
            dataset.setDomainIsPointsInTime(true);
            
            return dataset;
        }
        catch (NumberFormatException ex)
        {
            throw new NumberFormatException("Error in sumit.sarbox.method.UiMethod.createDataset which tries to create Dataset for Line Graph using Date." + "\n" + errorLine + "\n" + ex);
        } catch (ParseException ex)
        {
            throw new ParseException("Error in sumit.sarbox.method.UiMethod.createDataset which tries to create Dataset for Line Graph using Date." + "\n" + errorLine + "\n" + ex, 0);
        } catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.UiMethod.createDataset which tries to create Dataset for Line Graph using Date." + "\n" + errorLine + "\n" + ex);
        }
    }
    // </editor-fold>
    
    // </editor-fold>
}
