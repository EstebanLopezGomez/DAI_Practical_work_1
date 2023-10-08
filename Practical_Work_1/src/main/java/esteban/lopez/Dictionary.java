package esteban.lopez;

import java.util.Vector;

public class Dictionary {
    Vector<Word> wordsInDictionary= new Vector<Word>();

    public boolean addToDictionary(Word wordToAdd){

        int index = wordAlreadyIndictionary(wordToAdd);

        if(index<0){

            wordsInDictionary.add(wordToAdd);

        }
        else
        {
            wordsInDictionary.get(index).addOccurence();
        }

        return false;
    }

    public int wordAlreadyIndictionary(Word wordToTest){
        if(wordsInDictionary.isEmpty())return -1;

        for(Word wordInDict : wordsInDictionary){
            if(wordInDict.getText().equals(wordToTest.getText())){
                return wordsInDictionary.indexOf(wordInDict);
            }
        }
        return -1;
    }

    private void sortdictionary(String sortingType){

       Vector<Word> sorteddictionary= new Vector<Word>(wordsInDictionary.size());

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
           sorteddictionary.add(wordToSort);
           wordsInDictionary.remove(wordToSort);
       }

        wordsInDictionary = sorteddictionary;
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

