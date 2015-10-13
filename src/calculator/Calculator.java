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
        numbers = new ArrayList<>();
        operators = new ArrayList<>();
        calc = new View();
    }

    protected static void add_string(String value) {
        calc.set_text(calc.get_text() + value);
    }

    protected static void ButtonCancel() {
        calc.set_text("");
    }

    protected static void ButtonBackspace() {
        String text = calc.get_text();
        if (text.length()>0)
            calc.set_text(text.substring(0, text.length() - 1));
    }

    protected static void ButtonEqual() {
        calc.set_text(compute_expression());
    }

    private static String get_text() {
        return calc.get_text();
    }

    private static void set_text(String text) {
        calc.set_text(text);
    }

    private static String compute_expression() {
        if (get_numbers_and_operators()) {
            execute();
            return String.valueOf(numbers.get(0));
        } else {
            return "Error";
        }
    }

    private static boolean get_numbers_and_operators() {
        print();
        String expression = calc.get_text();
        String number = "";
        Character c;
        numbers.clear();
        operators.clear();
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);
            if (is_operator(c)) {
                if (c == '-' && i == 0) {
                    number += expression.charAt(i);
                } else {
                    addOperator(c);
                    try {
                        numbers.add(Double.valueOf(number));
                    } catch (NumberFormatException numberFormatException) {
                        return false;
                    }
                    number = "";
                    if (i + 1 < expression.length() && expression.charAt(i + 1) == '-') {
                        number += expression.charAt(i + 1);
                        i++;
                    }

                }
            } else {
                number += c;
            }

        }

        if (!number.isEmpty()) {
            try {
                numbers.add(Double.valueOf(number));
            } catch (NumberFormatException numberFormatException) {
                return false;
            }
        }
        return true;
    }

    private static void operate(Operator op) {
        int index = operators.indexOf(op);
        Double number1 = numbers.get(index);
        Double number2 = numbers.get(index + 1);
        switch (op.value) {
            case '^':
                numbers.add(index, Math.pow(number1, number2));
                break;
            case 'x':
                numbers.add(index, number1 * number2);

                break;
            case '/':
                numbers.add(index, number1 / number2);
                break;
            case '+':
                numbers.add(index, number1 + number2);
                break;
            case '-':
                numbers.add(index, number1 - number2);
                break;
        }
        operators.remove(op);
        numbers.remove(index + 1);
        numbers.remove(index + 1);
    }

    private static void execute() {
        boolean control = false;
        give_priority();
        if (verify_expression()) {
            for (int i = 0; i < 3; i++) {
                if (control && operators.size() > 0) {
                    i--;
                }
                for (Operator op : operators) {
                    if (op.priority == i) {
                        control = true;
                        operate(op);
                        break;
                    } else {
                        control = false;
                    }
                }

            }
        } else {
            System.out.println("Expressão incorreta.");
        }

    }

    private static boolean is_operator(char c) {
        return c == '+' || c == '-' || c == 'x' || c == '/' || c == '^';
    }

    private static boolean square(char c) {
        return c == '^';
    }

    private static void addOperator(Character c) {
        Operator op = new Operator(c);
        operators.add(op);
    }

    private static void give_priority() {
        for (int i = 0; i < operators.size(); i++) {
            switch (operators.get(i).value) {
                case '^':
                    operators.get(i).priority = 0;
                    break;
                case 'x':
                    operators.get(i).priority = 1;
                    break;
                case '/':
                    operators.get(i).priority = 1;
                    break;
                case '+':
                    operators.get(i).priority = 2;
                    break;
                case '-':
                    operators.get(i).priority = 2;
                    break;
            }
        }
    }

    private static boolean verify_expression() {
        return numbers.size() - 1 == operators.size();
    }

    private static void print() {

        for (Double number : numbers) {
            System.out.print("Numbers: " + number + " ");
        }
        for (Operator operator : operators) {
            System.out.print("op: " + operator.toString() + " ");
        }
    }

}
