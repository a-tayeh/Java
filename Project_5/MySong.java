public class MySong extends Song{
    private int playCount;
    private Object obj;

    public MySong(int playCount) {
        this.playCount = playCount;
    }

    public MySong(String title, String artist, String album, int playCount) {
        super(title, artist, album);
        this.playCount = playCount;

    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        if(playCount>0) {
            this.playCount = playCount;
        }
        else{
            throw new IllegalArgumentException("Play Count can't be negative");
        }
    }




    @Override
    public String toString() {
        return String.format("%s \n Play Count: %d",super.toString(),this.playCount);
    }
}
