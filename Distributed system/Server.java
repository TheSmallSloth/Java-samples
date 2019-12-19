
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements ServerInterface
{
	private ArrayList<ArrayList<String>> orders;
	private Registry registry;
	private String name;
	
	public Server(String name)
	{
		this.name = name;
		this.orders = new ArrayList<ArrayList<String>>();
		
		try
		{
			ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(this, 0);

			registry = LocateRegistry.getRegistry("mira1.dur.ac.uk", 45854);
						
			registry.rebind(name, stub);
			
			System.out.println("Server running with name " + name);
		}
		catch(Exception e)
		{
			System.out.println("Server could not access registry");
			System.out.println(e);
			return;
		}
	}
	
	public void addOrder(ArrayList<String> items)
	{
		this.orders.add(items);

		String[] servers;
		try
		{
			servers = this.registry.list();
		}
		catch(Exception e)
		{
			return;
		}

		for(int i = 0; i<servers.length; ++i)
		{
			if(!servers[i].equals(this.name))
			{
				try
				{
					((ServerInterface)this.registry.lookup(servers[i])).setOrders(this.orders);
				}
				catch(Exception e)
				{
					continue;
				}
			}
		}
	}
	
	public boolean cancelOrder(ArrayList<String> order)
	{
		for(int i=0; i<this.orders.size(); ++i)
		{
			if(this.orders.get(i).equals(order))
			{
				orders.remove(i);

				String[] servers;
				try
				{
					servers = this.registry.list();
				}
				catch(Exception e)
				{
					return true;
				}
				for(int j = 0; j<servers.length; ++j)
				{
					if(!servers[i].equals(this.name))
					{
						try
						{
							((ServerInterface)this.registry.lookup(servers[i])).setOrders(this.orders);
						}
						catch(Exception e)
						{
							continue;
						}
					}
				}

				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<ArrayList<String>> getOrders()
	{
		return this.orders;
	}
	
	public static void main(String[] args)
	{
		if(args.length == 0){System.out.println("Please give the server a name, eg \"java Server server1\"");}
		else{new Server(args[0]);}
	}
	
	public boolean isRunning()
	{
	    return true;
	}

	public void setOrders(ArrayList<ArrayList<String>> orders)
	{
		this.orders = orders;
	}
}
