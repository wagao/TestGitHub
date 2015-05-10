/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author Emma
 * @param <T>
 */
public class treeNode<T extends Comparable> {
    T data;
    public treeNode<T> parent;
    public treeNode<T> left;
    public treeNode<T> right;
    
    treeNode(){
        this.data = null;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
    treeNode(T value){
        this.data = value;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}
