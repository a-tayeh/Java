 import java.util.Scanner;
 import java.io.*;

 public class FileIOLab{
  public static void main(String[] args)  { 
    
    File inputFile; 
    String inputFileName = null;
   
    if(args.length > 0)                         // check for argument to main method
      inputFileName = args[0];                  // set file name to argument

    if(inputFileName == null){                  // if no argument, prompt user to enter it
      inputFileName = promptForFileName();
    }
  
    try{
      inputFile = getFile(inputFileName);       // validate input file

      String outFileName = "Modified-"+ inputFileName;
      File outFile = new File(outFileName);
      PrintWriter fileOutput = new PrintWriter(outFile);

      Scanner readFile = new Scanner(inputFile);
      while (readFile.hasNextLine()) {
        String line = readFile.nextLine().trim();    // get a line from file
    
        String lineWithOutComments = extractComments(line);

        String lineWithOutStringLiterals = extractStrings(line);
        String lineWithOutChars = extractChars(line);
        
        fileOutput.print(lineWithOutComments + "\n");
        fileOutput.print(lineWithOutStringLiterals + "\n");
        fileOutput.print(lineWithOutChars + "\n");

          System.out.println(lineWithOutComments);
          System.out.println(lineWithOutStringLiterals);            // display the file
          System.out.println(lineWithOutChars);            // display the file// display the file
      }
       fileOutput.close();
    }
    catch(FileNotFoundException noFile){
      System.out.println("Error reading from file; Program could not continue.");
    }
  }

  private static String promptForFileName(){
    Scanner in = new Scanner(System.in);   
    System.out.print("Please enter the file name:");
    if(in.next().length()>0){
        return in.next();
    }
    return promptForFileName();

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
  
  private static String extractComments(String line){
  
        if(line == null) return line;            // it's blank skip it

        if(line.contains("//")){              // remove comment, if present
          int beginning = line.indexOf("//");
          line = line.substring(0, beginning);
        }
      return line;
    }

    private static String extractStrings(String line){
        while(line.contains("\"")) {                // Line contains a String literal - remove the literal
          int start = line.indexOf('"');
          int end = line.indexOf('"', start + 1);

          String firstHalf = line.substring(0, start);
          String lastHalf = line.substring(end + 1);

          line = firstHalf + lastHalf;
        }
        return line;
    }
    private static String extractChars(String line){
        while(line.contains("'")){
            int start = line.indexOf("'");
            int end = line.indexOf("'",start+1);
            String firstHalf = line.substring(0,start);
            String lastHalf = line.substring(end+1);
            line = firstHalf+lastHalf;
        }
        return line;
    }

    public static boolean isHTMLMatched(String html){
      StackInterface<String> tagStack = new ArrayStack<>();
      int j = html.indexOf('<');
      while(j!=-1){
          int k = html.indexOf('>',j+1);
          if(k==-1){return false;}
          String tag = html.substring(j,k);
          if(!tag.startsWith("/")){
              tagStack.push(tag);
          }
          else{
              if(tag.isEmpty()){return false;}
              if(!tag.substring(1).equals(tagStack.pop())){return false;}

          }
          j = html.indexOf('<',k+1);
      }
      return tagStack.isEmpty();
    }
    public static String extractStringContent(String html, String tag){

    }


 }