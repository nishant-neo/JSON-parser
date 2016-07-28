package JSONParser;
import JSONParser.Token;


public class Tokenizer {
	private String text;
	private int pos;
	public Tokenizer( String text)
	{
		this.text = text;
		pos = 0;
	}
	public Token getNextToken( )
	{
		if(!hasNextToken())
			return null;
		switch(text.charAt(pos)){
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			int temp = pos+1;
			while( Character.isDigit(text.charAt(temp) ) )
				temp++;
			String str = text.substring(pos,temp );
			pos = temp;
			return new Token (str, 13);
			
		case '\"':
			int t_pos = text.indexOf('"', pos+1);
			int t = pos+1;
			pos = t_pos + 1; 
			//System.out.println("character  " + text.charAt(pos));
			//System.out.println("token detected  " + text.substring(t,t_pos ));
			return new Token (text.substring(t,t_pos ), 1);
			
		case '{':
			pos++;
			return new Token("{", 2);
		case '}':
			pos++;
			return new Token("}",3);
		case '[':
			pos++;
			return new Token("[",4);
		case ']':
			pos++;
			return new Token("]",5);
		case '\0':
			pos++;
			return new Token("", 6);
		case ':':
			pos++;
			//System.out.println("after : character  " + text.charAt(pos));
			return new Token(":", 7);
		case ',':
			pos++;
			return new Token(",", 8);
		case 't':
			if( text.substring(pos, pos+5) == "true"){
				String t_temp = text.substring(pos, pos+5);
				pos = pos + 5;
				return new Token(t_temp, 9);
			}
			else
			{
				pos++;
				return new Token("",-1);
			}
		case 'f':
			if( text.substring(pos, pos+5) == "false"){
				String f_temp = text.substring(pos, pos+6);
				pos = pos+6;
				return new Token(f_temp, 10);
			}
			else
			{
				pos++;
				return new Token("",-1);
			}
				
		case ' ':
			pos++;
			return new Token("SPACE", 11);
		case '\n':
			pos++;
			return new Token("NEWLINE", 11);
		case '\t':
			pos++;
			return new Token("TAB", 12);
			
		default:
			pos++;
			return new Token("",-1);

		}

	}
	public int getPosition()
	{
		return this.pos;
	}
	public boolean hasNextToken()
	{
		if( pos < text.length())
			return true;
		return false;
	}
	public char lookAhead()
	{
		return text.charAt(pos);
	}
}
