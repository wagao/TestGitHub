/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;


/**
 *
 * @author Emma
 */
public class WordDistanceFinder {
    public List<String> text;
    public String[] str;
    public WordDistanceFinder(){
        this.text = Arrays.asList("the","quick","brown","fox","quick");
    }
    public WordDistanceFinder( String[] list){
        this.str = Arrays.asList("the","quick","brown","fox","quick").toArray(new String[0]);
    }
    public WordDistanceFinder(List<String> list){
        this.text = list;
    }
    public int finder(String foo, String bar){
        return finder( foo,  bar,this.text );
    }
    public int finder(String foo, String bar, List<String> wordlist){
        if(wordlist==null || foo==null || foo.isEmpty() || bar==null|| bar.isEmpty()) {System.out.println("Please give the word to search.");}
        int d = Integer.MAX_VALUE  ; //wordlist.size();
        int index = 0;
        LinkedList<Integer> fooIndex=new LinkedList<>();
        LinkedList<Integer> barIndex=new LinkedList<>();
        Iterator it = wordlist.iterator();
        while(it.hasNext()){
            index++;
            String tmp=it.next().toString();
            if(tmp.compareTo(foo)==0){
                fooIndex.add(index);
            }else if(tmp.compareTo(bar)==0){
                barIndex.add(index);
            }
        }
        if( fooIndex.size()==0 || barIndex.size()==0) {
            System.out.println("the word \""+ ((fooIndex.size()==0)?foo:bar )+"\" is not found.");
            return -1;
        }
         it = fooIndex.iterator();
         while(it.hasNext()){
             Iterator it2 = barIndex.iterator();
             int fooPosition = Integer.parseInt(it.next().toString());
             while(it2.hasNext()){
                int barPosition = Integer.parseInt(it2.next().toString());
                 d=Math.min( Math.abs(fooPosition-barPosition), d);
             }
         }
        return d;
    }
}
