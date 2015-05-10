package javaapplication1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Iterator;
import tree.tree;
import java.util.HashSet;
import java.util.HashMap;

public class StrUtil {
    public static void main(String args[]){
            double[] A ={0, 0.0386,534.7657,34,897, 120.0386, 956, 38, -703, 86, -2 };

            Arrays.sort(A);
            System.out.println(" test Arrays:"+ Arrays.binarySearch(A, -2d));

//            testTree();


//		int[] B ={484,-534,5,7657,-34,897,-120,956,38,-703,86,548,68,-958,76,5834,9346,-45894};
//		int[] B = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//		int[] submax = MAXSubarray(B,0,B.length-1);
//		A=HeapSort(A,A.length-1,false);

            // get pop numbe
//		int[] Apop = {1,2,2,2,3,3,3,4,4,4,5,5,6,6,7,7,7,7,7,7,8,8,8,9};
//		int l = 3;
//		int[] Bpop = new int[Apop.length/l];
//		Bpop = GetPopNum(Apop, Bpop, 0, Apop.length-1, l);
//		System.out.println(FindMatchBoundry(Apop,10,19,false));
//		System.out.println(Arrays.toString(Bpop));

            //quicksort
//		QuickSort(A,1,A.length-1, true );
//		for (int i=0;i<Apop.length;i++)
//		    System.out.println(i + ":"+Apop[i]);

            //merge sort
//		A= MergeSort(A,0,A.length-1,false);

            // isnumber ?
//		if ( isNumeric("000.0386759000"))
//			System.out.println("this is a number");
//		else
//			System.out.println("this is not a number");
//		for (int i=0;i<A.length;i++)
//			System.out.println(A[i]);
//		int minstep = minStep(3,2);
//		System.out.println("test minStep:"+ minStep(7,16) );

//		stringTest("hahaha 123456");

//		hashTest();
            System.out.println(shortestCommentSubstr());
            String A1 = "4455667aaaaccccccbbbbbbaaaaccccccbcccaaaccbbbbbb3435341ccac";
            String B1 = "4abc1";
            System.out.println(shortestCommentSubstr(A1,B1));
    }
    
