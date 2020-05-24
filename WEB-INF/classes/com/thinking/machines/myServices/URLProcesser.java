package com.thinking.machines.myServices;
import java.net.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.pdfcrowd.*;

public class URLProcesser extends HttpServlet
{

public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
doPost(rq,rs);
}

public void doPost(HttpServletRequest rq,HttpServletResponse rs)
{
try
{
String url=rq.getParameter("url");
String fileName=rq.getParameter("fileName");

System.out.println("Request arrived");
System.out.println("Data arrived");
System.out.println("URL : "+url);
System.out.println("FileName : "+fileName);
System.out.println("coverter call hua");

URLProcesser.convert(url,fileName);
System.out.println("covert hogya");
}catch(Exception exception)
{
System.out.println(exception);
}
}//main method ends here

public static void convert(String url,String fileName) throws IOException, Pdfcrowd.Error 
{
try
{
System.out.println("code chala");
Pdfcrowd.HtmlToPdfClient client =new Pdfcrowd.HtmlToPdfClient("demo", "ce544b6ea52a5621fb9d55f8b542d14d");
FileOutputStream outputFile = new FileOutputStream("C:/tomcat9/webapps/myServices/WEB-INF/myfiles/urlTOPdf/"+fileName+".pdf");
byte[] pdf = client.convertUrl(url);
outputFile.write(pdf);
outputFile.close();
System.out.println("pdf convert hoke folder me save hogya hai");
}
catch(Pdfcrowd.Error why) 
{
System.err.println("Pdfcrowd Error: " + why);
throw why;
}
catch(IOException why) 
{
System.err.println("IO Error: " + why);
throw why;
}
}


}