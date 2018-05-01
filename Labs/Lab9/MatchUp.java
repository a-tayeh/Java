 import java.util.Scanner;
 import java.io.*;

 public class MatchUp
 {
  public static void main(String[] args) throws FileNotFoundException
  { 
    
    File inputFile; 
    String inputFileName = "";
   
    if(args.length > 0)
      inputFileName = args[0];
  
    inputFile = getFile(inputFileName);      // validate input file

    String output = countBraces(inputFile);  // process the input file
    
    System.out.println(output);       // display the file
    
  }

 
 
   private static File getFile(String fileName) throws FileNotFoundException{   
     File myFile = new File(fileName);
     
    while(!myFile.exists()){  
      System.out.println("File not found."); 
      fileName = promptForFileName();
      myFile = new File(fileName);
    } 
    return myFile;
   }
  
  
   private static String promptForFileName(){
    Scanner in = new Scanner(System.in);   
    System.out.print("Please enter source code file name:");
    return in.next();
  }
  
  
  private static String countBraces(File fileInput) throws FileNotFoundException{
      int charCount = 0;
    int braceCount = 0;
    Scanner inFile = new Scanner(fileInput);
   String result = "";
   
   while(inFile.hasNextLine()){        // read each line
    String line = inFile.nextLine();
    Scanner inLine = new Scanner(line);
    inLine.useDelimiter("");          // parse character by character
    
    String outLine = "";            // reset output line to empty string
   
    while(inLine.hasNext()){        // examine each character
     char ch = inLine.next().charAt(0);
        charCount++;
     outLine += ch;                 // concatenate the character to the output
    
     if(ch == '('){                 // test for opening brace
      braceCount++;                 // update the counter
      outLine += " " + braceCount;  // concatenate the count
     }
     else if(ch == ')'){            // test for closing brace
      outLine += " " + braceCount;  // concatenate the count
         braceCount--;              // update the counter
      if(braceCount == -1){
          return ""+charCount;            // return the location
      }
     }
    }
      result += outLine + "\n";
    }
//    return result;
      return "" + braceCount;
  }
 }