    public static int shortestCommentSubstr(){
        String A = "this is a test. You are a genius! ";
        String B = "aeiou";
        return shortestCommentSubstr(A, B);
    }
    public static int shortestCommentSubstr(String A, String B){
        if( A==null || B==null ){
            System.out.println("String is empty.");
            System.exit(1);//1- for empty String;
        }
        HashSet<Character> C = new HashSet<>();
        int B_len=B.length();
        for(int i=0;i<B_len;i++){
             C.add(B.charAt(i));
        }       
        return shortestCommentSubstr(A, C);
    }
    public static int shortestCommentSubstr(String A, HashSet<Character> B){
        if( A==null || B==null){
            System.out.println("String is null.");
            System.exit(1);//1- for empty String;
        }
        
        int A_len = A.length();
        int B_len = B.size();
        int countDistinct = 0, head =0, min_len=Integer.MAX_VALUE;
        HashMap<Character,Integer> count = new HashMap<>();
        
        if (  B_len==0 || A_len ==0 ){
            System.out.println("String is empty. The SCS is 0");
            return 0;
        }
        
        for(int i=0;i<A_len;i++){
            Character ch = new Character(A.charAt(i));
             if( !B.contains(ch)) continue;
             if ( count.containsKey(ch)){
                 Integer p = count.get(ch);
                 count.put(ch, p+1);
             }else{// this is the first occurance
                 countDistinct++;
                 count.put(ch, 1);
             }
             if(countDistinct==B_len){
                 while(head>=0 && head<=i-B_len+1){
                     if( !B.contains(A.charAt(head))) {
                         head++;
                         continue;
                     }
                     if(count.get(A.charAt(head))==1){
                        System.out.println(head+"-"+ i +" with min_len="+min_len);
                         min_len = Math.min(min_len, i-head+1);
                         break;
                     }else{
                        Integer p = count.get(A.charAt(head));
                        count.put(A.charAt(head), p-1);
                        head++;
                     }
                 }
             }
        }       
        if(min_len==Integer.MAX_VALUE) {
            System.out.println("Not all the char in B are found in A. Exit code 2.");
            System.exit(2);
        }
        return min_len;
    }
    public static void testTree(){
            tree<Integer> t = new tree<Integer>();
            t.insert(60);
            t.insert(55);
            t.insert(100);
            t.insert(45);
            t.insert(57);
            t.insert(67);
            t.insert(107);
//		t.printTree(t.getRoot());
//		 System.out.println("Row-wise: " + t.showData());

            TreeSet<Object> foo = new TreeSet<>();
            foo.add(( (Integer) 0).toString());
            foo.add("hello world!");
            foo.add("123hello world!");
            foo.add(new String());
            foo.add("7896");
            foo.add("!#@%$#^");
            foo.add("7896");
            foo.add("7896");

            Iterator<Object> it=foo.iterator() ;
                    while(it.hasNext()){
                            System.out.println(it.next()+" --------------------------");
            }

//		E	pollFirst()
//		Retrieves and removes the first (lowest) element, or returns null if this set is empty.
//		E	pollLast()
//		Retrieves and removes the last (highest) element, or returns null if this set is empty.
            System.out.println(" "+ foo.first()) ;
            System.out.println(" "+ foo.last()) ;
            System.out.println(" "+ foo.first()) ;
            System.out.println(" "+ foo.last()) ;
            System.out.println(" "+ foo.pollFirst()) ;
            System.out.println(" "+ foo.first()) ;
            System.out.println(" "+ foo.last()) ;
            System.out.println(" "+ foo.first()) ;
            System.out.println(" "+ foo.pollLast()) ;
            System.out.println(" "+ foo.last()) ;
            System.out.println(" "+ foo.last()) ;

            TreeMap<Integer, String> bar = new TreeMap<>();bar.put(456, "erithroe");
            bar.put(158, "214");
            System.out.println(" "+ bar.values()) ;

    }
    public static char[] stringTest(String a){
            char[] ret_value=a.toCharArray();
            System.out.println("started with:"+ ret_value[0]+"   and with length:"+ a.length() + " and the 6th char is "+ a.charAt(5));

            return ret_value;
    }
    // next step for p(n) from A[i,j] is only one of the two:
    // right A[i+j,j]
    // down  A[i,j+i]
    public static int	minStep(int m , int n){
		int ret_value = (int)Double.POSITIVE_INFINITY ;
		if (n>m){
			// only 2nd choice,  subproblem is move down to reach A[m,n]
			ret_value= minStep (m, n-m)+1;
		}else if (n<m){
			// only 1st choice, subproblem is move right to reach A[m,n]
			ret_value= minStep (m-n, n)+1;			
		} else if (n==m){
			if (n==1 && m ==1 ){
				ret_value=0;
			}else{
				System.out.println("this A["+m+","+n+"] will not reach A[1,1]");
			}
		}
		
		return ret_value;
	}
	
    public int getMin(int i, int j){
            return i>=j? j:j;
    }
    public static void hashTest(){
		HashMap<Integer, String> A = new HashMap<Integer, String>();
		HashMap  B = new HashMap(50,(float) 0.75);
		HashMap  C = new HashMap();
		C.put(1,"test");
		C.put(null,"5test");
		C.put("sehteo", null);
		System.out.println(C.size() + ":"+C.get(5));
//		System.out.println(C.size() + ":"+C.get("sehteo"));
		if (C.containsValue("test"))
		System.out.println(C.size() + ":"+C.get("sehteo"));
		System.out.println(C.values()); // no order
		
		Hashtable AT = new Hashtable();
		Hashtable BT = new Hashtable(50, (float)0.5);
		BT.put(1,"test");
//		BT.put(null,"est");
//		BT.put(5, null);
		BT.put("sehteo","t458est");
		System.out.println(BT.values()); // no order
	}

