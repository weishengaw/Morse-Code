// Wei Sheng Aw
// 3/14/20
// This is the class of a Morse Code Tree with a binary tree type outlining morse code, with lefts being dashes and right dots
// It has methods encode and decode, which encode and decode morse code messages

import java.util.*;

public class MorseCodeTree {
   private TreeNode<Character> overallRoot;
   
   // post: manually sets up the morse code pathings (---- for spaces, empty roots denoted by '@')
   public MorseCodeTree() {
      overallRoot = new TreeNode<Character>('@');
      overallRoot.left = new TreeNode<Character>('t');
      overallRoot.left.left = new TreeNode<Character>('m');
      overallRoot.left.left.left = new TreeNode<Character>('o');
      overallRoot.left.left.left.left = new TreeNode<Character>(' ');
      overallRoot.left.left.left.left.left = new TreeNode<Character>('0');
      overallRoot.left.left.left.left.right = new TreeNode<Character>('9');
      overallRoot.left.left.left.right = new TreeNode<Character>('@');
      overallRoot.left.left.left.right.right = new TreeNode<Character>('8');
      overallRoot.left.left.right = new TreeNode<Character>('g');
      overallRoot.left.left.right.left = new TreeNode<Character>('q');
      overallRoot.left.left.right.right = new TreeNode<Character>('z');
      overallRoot.left.left.right.right.right = new TreeNode<Character>('7');
      overallRoot.left.right = new TreeNode<Character>('n');
      overallRoot.left.right.left = new TreeNode<Character>('k');
      overallRoot.left.right.left.left = new TreeNode<Character>('y');
      overallRoot.left.right.left.right = new TreeNode<Character>('c');
      overallRoot.left.right.right = new TreeNode<Character>('d');
      overallRoot.left.right.right.left = new TreeNode<Character>('x');
      overallRoot.left.right.right.right = new TreeNode<Character>('b');
      overallRoot.left.right.right.right.right = new TreeNode<Character>('6');
      overallRoot.right = new TreeNode<Character>('e');
      overallRoot.right.left = new TreeNode<Character>('a');
      overallRoot.right.left.left = new TreeNode<Character>('w');
      overallRoot.right.left.left.left = new TreeNode<Character>('j');
      overallRoot.right.left.left.left.left = new TreeNode<Character>('1');
      overallRoot.right.left.left.right = new TreeNode<Character>('p');
      overallRoot.right.left.right = new TreeNode<Character>('r');
      overallRoot.right.left.right.right = new TreeNode<Character>('l');
      overallRoot.right.right = new TreeNode<Character>('i');
      overallRoot.right.right.left = new TreeNode<Character>('u');
      overallRoot.right.right.left.left = new TreeNode<Character>('@');
      overallRoot.right.right.left.left.left = new TreeNode<Character>('2');
      overallRoot.right.right.left.right = new TreeNode<Character>('f');
      overallRoot.right.right.right = new TreeNode<Character>('s');
      overallRoot.right.right.right.left = new TreeNode<Character>('v');
      overallRoot.right.right.right.left.left = new TreeNode<Character>('3');
      overallRoot.right.right.right.right = new TreeNode<Character>('h');
      overallRoot.right.right.right.right.left = new TreeNode<Character>('4');
      overallRoot.right.right.right.right.right = new TreeNode<Character>('5');
   }
   
   // pre: input must be valid for this morse code tree
   // post: returns the coded version of the string input
   public String encode(String input) {
      String s = input.toLowerCase(); // makes string uniform
      String code = ""; // string that is to be returned
      ArrayList<String> list = new ArrayList<>(); // temp list holding a sequence of morse code
      
      if (s.length() <= 0 || s.contains("@")) { // checks for invalid inputs
         throw new IllegalArgumentException("invalid input");
      }
      
      // first fencepost
      if (encode(overallRoot, s.charAt(0), list)) { // if code is found
          for (String i : list) { // adds list to code as a string
              code += i;
          }
      } else {
          throw new IllegalArgumentException("invalid input"); // if code is not found
      }
      
      for (int i = 1; i < s.length(); i++) { // does the same process as above, separating with spaces
            list.clear();
            if (encode(overallRoot, s.charAt(i), list)) {
                code += " ";
                for (String j : list) {
                    code += j;
                }
            } else {
                throw new IllegalArgumentException("invalid input");
            }
      }
      return code;
   }
   
   // post: returns whether or not a sequence is successful, recursively backtracks through options, storing it in a given ArrayList  
   private boolean encode(TreeNode root, char current, ArrayList<String> list) {
      if (root.data.equals(current)) { // found character
         return true;
      } else { // not found
         if (root.left != null) { // explores left side
            list.add("-");
            if (encode(root.left, current, list)) {
                return true;
            }
            list.remove(list.size() - 1);
         }
         if (root.right != null) { // explores right side
            list.add(".");
            if (encode(root.right, current, list)) {
               return true;
            }
            list.remove(list.size() - 1);
         }
         return false;
      }
   }
   
   // pre: input must be valid morse code    
   // post: returns decoded message
   public String decode(String input) {
      Scanner scan = new Scanner(input); // scanner to scan through a line of morse code
      String totalMsg = "";
      while (scan.hasNext()) {
          String temp = scan.next();
          totalMsg += decode(overallRoot, temp); // adds the character to the message
      }
      return totalMsg;
   }
   
   // private part of decode, paths towards a given character using morse code
   private String decode(TreeNode root, String input) {
      if (input.length() > 0) { // tracks until no more morse code input
          if (input.charAt(0) == '-') { // moves left for dash
              if (root.left == null) { // checks to make sure left exists
                 throw new IllegalArgumentException("invalid morse code input");
              } else {
                 return decode(root.left, input.substring(1, input.length())); // recursively calls with the rest of the code
              }
          } else if (input.charAt(0) == '.') { // moves right for dot
              if (root.right == null) { // checks to make sure right exists
                 throw new IllegalArgumentException("invalid morse code input");
              } else {
                 return decode(root.right, input.substring(1, input.length())); // recursively calls with the rest of the code
              }
          } else { // throws exception if it is any other character
              throw new IllegalArgumentException("invalid morse code input");
          }
      } else {
          if (root.data.equals('@')) { // checks to make sure it is not an empty character
              throw new IllegalArgumentException("invalid morse code input");
          } else {
              return root.data + ""; // returns the character
          }
      }
   }
   
   // public private pair to show the tree
   public void printSideways() {
      printSideways(overallRoot, 0);
   }

   // post: prints in reversed preorder the tree with given
   //       root, indenting each line to the given level
   private void printSideways(TreeNode root, int level) {
      if (root != null) {
          printSideways(root.right, level + 1);
          for (int i = 0; i < level; i++) {
              System.out.print("    ");
          }
          System.out.println(root.data);
          printSideways(root.left, level + 1);
      }
   }
   
   // TreeNode inner class
   public class TreeNode<E> {
      public E data;
      public TreeNode left;
      public TreeNode right;
                 
      // constructs a leaf node with given data
      public TreeNode(E data) {
          this(data, null, null);
      }
                         
      // constructs a branch node with given data, left subtree,
      // right subtree
      public TreeNode(E data, TreeNode left, 
                        TreeNode right) {
          this.data = data;
          this.left = left;
          this.right = right;
      }
   }
}
      