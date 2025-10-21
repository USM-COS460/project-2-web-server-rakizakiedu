package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServerTest {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Missing one or more arguments, try again. "
					+ "Usage: java src.HTTPServerTest (port number) (directory where file is located)");
			return;
		}
		
		int port = Integer.parseInt(args[0]); // For the port argument
		String directory = args[1]; // for the directory argument

		try (ServerSocket serverSocket = new ServerSocket(port)){
			System.out.println("Server started with Port: " + port);
			
			while (true) {
				Socket socketClient = serverSocket.accept();
				System.out.println("Client Connected.");
				
				Thread serverThread = new Thread(new HTTPClient(socketClient, directory)); // makes a new thread for every new client connected
				serverThread.start();
			}
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}