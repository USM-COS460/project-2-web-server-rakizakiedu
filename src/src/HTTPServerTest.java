package src;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServerTest {

	public static void main(String[] args) {
		if (args.length < 2) {
			return;
		}
		
		int port = Integer.parseInt(args[0]); // For the port argument
		String directory = args[1]; // for the directory argument
		//File document = new File(directory); 

		try (ServerSocket serverSocket = new ServerSocket(port)){
			
			while (true) {
				Socket socketClient = serverSocket.accept();
				InputStream clientInput = socketClient.getInputStream();
				OutputStream clientOutput = socketClient.getOutputStream();
				
				Thread serverThread = new HTTPClientTest(socketClient, clientInput, clientOutput);
				serverThread.start();
			}
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

// Steps / Requirements (Server)
// Must create SocketServer.
// Must let multiple clients connect to server using port. (using threads?)
// 