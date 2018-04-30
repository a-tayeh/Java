/**
 *          Ali Tayeh               Project_5          CMSC-256-001        Spring-2018
 *
 *  MusicManager is responsible for reading command line args and decide, based on command number, to show songs by an artist,
 *  display top 10 songs or determine whether an artist is in the playlist or not!
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class MusicManager {
    public static void main(String [] args){
        printHeading();
        try{
            SongReader reader = new SongReader();
            reader.parseSongData(args[0]);
            MySong[] arr = reader.toArray();
            int[] n = new int[reader.toArray().length];
            ArrayList <Integer> playCounts = new ArrayList<>();
            ArrayList<String> top10Songs = new ArrayList<>();
            if(args[1].equalsIgnoreCase("1")) {

                for (int i = 0; i < arr.length; i++) {
                    playCounts.add(arr[i].getPlayCount());
                }
                int index = n.length;
                int j = 0;

                for (int i = 0; i < arr.length; i++) {

                    if (arr[i].getPlayCount() == playCounts.get(i)) {
                        top10Songs.add(playCounts.get(i) + " " + arr[i].getTitle());
                        index--;
                        j++;
                    }

                }

                Collections.sort(top10Songs, new Comparator<String>() {
                    public int compare(String playCount, String title) {
                        return parseData(playCount) - parseData(title);
                    }

                    private int parseData(String str) {
                        return Integer.parseInt(str.split(" ")[0]);
                    }
                });
                Collections.reverse(top10Songs);
                int counter = 1;
                System.out.println("**** Songs are displayed by a play count number followed by song title ****");
                System.out.println();
                for (String a : top10Songs) {
                    if (counter != 11) {
                        System.out.println("#" + counter + ". " + a);
                        counter++;
                    }
                }
            }



           else if(args[1].equalsIgnoreCase("2")) {
                Arrays.sort(arr);
                ArrayList<MySong> songsArr=  new ArrayList<>();
                songsArr= reader.toArrlst();
                Collections.sort(songsArr);
                int msg = 0;
                int index = 1;
                boolean found = false;
                for (MySong a : songsArr) {

                    if (a.getArtist().equalsIgnoreCase(args[2].replaceAll("\"",""))) {
                        if(msg==0){
                            System.out.println("The artist, "+ args[2]+", is in the playlist!");
                            System.out.println("--------------------------------------");
                            System.out.println("Songs by the artist");
                            System.out.println();
                            msg = -1;
                        }
                        System.out.println(index+". "+a.getTitle());
                        found = true;
                        index++;
                    }
                }
                System.out.println();


                if(!found){
                    System.out.println("Artist isn't on this list");
                }
            }

            else if(args[1].equalsIgnoreCase("3")){

                Arrays.sort(arr);
                ArrayList<MySong> songsArr=  new ArrayList<>();
                songsArr= reader.toArrlst();
                int t = 0;

                Collections.sort(songsArr);
                boolean found = false;
                String duplicateAlbum = "";

                found =false;
                for(Song a : songsArr){
                    if(a.getArtist().equalsIgnoreCase(args[2]) && found == false){
                        found = true;
                        break;
                    }
                }

                if(found==true){
                    System.out.println("Artist, "+args[2]+", is in the playlist!");
                    System.out.println();
                    System.out.println("--------------------------------------------------");
                    found = false;
                    for(Song a : songsArr){
                        if(a.getArtist().equalsIgnoreCase(args[2]) && !a.getAlbum().equalsIgnoreCase(duplicateAlbum)){
                                System.out.print("Album: " + a.getAlbum() + "\n");
                                duplicateAlbum = a.getAlbum();
                                System.out.println();


                                for(int i = 0;i<songsArr.size();i++){
                                    if(songsArr.get(i).getAlbum().equalsIgnoreCase(a.getAlbum())){
                                        System.out.print("         Title: ");
                                        System.out.print(songsArr.get(i).getTitle()+"\n");
                                        t = -1;


                                    }
                                }
                                t = 0;
                                System.out.println("--------------------------------------------------");

                        }

                    }
                }
                else{
                    System.out.println("Artist, "+args[2]+ ", is not in the playlist!");
                }





            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }








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
}
