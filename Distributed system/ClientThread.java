import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread
{
	private Socket clientSocket;
	private BufferedReader clientReader;
	private OutputStream clientWriter;
	private FrontEnd frontEnd;
	
	public ClientThread(Socket clientSocket, FrontEnd frontEnd)
	{
		this.clientSocket = clientSocket;
		this.frontEnd=frontEnd;
	}

	@Override
	public void run()
	{
		try
		{
			this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.clientWriter = this.clientSocket.getOutputStream();
		}
		catch (IOException e)
		{
			System.out.println("A client socket failed to initialize");
			System.out.println(e);
			try{clientSocket.close();}catch(Exception ex){System.out.println(ex);}
			return;
		}
		
		
		int readChar;
		String clientRequest = "";

		try
		{
			while((readChar = clientReader.read()) != -1)
			{
				if(readChar == 0)
				{
					if(clientRequest.length() >= 5 && clientRequest.substring(0, 5).toLowerCase().equals("order"))
					{
						int cursor=5;
						
						ArrayList<String> items = new ArrayList<String>();
						
						String item = "";
						
						while(cursor < clientRequest.length())
						{
							if(clientRequest.charAt(cursor) != ' ' && clientRequest.charAt(cursor) != ',')
							{
								item += clientRequest.charAt(cursor);
							}
							else if(item.length()>0)
							{
								items.add(new String(item));
								item = "";
							}
							cursor++;
						}
						if(item.length()>0)
						{
							items.add(new String(item));
						}
						
						this.frontEnd.getStub().addOrder(items);
						
						this.clientWriter.write(("\n***Order placed***\n" + (char)0).getBytes());
					}
					else if(clientRequest.length() >= 6 && clientRequest.substring(0, 6).toLowerCase().equals("cancel"))
					{
						int cursor=6;
						
						ArrayList<String> items = new ArrayList<String>();
						
						String item = "";
						
						while(cursor < clientRequest.length())
						{
							if(clientRequest.charAt(cursor) != ' ' && clientRequest.charAt(cursor) != ',')
							{
								item += clientRequest.charAt(cursor);
							}
							else if(item.length()>0)
							{
								items.add(new String(item));
								item = "";
							}
							cursor++;
						}
						if(item.length()>0)
						{
							items.add(new String(item));
						}
						
						if(this.frontEnd.getStub().cancelOrder(items))
						{
							this.clientWriter.write(("\n***Order cancelled***\n" + (char)0).getBytes());
						}
						else
						{
							this.clientWriter.write(("\n***No such order***\n" + (char)0).getBytes());
						}
					}
					else if(clientRequest.length() >= 3 && clientRequest.substring(0, 3).toLowerCase().equals("get"))
					{
						ArrayList<ArrayList<String>> orders = this.frontEnd.getStub().getOrders();

						clientWriter.write("\n***Orders***:\n".getBytes());
						
						for(ArrayList<String> order:orders)
						{
							for(String item:order)
							{
								clientWriter.write(item.getBytes());
								clientWriter.write(", ".getBytes());
							}
							clientWriter.write("\n".getBytes());
						}
						clientWriter.write(("\n" +(char)0).getBytes());
					}
					else
					{
						clientWriter.write(("\n***Invalid command***\n" + (char)0).getBytes());
					}
					clientRequest = "";
				}
				else
				{
					clientRequest += (char)readChar;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("A client thread failed");
			System.out.println(e);
			try{clientSocket.close();}catch(Exception ex){System.out.println(ex);}
			return;
		}
	}
}
