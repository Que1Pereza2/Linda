package com.mycompany.serv6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionServ6 {
	private final int PORT=1237;
	private final String HOST="localhost";
	protected ServerSocket ss;
	protected Socket cs;
	
	public ConnectionServ6(String type) throws IOException{
		if(type.equalsIgnoreCase("server")) {
			ss=new ServerSocket(PORT);
		}else {
			cs=new Socket(HOST,PORT);
		}
	}
}
