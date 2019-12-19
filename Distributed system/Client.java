import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class Client
{
	private Socket clientSocket;
	private BufferedReader clientReader;
	private BufferedReader socketReader;
	private OutputStream socketWriter;
	
	public Client()
	{
		this.clientReader = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			this.clientSocket = new Socket("mira1.dur.ac.uk", 45754);
		}
		catch(Exception e)
		{
			System.out.println("Client failed to connect to Front End");
			System.out.println(e);
			try{clientSocket.close();}catch(Exception ex){System.out.println(ex);}
			return;
		}
		
		try
		{
			socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			socketWriter = clientSocket.getOutputStream();
		}
		catch(Exception e)
		{
			System.out.println("Socket error");
			System.out.println(e);
			try{clientSocket.close();}catch(Exception ex){System.out.println(ex);}
			return;
		}
		
		System.out.println("*******");
		System.out.println("Commands:");
		System.out.println("order item,item,item");
		System.out.println("cancel item,item,item");
		System.out.println("get");
		System.out.println("exit");
		System.out.println("*******");
		System.out.println("");
		
		while(true)
		{
			System.out.print("Command: ");

			try
			{
				String clientRequest = clientReader.readLine();
			
				if(clientRequest.length() >= 4 && clientRequest.substring(0,4).toLowerCase().equals("exit"))
				{
					break;
				}
			
				clientRequest += (char)0;
			
				socketWriter.write(clientRequest.getBytes());
			
				int readChar;
				String frontEndResponse="";
			
				while((readChar = socketReader.read()) != 0 && readChar != -1)
				{
					frontEndResponse += (char)readChar;
				}
				
				if(readChar == -1)
				{
					System.out.println("The front end has ended the connection");
				}
				else
				{
					System.out.println(frontEndResponse);
				}
			}
			catch(Exception e)
			{
				System.out.println("***An error occured, try again***\n");
				System.out.println(e);
			}
		}
		try{clientSocket.close();}catch(Exception ex){System.out.println(ex);}
	}
	
	public static void main(String[] args)
	{
		new Client();
	}
}
