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
public class Operator {
    protected char value;
    protected int priority;
    
    public Operator(char value){
        this.value = value;
    }

    Operator(Character c) {
        this.value = c;
    }
    
    @Override
    public String toString()
    {
        return this.value +" "+ this.priority;
    }
    
}
