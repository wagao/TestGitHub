/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.ArrayDeque;
import java.util.Deque;
/**
 *
 * @author Emma
 */
public class ListNode {
   public int val;
  public ListNode next;
   public  ListNode(int x) {
        val = x;
        next = null;
    }
    public static  void print( ListNode p) {
        if (p.next!=null)
        System.out.println( "{"+p.val+","+p.next.val+"}");
        else System.out.println(  "{"+p.val+"}");
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        // if(l1.val>l2.val) // let's say l1 has the smaller starter, so always return l1;
        // return mergeTwoLists(l2,l1);
        
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode start = head;
        ListNode p1 = l1;
        ListNode p2 = l2;
        
        while(p1!=null || p2!=null){            
            if(  p2==null || ( p1!=null && p1.val<p2.val) ){
                start.next = p1;
                p1 = p1.next;
            }else {
                start.next = p2;
                p2 = p2.next;
            }
            start=start.next;
        }
        
        return head.next;
    }

  public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null|| head.next==null||m>=n)  return head;
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode h=new ListNode(0);
        h.next = head;
        ListNode p1=h;
        ListNode p2=h;
        ListNode start=h;
        ListNode end=null;
        int i=0;
        // int j=0;
        
        while(p2.next!=null){
            i++;
            if(i<m){
                p2=p2.next;
            } else if(i==m){// start to reverse, p1 is the one before start/first node
                start = p2;
                p1=p2.next;
                p2=p2.next;
                stack.push(p2);
            }else if(i>m&& i<n){
                p2=p2.next;
                stack.push(p2);
            } if(i==n){// end reversing. p2 is the last node, end node is the one after last node
                p2=p2.next;
                stack.push(p2);
                end=p2.next;
                break;
            }
        }
        
       while(!stack.isEmpty()){
           start.next=stack.pop();
           start=start.next;
       }
       start.next=end;
        
        return h.next;
    }
   
  public ListNode sortListBU(ListNode head) {
        if(head==null|| head.next==null) return head;
        
        int n = countList(head);
        int curSize=1;
        
        ListNode h = new ListNode(0);
        h.next=head;
        ListNode p = h;
        while(curSize<n){
            ListNode last = p ;
            ListNode after= null;
            ListNode p1=p.next;
            ListNode p2= null;
            int merged =0;
            while(merged<n){
                int a= Math.min(n-merged , curSize);// no. of node in first half
                int b= Math.min(n-merged-a,curSize);// no. of node in sencond half
                
                if(b!=0){//sencond half is not emopty, 
                    ListNode tmp= p1;
                    int i=0;
                    while(i<a-1) {tmp=tmp.next;i++;}
                    p2=tmp.next;
                    tmp.next=null;
                    
                    tmp=p2;
                    i=0;
                    while(i<b-1) {tmp=tmp.next;i++;}
                    after = tmp.next;
                    tmp.next=null;
                }
                
                while(p1!=null||p2!=null){
                    if(p2==null|| (p1!=null && p1.val<=p2.val) ){
                        last.next = p1;
                        p1=p1.next;
                    } else {
                        last.next = p2;
                        p2=p2.next;
                    }
                    last=last.next;
                }
                
                last.next = null;
                p1=after;
                p2=null;
                merged += a+b;
            }
            curSize*= 2;
        }        
        return h.next;
    }
    // public ListNode findnext(ListNode h , int size){
    //     int n=0;
    //     ListNode p = h;
    //     while(p!=null && n<size){
    //         p = p.next;
    //         n++;
    //     }
    //     return p;
    // }
    public int countList(ListNode h) {
        if(h==null) return 0;
        int count = 0;
        ListNode p = h;
        while (p!=null) {
            p=p.next;
            count++;
        }
        return count;
    }
    public ListNode sortList(ListNode head) {
        if(head==null|| head.next==null) return head;
        
        ListNode half = head;
        ListNode whole = head.next.next;
        
        while(whole!=null&&whole.next!=null){
            half = half.next;
            whole  = whole.next.next;
        }
        
        ListNode second = sortList(half.next); // sort the second half;
        half.next=null;
        ListNode first = sortList(head); //sort the first half
        
        return Merge(first, second);
    }
    
    public ListNode Merge(ListNode h1, ListNode h2 ) {
        if(h1==null) return h2;
        if(h2==null) return h1;
        
        ListNode h = new ListNode(0);
        ListNode p = h;
        while(h1!=null&&h2!=null){
            if(h1.val<=h2.val){
                p.next = h1;
                h1 = h1.next;
            } else { 
                p.next = h2;
                h2 = h2.next;
            }
            p = p.next ;
        }
        
        if(h1==null && h2!=null){
            p.next = h2;
        }else {
            p.next = h1;
        }
        
        return h.next;
    }
}
