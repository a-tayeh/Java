import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SongReader {
    public static void main(String [] args){
        System.out.println(matchedTags("playlist(1).data"));
    }

    public static boolean matchedTags(String fileName) {
        StackInterface<String> tagStack = new ArrayStack<>();
        ArrayList<String> errFile = new ArrayList<>();
        ArrayList<Song> songCollection = new ArrayList<>();
        String temp = "";
        String tag = "";
        String openingTag = "";
        String data = "";
        String songName = "";
        String artistName = "";
        String albumName = "";
        boolean closingMatch = false;
        boolean endOfSong = false;
        boolean errCheck = false;


        try {
            Scanner reader = new Scanner(new File(fileName));

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                errFile.add(line);
                line = line.trim();
//                if(line.contains("<song>")){endOfSong = false;}
//                if(line.contains("<song>") && line.length()==("<song>").length()){
//                    tagStack.push(line);
//                    line = reader.nextLine();
//                }
                if(line.contains("<song>")){
                    if(!tagStack.isEmpty()) {
                        while (!tagStack.isEmpty()) {
                            tagStack.pop();

                        }

                    }

                    System.out.println();
                    System.out.println("New Song");
                    System.out.println("******************");


                }

                int j = line.indexOf('<');
                while (j != -1) {
                    int k = line.indexOf('>', j + 1);
                    if (k == -1) {
                        return false;
                    }
                     tag = line.substring(j, k + 1);

                    if (!tag.startsWith("</")) {
                        if(openingTag.equals("") || endOfSong!=true) {
                            openingTag = tag;
                            tagStack.push(tag);
                        }
                        else {
                            if(endOfSong==true) {
                                if (tag.equalsIgnoreCase("<artist>") || tag.equalsIgnoreCase("</artist>")) {
                                    System.out.println("This is an error");
                                    System.out.println(openingTag + " " + tag);
                                    System.out.println(reader.nextLine());


                                }
                            }
                        }
                    } else {
                        if(tag.equalsIgnoreCase("</song>")){
                            if(errCheck == true){
                                System.out.println("\n\n\n");
                                System.out.println("error file");
                                System.out.println("***************");
                                for(String n : errFile){
                                    System.out.println(n);
                                }
                                System.out.println("End of Error File");
                                System.out.println("***************");
                                errCheck = false;
                                errFile.clear();
                            }
                        }
                        temp = tag.replace("/", "");
                        if(!openingTag.equals(temp) && (!tag.equalsIgnoreCase("</song>") && !tag.equalsIgnoreCase("<song>"))){
                            System.out.println("This is an error!");
                            errCheck = true;
//                            if((line.contains(openingTag) && line.contains(tag)) || !line.contains("</song>")) {
//                                System.out.println(line);
//                            }
//                            else if(line.contains(tag)&&!line.contains(openingTag)){
//                                if(line.length()>tag.length()) {
//                                    data = line.replaceAll(tag,"");
//                                    System.out.println(openingTag + " " + data + " " + tag);
//                                }
//                                else{
//                                    System.out.println(openingTag+" "+ data + " "+tag);
//                                }
//
//                            }
                        }
                        else {
                            if (line.contains(tag) && line.contains(temp)) {
                                data = line.replaceAll(tag, "");
                                data = data.replaceAll(temp, "");
//                                System.out.println(temp.replaceAll("<|>", "") + ": " + data.trim());

                            }
                            else if(line.contains(tag) && !line.contains(openingTag)){

                                data += line.replaceAll(tag,"");
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




//                            System.out.println(data);
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
                            System.out.println("artist: "+artistName);
                            data = "";
                        }
                        else if(tag.equals("</album>")&&!data.equals("")){
                            albumName = data;
                            System.out.println("albums: "+albumName);
                            data = "";
                        }
                        else if(tag.equals("</title>")&&!data.equals("")){
                            songName = data;
                            System.out.println("title: "+songName);
                            data = "";
                        }

                        songCollection.add(new Song(songName,artistName,albumName));
                        closingMatch = false;
                    }






        }

    }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return tagStack.isEmpty();
    }
}
