# DAI_Practical_work_1
## Usage of the CLI
This programm was developped for the DAI course at the HEIG-VD. It allows the user to modify the text of a file and save it to another file.
The CLI is coded with Java (allowing it to be executed by any OS) with the [Picocli](https://picocli.info) package and Maven as software manager.

## Building the CLI
As already mentioned before, this program has been coded in Java. Once the project is downloaded, you shall use the commands : 
```mvn package``` followed by : ```java -jar target/Practical_Work_1-1.0-SNAPSHOT.jar``` to run the CLI.

## How to use the CLI
The CLI comes with a help option implemented but here's more information about the parameters of the CLI : 
* -i / --inputFile : Parameter where you need to specify the file (with the path) from where you want to extract the text. This is a parameter that is required for the CLI to work, without it you can't use the program.
  * -ie / --inputEncoding : Specifies the encoding of the input file. By default this value has the UTF-8 encoding. This parameter is not required for the execution of the CLI.

*  -o / --outputFile : Parameter that specifies the output file where the converted text will be written. As the inputFile, it's also a required parameter.
  * -oe / --outputEncoding : Specifies the encoding of the output file, having also by default the UTF-8 encoding.

* uppercase : One of the main options of the CLI. When chosen as option, it converts the input file's text to uppercase.
* lowercase : One of the main options of the CLI. As for the uppercase, it converts the input file's text to lowercase.
* dictionary : Last of the main options of the CLI. It allows to read the input file and make a sort of dictionary of the words in the file and to count how many occurrences there are.
  * .ds / --dictionarySorting : Sets the sorting method for the output dictionary. You may choose between Alphabetical or by number of occurrences

* -h / --help : Shows the help text for the CLI
 
## Exemples
* * Dictionnary sorted by number of occurences : `java -jar target/Practical_Work_1-1.0-SNAPSHOT.jar --inputFile=D:\Documents\HEIG\SEM3\DAI\Projects\Labos\DAI_Practical_work_1\Practical_Work_1\src\test\les_miserables.txt --outputFile=D:\Documents\HEIG\SEM3\DAI\Projects\Labos\DAI_Practical_work_1\Practical_Work_1\src\test\les_miserables_OUTPUT.txt dictionary -ds=Occurrences`
  * Result file : [les_miserables_Occurrences.txt](https://github.com/EstebanLopezGomez/DAI_Practical_work_1/blob/main/Practical_Work_1/src/test/les_miserables_OUTPUT.txt) .

* * Dictionnary sorted alphabetically : `java -jar target/Practical_Work_1-1.0-SNAPSHOT.jar --inputFile=D:\Documents\HEIG\SEM3\DAI\Projects\Labos\DAI_Practical_work_1\Practical_Work_1\src\test\les_miserables.txt --outputFile=D:\Documents\HEIG\SEM3\DAI\Projects\Labos\DAI_Practical_work_1\Practical_Work_1\src\test\les_miserables_Alphabetically_OUTPUT.txt dictionary -ds=Alphabetical`
  * Result file : [les_miserables_Alphabetical.txt](https://github.com/EstebanLopezGomez/DAI_Practical_work_1/blob/main/Practical_Work_1/src/test/les_miserables_Alphabetically_OUTPUT.txt) .
  
* File to uppercase with bad output encoding (US-ASCII) : `java -jar target/Practical_Work_1-1.0-SNAPSHOT.jar --inputFile=D:\Documents\HEIG\SEM3\DAI\Projects\Labos\DAI_Practical_work_1\Practical_Work_1\src\test\test_encoding.txt --outputFile=D:\Documents\HEIG\SEM3\DAI\Projects\Labos\DAI_Practical_work_1\Practical_Work_1\src\test\bad_econding_OUTPUT.txt -oe=US-ASCII uppercase`
  * Result file : [bad_encoding_OUTPUT.txt](https://github.com/EstebanLopezGomez/DAI_Practical_work_1/blob/main/Practical_Work_1/src/test/bad_econding_OUTPUT.txt) .
  * Same but with good encoding (UTF-8) : [good_encoding_OUTPUT.txt](https://github.com/EstebanLopezGomez/DAI_Practical_work_1/blob/main/Practical_Work_1/src/test/good_econding_OUTPUT.txt) .
