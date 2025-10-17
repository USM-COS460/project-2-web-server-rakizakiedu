package src;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HTTPClientTest extends Thread {

	Socket socket;
	InputStream clientInput;
	OutputStream clientOutput;
	
	public HTTPClientTest(Socket socket, InputStream clientInput, OutputStream clientOutput) {
		this.socket = socket;
		this.clientInput = clientInput;
		this.clientOutput = clientOutput;
	}
	public static void main(String[] args) {
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
