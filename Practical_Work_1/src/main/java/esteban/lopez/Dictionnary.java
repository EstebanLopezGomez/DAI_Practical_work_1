package esteban.lopez;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class Dictionnary {
    Vector<Word> wordsInDictionnary= new Vector<Word>();

    public boolean addToDictionnary(Word wordToAdd){

        int index = wordAlreadyInDictionnary(wordToAdd);

        if(index<0){

            wordsInDictionnary.add(wordToAdd);
            sortDictionnary();
        }
        else
        {
            wordsInDictionnary.get(index).addOccurence();
        }

        return false;
    }

    public int wordAlreadyInDictionnary(Word wordToTest){
        if(wordsInDictionnary.isEmpty())return -1;

        for(Word wordInDict : wordsInDictionnary){
            if(wordInDict.getText().equals(wordToTest.getText())){
                return wordsInDictionnary.indexOf(wordInDict);
            }
        }
        return -1;

    }

    private void sortDictionnary(){

       Vector<Word> sortedDictionnary= new Vector<Word>(wordsInDictionnary.size());

       while(!wordsInDictionnary.isEmpty()){
           Word wordToSort = wordsInDictionnary.get(0);
           for(int counter=1;counter < wordsInDictionnary.size();counter++){
                int comparator = wordToSort.getText().compareTo(wordsInDictionnary.get(counter).getText());

                if(comparator > 0){
                    wordToSort = wordsInDictionnary.get(counter);
                }
           }
           sortedDictionnary.add(wordToSort);
           wordsInDictionnary.remove(wordToSort);
       }

       wordsInDictionnary = sortedDictionnary;
    }

    public String showDictionnary(){
        StringBuilder strWithWords = new StringBuilder();
        for(Word word : wordsInDictionnary){
            strWithWords.append(word.getText()).append(" avec ").append(word.getOccurences()).append(" occurence/s dans le fichier\n");
        }

        return strWithWords.toString();
    }

}

