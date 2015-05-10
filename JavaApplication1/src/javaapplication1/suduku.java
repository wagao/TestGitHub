/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author Emma
 */
public class suduku {
        HashMap<Integer, Set<Character>> rows = new HashMap<>();
        HashMap<Integer, Set<Character>> cols = new HashMap<>();
        List<Integer[]> blank = new ArrayList<Integer[]>(); 
        Set[][] blockCheck = new HashSet[3][3];
        
       public void solveSudoku(char[][] board) {
        if (board==null) return;
        for(int i =0;i<9;i++){
            Set<Character> row = new HashSet<>();
            Set<Character> col = new HashSet<>();
            rows.put(i,row);
            cols.put(i,col);
        }
        for(int i =0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]!='.'){
                    rows.get(i).add(board[i][j]);
                    cols.get(j).add(board[i][j]);
                    if(blockCheck[i/3][j/3]==null){
                        blockCheck[i/3][j/3]=new HashSet<Character>();
                    }
                    blockCheck[i/3][j/3].add(board[i][j]);
                }else{
                    Integer[] b = new Integer[2];
                    b[0]=i;b[1]=j;
                    blank.add(b);
                }
            }
        }
        backCheck(board, 0 ); 
    }
    public boolean backCheck(char[][] board, int m){
        if(m==blank.size()) return true;
        Integer[] t=blank.get(m);
        int X= t[0];
        int Y= t[1];
        for(char i ='1';i<='9';i++){//try one from 0-9, test it can make the board
            if(!rows.get(X).contains(i) && !cols.get(Y).contains(i) && !blockCheck[X/3][Y/3].contains(i) ){// not contains in any of three
                //then try 
                rows.get(X).add(i);
                cols.get(Y).add(i);
                blockCheck[X/3][Y/3].add(i);
                if(backCheck(board, m+1)){// right guess!
                    board[X][Y]=i;
                    return true;
                }else{
                    rows.get(X).remove(i);
                    cols.get(Y).remove(i);
                    blockCheck[X/3][Y/3].remove(i);
                }
            }
        }
        return false;
    }
}
