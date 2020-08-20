// Wei Sheng Aw
// 3/14/20
// This is the client code used to test the Morse Code Tree functions

import java.util.*;
import java.io.*;

public class MorseCodeClient {
   public static void main(String[] args) throws FileNotFoundException{
      MorseCodeTree t = new MorseCodeTree();
      Scanner s = new Scanner(new File("morse_input.txt"));
      PrintStream output = new PrintStream(new File("morse_output.txt"));
      // shows the tree pathing
      // t.printSideways();
      
      // Showcases every letter
      System.out.println("t.encode(\"The quick brown fox jumps over the lazy dog\"):");
      System.out.println(t.encode("The quick brown fox jumps over the lazy dog"));
      System.out.println();

      
      // Showcases every number
      System.out.println("t.encode(\"1234567890\"):");
      System.out.println(t.encode("1234567890"));
      System.out.println();

      
      // Showcases file input decoding capability
      System.out.println("decoding test file 'morse_input.txt': ");
      while (s.hasNext()) {
         String temp = t.decode(s.next());
         System.out.print(temp);
      }
      System.out.println();
      System.out.println();
      
      // Showcases file output encoding capability
      System.out.println("encoding message 'test message' to file 'morse_output.txt'");
      output.println(t.encode("test message"));
      
      // Decodes a message
      System.out.println("t.decode(\".-. .- ---- .-. .- ---- .-. .- ... .--. ..- - .. -.\"):");
      System.out.println(t.decode(".-. .- ---- .-. .- ---- .-. .- ... .--. ..- - .. -."));
      System.out.println();

      
      // Decodes a message
      System.out.println("t.decode(\"... --- ...\"):");
      System.out.println(t.decode("... --- ..."));
      System.out.println();

      
      // Special case: empty input (encode throws exception, decode returns an empty string)
      // System.out.println(t.encode(""));
      // System.out.println(t.decode(""));

      
      // Encode exception, special character called
      // System.out.println(t.encode("@"));

      
      // Encode exception, invalid character called
      // System.out.println(t.encode("#"));

      
      // Decode exception, message too long
      // System.out.println(t.decode("------"));

      
      // Decode exception, char does not exist
      // System.out.println(t.decode("---."));

   }
}