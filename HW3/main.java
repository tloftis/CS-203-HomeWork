/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 2                                    */
/* main class: this tests the timing of the diffrent sorting   */
/*             algs                                            */
/***************************************************************/

import java.util.*;//used for mostly for Random
import java.io.*;//used to read files

public class main{

   /***************************************************************/
   /* Method: main                                                */
   /* Purpose: this tests the timing of the diffrent sort methods */
   /* Parameters:                                                 */
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
         key = strQckSort.quickSortStr(words[itter].toLowerCase());
         
         if((anagrams = wordMap.get(key)) != null){
            anagrams.add(words[itter]);
         }else{
            wordMap.put(key, new ArrayList<>(Arrays.asList(words[itter])));
         }
      }
      
      Iterator iterator = wordMap.keySet().iterator();

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
   /* String[] inputArr: the array to turn into an array          */
   /* String del: the string to put between each element          */
   /* Returns: String: the string produced from the array         */
   /***************************************************************/
   public static String explodeArrayStr(String[] inputArr, String del){
      String explodeStr = "";
      
      for(int itter = 0; itter < inputArr.length; itter++){
         if(inputArr[itter] == null)
            break;
         
         explodeStr += inputArr[itter] + del;   
      }  
      
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
         
			String line;
			while ((line = bufferedReader.readLine()) != null)
            words.add(line);
         
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
      
      return words.toArray(new String[words.size()]);
   }
}