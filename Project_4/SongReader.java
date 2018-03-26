import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SongReader {
    public static void main(String [] args){
        System.out.println(matchedTags("playlist(1).data"));
    }

    public static boolean matchedTags(String fileName) {
        StackInterface<String> tagStack = new ArrayStack<>();
        String temp = "";
        String tag = "";
        String openingTag = "";
        String data = "";
        boolean closingMatch = false;
        boolean onLine = false;

        try {
            Scanner reader = new Scanner(new File(fileName));

            while (reader.hasNextLine()){
                String line = reader.nextLine().trim();
                if(line.contains("<song>")|| line.contains("</song>")){
                    line = line.replaceAll("<song>,</song>","");
                }
                int j = line.indexOf('<');
                while (j != -1) {
                    int k = line.indexOf('>', j + 1);
                    if (k == -1) {
                        return false;
                    }
                     tag = line.substring(j, k + 1);

                    if (!tag.startsWith("</")) {
                         openingTag = tag;
                         tagStack.push(tag);
                    } else {
                        temp = tag.replace("/", "");
                        if(!openingTag.equals(temp) && !tag.equalsIgnoreCase("</song>")){
                            System.out.println("This is an error!");
                            if(line.contains(openingTag) && line.contains(tag)) {
                                System.out.println(line);
                            }
                            else if(line.contains(tag)&&!line.contains(openingTag)){
                                if(line.length()>tag.length()) {
                                    data = line.replaceAll(tag,"");
                                    System.out.println(openingTag + " " + data + " " + tag);
                                }
                                else{
                                    System.out.println(openingTag+" "+ data + " "+tag);
                                }

                            }
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
                                closingMatch = true;
                                tagStack.pop();

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

                    if(closingMatch == true){

                        if(tag.equals("</artist>") && !data.equals("")){
                            System.out.println("artist: "+data);
                            data = "";
                        }
                        else if(tag.equals("</album>")&&!data.equals("")){
                            System.out.println("albums: "+data);
                            data = "";
                        }
                        else if(tag.equals("</title>")&&!data.equals("")){
                            System.out.println("title: "+data);
                            data = "";
                        }

                        closingMatch = false;
                    }






        }

    }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return tagStack.isEmpty();
    }
}
