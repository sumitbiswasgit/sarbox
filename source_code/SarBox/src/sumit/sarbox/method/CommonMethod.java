package sumit.sarbox.method;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class CommonMethod
{
    // <editor-fold defaultstate="collapsed" desc="File Operations">
    
    /*Check if Directory or File Exists or not*/
    public boolean checkDirectoryFileExists(String strPath) throws Exception
    {
        boolean boolFalg = false;
        
        try
        {
            File objFile = new File(strPath);
            
            if(objFile.exists() == true)
            {
                boolFalg = true;
            }
            return boolFalg;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.checkDirectoryFileExists which tries to check if directory or file exists." + "\n" + ex);
        }
    }
    
    /*Create Directory*/
    public boolean createDirectory(String strPath) throws Exception
    {
        boolean boolFalg = false;
        
        try
        {
            File objFile = new File(strPath);
            
            objFile.mkdir();
            
            boolFalg = true;

            return boolFalg;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.createDirectory which tries to create directory." + "\n" + ex);
        }
    }
    
    /*Create File*/
    public boolean createFile(String strPath) throws Exception
    {
        boolean boolFalg = false;
        
        try
        {
            File objFile = new File(strPath);
            
            objFile.createNewFile();
            
            boolFalg = true;

            return boolFalg;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.createFile which tries to create file." + "\n" + ex);
        }
    }
    
    /*Read File (-1 will return all lines)*/
    public ArrayList readFile(String strPath, int intLineCount) throws Exception
    {
        ArrayList objArrayList = new ArrayList();
        String strLine;
        int intCount = 0;
        
        FileInputStream fstream = new FileInputStream(strPath);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        try
        {
            while ((strLine = br.readLine()) != null)
            {
                if(intCount != intLineCount)
                {
                    objArrayList.add(strLine);
                }
                else
                {
                    break;
                }

                intCount = intCount + 1;
            }

            return objArrayList;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.readFile which tries to read sar file till defined line number." + "\n" + "Error reading file at line: " + (intCount + 1) + " for " + strPath + System.getProperty("line.separator") + ex);
        }
        finally
        {
            in.close();
        }
    }
    
    /*Read Sar File*/
    public ArrayList readSarFile(String strPath, int intGlobalTruncate, int intGlobalInterval, String strGlobalRelativeDateTimeFormat, int intGlobalActualDateTimeLength) throws Exception
    {
        ArrayList objALSarFileContent = new ArrayList();
        String strSarLine;
        String[] strArraySarLine;
        int intSarLineCount = 1;
        boolean boolEmptyLineFalg = false;
        
        String strAbsoluteDateTimeOne = "";
        String strAbsoluteDateTimeTwo = "";
        String strSarLineManupulate = "";
        String strRelativeDateTime = "";
        
        DateFormat dateFormat = new SimpleDateFormat(strGlobalRelativeDateTimeFormat);
        Calendar objCalendar = Calendar.getInstance();
        objCalendar.set(2000, 00, 01, 00, 00, 00);
        objCalendar.add(Calendar.SECOND, intGlobalInterval);
        
        FileInputStream objFileInputStream = new FileInputStream(strPath);
        DataInputStream objDataInputStream = new DataInputStream(objFileInputStream);
        BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objDataInputStream));
        
        try
        {
            while ((strSarLine = objBufferedReader.readLine()) != null)
            {
                if(intSarLineCount > intGlobalTruncate)
                {
                    /*Remove all extra spaces*/
                    strArraySarLine = strSarLine.split(" ");
                    strArraySarLine = removeSpecifiedCharacterFromStringArray(strArraySarLine,  "");
                    strSarLine = combineStringArrayToString(strArraySarLine, " ");
                    strSarLine = strSarLine.trim();
                    
                    /*If Sar line is longer than ActualDateTime length, the proceed*/
                    if(strSarLine.length() > intGlobalActualDateTimeLength)
                    {
                        /*Get the absolute Timestamp*/
                        strAbsoluteDateTimeTwo = strSarLine.substring(0, intGlobalActualDateTimeLength);
                        
                        if((boolEmptyLineFalg == true) && (strAbsoluteDateTimeTwo.compareTo(strAbsoluteDateTimeOne) != 0))
                        {
                            boolEmptyLineFalg = false;
                            
                            objCalendar.add(Calendar.SECOND, -intGlobalInterval);
                            strRelativeDateTime = dateFormat.format(objCalendar.getTime());
                            
                            strAbsoluteDateTimeOne = strAbsoluteDateTimeTwo;
                        }
                        else if((boolEmptyLineFalg == false) && (strAbsoluteDateTimeTwo.compareTo(strAbsoluteDateTimeOne) != 0))
                        {
                            objCalendar.add(Calendar.SECOND, intGlobalInterval);
                            strRelativeDateTime = dateFormat.format(objCalendar.getTime());
                            
                            strAbsoluteDateTimeOne = strAbsoluteDateTimeTwo;
                        }
                        else if((boolEmptyLineFalg == true) && (strAbsoluteDateTimeTwo.compareTo(strAbsoluteDateTimeOne) == 0))
                        {
                            boolEmptyLineFalg = false;
                        }
                        
                        strSarLineManupulate = strSarLine.substring(intGlobalActualDateTimeLength, strSarLine.length());
                        strSarLineManupulate = strRelativeDateTime + strSarLineManupulate;
                        objALSarFileContent.add(strSarLineManupulate);
                    }
                    else if(strSarLine.compareTo("") == 0)
                    {
                        boolEmptyLineFalg = true;
                        objALSarFileContent.add(strSarLine);
                    }
                }
                
                intSarLineCount = intSarLineCount + 1;
            }
            
            return objALSarFileContent;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.readSarFile which tries to read sar file and manupulate date time." + "\n" + "Error reading Sar file at line: " + intSarLineCount + System.getProperty("line.separator") + ex);
        }
        finally
        {
            objDataInputStream.close();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Write Sar Counter Collection To File">
    public boolean writeSarCounterCollectionToFile(String strSarCounterCollectionFilePath) throws Exception
    {
        File file = new File(strSarCounterCollectionFilePath);
            
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
            
        boolean boolStatus = false;
        try
        {
            createFile(strSarCounterCollectionFilePath);
            
            bw.write("CPU|-1|%guest %idle" + System.getProperty("line.separator"));
            bw.write("Processes|-1|cswch/s" + System.getProperty("line.separator"));
            bw.write("Interrupts|-1|intr/s" + System.getProperty("line.separator"));
            bw.write("Swap|-1|pswpout/s" + System.getProperty("line.separator"));
            bw.write("Paging|-1|pgpgin/s pgpgout/s" + System.getProperty("line.separator"));
            bw.write("IO|-1|bread/s" + System.getProperty("line.separator"));
            bw.write("Page|-1|campg/s" + System.getProperty("line.separator"));
            bw.write("Memory|-1|%memused" + System.getProperty("line.separator"));
            bw.write("Memory_Statistic|-1|%swpused" + System.getProperty("line.separator"));
            bw.write("Kernel_Table|-1|inode-nr" + System.getProperty("line.separator"));
            bw.write("Load|-1|runq-sz" + System.getProperty("line.separator"));
            bw.write("Devices|-1|%util" + System.getProperty("line.separator"));
            bw.write("Interface_Traffic|-1|rxmcst/s" + System.getProperty("line.separator"));
            bw.write("Interface_Error|-1|txfifo/s" + System.getProperty("line.separator"));
            bw.write("NFS_Client|-1|access/s getatt/s" + System.getProperty("line.separator"));
            bw.write("NFS_Server|-1|saccess/s sgetatt/s" + System.getProperty("line.separator"));
            bw.write("Socket|-1|tcp-tw" + System.getProperty("line.separator"));
            bw.write("IPv4_Network_Traffic|-1|fragcrt/s" + System.getProperty("line.separator"));
            bw.write("IPv4_Network_Error|-1|fragf/s" + System.getProperty("line.separator"));
            bw.write("ICMPv4_Network_Traffic|-1|oadrmkr/s" + System.getProperty("line.separator"));
            bw.write("ICMPv4_Network_Error|-1|oredir/s" + System.getProperty("line.separator"));
            bw.write("TCPv4_Network_Traffic|-1|oseg/s" + System.getProperty("line.separator"));
            bw.write("TCPv4_Network_Error|-1|orsts/s" + System.getProperty("line.separator"));
            bw.write("UDPv4_Network_Traffic|-1|idgmerr/s" + System.getProperty("line.separator"));
            bw.write("Node_Statistic|-1|tcp6sck" + System.getProperty("line.separator"));
            bw.write("IPv6_Network_Traffic|-1|irec6/s" + System.getProperty("line.separator"));
            bw.write("IPv6_Network_Error|-1|idisc6/s" + System.getProperty("line.separator"));
            bw.write("ICMPv6_Network_Traffic|-1|imsg6/s" + System.getProperty("line.separator"));
            bw.write("ICMPv6_Network_Error|-1|ierr6/s" + System.getProperty("line.separator"));
            bw.write("UDPv6_Network_Traffic|-1|idgm6/s" + System.getProperty("line.separator"));
            bw.write("CPU_Clock|-1|MHz" + System.getProperty("line.separator"));
            
            boolStatus = true;
            
            return boolStatus;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.writeSarCounterCollectionToFile which tries to create Sar Counter Collection file." + "\n" + ex);
        }
        finally
        {
            bw.close();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Write Sar Counter Collection Help To File">
    public boolean writeSarCounterCollectionHelpToFile(String strSarCounterCollectionHelpFilePath) throws Exception
    {
        File file = new File(strSarCounterCollectionHelpFilePath);
            
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
            
        boolean boolStatus = false;
        try
        {
            /*http://man.he.net/man1/sar*/
            createFile(strSarCounterCollectionHelpFilePath);
            
            bw.write("tps|Total number of transfers per second that were issued  to physical  devices.   A  transfer  is  an I/O request to a physical device. Multiple logical requests  can  be  combined  into a single I/O request to the device.  A transfer is of indeterminate size." + System.getProperty("line.separator"));
            bw.write("rtps|Total number of read requests per second issued to physical devices." + System.getProperty("line.separator"));
            bw.write("wtps|Total number of write requests per second issued to physical devices." + System.getProperty("line.separator"));
            bw.write("bread/s|Total amount of data read from the devices in blocks  per second. Blocks  are equivalent to sectors with 2.4 kernels and newer and therefore have a size  of  512  bytes. With older kernels, a block is of indeterminate size." + System.getProperty("line.separator"));
            bw.write("bwrtn/s|Total  amount  of  data  written to devices in blocks per second." + System.getProperty("line.separator"));
            bw.write("pgpgin/s|Total number of kilobytes the system paged in  from  disk per second.  Note: With old kernels (2.2.x) this value is a number of blocks per second (and not kilobytes)." + System.getProperty("line.separator"));
            
            boolStatus = true;
            
            return boolStatus;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.writeSarCounterCollectionHelpToFile which tries to create Sar Counter Collection Help file." + "\n" + ex);
        }
        finally
        {
            bw.close();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Remove specified characters from String Array">
    public String[] removeSpecifiedCharacterFromStringArray(String[] strArrayData, String strChar) throws Exception
    {
        ArrayList<String> list = new ArrayList<String>();
        String[] strArrayReturn = null;
        
        try
        {
            for (String s : strArrayData)
                if (!s.equals(strChar))
                    list.add(s);
            
            strArrayReturn = list.toArray(new String[list.size()]);
                        
            return strArrayReturn;
        }
        catch(Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.removeSpecifiedCharacterFromStringArray which tries to remove specific character from Array which holds Sar file content." + "\n" + ex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Method to combine String Array to String seperated by deliminator">
    public String combineStringArrayToString(String[] strStringArray, String strDeliminator) throws Exception
    {
        String strLine = "";
        try  
        {
            for(int i=0; i<strStringArray.length; i++)
            {
                strLine = strLine + strStringArray[i] + strDeliminator;
            }
            
            return strLine;  
        }  
        catch(Exception ex)  
        {  
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.combineStringArrayToString which tries to combine String Array to String seperated by deliminator." + "\n" + ex);
        }  
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Method to find if the value is Double Numeric or not">
    public boolean isDoubleNumeric(String input)  
    {  
        try  
        {
            Double.parseDouble(input);
            return true;  
        }  
        catch(Exception ex)  
        {  
            return false;  
        }  
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Method to find if the value is Integer Numeric or not">
    public boolean isIntegerNumeric(String input)  
    {  
        try  
        {
            Integer.parseInt(input);
            return true;  
        }  
        catch(Exception ex)  
        {  
            return false;  
        }  
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get Sar Counter Collection predefiled in ArrayList">
    public ArrayList getSarCounterCollectionPredefinedInArrayList(ArrayList objALPredefinedSarCounterCollectionValue, String strSarCounterCollectionHelpFilePath) throws Exception
    {
        try
        {
            boolean boolSarCounterCollectionSeriesFalg = false;
            
            for(int i=0; i<objALPredefinedSarCounterCollectionValue.size(); i++)
            {
                String[] strArrayTemp = objALPredefinedSarCounterCollectionValue.get(i).toString().split("\\|");
                
                if(strArrayTemp.length == 3)
                {
                    if((Integer.parseInt(strArrayTemp[1]) < -1) || (Integer.parseInt(strArrayTemp[1]) > 1))
                    {
                        throw new Exception("Error reading Sar Counter Collection file at Line :" + (i + 1) + " because the Counter Series value is lesser that -1 or greater than 1 at location " + System.getProperty("line.separator") + strSarCounterCollectionHelpFilePath);
                    }
                    if((Integer.parseInt(strArrayTemp[1]) == 0) || (Integer.parseInt(strArrayTemp[1]) == 1))
                    {
                        boolSarCounterCollectionSeriesFalg = true;
                    }
                    objALPredefinedSarCounterCollectionValue.add(i, new ArrayList());
                    for(int j=0; j<strArrayTemp.length; j++)
                    {
                        ((ArrayList)objALPredefinedSarCounterCollectionValue.get(i)).add(strArrayTemp[j]);
                    }

                    objALPredefinedSarCounterCollectionValue.remove(i + 1);
                }
                else
                {
                    throw new Exception("Error reading Sar Counter Collection file at Line :" + (i + 1) + " at location " + System.getProperty("line.separator") + strSarCounterCollectionHelpFilePath);
                }
            }
            
            for(int i=0; i<objALPredefinedSarCounterCollectionValue.size(); i++)
            {
                int intCompareValue = 0;
                if((i +1) < objALPredefinedSarCounterCollectionValue.size())
                {
                    intCompareValue = i + 1;
                }
                else
                {
                    break;
                }
                for(int j=intCompareValue; j<objALPredefinedSarCounterCollectionValue.size(); j++)
                {
                    if(((ArrayList)objALPredefinedSarCounterCollectionValue.get(i)).get(0).toString().compareTo(((ArrayList)objALPredefinedSarCounterCollectionValue.get(j)).get(0).toString()) == 0)
                    {
                        throw new Exception("Counter Collection Name is same at Line No : " + (i + 1) + " and " + (j + 1) + " for Sar Counter Collection file at location " + System.getProperty("line.separator") + strSarCounterCollectionHelpFilePath + System.getProperty("line.separator") + " Every Sar Counter Collection Name should be unique");
                    }
                }
            }
            
            if(boolSarCounterCollectionSeriesFalg == true)
            {
                JOptionPane.showMessageDialog(null, "There are manual settings (0 or 1) in Sar Counter Collection to forcefully set Counter Series on or off at location " + System.getProperty("line.separator") + strSarCounterCollectionHelpFilePath + System.getProperty("line.separator") + System.getProperty("line.separator") + System.getProperty("line.separator") + "0 being No Counter Series. e.g. Process does not have any Counter Series." + System.getProperty("line.separator") + "1 being Counter Series available. e.g. CPU have Counter Series like 0, 1, etc." + System.getProperty("line.separator") + "-1 is the default Counter Series setting. SarBox will determine and take appropriate action.", "SarBox", JOptionPane.INFORMATION_MESSAGE);
            }
            
            return objALPredefinedSarCounterCollectionValue;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.getSarCounterCollectionPredefinedInArrayList which tries to get Sar Counter Collection predefiled in ArrayList." + "\n" + ex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get Sar Counter Collection in ArrayList">
    public ArrayList getSarCounterCollectionInArrayList(ArrayList objArrayList, ArrayList objALPredefinedSarCounterCollection, String intSelectedDateTimeFormat) throws Exception
    {
        int i = 0;
        String strFirstCounterNames = "";
        boolean boolCounterNameMatch = false;
        int intUnrecognized = 1;
        
        try
        {
            ArrayList objALSarCounterCollection = new ArrayList();
            boolean boolEmptyLineFlag = false;
            
            for(i=0; i<objArrayList.size(); i++)
            {
                if(objArrayList.get(i).toString().compareTo("") == 0)
                {
                    boolEmptyLineFlag = true;
                }
                else if(boolEmptyLineFlag == true)
                {
                    boolEmptyLineFlag = false;
                    
                    if(strFirstCounterNames.compareTo("") == 0)
                    {
                        strFirstCounterNames = objArrayList.get(i).toString().substring(intSelectedDateTimeFormat.length(), objArrayList.get(i).toString().length());
                    }
                    
                    for(int j=0; j<objALPredefinedSarCounterCollection.size(); j++)
                    {
                        String strSarLineWithoutDateTime = objArrayList.get(i).toString().substring(intSelectedDateTimeFormat.length(), objArrayList.get(i).toString().length());
                        String strSarPredefinedCounterName = ((ArrayList)objALPredefinedSarCounterCollection.get(j)).get(2).toString();
                        int intSarPredefinedSeriesValue = Integer.parseInt(((ArrayList)objALPredefinedSarCounterCollection.get(j)).get(1).toString());
                        
                        boolCounterNameMatch = false;
                        
                        if(strSarLineWithoutDateTime.contains(strSarPredefinedCounterName) == true)
                        {
                            boolCounterNameMatch = true;
                            String[] strArraySarLineWithoutDateTime = strSarLineWithoutDateTime.split(" ");
                            
                            if(intSarPredefinedSeriesValue == 0)
                            {
                                String strSarLineWithCounterName = ((ArrayList)objALPredefinedSarCounterCollection.get(j)).get(0).toString() + strSarLineWithoutDateTime;
                                
                                objALSarCounterCollection.add(new ArrayList());
                                ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(strSarLineWithCounterName);
                                ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(new ArrayList());
                            }
                            else if(intSarPredefinedSeriesValue == 1)
                            {
                                String strSarLineWithCounterName = ((ArrayList)objALPredefinedSarCounterCollection.get(j)).get(0).toString() + strSarLineWithoutDateTime;
                                
                                objALSarCounterCollection.add(new ArrayList());
                                ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(strSarLineWithCounterName);
                                ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(new ArrayList());
                                
                                objALSarCounterCollection = addSarCounterSeriesInCounterCollectionArrayList(objArrayList, objALSarCounterCollection, intSelectedDateTimeFormat, i);
                            }
                            else if(strArraySarLineWithoutDateTime[1].toUpperCase().compareTo(strArraySarLineWithoutDateTime[1]) != 0)
                            {
                                String strSarLineWithCounterName = ((ArrayList)objALPredefinedSarCounterCollection.get(j)).get(0).toString() + strSarLineWithoutDateTime;
                                
                                objALSarCounterCollection.add(new ArrayList());
                                ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(strSarLineWithCounterName);
                                ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(new ArrayList());
                            }
                            else if(strArraySarLineWithoutDateTime[1].toUpperCase().compareTo(strArraySarLineWithoutDateTime[1]) == 0)
                            {
                                String strSarLineWithCounterName = ((ArrayList)objALPredefinedSarCounterCollection.get(j)).get(0).toString() + strSarLineWithoutDateTime;
                                
                                objALSarCounterCollection.add(new ArrayList());
                                ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(strSarLineWithCounterName);
                                ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(new ArrayList());
                                
                                objALSarCounterCollection = addSarCounterSeriesInCounterCollectionArrayList(objArrayList, objALSarCounterCollection, intSelectedDateTimeFormat, i);
                            }
                            
                            break;
                        }
                    }
                    
                    if(boolCounterNameMatch == false)
                    {
                        String strSarLineWithoutDateTime = objArrayList.get(i).toString().substring(intSelectedDateTimeFormat.length(), objArrayList.get(i).toString().length());
                        String[] strArraySarLineWithoutDateTime = strSarLineWithoutDateTime.split(" ");
                        
                        if(strArraySarLineWithoutDateTime[1].toUpperCase().compareTo(strArraySarLineWithoutDateTime[1]) != 0)
                        {
                            String strSarLineWithCounterName = "Unrecognized_" + intUnrecognized + strSarLineWithoutDateTime;

                            objALSarCounterCollection.add(new ArrayList());
                            ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(strSarLineWithCounterName);
                            ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(new ArrayList());
                        }
                        else if(strArraySarLineWithoutDateTime[1].toUpperCase().compareTo(strArraySarLineWithoutDateTime[1]) == 0)
                        {
                            String strSarLineWithCounterName = "Unrecognized_" + intUnrecognized + strSarLineWithoutDateTime;

                            objALSarCounterCollection.add(new ArrayList());
                            ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(strSarLineWithCounterName);
                            ((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).add(new ArrayList());

                            objALSarCounterCollection = addSarCounterSeriesInCounterCollectionArrayList(objArrayList, objALSarCounterCollection, intSelectedDateTimeFormat, i);
                        }
                        
                        intUnrecognized = intUnrecognized + 1;
                    }
                    
                    if(objALSarCounterCollection.size() > 1)
                    {
                        if(strFirstCounterNames.compareTo(objArrayList.get(i).toString().substring(intSelectedDateTimeFormat.length(), objArrayList.get(i).toString().length())) == 0)
                        {
                            objALSarCounterCollection.remove(objALSarCounterCollection.size() - 1);
                            break;
                        }
                    }
                }
            }
            
            return objALSarCounterCollection;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.getSarCounterCollectionInArrayList which tries to get Sar Counter Collection in ArrayList." + "\n" + "Error getting Sar Counter Collection from selected file at Line :" + (i + 1) + "\n" + ex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Add Sar Counter Series in Counter Collection ArrayList">
    public ArrayList addSarCounterSeriesInCounterCollectionArrayList(ArrayList objArrayList, ArrayList objALSarCounterCollection, String intSelectedDateTimeFormat, int intStartIndex) throws Exception
    {
        int i = 0;
        
        try
        {
            for(i=intStartIndex+1; i<objArrayList.size(); i++)
            {
                if(objArrayList.get(i).toString().compareTo("") != 0)
                {
                    String[] strArraySarLineWithoutDateTime = objArrayList.get(i).toString().substring(intSelectedDateTimeFormat.length(), objArrayList.get(i).toString().length()).split(" ");
                    String strCounterSeries = strArraySarLineWithoutDateTime[1];
                    if((isDoubleNumeric(strCounterSeries) == false) && (isIntegerNumeric(strCounterSeries) == false))
                    {
                        ((ArrayList)((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).get(1)).add(strCounterSeries);
                    }
                    else if((isDoubleNumeric(strCounterSeries) == true) && (isIntegerNumeric(strCounterSeries) == true))
                    {
                        ((ArrayList)((ArrayList)objALSarCounterCollection.get(objALSarCounterCollection.size() - 1)).get(1)).add(strCounterSeries);
                    }
                }
                else
                {
                    return objALSarCounterCollection;
                }
            }
            
            return objALSarCounterCollection;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.addSarCounterSeriesInCounterCollectionArrayList which tries to add Sar Counter Series in Counter Collection ArrayList." + "\n" + "Error getting Sar Counter Collection from selected file at Line :" + (i + 1) + "\n" + ex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get Selected User Timestamp Sar Content from Full Sar Content">
    public ArrayList getSelectedSarTimeStampContent(ArrayList objALFullSarContent, String strStartTimestamp, String strEndTimestamp, String strGlobalRelativeDateTimeFormat) throws Exception
    {
        ArrayList objALSelectedSarContent = new ArrayList();
        
        try
        {
            objALSelectedSarContent = objALFullSarContent;
            for(int i=0; i<objALSelectedSarContent.size(); i++)
            {
                String strSarDateTime = objALSelectedSarContent.get(i).toString();
                if(strSarDateTime.length() > strGlobalRelativeDateTimeFormat.length())
                {
                    strSarDateTime = strSarDateTime.substring(0, strGlobalRelativeDateTimeFormat.length());
                    
                    SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat(strGlobalRelativeDateTimeFormat);
                    Date objDateUserStart = objSimpleDateFormat.parse(strStartTimestamp);
                    Date objDateSarStart = objSimpleDateFormat.parse(strSarDateTime);
                    long longDifference = objDateSarStart.getTime() - objDateUserStart.getTime();
                    if(longDifference < 0)
                    {
                        objALSelectedSarContent.remove(i);
                        i = i - 1;
                    }
                    else
                    {
                        break;
                    }
                }
            }
            for(int i=0; i<objALSelectedSarContent.size(); i++)
            {
                if(objALSelectedSarContent.get(i).toString().compareTo("") == 0)
                {
                    objALSelectedSarContent.remove(i);
                    i = i - 1;
                }
                else
                {
                    break;
                }
            }
            for(int i=objALSelectedSarContent.size()-1; i>0; i--)
            {
                String strSarDateTime = objALSelectedSarContent.get(i).toString();
                if(strSarDateTime.length() > strGlobalRelativeDateTimeFormat.length())
                {
                    strSarDateTime = strSarDateTime.substring(0, strGlobalRelativeDateTimeFormat.length());
                    
                    SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat(strGlobalRelativeDateTimeFormat);
                    Date objDateUserEnd = objSimpleDateFormat.parse(strEndTimestamp);
                    Date objDateSarEnd = objSimpleDateFormat.parse(strSarDateTime);
                    long longDifference = objDateSarEnd.getTime() - objDateUserEnd.getTime();
                    if(longDifference > 0)
                    {
                        objALSelectedSarContent.remove(i);
                    }
                    else
                    {
                        break;
                    }
                }
            }
            for(int i=objALSelectedSarContent.size()-1; i>0; i--)
            {
                if(objALSelectedSarContent.get(i).toString().compareTo("") == 0)
                {
                    objALSelectedSarContent.remove(i);
                }
                else
                {
                    break;
                }
            }
            
            return objALSelectedSarContent;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.getSelectedSarTimeStampContent which tries to get Selected User Timestamp Sar Content from Full Sar Content." + "\n" + ex);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get Sar Content for selected Counter from User Selected Sar Content">
    public ArrayList getSarContentForSelectedCounter(ArrayList objALSarContent, String strCounterNames) throws Exception
    {
        ArrayList objALSelectedCounterSarContent = new ArrayList();
        
        try
        {
            for(int i=0; i<objALSarContent.size(); i++)
            {
                if(objALSarContent.get(i).toString().contains(strCounterNames) == true)
                {
                    for(int j=i+1; j<objALSarContent.size(); j++)
                    {
                        if(objALSarContent.get(j).toString().compareTo("") != 0)
                        {
                            objALSelectedCounterSarContent.add(objALSarContent.get(j).toString());
                        }
                        else
                        {
                            break;
                        }
                    }
                }
            }
            
            return objALSelectedCounterSarContent;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.getSarContentForSelectedCounter which tries to get Sar Content for selected Counter from User Selected Sar Content." + "\n" + ex);
        }
    }
    
    public ArrayList getSarContentForSelectedCounter(ArrayList objALSarContent, String strCounterNames, String strSeriesName, String strGlobalRelativeDateTimeFormat) throws Exception
    {
        ArrayList objALSelectedCounterSarContent = new ArrayList();
        
        try
        {
            for(int i=0; i<objALSarContent.size(); i++)
            {
                if(objALSarContent.get(i).toString().contains(strCounterNames) == true)
                {
                    for(int j=i+1; j<objALSarContent.size(); j++)
                    {
                        if(objALSarContent.get(j).toString().compareTo("") != 0)
                        {
                            if(objALSarContent.get(j).toString().substring(strGlobalRelativeDateTimeFormat.length(), objALSarContent.get(j).toString().length()).trim().split(" ")[0].compareTo(strSeriesName) == 0)
                            {
                                String strCurrentSarLine = objALSarContent.get(j).toString().substring(strGlobalRelativeDateTimeFormat.length(), objALSarContent.get(j).toString().length()).trim();
                                strCurrentSarLine = strCurrentSarLine.substring(strSeriesName.length(), strCurrentSarLine.length());
                                strCurrentSarLine = objALSarContent.get(j).toString().substring(0, strGlobalRelativeDateTimeFormat.length()).trim() + strCurrentSarLine;
                                //objALSelectedCounterSarContent.add(objALSarContent.get(j).toString());
                                objALSelectedCounterSarContent.add(strCurrentSarLine);
                            }
                        }
                        else
                        {
                            break;
                        }
                    }
                }
            }
            
            return objALSelectedCounterSarContent;
        }
        catch (Exception ex)
        {
            throw new Exception("Error in sumit.sarbox.method.CommonMethod.getSarContentForSelectedCounter which tries to get Sar Content for selected Counter from User Selected Sar Content." + "\n" + ex);
        }
    }
    // </editor-fold>
}
