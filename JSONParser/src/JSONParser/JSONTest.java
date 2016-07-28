package JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import JSONParser.JSONObject;
import JSONParser.Parser;

public class JSONTest 
{
	public static void main(String args[])
	{
		String file = "";
	
		BufferedReader br = null;
		try {
			
			Path path = Paths.get( "/home/triniti/JSONParser/JSONParser/src/JSONParser/object2.txt");
			//br = new BufferedReader(new FileReader("object.txt"));
			file = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

		} catch (IOException e) {
			e.printStackTrace();}
		Parser ob = new Parser(file);

		JSONObject l = ob.parseJSON();
		//for(JSONObject i:l)
			System.out.println(l);
	}
}