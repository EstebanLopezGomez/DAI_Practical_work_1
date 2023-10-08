package esteban.lopez;

import java.util.Vector;

/**
 * Class Dictionary that allows us to have a list of Words and manipulate that list
 */
public class Dictionary {

    //List of Words that are on the dictionary
    Vector<Word> wordsInDictionary= new Vector<Word>();

    /**
     * Adds a word to dictionary, if that word is already in the dictionary, it adds an occurence
     * to it
     * @param wordToAdd Word that may be added to the dictionary
     */
    public void addToDictionary(Word wordToAdd){

        //gets the index of the word in the dictionary
        int index = wordAlreadyIndictionary(wordToAdd);

        if(index<0){
            wordsInDictionary.add(wordToAdd);
        }
        else{
            wordsInDictionary.get(index).addOccurence();
        }
    }

    /**
     * Tests if the word is already in the dictionary
     * @param wordToTest Word that has to be compared with the words in the dictionary
     * @return the index of the word in the dictionary or -1 if the word isn't in the dictionary
     */
    public int wordAlreadyIndictionary(Word wordToTest){
        //If the dictionary is empty there's no word to test
        if(wordsInDictionary.isEmpty())return -1;

        //For each word in the dictionary tests if it has the same text that the word we want to add
        for(Word wordInDict : wordsInDictionary){
            if(wordInDict.getText().equals(wordToTest.getText())){
                return wordsInDictionary.indexOf(wordInDict);
            }
        }
        return -1;
    }

    /**
     * Sorts the dictionary by the sorting type chosen by the usr
     * @param sortingType Sorting to do on dictionary (Alphabetical or Occurrences)
     */
    private void sortdictionary(String sortingType){

        //Dictionary where the sorted words are stored
        Vector<Word> sortedDictionary= new Vector<Word>(wordsInDictionary.size());

        while(!wordsInDictionary.isEmpty()){
           Word wordToSort = wordsInDictionary.get(0);
           for(int counter=1;counter < wordsInDictionary.size();counter++){

               switch (sortingType){
                   case "Alphabetical":
                       int comparator = wordToSort.getText().compareTo(wordsInDictionary.get(counter).getText());

                       if(comparator > 0){
                           wordToSort = wordsInDictionary.get(counter);
                       }
                       break;

                   case "Occurrences":
                       if(wordToSort.getOccurences()<wordsInDictionary.get(counter).getOccurences()){
                           wordToSort=wordsInDictionary.get(counter);
                       }
                       break;
               }

           }
           sortedDictionary.add(wordToSort);
           wordsInDictionary.remove(wordToSort);
        }

        wordsInDictionary = sortedDictionary;
    }

    public String showDictionary(String sortingType){
        sortdictionary(sortingType);

        StringBuilder strWithWords = new StringBuilder();
        for(Word word : wordsInDictionary){
            strWithWords.append(word.show());
        }
        return strWithWords.toString();
    }

}

