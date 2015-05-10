/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author Emma
 */
public class treeTest {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        treeNode root=new treeNode(3);
        tree t=new tree(root);
        if ( t.insert(123) && t.insert(4) && t.insert(7) && t.insert(34) && t.insert(64))
              System.out.println("Inserted Successfully!")  ;
         t.insert(342113);
         t.insert(1);
         t.insert(46);
        t.inorderTraverse();        System.out.println();
        t.TraverseByLevel();
        if ( t.search(46)!=null ){
            System.out.println("\ntree search found:"+t.search(46).data);
            if ( t.delete(46) )  System.out.println("Deleted successully!Now the tree is :");
            t.inorderTraverse();
        }
        t.TraverseByLevel();
        if (t.search(234)!=null){
            System.out.println("tree search found");
        }
    
        
    }
    
}
