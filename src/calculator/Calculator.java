/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.ArrayList;

/**
 *
 * @author gperipolli
 */
public class Calculator {
    private static View calc;
    private static ArrayList<Double> numbers;
    private static ArrayList<Operator> operators;
    

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
        calc.set_text(compute_expression());
    }
    private static String get_text ()
    {
        return calc.get_text();
    }
    private static void set_text(String text)
    {
        calc.set_text(text);
    }

    private static String compute_expression() {
        get_numbers_and_operators ();
        operate();
        return numbers.get(0).toString();
    }
    //terminar metodos de calculo
    private static void get_numbers_and_operators() {
        String expression = calc.get_text();
        String number ="";
        Character c;
        for (int i=0; i<expression.length();i++)
        {
            c = expression.charAt(i);
            if (is_square(c)){
                addOperator(c);
                i++;
                numbers.add(Double.valueOf(number));
                number="";
            }else{
                if (is_operator(c))
                {
                    addOperator(c);
                    numbers.add(Double.valueOf(number));
                    number="";
                }else
                {
                    number+=c;
                }
        }
        }
    }

    private static void operate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static boolean is_operator(char c) {
        return c == '+' || c == '-' || c == 'x' || c == '/'||c == '%';
    }

    private static boolean is_square(char c) {
        return c == '^';      
    }

    private static void addOperator(Character c) {
        
    }

    
}
