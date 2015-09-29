/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author gperipolli
 */
public class Calculator {
    private static View calc;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        calc = new View();
    }
      
    protected static void add_string (String value)
    {
        calc.set_text(calc.get_text()+value);
    }

    protected static void ButtonCancel() {
        calc.set_text("");
    }

    protected static void ButtonBackspace() {
        String text = calc.get_text();
        calc.set_text(text.substring(0, text.length()-1));
    }


    protected static void ButtonEqual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private static String get_text ()
    {
        return calc.get_text();
    }
    private static void set_text(String text)
    {
        calc.set_text(text);
    }

    
}
