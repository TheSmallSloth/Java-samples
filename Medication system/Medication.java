
public class Medication
{
	private String company;
	private String drugName;
	private String barcode;
	private float strength;
	private int tabletsPerPack;
	
	public Medication(String company, String drugName, String barcode, float strength, int tabletsPerPack)
	{
		this.company = company;
		this.drugName = drugName;
		this.barcode = barcode;
		this.strength = strength;
		this.tabletsPerPack = tabletsPerPack;
	}
	
	public String getCompany()
	{
		return this.company;
	}
	
	public String getName()
	{
		return this.drugName;
	}
	
	public String getBarcode()
	{
		return this.barcode;
	}
	
	public float getStrength()
	{
		return this.strength;
	}
	
	public int getTabletsPerPack()
	{
		return this.tabletsPerPack;
	}
}
