/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;
import myGraph.myGraph;
import java.util.Arrays;
/**
 *
 * @author Emma
 */
public class maze {
    public myGraph M;
    public int[][] AdjMatrix;
    public maze(){
        this(9,9);
    }
    public maze(int m,int n){ // m rows, n column
        this.M = new myGraph(m*n, false); 
        AdjMatrix = new int[m*n][n*m];
//        Arrays.fill(AdjMatrix, 0);
        for(int i=0;i<m*n;i++){
             Arrays.fill(AdjMatrix[i], 0);
//            for(int j=0;j<m*n;j++){AdjMatrix[i][j]=0;
//        }
        }
        generate(m,n);
    }
    public int[] getDFS(){
        return this.M.traverseDFS(0);
    }
    public void printM(int m){
        
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                System.out.print(AdjMatrix[i][j]);
        }System.out.println();
        }
    }
    public void generate(int m, int n){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
//                System.out.printf("(%d, %d) %d up %d " ,i,j,i*n+j,i*n+j-n);
//                System.out.printf("left  %d  right %d" ,i*n+j-1,i*n+j+1);
//                System.out.println("               " + i*n+j+n);
                if(i!=0   && i*n+j-n>=0)   {
                    this.M.allV[i*n+j].Adj.put(i*n+j-n, 1); // the vertex above 
                    AdjMatrix[i*n+j][i*n+j-n]= 1;
                }
                if(i!=m-1 && i*n+j+n<m*n)  {
                    this.M.allV[i*n+j].Adj.put(i*n+j+n, 1); // the vertex below
                    AdjMatrix[i*n+j][i*n+j+n]= 1;
                }
                if(j!=0   && i*n+j-1>=0)     {
                    this.M.allV[i*n+j].Adj.put(i*n+j-1, 1); // the vertex to the left
                    AdjMatrix[i*n+j][i*n+j-1]= 1;
                }
                if(j!=n-1 && i*n+j+1<m*n)   {
                    this.M.allV[i*n+j].Adj.put(i*n+j+1, 1); // the vertex to the right
                    AdjMatrix[i*n+j][i*n+j+1]= 1;
                }
//                if (i==j)  this.M.allV[i][j]=0;
            }
        }
        
    }
    public void draw(int m, int n, int[] sequence){
        int front=0;
        int end=0;
        int[][] V=new int[m][n+1];
        int[][] H=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                H[i][j] = 1;
                V[i][j] = 1;
            }
                V[i][n] = 1;
        }
        for(int i=1;i<sequence.length-1;i++){
            if ( sequence[i+1]-sequence[i]==1 ){ // move right
                V[sequence[i]/n][sequence[i]%n+1]=0;
            } else if ( sequence[i+1]-sequence[i]== -1 ){ // move left
                V[sequence[i]/n][sequence[i]%n]=0;
            }
            else if ( sequence[i+1]-sequence[i]== n ){//move down
                H[sequence[i]/n][sequence[i]%n]=0;
            } else if ( sequence[i+1]-sequence[i]== (-1*n) ){//move up
                H[sequence[i]/n-1][sequence[i]%n]=0;
            }
        }
         for(int j=0;j<n;j++){System.out.print("___");}
            System.out.println("");
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(V[i][j]==1){
                    System.out.print("|");
                }
                if(H[i][j]==1){
                    System.out.print("__");
                }else 
                    System.out.print("  ");
            }
            System.out.println("|");
        }
    }
    public static void main(String args[]){
        int m =9;
        int n=9;
        maze A = new  maze(m,n);
//        A.M.printAdj();
        int[] mazePath= A.getDFS();
        for  (int i =0;i<mazePath.length;i++) {  System.out.print (" "+mazePath[i]);} System.out.println("") ;
        int step=0;
        int[][] Map=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                Map[i][j] = -1;
            }}
         Map[0][0] =0;
        for(int i=1;i<mazePath.length;i++){
                int x = mazePath[i]/n;
                int y = mazePath[i]%n;
            if(mazePath[i]!=mazePath[i-1] && Map[x][y] ==-1){
                step++;
//                System.out.println(x+"  "+y+" : "+step);
                Map[x][y] = step;
            }
        }
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(" "+Map[i][j]);                
            }
                System.out.println(" ");
        }
        
        
        A.draw(m,n,mazePath);
    }
}
