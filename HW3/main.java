/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 3                                    */
/* main class: this tests the timing of the diffrent sorting   */
/*             algs                                            */
/***************************************************************/

import java.util.*;
import java.io.*;//used to read files

public class main{

   /***************************************************************/
   /* Method: main                                                */
   /* Purpose: Used to find anagrams in text files of words       */
   /* Parameters:                                                 */
   /*    args[0]: used to give the file location                  */
   /* Returns: Void: Output to the console only                   */
   /***************************************************************/
	public static void main(String[] args) {
      
      if(args.length < 1){ 
         System.out.println("File location must be specified");
         return;
      }
      
      QuickSort strQckSort = new QuickSort();
      Map<String, ArrayList<String>> wordMap = new HashMap<>();
      ArrayList<String> value;
      List<String> anagrams;
      String[] anagramsArray;
      String[] words = readWords(args[0]);
      String key;
      
      for(int itter = 0; itter < words.length; itter++){
         //The key used for the hash table is the string in sorted order
         //anagrams have the same keys as each other as when they are sorted they match
         key = strQckSort.quickSortStr(words[itter].toLowerCase());
         
         //When keys match, it puts the word in the list that the key pulls up
         if((anagrams = wordMap.get(key)) != null){
            anagrams.add(words[itter]);
         }else{
            wordMap.put(key, new ArrayList<>(Arrays.asList(words[itter])));
         }
      }
      
      Iterator iterator = wordMap.keySet().iterator();

      //This goes through all lists in the has map and prints out the results
      while (iterator.hasNext()) {
         key = iterator.next().toString();
         value = wordMap.get(key);
         anagramsArray = value.toArray(new String[value.size()]);
         
         if(anagramsArray.length > 1)
            System.out.println(key + ": " + explodeArrayStr(anagramsArray, ", "));
      }
   }
   
   /***************************************************************/
   /* Method: explodeArrayStr                                     */
   /* Purpose: creates string from all elements in an array       */
   /* Parameters:                                                 */
   /* String[] inputArr: the array to turn into an string         */
   /* String del: the string to put between each element          */
   /* Returns: String: the string produced from the array         */
   /***************************************************************/
   public static String explodeArrayStr(String[] inputArr, String del){
      String explodeStr = "";
      
      for(int itter = 0; itter < inputArr.length; itter++){
         if(inputArr[itter] == null)
            break;
         
         //add deliminator to end of string being made
         explodeStr += inputArr[itter] + del;   
      }  
      
      //remove last deliminator for sake of looking nice
      explodeStr = explodeStr.substring(0, explodeStr.length()- del.length());;
      
      return explodeStr;
   }
   
   /***************************************************************/
   /* Method: readWords                                           */
   /* Purpose: Reads all words from an text file and outputs them */
   /*          them in an array of strings                        */   
   /* Parameters:                                                 */
   /* String fileLocation: the location of the file to read from  */
   /* Returns: String[]: the array of words in given file         */
   /***************************************************************/
   public static String[] readWords(String fileLocation){
      List<String> words = new ArrayList<String>();
      
		try {
			File file = new File(fileLocation);
			FileReader fileReader = new FileReader(file);
         
			BufferedReader bufferedReader = new BufferedReader(fileReader);
         
         //Goes through all line items and adds them to an arrayList
			String line;
			while ((line = bufferedReader.readLine()) != null)
            words.add(line);
         
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
      
      //Takes list array and outputs an array of words
      return words.toArray(new String[words.size()]);
   }
}