/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Emma
 */
public class hummingWeight {
//     public hummingWeight() {
//     }
     public int hammingWeight_2(int n) {
        int sum=0;
        while(n!=0){
            
            if( (n&(n-1))==1)
                sum++;
//              n = n>>>1;
        }
        System.out.println(sum);
        return sum;
    }
     public int reverseBit(int n) {
        int res=0;
        System.out.println(Integer.toBinaryString(n));
//        while(n!=0){
        for (int i = 0; i < 32; i++) {
            res = (res<<1)+(n&1);
            n=n>>>1;
        }
        System.out.println(Integer.toBinaryString(res));
        return res;
    }
     
//    public int[] rotate_1(int[] nums, int k) {
//        System.out.println(Arrays.toString(nums));
//        int n = nums.length;
//        if((k%n)==0|| n==1 ) return nums;
//        k = k%n ;
//        int tmp=nums[0];
//        int i=0, count=0;
//        while(count<n){
////            tmp=nums[i];
//            if(i==k){
//                nums[i]=tmp;
//            }else 
//                nums[i] = nums[(i-k+n)%n];
//            i = (i-k+n)%n ;
//            count++;
//        }
//        System.out.println(Arrays.toString(nums));
//        return nums;
//    }
    public int[] rotate_2(int[] nums, int k) {
        int n = nums.length;
        k=k%n;
       reverse(nums, 0 , n-1);
       reverse(nums, 0 , k-1);
       reverse(nums, k , n-1);
        System.out.println(Arrays.toString(nums));
        return nums;
    }
    public int[] reverse(int[] nums, int from, int to){
        if(from>=to||to>=nums.length) return nums;
        int left=from;
        int right=to;
        while(left<right){
            int tmp=nums[left];
            nums[left] = nums[right];
             nums[right] = tmp;
             left++;right--;
        }
        return nums;
    }
    public int[] rotate_3(int[] nums, int k) {
        int n = nums.length;
        if((k%n)==0|| n==1 ) return nums;
        k = k%n ;
        int[] res = Arrays.copyOf(nums, 2*n-k);
        for(int i=0;i<n-k;i++){
            res[n+i]=res[i];
        }
        nums = Arrays.copyOfRange(res, n-k,n-k+n);
        System.out.println(Arrays.toString(nums));
        return nums;
    }
    
