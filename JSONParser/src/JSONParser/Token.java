package JSONParser;

public class Token {
	private String lexeme;
	private int type;
	
	public Token( String lexeme,  int type)
	{
		this.lexeme = lexeme;
		this.type = type;
	}
	public String getLexeme()
	{
		return this.lexeme;
	}
	public void setLexeme( String lexeme)
	{
		this.lexeme = lexeme;
	}
	public int getType()
	{
		return this.type;
	}
	public void setType( int type)
	{
		this.type =  type;
	}
	public String toString()
	{
		return lexeme + " " + type;
	}
	
	

}
