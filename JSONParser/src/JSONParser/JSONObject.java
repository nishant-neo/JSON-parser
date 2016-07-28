package JSONParser;

import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;

public class JSONObject {
	
	private Map<String,JSONValue> map;

	public JSONObject( Map<String,JSONValue> map ) 
	{
		this.map = map;
	}
	public Map<String, JSONValue> getPair() 
	{
		return map;
	}

	public void setPair(Map<String, JSONValue> map) 
	{
		this.map = map;
	}
	// add a name/value pair to the object
	public void add(String name, JSONValue value) 
	{
		
	}
	
	// return the value corresponding to a name.
	// return null if the name does not exist
	public JSONValue get(String name) 
	{
		return null;
	}
	public String toString() 
	{
		//System.out.println("WE");
		String str = new String();
		for (Map.Entry<String, JSONValue> entry : map.entrySet()) 
		    str = str + entry.getKey() + ":" + entry.getValue().toString() + "\n";
		return str;
	}
}