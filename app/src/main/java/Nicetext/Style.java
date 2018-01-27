package Nicetext;

public class Style {
	String bold = "BOLD";
    String italic = "ITALIC";
    String strike = "STRIKE";
    String underline = "UNDERLINE";
    String subscript = "SUBSCRIPT";
    String superscript = "SUPERSCRIPT";
    
    String type;
    int start;
    int end;
    
    public String BOLD()
    {
        return bold;
    }
    public String ITALIC()
    {
        return italic;
    }
    public String STRIKE()
    {
        return strike;
    }
    public String UNDERLINE()
    {
        return underline;
    }
    public String SUBSCRIPT()
    {
        return subscript;
    }
    public String SUPERSCRIPT()
    {
        return superscript;
    }
    public void setValue(String type, int start, int end)
    {
        this.type = type;
        this.start = start;
        this.end = end;
    }
    public String getType()
    {
        return type;
    }
    public int getStart()
    {
        return start;
    }
    public int getEnd()
    {
        return end;
    }
}
