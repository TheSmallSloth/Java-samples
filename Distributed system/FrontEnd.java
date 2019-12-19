import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class FrontEnd
{
	private int primaryServer;
	private Registry registry;
	
	private ServerSocket listenSocket;

	private String[] servers;
	
	public FrontEnd()
	{
		this.primaryServer = 0;

		try
		{
			registry = LocateRegistry.getRegistry("mira1.dur.ac.uk", 45854);
		}
		catch(Exception e)
		{
			System.out.println("Could not connect to registry");
			System.out.println(e);
			return;
		}
		
		try
		{
			this.listenSocket = new ServerSocket(45754);
		}
		catch(Exception e)
		{
			System.out.println("Failed to create server socket");
			System.out.println(e);
			return;
		}
		
		System.out.println("Front End listening");
		
		try
		{
			while(true)
			{
				new ClientThread(this.listenSocket.accept(), this).start();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error occured on the front end");
			System.out.println(e);
		}
	}
	
	public synchronized ServerInterface getStub()
	{
		ServerInterface stub = null;

		boolean foundServer = false;
		boolean gotList = false;

		while(!gotList)
		{
			gotList = true;
			try
			{
				this.servers = registry.list();
			}
			catch(Exception e)
			{
				gotList = false;
			}
		}

		while(!foundServer)
		{
			foundServer = true;
			try
			{
				stub = (ServerInterface)registry.lookup(this.servers[primaryServer]);
				stub.isRunning();
			}
			catch(Exception e)
			{
				foundServer = false;
				primaryServer = (primaryServer + 1)%this.servers.length;
			}
		}

		return stub;
	}
	
	public static void main(String[] args)
	{
		new FrontEnd();
	}
}
