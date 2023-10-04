package esteban.lopez;


public class Word {
    private String  text;
    private int occurences=1;


    public Word(String textToAdd){
        text = textToAdd;
    }
    public String getText(){
        return text;
    }

    public  int getOccurences(){
        return occurences;
    }

    public void addOccurence(){occurences++;}

    public void show(){
        System.out.println("text : "+text+" - Occurences : "+occurences);
    }


}
