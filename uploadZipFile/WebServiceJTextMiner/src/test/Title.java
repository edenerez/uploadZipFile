package test;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;


/*
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
*/
//@Path("/test/title/")
@Path("/test")
public class Title {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTEXT_HTML()
	{
		System.out.println("TEXT_HTML");
		return "<html><body><p>test title TEXT_HTML</p></body></html>";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String returnTEXT_PLAIN(){
		System.out.println("TEXT_PLAIN");
		
		return "test title TEXT_PLAIN";
	}
	
	@Path("/v")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String returnTEXT(){
		System.out.println("TEXT_PLAIN");
		
		return "test title vvv";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String returnTEXT_XML()
	{
		System.out.println("TEXT_XML");
		
		return "test title TEXT_XML";
	}
	
	@GET
	@Path("{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPathParam(
			@PathParam("name") String name)
	{
		//http://localhost:8080/WebServiceJTextMiner/api/test/bla
		return "<html><body><p>test title TEXT_HTML "+name+"</p></body></html>";
	}
	
	@GET
	@Path("/QueryParam")
	@Produces(MediaType.TEXT_PLAIN)
	public String getQueryParam(
			@QueryParam("name") String name)
	{
		//http://localhost:8080/WebServiceJTextMiner/api/test/QueryParam?name=bla2
		return "<html><body><p>test title TEXT_HTML "+name+"</p></body></html>";
	}
	
	@GET
	@Path("/MatrixParam")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMatrixParam(
			@MatrixParam("name") String name)
	{
		//http://localhost:8080/WebServiceJTextMiner/api/test/MatrixParam;name=bla2
		return "<html><body><p>test title TEXT_HTML "+name+"</p></body></html>";
	}
	
	@POST
	@Path("/FormParam")
	@Produces(MediaType.TEXT_HTML)
	public String getFormParam(
			@FormParam("name") String name)
	{
		System.out.println("TEXT_HTML");
		return "<html><body><p>test title TEXT_HTML "+name+"</p></body></html>";
	}
	
	
	@GET
	@Path("/DownloadFile")
	@Produces("text/plain") // "application/pdf" , "image/png"
	public Response getFile(
			@MatrixParam("name") String name)
	{
		//http://localhost:8080/WebServiceJTextMiner/api/test/DownloadFile
		File file = new File("c://DummyAE.xml");
		ResponseBuilder rb = Response.ok(file);
		rb.header("Content-Disposition", "attachment; filename=DisplayName-DummyAE.xml");
		return rb.build();
	}
	
	
	@POST
	@Path("/upload1")
	@Consumes(MediaType.MULTIPART_FORM_DATA) //"multipart/form-data")
	public String uploadFile1(
			InputStream fileInputStream)//,
			//@FormDataParam("file") FormDataContentDisposition contentDispositionHeader)
	{
		
		System.out.println("upload1");
		String filePath = "C:\\bla.zip" ;//+ contentDispositionHeader.getFileName();
			 
		// save the file to the server
		saveFile(fileInputStream, filePath);
			 
		String output = "File saved to server location : " + filePath;
			 
		return output;
		
	}
	
	@POST
    @Path("/upload2") // working
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile2(FormDataMultiPart form) {
    	System.out.println("upload32");
		
         FormDataBodyPart filePart = form.getField("file");
 
         ContentDisposition headerOfFilePart =  filePart.getContentDisposition();
 
         InputStream fileInputStream = filePart.getValueAs(InputStream.class);
 
         String filePath = "C:\\Users\\user\\Desktop\\" + headerOfFilePart.getFileName();

        // save the file to the server
        saveFile(fileInputStream, filePath);
 
        String output = "File saved to server location using FormDataMultiPart : " + filePath;
	 
	    return Response.status(200).entity(output).build();
 
	}
	
	// save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream,
            String serverLocation) {
 
        try {
            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
 
            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
            	//System.out.println("bytes: " + bytes);
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
            
        } catch (IOException e) {
 
            e.printStackTrace();
        }
 
    }
	
    
    @POST
	@Path("/uploadText")
	@Consumes(MediaType.MULTIPART_FORM_DATA) //"multipart/form-data")
	public String uploadTextFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader)
	{
		
		System.out.println("uploadText");
		//String filePath = "C:\\Users\\user\\Desktop\\" + contentDispositionHeader.getFileName();
		String filePath = contentDispositionHeader.getFileName();
		
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		// save the file to the server
		saveFile(fileInputStream, filePath);
		
		
		String strFile = System.getProperty("user.dir")+"/"+filePath;
		String output = "File saved to server location : " + filePath + " IN " + System.getProperty("user.dir");
			 
		File file =  new File(strFile);
		
		String fileContentWindows = IOUtils.readFromFile(file.getAbsolutePath(),"Windows-1255");
		String fileContentUnicode = IOUtils.readFromFile(file.getAbsolutePath(),"Unicode");
		String fileContentUTF = IOUtils.readFromFile(file.getAbsolutePath(),"UTF-8");
		String fileContentANSI = IOUtils.readFromFile(file.getAbsolutePath(),"Cp1255");
		int lengthWindows = fileContentWindows.split("[à-ú]").length;
		int lengthUnicode = fileContentUnicode.split("[à-ú]").length;
		int lengthUTF = fileContentUTF.split("[à-ú]").length;
		int lengthANSI = fileContentANSI.split("[à-ú]").length;
		int i = 0;
		int maxLength = Math.max(lengthWindows, Math.max(lengthUnicode,lengthUTF));
		if(maxLength==lengthWindows)
			 IOUtils.writeToFile(System.getProperty("user.dir")+"/" + file.getName().replaceAll(".txt", ".rtf"), fileContentWindows);
		else if(maxLength==lengthUnicode)
			IOUtils.writeToFile(System.getProperty("user.dir")+"/" + file.getName().replaceAll(".txt", ".rtf"), fileContentUnicode);
		else if(maxLength==lengthUTF)
			IOUtils.writeToFile(System.getProperty("user.dir")+"/" + file.getName().replaceAll(".txt", ".rtf"), fileContentUTF);
		else //if (maxLength==lengthANSI)
			System.out.println("file: " + file.getName()+"\tlengthWindows:\t" + lengthWindows + "\tlengthUnicode:\t" + lengthUnicode+ "\tlengthUTF:\t" + lengthUTF+ "\tlengthANSI:\t" + lengthANSI);
		//System.out.println("file: " + file.getName()+"\tlengthWindows:\t" + lengthWindows + "\tlengthUnicode:\t" + lengthUnicode+ "\tlengthUTF:\t" + lengthUTF+ "\tlengthANSI:\t" + lengthANSI);
		
		
		return output + "<br>file.getAbsolutePath(): " + file.getAbsolutePath() + "<br>fileContentWindows: <br>" + fileContentWindows 
		+ "<br>fileContentUnicode: <br>" + fileContentUnicode 
		+ "<br>fileContentUTF: <br>" + fileContentUTF;
		
	}
    
    
    @POST
	@Path("/uploadFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA) //"multipart/form-data")
	public String uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader)
	{
		
		System.out.println("uploadFile");
		//String filePath = "C:\\Users\\user\\Desktop\\" + contentDispositionHeader.getFileName();
		String filePath = contentDispositionHeader.getFileName();
		
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		// save the file to the server
		saveFile(fileInputStream, filePath);
		
		
		String strFile = System.getProperty("user.dir")+"/"+filePath;
		String output = "File saved to server location : " + filePath + " IN " + System.getProperty("user.dir");
			 
		File file =  new File(strFile);
		
		return output + "<br>file.getAbsolutePath(): " + file.getAbsolutePath() + "<br>fileContentWindows: <br>";
		
	}
    
    
    @POST
    @Path("/uploadZipFile") // working
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadZipFile(FormDataMultiPart form) {
    	System.out.println("uploadZipFile");
		
         FormDataBodyPart filePart = form.getField("zipfile");
 
         ContentDisposition headerOfFilePart =  filePart.getContentDisposition();
 
         InputStream fileInputStream = filePart.getValueAs(InputStream.class);
 
         String filePath = "C:\\Users\\user\\Desktop\\" + headerOfFilePart.getFileName();

        // save the file to the server
        saveFile(fileInputStream, filePath);
 
        String output = "File saved to server location using FormDataMultiPart : " + filePath;
	 
	    return Response.status(200).entity(output).build();
 
	}
	 
    @POST
    @Path("/uploadTxtFile") // working
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadTxtFile(FormDataMultiPart form) {
    	System.out.println("uploadTxtFile");
		
         FormDataBodyPart filePart = form.getField("txtfile");
 
         ContentDisposition headerOfFilePart =  filePart.getContentDisposition();
 
         InputStream fileInputStream = filePart.getValueAs(InputStream.class);
 
         String filePath = "C:\\Users\\user\\Desktop\\" + headerOfFilePart.getFileName();

        // save the file to the server
        saveFile(fileInputStream, filePath);
 
        String output = "File saved to server location using FormDataMultiPart : " + filePath;
	 
        
        return Response.status(200).entity(output).build();
        /*
        try {
        	return Response.created(new URI("http://google.com")).build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	    
 
	}
    
    
	  
	
}
