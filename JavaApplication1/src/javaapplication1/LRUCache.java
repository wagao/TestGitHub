/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Emma
 */
public class LRUCache {

    // public class Entry{
    //      int key;
    //      int cache;
    //     // private long freq;
    //     public Entry(int x, int y){
    //         this.key = x;
    //         this.cache = y;
    //         // this.freq=1l;
    //     }
        // public int getKey(){
        //     return this.value;
        // }
        // public int getValue(){
        //     return this.value;
        // }
        // public void setValue(int newVal){
        //     this.value=newVal;
        // }
        // public long getFreq(){
        //     return this.freq;
        // }
        // public void increaseFreq(){
        //     increaseFreq(1);
        // }
        // public void increaseFreq(long x){
        //     this.freq += x;
        // }
    // }
    
    TreeMap<Integer, Integer> Cache; 
    HashMap<Integer, Integer> cacheIdx;
    HashMap<Integer, Integer> pairs;
    int Max;
    int step;
    
    
    public LRUCache(int capacity) {
        this.Max = capacity;
        step=0;
        pairs = new HashMap<>();
        cacheIdx = new HashMap<>();
        Cache = new  TreeMap<>();
        // this.Cache = new  PriorityQueue<>(capacity);
        // (capacity, new Comparator<Entry>(){
        //     public int compare(Entry o1, Entry o2){ 
        //         return (int)Math.floor(o1.getFreq()-o2.getFreq());  // the smallest freq at the top the quue
        //     }
        // });
    }
    
    public int get(int key) {
        if(pairs.containsKey(key)){
            int value = pairs.get(key);
            int previousIdx = cacheIdx.get(key); // get prvious step of the key
            if(Cache.containsKey(previousIdx)) { // it's within the caches
                Cache.remove(previousIdx); // remove it, at most one step record for one key in cache.
            }
            
            step++;
            cacheIdx.put(key, step);
            Cache.put(step, key);
            return value;
        } else 
            return -1;
        // int ret_val = -1;
        // for(Entry i: this.Cache){
        //     if(i.getKey()==key)
        //         return i.getValue();
        // }
        // return ret_val;
    }
    
    public void set(int key, int value) {
        pairs.put(key, value);
        if (Cache.size()==Max){ //reach the max
            Cache.pollFirstEntry(); // remove the least used one 
        }
        updateStep(key);
    }
    public void updateStep(int key){
        step++;
        if(cacheIdx.containsKey(key)){
            int previousIdx = cacheIdx.get(key); // get prvious step of the key
            if(Cache.containsKey(previousIdx)) { // it's within the caches
                Cache.remove(previousIdx); // remove it, at most one step record for one key in cache.
            }
        } 
        // update cache 
        cacheIdx.put(key, step);
        Cache.put(step, key);
        
    }
}