    public static int patition(double[] A, int h, int r, boolean Ascornot){
            int i=h-1;
            int mode = Ascornot? 1:-1;
            for(int j=h;j<r;j++){
                    if( (A[j]-A[r])*mode<=0){
                            i=i+1;
                            swap(A,i,j);
                    }
            }
            swap(A,i+1,r);
            return i+1;
    }

    public static int[] GetPopNum(int A[], int[] B, int h, int r , int l){ // anything has a length more than l=n/4
       if ( r-h+1==l &&  A[r]==A[h] ){
                            B[r/l]=A[r];
            } else if (r-h+1>l){
                    if (A[r]-A[h] > r-h - (l-1)){ //there is no pop at all
                            return B;
                    } else {
                            int mid= ( r+h)/2;
                            B=GetPopNum(A,B,h,mid,l);
                            B=GetPopNum(A,B,mid+1,r,l);
                            if(A[mid]==A[mid+1] ){
                                    int i=FindMatchBoundry(A,h,mid,false);
                                    int j=FindMatchBoundry(A,mid+1,r,true);
                                    if (j-i>=l-1){
                                            B[mid/l]=A[mid];					
                                    }
                            }
                    }
            }
            return B;
    }	

    public static int FindMatchBoundry(int[] A, int h, int r, boolean LefttoRight){
		int pivot = LefttoRight? h: r;
		int ret_value = LefttoRight? h: r ;
		if(r-h==1 && A[r]==A[h]){
			ret_value = LefttoRight ? r: h;
		} else if (r-h>1){
			int mid=(h+r)/2;
			if(A[mid]==A[pivot]){ // search the further part
				ret_value=mid;
				if (LefttoRight){//
					ret_value=FindMatchBoundry(A,mid,r,LefttoRight);
				}else{
					ret_value=FindMatchBoundry(A,h,mid,LefttoRight);
				}
			}else{
				if (LefttoRight){
					ret_value=FindMatchBoundry(A,h,mid,LefttoRight);
				}else{
					ret_value=FindMatchBoundry(A,mid+1,r,LefttoRight);
				}
			}			
		}
		return ret_value;
	}

    public static double[] QuickSort(double[] A, int h, int r, boolean Ascornot){
		if (h<r){
		int p= patition(A, h, r , Ascornot);
		QuickSort(A,1,p-1,Ascornot);
		QuickSort(A,p+1,r,Ascornot);
			
		}
		return A;
	}

    public static double[] HeapSort(double[] A, int n, boolean Ascornot) {
            if ( Ascornot == true){
                    BuildMaxHeapify(A, n);
            }
            else {
                    BuildMinHeapify(A, n);
            }

            for(int i=n;i>=2;i--){
                    swap(A,1,i);
                    if ( Ascornot == true){ 
                            MaxHeapify(A,1,i-1);
                    }
                    else {
                            MinHeapify(A,1,i-1);
                    }
            }
            return A;
    }

