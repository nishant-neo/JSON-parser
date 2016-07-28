package JSONParser;

import java.util.ArrayList;

public class JSONValue {
	
	private String jString ;
	private JSONObject jObj ;
	private ArrayList<JSONValue> jArray;
	private int jInt;
	private boolean jBool;
	private int type;
	/* Implement 3 constructors each of which takes one of
	String, JsonObject or array of JsonValues as argument, and
	assigns it to the appropriate field. Only one of these three
	fields should be non-null */
	// ... the constructors
	// we will need 3 different methods for accessing the appropriate fields.
	// Note that this helps with strong-typing
	public JSONValue( Boolean jBool)
	{
		this.type = 4;
		this.jBool = jBool;
	}
	public JSONValue( int jInt)
	{
		this.type = 5;
		this.jInt = jInt;
	}
	public JSONValue( String jString )
	{
		this.type = 1;
		this.jString = jString;
	}
	public JSONValue( JSONObject jObj)
	{
		this.type = 3;
		this.jObj = jObj;
	}
	public JSONValue( ArrayList <JSONValue> jArray)
	{
		this.type = 2;
		this.jArray = jArray;
	}
	public String getString() 
	{
		return this.jString;
	}
	public JSONObject getObj() 
	{
		return this.jObj;
		
	}
	public ArrayList<JSONValue> getArray() 
	{
		return this.jArray;
		
	}
	public String toString()
	{
		//System.out.println("TY");
		//System.out.println(this.type);
		if( this.type == 1)
			return this.jString;
		else if( this.type == 2)
		{
			;
		}
		else if( this.type == 3)
		{
			return this.jObj.toString();
		}
		else if( this.type == 4)
		{
			if( this.jBool)
				return "true";
			else
				return "false";
		}
		else if( this.type == 5)
		{
			String s = Integer.toString(this.jInt);
			return s;
		}
		return "NULL";
	}
}