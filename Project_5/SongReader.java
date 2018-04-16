/**
 *          Ali Tayeh               Project_5          CMSC-256-001        Spring-2018
 *
 *  SongReader class is responsible for parsing data correctly from an input file and create Song objects of
 *  correctly parsed data and, if necessary, create an output file called errorLog.txt if there are any errors
 *  detected within the input file with an arrow pointing SPECIFICALLY, where the error occurred!
 *  a brief explanation of how the arrow-pointer algo works can be found at the end of the SongReader class.
 */

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SongReader {
    // where our errorLog data will be stored
    private static ArrayList<String> errorLog = new ArrayList<>();
    private static ArrayList<MySong> songCollection = new ArrayList<>();


    public static void main(String [] args){
        // call to printHeading
        printHeading();
        // takes in filename as command line args
//        parseSongData("playlist(1).data");
        // writing the ErrorLog.txt if there is an error
        if(errorLog.size()>0){
            try{
                FileWriter writeOut = new FileWriter("ErrorLog.txt");
                writeOut.write("\n******************************ErrorLog****************************"+"\n\n");
                writeOut.write("# Arrows point to the error(s) found while parsing the input file"+"\n\n");
                writeOut.write("******************************************************************"+"\n\n");
                for(String errorLine : errorLog){
                    writeOut.write(errorLine+"\n");
                    writeOut.flush();
                }
                writeOut.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        toArray();
    }

    private static void printHeading(){
        System.out.println();
        System.out.println("Calling printHeading()");
        System.out.println();
        System.out.println();
        System.out.println("*******************************************************************************");
        System.out.println();
        System.out.println("    Ali Tayeh           Project_5          CMSC-256-001           Spring-2018");
        System.out.println();
        System.out.println("*******************************************************************************");

    }

    public static boolean parseSongData(String fileName) {
        // Declaring and initializing our stack where it will be used specifically for tag matching purposes
        StackInterface<String> tagStack = new ArrayStack<>();
        ArrayList<String> errFile = new ArrayList<>();


        // declaring and initializing our variables
        String temp = "";
        String tag = "";
        String openingTag = "";
        String data = "";
        String songName = "";
        String artistName = "";
        String albumName = "";
        int playCount = 0;
        boolean closingMatch = false;
        boolean endOfSong = false;
        boolean errCheck = false;
        int counter = 0;


        // Where parsing data starts
        try {
            Scanner reader = new Scanner(new File(fileName));

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                errFile.add(counter,line);
                counter++;
                line = line.trim();
                if(line.contains("<song>")){
                    errCheck = false;
                    endOfSong = false;


                }
                if(line.contains("<song>") ){
                    if(!tagStack.isEmpty()) {
                        while (!tagStack.isEmpty()) {
                            tagStack.pop();
                        }
                    }
                }

                int j = line.indexOf('<');
                while (j != -1) {
                    int k = line.indexOf('>', j + 1);
                    if (k == -1) {
                        return false;
                    }
                    tag = line.substring(j, k + 1);

                    // where the arrow-pointer magic happens
                    if (!tag.startsWith("</")) {
                        if(!openingTag.equalsIgnoreCase("") && !openingTag.equalsIgnoreCase("<song>")){
                            if(line.toLowerCase().contains("<title>") || line.toLowerCase().contains("<artist>") || line.toLowerCase().contains("<album>")){
                                errCheck = true;
                                errFile.add(counter-1,line+"      <-----------------");
                                errFile.remove(counter);
                            }
                        }

                        if(openingTag.equals("") || endOfSong!=true) {
                            openingTag = tag;
                            tagStack.push(tag);
                        }

                    } else {

                        if(tag.equalsIgnoreCase("</song>")){


                            if(errCheck==false){
                                errFile.clear();
                            }
                            if(errCheck == true){
                                for(String n : errFile){
                                    errorLog.add(n);
                                }
                                songName = "";
                                artistName = "";
                                albumName = "";
                                playCount = 0;
                                errFile.clear();
                            }
                            endOfSong = true;
                            counter = 0;


                        }
                        temp = tag.replace("/", "");

                        if(!openingTag.equals(temp) && (!tag.equalsIgnoreCase("</song>") && !tag.equalsIgnoreCase("<song>"))){
                            errCheck = true;
                            errFile.add(counter-1,line+"      <-----------------");
                            errFile.remove(counter);
                        }


                        else {
                            if (line.contains(tag) && line.contains(temp)) {
                                data = line.replaceAll(tag, "");
                                data = data.replaceAll(temp, "");
                                if(data.toLowerCase().contains("<artist>") || data.toLowerCase().contains("</artist>")){
                                    errCheck = true;
                                }
                                if(data.toLowerCase().contains("<title>") || data.toLowerCase().contains("</title>")){
                                    errCheck = true;
                                }
                                if(data.toLowerCase().contains("<album>") || data.toLowerCase().contains("</album>")){
                                    errCheck = true;
                                }
                                if(data.toLowerCase().contains("<playcount>") || data.toLowerCase().contains("</playcount>")){
                                    errCheck = true;
                                }
                                if(tag.equalsIgnoreCase("</artist") && errCheck == false){
                                    artistName  = data;
                                }
                                else if(tag.equalsIgnoreCase("</title") && errCheck == false){
                                    songName = data;
                                }
                                else if(tag.equalsIgnoreCase("</album") && errCheck == false){
                                    albumName = data;
                                }
                                else if(tag.equalsIgnoreCase("</playcount") && errCheck == false){
                                    playCount += Integer.parseInt(data);
                                }
                            }
                            else if(line.contains(tag) && !line.contains(openingTag)){

                                data += line.replaceAll(tag,"");
                                if(tag.equalsIgnoreCase("</artist>")){
                                    artistName  = data;
                                }
                                else if(tag.equalsIgnoreCase("</title>")){
                                    songName = data;
                                }
                                else if(tag.equalsIgnoreCase("</album>")){
                                    albumName = data;
                                }
//
                            }


                            if (temp.equals(tagStack.peek())) {

                                if(endOfSong==false){
                                    tagStack.pop();
                                    closingMatch = true;
                                    openingTag = "";
                                }


                                else
                                {
                                    endOfSong = true;
                                    closingMatch = true;
                                    tagStack.pop();

                                }
                            }
                        }

                    }
                    j = line.indexOf('<', k + 1);



                }


                if(closingMatch == false){
                    if(line.length()>tag.length()){
                        data = line.replaceAll(tag, "");
                        data = data.replaceAll(temp, "");
                    }
                    else if(!line.contains(openingTag)){
                        data = line;
                    }
                }


                if(data.length()>0){
                    data = data.trim();
                }

                if(closingMatch == true && errCheck == false){

                    if(tag.equals("</artist>") && !data.equals("")){
                        artistName = data;
                        data = "";
                    }
                    else if(tag.equals("</album>")&&!data.equals("")){
                        albumName = data;
                        data = "";
                    }
                    else if(tag.equals("</title>")&&!data.equals("")){
                        songName = data;
                        data = "";
                    }
                    else if(tag.equals("</playcount>")&&!data.equals("")){
                            if(playCount>0){
                                playCount += Integer.parseInt(data);;
                            }
                            else if(playCount == 0){
                                playCount = Integer.parseInt(data);
                            }
                            data = "";
                    }


                    closingMatch = false;
                }
                if(endOfSong==true && errCheck==false ){
                    songCollection.add(new MySong(songName, artistName, albumName,playCount));
                    endOfSong = false;
                    playCount =0;

                }


            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("Number of valid Song objects Created: "+songCollection.size());
        System.out.println();
        // Outputs correctly parsed Song objects
        for(Song a : songCollection){
            System.out.println(a.toString());
            System.out.println();
        }


        return tagStack.isEmpty();
    }
    public static MySong[] toArray(){
        MySong[] songs = new MySong[songCollection.size()];
        for(int i = 0;i<songs.length;i++){
            songs[i] = songCollection.get(i);
        }
        return songs;
    }
}
/**
 *                                                   Arrow-pointer Algorithm
 *
 *    Create two array-lists, a main one where errorLog data will be stored and a temporary one that will clear itself each time we
 *    read data between a single song , so <song> some data </song> --> tempArrLst.clear() , <song> some data</song> --> tempArrLst.clear(),
 *    in addition to that, create a counter variable to use as an index when adding to an arraylist so , tmpArrLst.add(counter,data), and that
 *    counter increments itself each time we read a line.
 *
 *    Before having the tempArrLst clear itself, a boolean variable, errCheck, is used to see if there was an error, if there was, then we do
 *    tmpArrLst.add(counter,"line + <-----------------"), will add the arrow at the exact position of the original error line.
 *    tmpArrLst.remove(line) to get rid of the original error line without the arrow-pointer.
 *
 *    when we reach the end of a single song , so if tag = </song>, we implement an enhanced for loop through the tmpArrLst
 *    and add each line to the mainArrLst, and after the loop is done, set counter = 0 and tmpArrLst.clear() is called so we can read
 *    the next <song> data </song> in the input file.
 *
 *
 */
