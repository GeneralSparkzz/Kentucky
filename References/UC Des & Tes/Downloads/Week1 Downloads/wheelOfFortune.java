public class wheelOfFortune {
    private String phrase = "JAWS";
    private String hidden = "----";
    private String category = "movie";
    /** Creates a new instance of wheelOfFortune */
    public wheelOfFortune() {
    }
    
    public wheelOfFortune(String p, String c)
    {
        setPhrase(p);
        setCategory(c);
    }
    
    public void newGame(String p, String c)
    {
        setPhrase(p);
        setCategory(c);
    }
    public void setPhrase(String p)
    {
        phrase = p.toUpperCase();
        hidden = phrase;
        makeHidden();
        
    }
    
    public void setCategory(String c)
    {
        category = c;
    }
    
    public String getPhrase()
    {
        return phrase;
    }
    
    public String getHidden()
    {
        return hidden;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void makeHidden()
    {
        for(char letter = 'A'; letter <= 'Z'; letter ++)
        {
            hidden = hidden.replace(letter, '-');
        }
    }
    public int guessPhrase(String guess)
    {
        if(guess.equalsIgnoreCase(phrase))
        {
            hidden = phrase;
            return 1;
        }
        else
        {
            return 0;
        }
    }
    public void showHidden()
    {
        System.out.println("\n" + hidden);
        System.out.println("The category is " + category);
    }
    
    public int guessLetter(char letter)
    {
        int position;
        int count = 0;
        letter = Character.toUpperCase(letter);
        position = phrase.indexOf(letter);
        //position in string of letter
        while(position != -1)
        {
            hidden = hidden.substring(0, position) + letter + 
                hidden.substring(position +1, hidden.length());
            position = phrase.indexOf(letter, position +1);
            count ++;
            
        }
        return count;
        
      
    }
    
     public int gameover()
       {
            return hidden.indexOf('-');
       }
}
