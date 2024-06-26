package com.mycompany.serv4_5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionServ4_5 {
	private final int PORT=1236;
	private final String HOST="localhost";
	protected ServerSocket ss;
	protected Socket cs;
	
	public ConnectionServ4_5(String type) throws IOException{
		if(type.equalsIgnoreCase("server")) {
			ss=new ServerSocket(PORT);
		}else{
			cs= new Socket(HOST,PORT);
		}
	}
}
