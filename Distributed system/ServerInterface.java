
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
public interface ServerInterface extends Remote
{
	public void addOrder(ArrayList<String> items) throws RemoteException;
	
	public boolean cancelOrder(ArrayList<String> order) throws RemoteException;
	
	public ArrayList<ArrayList<String>> getOrders() throws RemoteException;
	
	public boolean isRunning() throws RemoteException;

	public void setOrders(ArrayList<ArrayList<String>> orders) throws RemoteException;
}
