package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HTTPClient extends Thread {

	Socket socket;
	String directory;
	
	public HTTPClient(Socket socket, String directory) {
		this.socket = socket;
		this.directory = directory;
	}
	
	@Override
	public void run() {
		try {
			InputStream clientInput = socket.getInputStream(); // Handles client input
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientInput)); // Handles what the server sees
			OutputStream clientOutput = socket.getOutputStream(); // Handles client output

			String getRequest = reader.readLine(); // Prompts user for request (ex: "GET /index.html HTTP/1.0")
			String[] requestTokens = getRequest.split(" "); // separates the 3 parts of the request. 
			String requestedFile = requestTokens[1]; // gets the second element of the requestTokens array, which is the file to open (like \index.html)
			
			// http://localhost:1111/index.html to load index.html.
			
			File file = new File(directory, requestedFile);
            
			String MIMEType = getMIMEType(requestedFile);
			LocalDate date = LocalDate.now();
			
            // if file exists/is found, display the page
            if (file.exists()) {
            	byte[] fileBytes = null; // has to be byte array to accommodate images, since images are stored as binary files, not strings
            	
            	if (MIMEType != null) {
                	if (MIMEType.startsWith("text")) {
                	    // means it is a text file (like .html and .css)
                	    String body = readFile(file);
                	    fileBytes = body.getBytes();
                	} 
                	else {
                	    // means it is a non-text file, like image (like .jpeg)
                	    fileBytes = Files.readAllBytes(file.toPath());
                	}
            	}

            	clientOutput.write("HTTP/1.0 200 OK\r\n".getBytes());
            	clientOutput.write(("Date: " + date.getDayOfWeek() + ", " + date.format(DateTimeFormatter.ofPattern("dd MM yyyy")) + " " +
            			LocalTime.now() + " EST\r\n").getBytes());
            	clientOutput.write("Server: HTTPServerTest/1.0\r\n".getBytes());
            	clientOutput.write(("Content-Type: " + MIMEType + "\r\n").getBytes());
            	clientOutput.write(("Content-Length: " + fileBytes.length + "\r\n").getBytes());
            	clientOutput.write("\r\n".getBytes());
            	clientOutput.write(fileBytes); // this part makes it so you can't really scroll up to see the info above, but if you comment it out, you can see that it is there.
            	clientOutput.flush();

            }
            // if file isn't found, output 404
            else {                
            	String errorHeader = "<h1>404 - Not Found</h1>";
            	
                clientOutput.write("HTTP/1.0 404 Not Found\r\n".getBytes());
            	clientOutput.write(("Date: " + date.getDayOfWeek() + ", " + date.format(DateTimeFormatter.ofPattern("dd MM yyyy")) + " " +
            			LocalTime.now() + " EST\r\n").getBytes());
            	clientOutput.write("Server: HTTPServerTest/1.0\r\n".getBytes());
                clientOutput.write("Content-Type: text/html\r\n".getBytes());
                clientOutput.write(("Content-Length: " + errorHeader.length() + "\r\n").getBytes());
                clientOutput.write("\r\n".getBytes());
                clientOutput.write(errorHeader.getBytes());
                clientOutput.flush();
            }
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static String readFile(File file) throws IOException {
		String fileString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				fileString += currentLine + "\n";
			}
		}
		return fileString;
		
	}
	
	public static String getMIMEType(String requestedFile) {
		String MIMEType;

		if (requestedFile.endsWith(".html")) {
		    MIMEType = "text/html";
		} 
		else if (requestedFile.endsWith(".css")) {
		    MIMEType = "text/css";
		} 
		else if (requestedFile.endsWith(".jpeg")) {
			MIMEType = "image/jpeg";
		}
		else if (requestedFile.endsWith(".png")) {
			MIMEType = "image/png";
		}
		else if (requestedFile.endsWith(".gif")) {
			MIMEType = "image/gif";
		}
		else if (requestedFile.endsWith(".webp")) {
			MIMEType = "image/webp";
		}
		else {
			MIMEType = null;
		}
        
		return MIMEType;
	}

}
