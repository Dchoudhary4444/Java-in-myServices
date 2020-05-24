package com.thinking.machines.myServices;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class DownloadFileServlet extends HttpServlet 
{



protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
System.out.println("request aayi");
String filePath = "C:/tomcat9/webapps/myServices/WEB-INF/myfiles/urlTOPdf/"+request.getParameter("fileName")+".pdf";
File downloadFile = new File(filePath);
FileInputStream inStream = new FileInputStream(downloadFile);
String relativePath = getServletContext().getRealPath("");
System.out.println("relativePath = " + relativePath);
         
// obtains ServletContext
ServletContext context = getServletContext();
         
// gets MIME type of the file
String mimeType = context.getMimeType(filePath);
if (mimeType == null) 
{        
// set to binary type if MIME mapping not found
mimeType = "application/octet-stream";
}
System.out.println("MIME type: " + mimeType);
         
// modifies response
response.setContentType(mimeType);
//response.setContentType("application/octet-stream");
response.setContentLength((int) downloadFile.length());
         
// forces download
String headerKey = "Content-Disposition";
String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
response.setHeader(headerKey, headerValue);
         
// obtains response's output stream
OutputStream outStream = response.getOutputStream();
         
byte[] buffer = new byte[4096];
int bytesRead = -1;
         
while ((bytesRead = inStream.read(buffer)) != -1) {
outStream.write(buffer, 0, bytesRead);
}
         
inStream.close();
outStream.close();   
System.out.println("code ends here");  
}
}