package com.mycompany.replica1_3;

import com.mycompany.crud.Crud;
import com.mycompany.linda.Tuple;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Replica1_3Thread extends Crud {
	private int id;
	private Socket cs;
	public List<Tuple> database = new ArrayList<>();
	public boolean s1_3r;

	public Replica1_3Thread(int id,Socket cs,boolean s1_3r) {
		this.id=id;
		this.cs=cs;
		this.s1_3r=s1_3r;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Client Online");
			DataInputStream in = new DataInputStream(cs.getInputStream());
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());
			
			while(true) {
				String message=in.readUTF();
			
				if(message.equalsIgnoreCase("END OF SERVICE")) break;
				
				System.out.println("Message recieved -> "+message+" by Replica1_3"+id);

				//Extract the option from the message
				int option = getOption(message);

				//Transform the message into a tuple
				Tuple tuple = tuplifyer(message);

				//Switch case for the options
				switch (option){
					//Case for addNote
					case 1:
						this.database = addNote(tuple, database);
							out.writeUTF("Tuple added succesfully");
						break;

					//Case for addNote
					case 2:
						ArrayList<Tuple> result = findNote(tuple, database);
						if(result.isEmpty()) out.writeUTF("Not a touple with those characteristcs was found");
						else{

							//Returns the message of every tuple in the result if not empty
							for(Tuple t : result){
								out.writeUTF(messagefyer(t));
							}
						}
						break;

						//Case for deleteNote
					case 3:
						List<Tuple> results = findNote(tuple, database);
						this.database = deleteNote(results, database);
							out.writeUTF("Coincident results were deleted");
				}

			}
			cs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}