# sarbox
SarBox parses and analyzes SAR data in charts or text formats. Output can be also be exported to csv or PDF format.

## Generate Sar Log

To collect sar statistics from an environment, use below syntax

`[user@host ~]# sar -A 30 2 > sar_linux.log`

“30 2” reports for every 30 seconds a total of 2 times, written in sar_linux.log.

Sar log file would like below

![SampleSarLog](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/0SarLog.jpg)

Line# 1 and # 2 are Host Description.

Lines# 3 to # 13 are first set of sar data for Count# 1 with 30 seconds interval.

Lines# 15 to # 25 are second set of sar data for Count# 2 with 30 seconds interval.
## Access SarBox
`java -jar -Xms1g -Xmx1g SarBox.jar`

or double click SarBox.jar in Windows alike

## Initial SarBox screen

![SarBox Initial Screen](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/1Start_Screen.jpg)

To open a project, click File -> New Project

Select Sar_linux.log and click Open

![SarBox Select Sar Log](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/2SelectSarLog.jpg)

## Configuration Setting

Enter the values as required. Descriptions are provided below.

![SarBox Select Setting](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/3SelectSetting.jpg)

> Truncate first 2 lines which are sar log header and empty line. These two lines are not used by SarBox to process output.

> Select Date Time format correctly. There are few common format available or user can add of their own.

> Calculate sar log interval and provide the value

![SarBox Relative Time](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/3ParseTime.jpg)

> SarBox converts “absolute timestamp” to “relative timestamp”. I.e. 01:08:44 PM is converted to Thu Jan 01 13:08:44 IST 1970. This will be followed for all other lines in sar log.

> Relative Timestamp is required because there is no date in selected sar log and the log can run for more than 1 day in which case time will be repetitive. In order to maintain the sequence of sar data, a relative date is added based on their occurrence in sar log.

![SarBox Confirm Setting](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/4ConfirmSetting.jpg)

## Duration

Enter the values as required to select duration for SarBox to process and provide output. Descriptions are provided below.

![SarBox Select Duration](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/5SelectDuration.jpg)

Click Apply and OK

## SarBox Output

SarBox has parsed selected sar log and displayed Counters available in sar log.

Select any counter to get the details.

![SarBox Output](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/6SarBox.jpg)

## Export

Parsed sar log can be exported into csv or PDF format

![SarBox Export Option](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/7ExportOptions.jpg)

## PDF Output

![SarBox PDF Output](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/8PDFOutput.jpg)

## Configuration Files

SarBox is highly configurable. User can add, modify or delete counter name.

![SarBox Bin Folder](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/9BinFolder.jpg)

![SarBox Configuration Files](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/10ConfigurationFile.jpg)

![SarBox Counter Collection](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/11SarCounterCollection.jpg)

![SarBox Output After Configuring Counter Name](https://github.com/sumitbiswasgit/sarbox/blob/master/Help_Images/12CounterName.jpg)
