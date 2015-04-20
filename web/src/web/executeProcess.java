package web;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

//import java.util.logging.Logger;

public class executeProcess{
public  void executeProcess() throws IOException,  
            InterruptedException {  
  
        final File executorDirectory = new File("/home/sushmita/Downloads");  
  
        
 final String shellScript = "./abc.sh";  
 String[] commands ={shellScript};
 ProcessBuilder processBuilder = new ProcessBuilder(commands);  
           
  
        processBuilder.directory(executorDirectory);  
  
        Process process = processBuilder.start();  
  
        try {  
            int shellExitStatus = process.waitFor();  
            if (shellExitStatus != 0) {  java.io.InputStream is = process.getInputStream();
            java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
            // And print each line
            String s = null;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
            is.close(); 
              
            }  
        } catch (InterruptedException ex) {  
            
        System.out.println("error");
        }  
  
    }  
public static void main(String[] args) throws IOException, InterruptedException
{
	executeProcess exec=new executeProcess();
	exec.executeProcess();
	
	}
}