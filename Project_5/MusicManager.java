import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class MusicManager {
    public static void main(String [] args){
        SongReader reader = new SongReader();
        reader.parseSongData(args[0]);
        MySong[] arr = reader.toArray();
        int[] n = new int[reader.toArray().length];
        ArrayList <Integer> me = new ArrayList<>();
        ArrayList<String> top10Songs = new ArrayList<>();
        ArrayList<String> albums = new ArrayList<>();
        if(args[1].equalsIgnoreCase("1")) {
            for (int i = 0; i < n.length; i++) {
                me.add(arr[i].getPlayCount());
            }
            Arrays.sort(n);
            int index = n.length;
            int j = 0;

            boolean p = false;
            for (int i = 0; i < arr.length; i++) {

                if (arr[i].getPlayCount() == me.get(i)) {
                    top10Songs.add(me.get(i) + " " + arr[i].getTitle());
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
            for (String a : top10Songs) {
                if (counter != 11) {
                    System.out.println("#" + counter + ". " + a);
                    counter++;
                }
            }
        }


         if(args[1].equalsIgnoreCase("2")) {
             Arrays.sort(arr);
             int msg = 0;
             int index = 1;
             boolean found = false;
             for (Song a : arr) {

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







    }
}
