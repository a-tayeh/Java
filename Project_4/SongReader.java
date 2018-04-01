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
        int counter = 0;



        try {
            Scanner reader = new Scanner(new File(fileName));

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                errFile.add(counter,line);
                counter++;
                line = line.trim();
//                if(line.contains("<song>")){endOfSong = false;}
//                if(line.contains("<song>") && line.length()==("<song>").length()){
//                    tagStack.push(line);
//                    line = reader.nextLine();
//                }
                if(line.contains("<song>") && line.length() == ("<song>").length()){
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

                            if(errCheck==false){
                                errFile.clear();
                            }
                            if(errCheck == true){
                                System.out.println("\n\n\n");
                                System.out.println("error file");
                                System.out.println("***************");
                                System.out.println();
                                for(String n : errFile){
                                    System.out.println(n);
                                }
                                System.out.println();
                                System.out.println("End of Error File");
                                System.out.println("***************");
                                errCheck = false;
                                albumName = "";
                                artistName = "";
                                songName = "";
                                errFile.clear();
                            }
                            endOfSong = true;
                            counter = 0;

                        }
                        temp = tag.replace("/", "");

                        if(!openingTag.equals(temp) && (!tag.equalsIgnoreCase("</song>") && !tag.equalsIgnoreCase("<song>"))){
//                            System.out.println("This is an error!");
                            errCheck = true;
                            errFile.add(counter-1,line+"      <-----------------");
                            errFile.remove(counter);
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
                                if(data.toLowerCase().contains("<artist>") || data.toLowerCase().contains("</artist>")){
                                    errCheck = true;
                                }
                                if(data.toLowerCase().contains("<title>") || data.toLowerCase().contains("</title>")){
                                    errCheck = true;
                                }
                                if(data.toLowerCase().contains("<album>") || data.toLowerCase().contains("</album>")){
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
//                                System.out.println(temp.replaceAll("<|>", "") + ": " + data.trim());

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
//                            System.out.println("artist: "+artistName);
                            data = "";
                        }
                        else if(tag.equals("</album>")&&!data.equals("")){
                            albumName = data;
//                            System.out.println("albums: "+albumName);
                            data = "";
                        }
                        else if(tag.equals("</title>")&&!data.equals("")){
                            songName = data;
//                            System.out.println("title: "+songName);
                            data = "";
                        }


                        closingMatch = false;
                    }
//                System.out.println(endOfSong);
                if(endOfSong==true && errCheck==false){
                    if(songName.length()!=0 && artistName.length()!=0&& albumName.length()!=0) {
                        songCollection.add(new Song(songName, artistName, albumName));
//                        endOfSong = false;
                    }
                    endOfSong = false;
                }









        }

    }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("Number of valid Song objects Created: "+songCollection.size());
        System.out.println();
        for(Song a : songCollection){
            System.out.println("Artist: "+ a.getArtist()+" Album: "+a.getAlbum()+" Title: "+ a.getTitle());
        }


        return tagStack.isEmpty();
    }
}
