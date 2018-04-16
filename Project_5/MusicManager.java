import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MusicManager {
    public static void main(String [] args){
        SongReader reader = new SongReader();
        int index = 0;

        reader.parseSongData("playlist(1).data");
        MySong[] arr = reader.toArray();
        int[] n = new int[reader.toArray().length];
        ArrayList <MySong> m = new ArrayList<>();
        ArrayList<String> top10Songs = new ArrayList<>();
        ArrayList<String> albums = new ArrayList<>();
        for(int i = 0;i<n.length;i++){
            n[i] =arr[i].getPlayCount();
        }
        Arrays.sort(n);
        int index2 = n.length;
            for (int i = 0; i < n.length ; i++) {
                for (int j = 0; j <arr.length ; j++) {
                    if (arr[j].getPlayCount() == n[i]) {
                        top10Songs.add(arr[j].getTitle()+"  ("+ n[i]+" time(s))");
                        index2--;
                    }

                }
                index++;

            }
            Collections.reverse(top10Songs);
            int counter = 1;
            for(String a : top10Songs){
                if(counter!=11) {
                    System.out.println("#"+counter+". "+a);
                    counter++;
                }
            }
        System.out.println();
        System.out.println();
        String artist = "The Weeknd";
        Arrays.sort(arr);
        for(Song a : arr){

            if(a.getArtist().equalsIgnoreCase(artist)){
                System.out.println(a.getTitle());
            }
        }







    }
}
