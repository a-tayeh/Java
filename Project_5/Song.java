/**
 *          Ali Tayeh               Project_5           CMSC-256-001        Spring-2018
 *
 *  Song class implements the Comparable interface and overrides the compareTo, equals and toString from
 *  super class Object
 */

public class Song implements Comparable<Song>{
    // our 3 instance variables
    private String title;
    private String artist;
    private String album;

    public Song(){
        this.title = "";
        this.artist = "";
        this.album = "";
    }

    public Song(String title, String artist, String album){
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title!=null) {
            this.title = title;
        }
        else{
            throw new IllegalArgumentException("Title can't be null");
        }
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if(artist!=null) {
            this.artist = artist;
        }
        else{
            throw new IllegalArgumentException("Artist can't be null");
        }

    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        if(album!=null) {
            this.album = album;
        }

        else{
            throw new IllegalArgumentException("Album can't be null");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            throw new IllegalArgumentException("Passed in object can't be null");
        }
        if(!(obj instanceof Song)){
            return false;
        }
        if(this.getTitle().equalsIgnoreCase(((Song) obj).getTitle()) &&
                this.getArtist().equalsIgnoreCase(((Song) obj).getArtist())&& ((Song) obj).getAlbum().equalsIgnoreCase(((Song) obj).getAlbum())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(" Title: %s \n Artist: %s \n Album: %s", getTitle(),getArtist(),getAlbum());
    }

    @Override
    public int compareTo(Song o) {
        if(!(o instanceof Song) || o==null){
            throw new IllegalArgumentException("Object is not an instance of Song object or it's null");
        }

        else if(this.equals(o)){
            return 0;
        }
        else if(this.getArtist().compareTo(o.getArtist())<0){
            return -1;
        }
        else if(this.getArtist().compareTo(o.getArtist())>0){
            return 1;
        }
        else if(this.getArtist().compareTo(o.getArtist())==0){
            if(this.getAlbum().compareTo(o.getAlbum())<0){
                return -1;
            }
            else if(this.getAlbum().compareTo(o.getAlbum())>0){
                return 1;
            }
            else if(this.getAlbum().compareTo(o.getAlbum())==0){
                if(this.getTitle().compareTo(o.getTitle())<0){
                    return -1;
                }
                else if(this.getTitle().compareTo(o.getTitle())>0){
                    return 1;
                }
            }
        }
        return 2;

    }
}
