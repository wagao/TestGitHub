/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.Deque;
import java.util.ArrayDeque;
/**
 *
 * @author Emma
 */
public class MinStack{
    Deque<Integer> stack;
    int min;
    public MinStack(){
    stack = new ArrayDeque<>();
    }
    
    public void push(int item){
        if(stack.isEmpty()){
            min = item;
            stack.push(item);
        }
        
        if(item<min){
            min = item;
        }
            stack.push(item-min);
    }
    public void pop(){
        if(stack.isEmpty()){
            return;
        }
        int item = stack.peek();
        if(item<0){
            min = min + item;
        }
    }
    public int top(){
        if(stack.isEmpty()){
            return Integer.MIN_VALUE;
        }
        int item = stack.peek();
        if(item<0){
            return min;
        }else 
            return item+min;
    }
    

    public int getMin() {
    return (int) min;
            
            }
}
