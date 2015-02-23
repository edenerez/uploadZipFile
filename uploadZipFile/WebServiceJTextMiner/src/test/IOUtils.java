package test;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class IOUtils
{
	
/*	
	public static UnicodeReader openRead(String filePath) throws Exception
	{
		File file = new File(filePath);
		
		FileInputStream fileInputStream = new FileInputStream(file);
		
		// this is just the default encoding used if the document do not contain the BOM
		UnicodeReader unicodeReader = new UnicodeReader(fileInputStream, Settings.defaultEncoding);
		
		return unicodeReader;
	}
*/
	
	public static void delete(File file)
	    	throws IOException{
	 
	    	if(file.isDirectory()){
	 
	    		//directory is empty, then delete it
	    		if(file.list().length==0){
	 
	    		   file.delete();
	    		   System.out.println("Directory is deleted : " 
	                                                  + file.getAbsolutePath());
	 
	    		}else{
	 
	    		   //list all the directory contents
	        	   String files[] = file.list();
	 
	        	   for (String temp : files) {
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	 
	        	      //recursive delete
	        	     delete(fileDelete);
	        	   }
	 
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	     System.out.println("Directory is deleted : " 
	                                                  + file.getAbsolutePath());
	        	   }
	    		}
	 
	    	}else{
	    		//if file, then delete it
	    		file.delete();
	    		//System.out.println("File is deleted : " + file.getAbsolutePath());
	    	}
	    }
	public static BufferedReader openRead(String filePath, String encoding) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(
			    new FileInputStream(filePath), encoding));
		
		return in;
	}
	
	public static String readFromFile(String fname, String encode)
	{
		String strResult = "";
		try {
		    File fileDir = new File(fname);

		    BufferedReader in = new BufferedReader(
		       new InputStreamReader(
		                  new FileInputStream(fileDir), encode));

		    String str;

		    while ((str = in.readLine()) != null) {
		    	strResult += str + "\r\n";
		    }

		    in.close();
	    } 
	    catch (UnsupportedEncodingException e) 
	    {
	        System.out.println(e.getMessage());
	    } 
	    catch (IOException e) 
	    {
	        System.out.println(e.getMessage());
	    }
	    catch (Exception e)
	    {
	        System.out.println(e.getMessage());
	    }
		
		return strResult;
	}
	
	public static String readFromFile(String absolutePath) {
		String readString = "";
		File file = new File(absolutePath);
		try {
          BufferedReader output = new BufferedReader(new FileReader(file));
          String strLine = "";
          while(strLine!=null)
          {
        	  strLine= output.readLine();
        	  readString+=" " +strLine;
          }
          output.close();
        } catch ( IOException e ) {
           e.printStackTrace();
        }
		return readString;
	}
	
	
	public static void writeToFile(String fname, String content)
	{
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(fname), "UTF-8"));
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