    public boolean isPalindrome(int x) {
        int reverse = 0;
        int n=x;
        while(n>0){
            if((n%10)!=0)
                reverse= reverse*10 + n%10;
            n = n/10;
        }
        return x<10? true : x==reverse;
    }
    
    
    public boolean isPalindrome(String s) {
        if(s==null) return false;
         s=s.trim().toLowerCase();
         StringBuffer test= new StringBuffer();
         for(char c: s.toCharArray() ){
             if(c>='a'&&c<='z')
                test.insert(0,c);
         }
        int n = test.length();
        if(n<=1) return true;
        return test.toString().equals(test.reverse().toString());
    }
    
    
    public int rob_2(int[] num) {
        if(num==null || num.length==0) return 0;
        int n =num.length;
        if(n==1) return num[0];
        if(n==2) return Math.max(num[0],num[1]);
        int prepre=num[0];
        int pre=Math.max(num[0],num[1]);
        int max= Math.max(prepre,pre);
        for(int i=2;i<n;i++){
            if(prepre==pre){ // didn't rob pre , can always rob this one 
                pre = (pre + num[i] );
            }else{// try both 
                if(prepre+num[i]>=pre) { // rob prepre, rather than pre
                    int t=pre;
                    pre=prepre+num[i];
                    prepre = t;
                }else{//don't rob this one anyway,move forward
                    prepre=pre;
                }
            }
        
        }
        return pre;
        // s = new int[num.length];
        // Arrays.fill(s,-1);
        // return rob(num, 0);
    }
    
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new LinkedList<>();
        for (int i =0; i<rowIndex;i++){
            for(int j =0; j<res.size()-1;j++){
                res.set(j, res.get(j)+res.get(j+1));
            }
            res.add(0,1);
        }
        return res;
    }
    
    
      public List<Integer> getRow_2(int rowIndex) {
         List<Integer> res2 = new LinkedList<>();
        int[] res=new int[rowIndex+1];
           res[0] =1; res[rowIndex]=1;
        for (int i =0; i<=rowIndex;i++){
            for(int j =1; j<=i;j++){
                res[rowIndex] = res[j];
                res[j] =res[j]+ res[0];
                res[0] =res[rowIndex];
            }
           res[0] =1; res[rowIndex]=1;
        }
           for(int i : res)
           res2.add(i);
        return res2; //Arrays.asList(res);
    }
    
    public int rob(int[] num) {
        if(num==null || num.length==0) return 0;
        return rob(num, 0);
    }
    public int rob(int[] num, int h) {
        if(h>=num.length) return 0;
        if(h==num.length-1) return num[h];
        return Math.max(num[h]+rob(num, h+2), rob(num, h+1) ); // make decision rob it or not.
    }
    
    public String longestCommonPrefix(String[] strs) {       
        if(strs==null || strs.length==0 ) return null;
        int n = strs.length;
        int minlen=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(strs[i]==null||strs[i].isEmpty()) return "";
            minlen=Math.min(minlen, strs[i].length());
        }
        
        for(int i=0;i<minlen;i++){
            for(int j=0;j<n-1;j++){
                if(strs[j].charAt(i)!=strs[j+1].charAt(i))
                return strs[j].substring(0,i);
            }
        }
        return (strs==null||n==0) ? null : strs[0].substring(0,minlen);
    }
    
    public long get(int n) {
        long res=1;
//        System.out.println(factor);
        while(n>0){ 
            res *= n; 
            n-- ;
        }
        System.out.println(res);
        return res;
    }
     public String convertToTitle(int n) {
//         String res = "";
         StringBuffer res=new StringBuffer();
        
        while(n>0){
            n--;
            int k = n%26+'A' ;
            char ch = (char)k;
//            res = ch+""+res;
            res.append(ch);
             n =  n/26 ;
        }
        
        return res.reverse().toString();
    }
     public int convertToInt(String s) {
         int res=0;
         for(int i=s.length()-1;i>=0;i--){
             int k= s.charAt(i)- 64;
             res += k*Math.pow( 26 , s.length()-1-i);
         }         
         return res;
     }
     
    public int trailingZeroes(int n) {
        int maxDig = (int)Math.floor(Math.log(n)/Math.log(5));
//        System.out.println("maxDig:"+maxDig);
        int total=0;
        for (int i =1; i<=maxDig;i++){
//            int noof0=1;
//            int digit = n % 10; //get the last difit
//            if( i==maxDig &&digit<5)
//                noof0--;
//        System.out.println(i+"  digit:"+ digit +"  noof:"+noof0) ;
            total +=  n/Math.pow(5,i);
//            n = n/10;
        }
        System.out.println(total);
        return total;
    }
    
    public int titleToNumber(String s) {
        return s.isEmpty()? 0 : titleToNumber(s,0);
    }
        
    public int titleToNumber(String s, int idx) {
        if(idx==s.length()-1) return s.charAt(idx)-64;
        return  (s.length()-1-idx) * (s.charAt(idx)-64)* 26+titleToNumber(s, idx+1) ;
    }
    
    public int[] rotate_4(int[] nums, int k) {
        int n = nums.length;
        if((k%n)==0|| n==1 ) return nums;
        k =  k%n  ;
        System.out.println(Arrays.toString(nums));
        int[] res = new int[n];
        for(int i=0;i<n;i++){
            res[i]= nums[(n+i-k)%n]; 
        System.out.println(i+":"+res[i]+"-"+(n+i-k)%n);
        }
        System.out.println(Arrays.toString(res));
        return res;
    }
    public int reverseUnsignedInt(int a){
        System.out.println(a);
        int res=0;
        while(a>0){
            res = res*10 + a%10;
            a=a/10;
        }
        System.out.println(res);
        return res;
    }
    public String convert(String s, int nRows) {
        if(nRows==1) return s;
        int len =s.length();
        String[] res = new String[nRows];
        char[] ch=s.toCharArray();
        int count=0;
        int flag=1;
        for(int i=0;i<len;i++){
            if(res[count]==null) res[count]= new String(ch[i]+"");
            else  res[count]=res[count]+""+ch[i];
        System.out.println(ch[i]+" : " +count+" - "+res[count]);
            if(flag==1) {
                count++;
                if(count==nRows) {
                    flag=0;
                    count--;count--;
                    if(count==0) {
                        flag=1;
                    }
                }
            } else{ 
                count--;
                if(count==0) {
                    flag=1;
                }
            }
        }
        String r="";
        for(String i: res){
            r=r.concat(i);
        }
        System.out.println(r);
        return r;
    }
     public String ZigZag(String s, int nRows) {
        
        if(nRows==1||s.length()<=nRows) return s;
        int len =s.length();
        StringBuffer[] res = new StringBuffer[nRows];
        char[] ch=s.toCharArray();
        int count=0;
        int flag=1;
        for(int i=0;i<len;i++){
             if(res[count]==null) res[count]= new StringBuffer();
//             else   // res[count]=res[count]+""+ch[i];
            res[count].append(ch[i]);
        // System.out.println(ch[i]+" : " +count+" - "+res[count]);
            if(flag==1) {
                count++;
                if(count==nRows) {
                    flag=0;
                    count--;count--;
                    if(count==0) {
                        flag=1;
                    }
                }
            } else{ 
                count--;
                if(count==0) {
                    flag=1;
                }
            }
        }
        StringBuffer r=new StringBuffer();;
        for(StringBuffer i: res){
            r.append(i);
        }
        // System.out.println(r);
        return r.toString();
    }
    public int majorityElement(int[] num) {
        int len=num.length;
        if (len==1) return num[0];
        Arrays.sort(num);
        int need=len/2;
        for(int i=0;i<len-len/2-1;i++){
            if( num[i]== num[i+need])
                return num[i];
        }
        return num[0];
    }
}
