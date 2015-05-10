/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Emma
 */
public class wordBreak {
    public wordBreak(){
    }
//    public  List<String> wordBreak(String s, Set<String> dict) {
//        HashMap<Integer,ArrayList<String>> r = new HashMap<>();
//        int len = s.length();
//        ArrayList<String> p = new ArrayList<>();
//        if(s==null|| dict ==null || dict.size()==0) return p;
//        
//        // p.add("");
//        // r.put(len, p);
//         for(int i =len-1;i>=0;i--){
//        //for(int i =0;i<len;i++){
//                    if (dict.contains(s.substring(i))){
//                        ArrayList<String> tmp = new ArrayList<>();
//                        tmp.add(s.substring(i));  
//                        r.put(i, tmp);
//                       // continue; 
//                    }
//             for(int j =0; j<i; j++){
//            //for(int j =len; j>i; j--){
//                if(j ==len) { 
////                    if (dict.contains(s.substring(i,j))){
//                }
//                if (dict.contains(s.substring(j,i)) &&  r.get(i)!=null &&r.get(i).size()!=0){
//                    ArrayList<String> tmp = r.get(j);
//                    if(tmp==null){
//                        tmp = new ArrayList<>();
//                    }
//                    for(String substr : r.get(i)){
//                        if(substr.isEmpty())  tmp.add(0,s.substring(j,i));
//                        else { tmp.add( 0, s.substring(j,i)+" "+substr );   System.out.println("5:"+tmp.get(0)); }
//                    }
//                    r.put(j, tmp); 
//                }
//            }
//        }
//        return r.get(0)==null ? p : r.get(0);
//    }
//   
         Map<String,List<String>> r  = new HashMap<String, List<String>>();;
         public  List<String> wordBreak_2(String s, Set<String> dict) {
//             r= new HashMap<>();
        List<String> str= new LinkedList<>();
        
//        if(s==null|| dict ==null || dict.isEmpty()|| s.isEmpty()) return null;
        int len = s.length();
        
        for(int i =1;i<=len;i++){
            String front = s.substring(0, i);
            if (dict.contains(front) ){
                if(i==len){
                    str.add(s); 
                }else{
                    String remain = s.substring(i);
                    List<String> subStr =  r.containsKey(remain) ?  r.get(remain): wordBreak_2(remain, dict);
                    if(subStr!=null && subStr.size()>0 ){
                        for( String p: subStr){
                            str.add(front +" "+ p);
//                            System.out.println(i+":"+s.substring(0,i)+" "+p);
                        }
                        r.put(remain, subStr);
                    }
                }
            } 
        }
        return str;
        // return r.get(0);
    }
  
           Map<String, List<String>> results = new HashMap<String, List<String>>();

    public List<String> wordBreak_3(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>();

        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String front = s.substring(0, i);
            if (dict.contains(front)) {
                if (i == len) {
                    words.add(front);
                } else {
                    String remain = s.substring(i, len);
                    List<String> remainSet = results.containsKey(remain) ? 
                            results.get(remain) : wordBreak_3(remain, dict);
                    if (remainSet != null) {
                        for (String item : remainSet) {
                            words.add(front + " " + item);
                        }
                        results.put(remain, remainSet);
                    }  

                }
            }
        }
        return words;
    }
}
