package JSONParser;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;


import JSONParser.Token;
import JSONParser.Tokenizer;

public class Parser {
	/*Reference:
	Type 1 : ""
		 2 : {
		 3 : }
		 4 : [
		 5 : ]
		 7 : :
		 8 : ,
		-1 : default error 
		
	JSON Grammar:
		json   -> value
		value  -> STRING 
				| NUMBER
				| object
				| array
				| true
				| false
				| null
		object -> '{' pair ( ',' pair)* '}'
				| '{' '}'
		pair   -> STRING':'value
		array  -> '['value(','value)*
				| '['']'*/
	
	
	private Tokenizer tokenizer;
	private ArrayList <JSONObject> objectList;
	
	public Parser( String text)
	{
		this.tokenizer = new Tokenizer( text );
	}

	public JSONObject parseJSON()
	{
		Token token = tokenizer.getNextToken();
		//JSONObject json = getJSONObject();
		if(token != null && token.getType() == 2){
			//objectList.add(getJSONObject());
			return getJSONObject();
		}
		else
		{
			System.out.println("Expected { at position" + tokenizer.getPosition());
			System.exit(1);
		}
		return null;
		

	}
	public JSONObject getJSONObject()
	{
		Token token;
		Map<String,JSONValue> map = new HashMap<String,JSONValue>();
		computeField(map);
		token = tokenizer.getNextToken();
	
		if(token == null || token.getType() != 3){
			System.out.println("Expected } at position: "+ tokenizer.getPosition() + tokenizer.lookAhead());
			//System.exit(1);
		}	
		//System.out.println("object parsing done");
		System.out.println("object:" +new JSONObject(map).toString());
		return new JSONObject(map);
		//return null;
	}
	public void computeField(Map<String,JSONValue> map)
	{
		String key;
		
		if( tokenizer.hasNextToken() && tokenizer.lookAhead() == '}')
			return;
		Token token = tokenizer.getNextToken();
		while( token.getType() == 11 || token.getType() == 12 || token.getType() == 13)//pass if token has space
			token = tokenizer.getNextToken();
		if( token == null || token.getType() != 1)
		{
			System.out.println("Expected a string for key at position:  " + tokenizer.getPosition());
			System.exit(1);
		}
		key = token.getLexeme();
		token = tokenizer.getNextToken();
		while( token.getType() == 11 || token.getType() == 12 || token.getType() == 13)//pass if token has space
			token = tokenizer.getNextToken();
		if( token == null || token.getType() != 7)
		{
			System.out.println("Expected a : for key at position:  " + tokenizer.getPosition());
			System.exit(1);
		}
		
		//value in K,V pair
		token = tokenizer.getNextToken();
		while( token.getType() == 11 || token.getType() == 12 || token.getType() == 13)//pass if token has space
			token = tokenizer.getNextToken();
	
		if(token.getType() == 1){//string
			
			//token = tokenizer.getNextToken();
			JSONValue jsonValue = new JSONValue( token.getLexeme());
			//val.value = k.getLexeme();
			//val.type = 1;
			map.put(key, jsonValue);
			
		} 
		else if( token.getType() == 13)//Integer
		{
			JSONValue jsonValue = new JSONValue( Integer.parseInt( token.getLexeme() ));
			map.put(key, jsonValue);
		}
		else if( token.getType() == 9 || token.getType() == 10)//Boolean
		{
			JSONValue jsonValue = new JSONValue( Boolean.parseBoolean( token.getLexeme() ));
			map.put(key, jsonValue);
		}
		else if( token.getType() == 2)//JSON Object
		{
			JSONObject nestedObject = getJSONObject();
			JSONValue jsonValue = new JSONValue( nestedObject );
			map.put(key,jsonValue);
		}
		else if( token.getType() == 4)//Array
		{
			ArrayList <JSONValue> jsonArray = getJSONArray();
			JSONValue jsonValue = new JSONValue( jsonArray );
			map.put(key,jsonValue);	
		}
		else
		{
			System.out.println("Unexpected character at position" + tokenizer.getPosition());
			System.exit(1);
		}
		//System.out.println("Comma detected");
		//System.out.println(tokenizer.lookAhead());
		if(tokenizer.hasNextToken() && tokenizer.lookAhead() == ','){
			System.out.println("Continued");
			token = tokenizer.getNextToken();
			computeField(map);
		}
			
	}
	public ArrayList<JSONValue> getJSONArray()
	{
		Token token;
		ArrayList<JSONValue> array= new ArrayList<JSONValue>();
		computeArrayField(array);
		token = tokenizer.getNextToken();
	
		if(token == null || token.getType() != 5){
			System.out.println("Expected ]");
			System.exit(1);
		}	
		return array;
		//return null;
	}
	public void computeArrayField( ArrayList<JSONValue> array)
	{
		//Token token;
		if( tokenizer.hasNextToken() && tokenizer.lookAhead() == ']')
			return;
		Token token = tokenizer.getNextToken();
		while( token.getType() == 11 || token.getType() == 12 || token.getType() == 13)//pass if token has space
			token = tokenizer.getNextToken();

		if( token.getType() == 1)//String
		{
			JSONValue jsonValue = new JSONValue( token.getLexeme());
			array.add( jsonValue);
		}
		else if( token.getType() == 13)//Integer
		{
			JSONValue jsonValue = new JSONValue( Integer.parseInt( token.getLexeme() ));
			array.add( jsonValue);
		}
		else if( token.getType() == 9 || token.getType() == 10)//Boolean
		{
			JSONValue jsonValue = new JSONValue( Boolean.parseBoolean( token.getLexeme() ));
			array.add(  jsonValue);
		}
		else if( token.getType() == 2)//Object
		{
			JSONObject nestedObject = getJSONObject();
			JSONValue jsonValue = new JSONValue( nestedObject);
			array.add( jsonValue);	
		}
		else if( token.getType() == 4)//Array
		{
			ArrayList <JSONValue> nestedArray = getJSONArray();
			JSONValue jsonValue = new JSONValue( nestedArray);
			array.add( jsonValue);	
		}
		else
		{
			System.out.println("Unknown Character at position" + tokenizer.getPosition());
			System.exit(1);
		}
		System.out.println("Comma detected");
		if(tokenizer.hasNextToken() && tokenizer.lookAhead() == ','){
			
			token = tokenizer.getNextToken();
			computeArrayField(array);
		}
		
	}

}
