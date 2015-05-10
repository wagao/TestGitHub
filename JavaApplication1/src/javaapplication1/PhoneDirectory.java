/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
/**
 *
 * @author Emma
 */
public class PhoneDirectory {   
   private TreeMap<String, String> data;  //<name, number> pair
   private int size;
   public PhoneDirectory(){
       this.data = new TreeMap<>();preLoad();
       this.size = this.data.size();//0
   }     
   public String print(){
       return this.data.values().toString();
   }
   public void  preLoad(){
       this.data.put("tim", "12334");
       this.data.put("JIm", "78694");
       this.data.put("Jeff", "32435");
       this.data.put("She", "111");
   }
   public String find(String name){
       if( this.data.containsKey(name)){
           return this.data.get(name);
       }else 
           return "No found";       
   }
   public String getName(String number){
       String p ="No found"; ;
       if( this.data.containsValue(number)){
           Set<String> allKey= this.data.keySet();
           for (Iterator it=allKey.iterator();it.hasNext();){
               p = it.next().toString();
               if(this.data.get(p).equals(number)){
                   break;
               }
           }           
       }
       return p;
   }
   public boolean insert(String name, String number){
       if( name==null || name.isEmpty() || number==null || number.isEmpty()){
           System.out.println("wrong input. Name or number could not be empty.");
           return false;
       }
            this.data.remove(name);
            this.data.put(name, number);
           System.out.println("insert successfully");
            return true;
//       if(this.data.containsKey(name)){
//            this.data.remove(name);
//            this.data.put(name, number);
//       }else{
//           this.data.put(name, number);
//       }
   }
}