    public static double[] BuildMaxHeapify(double[] A, int n) {
            for (int i=n/2;i>=1;i--){
                    MaxHeapify(A,i,n);
            }
            return A;
    }
    public static double[] MaxHeapify(double[] A, int i, int n) {
            int left= 2*i;
            int right=2*i+1;
            int largest;
            if( left<=n && A[left]>A[i]){
                    largest = left;
            } else{
                    largest = i;
            }

            if (right<=n && A[right]>A[largest]){
                    largest=right;
            }
            if (largest != i){
                    swap(A,i,largest);
                    MaxHeapify(A,largest,n);
            }
            return A;
    }
    public static double[] BuildMinHeapify(double[] A, int n) {
            for (int i=n/2;i>=1;i--){
                    MinHeapify(A,i,n);
            }
            return A;
    }
    public static double[] MinHeapify(double[] A, int i, int n) {
            int left= 2*i;
            int right=2*i+1;
            int smallest;
            if( left<=n && A[left]<A[i]){
                    smallest = left;
            } else{
                    smallest = i;
            }

            if (right<=n && A[right]<A[smallest]){
                    smallest=right;
            }
            if (smallest != i){
                    swap(A,i,smallest);
                    MinHeapify(A,smallest,n);
            }
            return A;
    }
    public static double[] swap(double[] A, int i, int j) {
            double temp= A[i];
            A[i] = A[j];
            A[j] = temp;
            return A;
    }
    public static boolean isNumeric(String Str){
            boolean ret_val = false;
            try{
                    Double.parseDouble(Str);	
                    ret_val = true;		
                    }catch ( NullPointerException e){
                             System.out.println("The input string is empty.");
                    } catch ( NumberFormatException e){
                            System.out.println("This is not a number.");
                    } finally {
                            System.out.println("This is done no matter it is.");
                    }
            return ret_val;
    }

    public static double[] Merge(double A[], int h, int m, int r, boolean AscorNot){ 
            int mode;
            if (AscorNot==true) mode =1; else mode=-1;
            int nl= m-h+1;
            int nr=r-m;
            double[] L = new double[nl+1];
            double[] R = new double[nr+1];
            int i,j,k;
            for (i=0;i<nl;i++)
                    L[i] = A[h+i];
            for (i=0;i<nr;i++)
                    R[i] = A[m+i+1];
            L[nl] = mode * Double.POSITIVE_INFINITY;
            R[nr] = mode * Double.POSITIVE_INFINITY;
            for(i=0,j=0,k=h;k<=r;k++){ 
                    if (mode*(L[i]-R[j]) <0 ){
                            A[k]=L[i];
                            i++;
                    }else {
                            A[k]=R[j];
                            j++;
                    }			
            }

            return A;
    }

    public static double[] MergeSort(double A[] , int h, int r, boolean AscorNot){
            if (r-h>0)
                    { int mid =(int)Math.floor((r+h)/2); 
                    MergeSort(A,h,mid,AscorNot);
                    MergeSort(A,mid+1,r,AscorNot);
                    Merge(A,h,mid,r,AscorNot);
                    }

            return A;
    }

    public static int[] MAXSubarray(int A[] , int h, int r){
            int[] ret_array = {0,0,0};
            if (r<h) {
                    System.out.println("Input invalid. Exited");
            } else if (r==h){
                    ret_array[0]=h;
                    ret_array[1]=r;
                    ret_array[2]=A[h];
            } else {

                    int mid = (int)Math.floor((r+h)/2) ;
                    int[] L =new int[3];
                    int[] R =new int[3];
                    int[] C =new int[3];
                    L=MAXSubarray(A,h,mid);
                    R=MAXSubarray(A,mid+1, r);
                    C=MAXSubarrayCross(A,h,r,mid);

                    if (L[2]>=R[2] && L[2]>=C[2]){
                            ret_array = L;
                    }else if(R[2]>=L[2] && R[2]>=C[2]){
                            ret_array = R;
                    }else
                    {
                            ret_array = C;
                    }
            }
            return ret_array;
    }

    public static int[] MAXSubarrayCross(int A[] , int h, int r, int mid){
		int sumL = (int) Double.NEGATIVE_INFINITY;
		int sumR = (int) Double.NEGATIVE_INFINITY;
		int MaxL = 0;
		int MaxR = 0;
		for(int i=mid;i>=h;i--){
			if (sumL + A[i] >sumL){
				sumL =sumL + A[i];
				MaxL = i;
			}
		}
		for(int i=mid+1;i<=r;i++){
			if (sumR + A[i] >sumR){
				sumR =sumR + A[i];
				MaxR = i;
			}
		}
		int[] ret_array = {MaxL,MaxR,sumL+sumR } ;
		return ret_array;
	}
}
