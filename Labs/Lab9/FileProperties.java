
import java.util.Scanner;
import java.io.*;


public class FileProperties {
  public static void main(String[] args) {

    // Obtain a list of all files in the current directory
    File currentDirectory = new File(".");
    String[] fileNames = currentDirectory.list();
    System.out.println("\nThe current directory contains the following files:");

    // Display each name in the list
    for (int i = 0; i < fileNames.length; i++)
      System.out.println(fileNames[i]);

    String fileName = null;

    if(args.length > 0)                    // check for argument to main method
      fileName = args[0];                  // set file name to argument
    else{
      Scanner keyIn = new Scanner(System.in);
      System.out.println("Enter a file name: ");
       fileName = keyIn.next();
    }

    // Create a File object
    File f = new File(fileName);
    System.out.println("\nProperties of the file, " + fileName + ":");
    // Display properties of file
    if (f.exists())
      System.out.println("File exists");
    if (f.canRead())
      System.out.println("File can be read");
    if (f.canWrite())
      System.out.println("File can be written");
    if (f.isDirectory())
      System.out.println("File is a directory");
    if (f.isFile())
      System.out.println("File is a normal file");
    System.out.println("The file has " + f.length() + " bytes\n");  // how many bytes in the file
    
  }
}
