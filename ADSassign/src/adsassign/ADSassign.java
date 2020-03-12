//Name :- Kawakib Darain
//Roll No :- 41
//Subject :- ADS Assignment (5 marks)
//Topic :- RB-Tree - IP Address Application, printing the path of node in the tree.

package adsassign;

import java.util.Scanner;

/**
 *
 * @author Kawakib darain
 */

class Node {
	int data; // key
	Node parent; // pointer to the parent
	Node left; // pointer left child
	Node right; // pointer right child
	int color; // 1 . Red, 0 . Black
}

public class ADSassign {
	
        private Node root;
	private Node TNULL;
        
	public ADSassign() {
		TNULL = new Node();
		TNULL.color = 0;
		TNULL.left = null;
		TNULL.right = null;
		root = TNULL;
	}        
        
        // rotate left at node x
	public void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != TNULL) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

	// rotate right at node x
	public void rightRotate(Node x) {
		Node y = x.left;
		x.left = y.right;
		if (y.right != TNULL) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.right) {
			x.parent.right = y;
		} else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}
        
	// insert the key to the tree in its appropriate position
	// and fix the tree
	public void insert(int key) {
		// Ordinary Binary Search Insertion
		Node node = new Node();
		node.parent = null;
		node.data = key;
		node.left = TNULL;
		node.right = TNULL;
		node.color = 1; // new node must be red

		Node y = null;
		Node x = this.root;

		while (x != TNULL) {
			y = x;
			if (node.data < x.data) {
				x = x.left;
			} else {
				x = x.right;
			}
		}

		// y is parent of x
		node.parent = y;
		if (y == null) {
			root = node;
		} else if (node.data < y.data) {
			y.left = node;
		} else {
			y.right = node;
		}

		// if new node is a root node, simply return
		if (node.parent == null){
			node.color = 0;
			return;
		}

		// if the grandparent is null, simply return
		if (node.parent.parent == null) {
			return;
		}

		// Fix the tree
		fixInsert(node);
	}
        
	// fixing red-black tree
	private void fixInsert(Node k){
		Node u;
		while (k.parent.color == 1) {
			if (k.parent == k.parent.parent.right) {
				u = k.parent.parent.left; // uncle
				if (u.color == 1) {
					// case 3.1
					u.color = 0;
					k.parent.color = 0;
					k.parent.parent.color = 1;
					k = k.parent.parent;
				} else {
					if (k == k.parent.left) {
						// case 3.2.2
						k = k.parent;
						rightRotate(k);
					}
					// case 3.2.1
					k.parent.color = 0;
					k.parent.parent.color = 1;
					leftRotate(k.parent.parent);
				}
			} else {
				u = k.parent.parent.right; // uncle

				if (u.color == 1) {
					// mirror case 3.1
					u.color = 0;
					k.parent.color = 0;
					k.parent.parent.color = 1;
					k = k.parent.parent;	
				} else {
					if (k == k.parent.right) {
						// mirror case 3.2.2
						k = k.parent;
						leftRotate(k);
					}
					// mirror case 3.2.1
					k.parent.color = 0;
					k.parent.parent.color = 1;
					rightRotate(k.parent.parent);
				}
			}
			if (k == root) {
				break;
			}
		}
		root.color = 0;
	}

        // searching the key and printing path
	private Node searchTreeHelper(Node node, int key) {
                if (node == TNULL) {
			return node;
		}
		if (key < node.data) {
                        System.out.print(node.data+"-->");
			return searchTreeHelper(node.left, key);
		}
                else if(key > node.data){
                    System.out.print(node.data+"-->");
                    return searchTreeHelper(node.right, key);
                }
                else if(key == node.data){
                    System.out.println(node.data);
                    return node;
                }
            
            return node;
        }
        
        // printing the tree structure on the screen
	private void printHelper(Node root, String indent, boolean last) {
	   	if (root != TNULL) {
		   System.out.print(indent);
		   if (last) {
		      System.out.print("R----");
		      indent += "     ";
		   } else {
		      System.out.print("L----");
		      indent += "|    ";
		   }
            
           String sColor = root.color == 1?"RED":"BLACK";
		   System.out.println(root.data + "(" + sColor + ")");
		   printHelper(root.left, indent, false);
		   printHelper(root.right, indent, true);
		}
	}

	// search the tree for the key k
	// and return the corresponding node
	public Node searchTree(int k) {
		return searchTreeHelper(this.root, k);
	}

        //Returns Root
	public Node getRoot(){
		return this.root;
	}
                
	// print the tree structure on the screen
	public void Print() {
            printHelper(this.root, "", true);
	}
	
	public static void main(String [] args){
            
            Scanner sc = new Scanner(System.in);
            ADSassign rb = new ADSassign();
            System.out.println("_________________________________________________");
            System.out.println("        RB-TREE :- IP ADDRESS APPLICATION        ");
            System.out.println("_________________________________________________");
            
            char ch;   
            //Do-While Loop
            do{
                System.out.println("Enter :- ");
                System.out.println("1. To Insert Data\t2. To Print Path of Key");
                System.out.println("3. To Print Tree");
                System.out.println("_________________________________________________");
                int choice = sc.nextInt();            
                switch (choice)
                {
                    case 1 : 
                            System.out.println("Enter data to be inserted :- ");
                            rb.insert(sc.nextInt());                     
                            break;                          
                    case 2 : 
                            System.out.println("Enter the Key to be searched :- ");
                            int key = sc.nextInt();
                            System.out.println("Path :- ");
                            System.out.println((rb.searchTree(key)!=null) ? "" : "Not found");	
                            break;
                    case 3:
                            System.out.println("_________________________________________________");
                            System.out.println("                     RB-TREE                     ");
                            System.out.println("_________________________________________________");
                            rb.Print();
                            break;
                    default: 
                            System.out.println("Invalid choice");
                }
                System.out.println("_________________________________________________");                
                System.out.println("Press Y to continue... ");
                ch = sc.next().charAt(0);  
                System.out.println("_________________________________________________");
            
            }while(ch == 'Y'|| ch == 'y');
            
            System.out.println("Exiting...");
        }
}