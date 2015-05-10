/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author Emma
 * @param <T>
 */
public class tree<T extends Comparable > {
    treeNode<T> root=new treeNode();
    public tree(){
        this.root=null;
    }
    public tree(treeNode<T> newnode){
        this.root=newnode;
    }

    public tree(T value){
        this.root.data=value;
    }
    
    public void TraverseByLevel(){
        TraverseByLevel(this.root);
    }
    public void TraverseByLevel(treeNode<T> start){
         treeNode<T> p = start;
         int level=0;
         LinkedList<treeNode<T>> currentLevel = new LinkedList<>();
         LinkedList<treeNode<T>> nextLevel = new LinkedList<>();
         currentLevel.add(p);
         
         while( !currentLevel.isEmpty()){
             p=currentLevel.remove();
             System.out.print(" "+p.data.toString());
             if(p.left!=null){
                 nextLevel.add(p.left);
             }
             if(p.right!=null){
                 nextLevel.add(p.right);
             }
             if(currentLevel.isEmpty()){
                System.out.println("  level "+level+" ");
                if(  !nextLevel.isEmpty()){
                    currentLevel= nextLevel;
                    nextLevel = new LinkedList<>();
                   level++;                    
                }
             }
         }
     }
    public boolean inorderTraverse(){
        return inorderTraverse(this.root);
    }
    public boolean inorderTraverse(treeNode<T> start){
        boolean ret_value = false;
        if ( start == null){
            System.out.println("The tree is empty!");
            return ret_value;
        }
        if( start.left != null){
            ret_value = inorderTraverse(start.left);
        }
        System.out.print(" "+start.data.toString());
        if( start.right != null){
            ret_value = inorderTraverse(start.right);
        }        
        return ret_value;
    }
    
    public treeNode<T> search(T  value){
        return this.search(this.root, value);
    }
    public treeNode<T> minInorder(treeNode<T> foo){
        treeNode<T> p=foo;
        if(p.left!=null){
            p=p.left;
        }
        return p;
    }
    public treeNode<T> maxInorder(treeNode<T> foo){
        treeNode<T> p=foo;
        if(p.right!=null){
            p=p.right;
        }
        return p;
    }
    public treeNode<T> successorInorder(treeNode<T> foo){
        treeNode<T> p=foo;
        if(p.right!=null) return minInorder(p.right);
        else {
            treeNode<T> y=p.parent;
            while(y!=null && p==y.right){
                p=y;
                y=y.parent;
            }
            return y;
        }
    }
    public treeNode<T> precedessorInorder(treeNode<T> foo){
        treeNode<T> p=foo;
        if(p.left!=null) return maxInorder(p.left);
        else {
            treeNode<T> y=p.parent;
            while(y!=null && p==y.left){
                p=y;
                y=y.parent;
            }
            return y;
        }
    }
   
    public treeNode<T> search(treeNode<T> start,T value){
        treeNode<T> ret_value = null; 
        treeNode<T> p=start;
        while(p!= null){
            if(p.data.compareTo(value)==0){
                ret_value = p; 
                break;
            }else if(p.data.compareTo(value)>0){
                p=p.left;
            }else{
                p=p.right;
            }
        }
        return ret_value;
    }
    public boolean transplant(treeNode<T>  foo, treeNode<T> bar){
        boolean ret_value = false;
        if (foo.parent==null){ // foo is the root
            this.root = bar;
//            bar.parent=null;
            ret_value = true;
        }else {
            if( foo.parent.left == foo){ // foo is left child
                foo.parent.left=bar;
            }else {// fool is right child
                foo.parent.right=bar;
            }
            ret_value = true;
        }        
        if (bar != null){
            bar.parent=foo.parent;
        }
        return ret_value;
    }
    public boolean delete(treeNode<T>  dNode){
        boolean ret_value = false;
        if (dNode==null){ System.out.println("The node is empty!"); return ret_value ; }
        if( dNode.left == null ){
            transplant(dNode, dNode.right);ret_value=true;
        }else {
            if (dNode.right == null ){
                transplant(dNode, dNode.left);
            }else {
                treeNode<T> y = minInorder(dNode.right);
                if(y.parent!=dNode){
                    transplant(y,y.right);
                    y.right = dNode.right;
                    y.right.parent = y; 
                }
                transplant(dNode, y);
                y.left=dNode.left;
                y.left.parent=y;
                ret_value =true;
            }
        }        
        return ret_value;
    }
    public boolean delete(T value){
        boolean ret_value = false;
        treeNode<T> p = this.search(value);
        if ( p!=null){
            ret_value = this.delete(p);
        }
        return ret_value;
    }
    public boolean insert(T newValue){
        treeNode<T> newNode = new treeNode<>(newValue);
        return this.insert(newNode);
    }
    public boolean insert(treeNode<T> newNode){
        boolean ret_value = false;
        if(this.root == null){
            this.root = newNode;
            ret_value = true;
        } else {
            treeNode<T> tmp = this.root;
            treeNode<T> p = tmp.parent;
            while(tmp != null){
                try{
                    if( newNode.data.compareTo(tmp.data) < 0){
                        p = tmp;
                        tmp = tmp.left;
//                        System.out.println("The node left"+ p.data);
                    }else if ( newNode.data.compareTo(tmp.data) > 0 ){
                        p = tmp;
                        tmp = tmp.right;
//                        System.out.println("The node right"+ p.data);
                    }else {
                        System.out.println("The node "+ newNode.data.toString() +" is already in the Tree");
                    }
                }catch ( Exception e ){
                    System.out.println(e.getMessage());
                    return ret_value;
                }  
            }//find the place to insert the newNode as a new Leaf
            if( newNode.data.compareTo(p.data) < 0){
                p.left = newNode;
            }else {
                p.right = newNode;
            }
            newNode.parent= p;
            ret_value=true;
        }        
        return ret_value;
    }
}
