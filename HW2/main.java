/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 2                                    */
/* main class: this tests the timing of the diffrent sorting   */
/*             algs                                            */
/***************************************************************/

import java.util.*;//used for mostly for Random

public class main{

   /***************************************************************/
   /* Method: main                                                */
   /* Purpose: this tests the timing of the diffrent sort methods */
   /* Parameters:                                                 */
   /* Returns: Void: Output to the console only                   */
   /***************************************************************/
	public static void main(String[] args) {
      //the values of n to test the speed of the algs with
      int[] valuesOfN = new int[] {100, 1000, 10000, 20000, 30000, 40000, 50000};
      
      //the array that data will be stored in to be turned into a table
      String[][] valueTable = new String[10][valuesOfN.length + 1];
      
      int cellLength = 18; // the max char length each cell can be
      
      //data to "header" the rows of the table
      valueTable[0][0] = "n = ";
      valueTable[1][0] = "insertionSort";
      valueTable[4][0] = "MergeSort";
      valueTable[7][0] = "QuickSort";
      
      int[] sortMergeArr = new int[0]; 
      int[] sortQuickArr = new int[0];
      int[] sortInsertArr = new int[0];;
      
      MergeSort sortMerge = new MergeSort(sortMergeArr);
      QuickSort sortQuick = new QuickSort(sortQuickArr);
      InsertionSort sortInsert = new InsertionSort(sortInsertArr);
      
      for(int itter = 0; itter < valuesOfN.length; itter++){
         sortMergeArr = randomArrIntGen(valuesOfN[itter], 1, 100);
         sortQuickArr = copyArr(sortMergeArr);
         sortInsertArr = copyArr(sortMergeArr);
      
         sortMerge.setArray(sortMergeArr);
         sortQuick.setArray(sortQuickArr);
         sortInsert.setArray(sortInsertArr);
         
         valueTable[0][itter + 1] = valuesOfN[itter] + "";
         valueTable[3][itter + 1] = "time: " + sortInsert.timeAlg();
         valueTable[6][itter + 1] = "time: " + sortMerge.timeAlg();
         valueTable[9][itter + 1] = "time: " + sortQuick.timeAlg();
         
         valueTable[2][itter + 1] = "swaps: " + sortInsert.getSwapCount();
         valueTable[5][itter + 1] = "swaps: " + sortMerge.getSwapCount();
         valueTable[8][itter + 1] = "swaps: " + sortQuick.getSwapCount();
         
         valueTable[1][itter + 1] = "comps: " + sortInsert.getCompCount();
         valueTable[4][itter + 1] = "comps: " + sortMerge.getCompCount();
         valueTable[7][itter + 1] = "comps: " + sortQuick.getCompCount();
      }
      
      tableGen(valueTable, cellLength);
   }
   
   /***************************************************************/
   /* Method: tableGen                                            */
   /* Purpose: Homegrown quick and dirty text table generator     */
   /* Parameters:                                                 */
   /* String[][] tableData: data in row/column orentation         */
   /* int cellLen: the max length of a cell in the table          */
   /* Returns: void: outputs a table to the console               */
   /***************************************************************/
   public static void tableGen(String[][] tableData, int cellLen){
      String tableRow = new String();
      String rowSeperator = new String();
      
      for(int itter = 0; itter < tableData.length; itter++){
         for(int itterInner = 0; itterInner < tableData[itter].length; itterInner++){
            tableData[itter][itterInner] = stdStrLen(tableData[itter][itterInner], cellLen);
         }
         
         tableRow = explodeArray(tableData[itter], "| ");
         rowSeperator = "";
         
         for(int itterChar = 0; itterChar < tableRow.length(); itterChar++)
            rowSeperator += "-";
            
         rowSeperator = "|" + rowSeperator + "|";
         
         System.out.println(rowSeperator);
         System.out.println("|" + tableRow + "|");
      }
      
      System.out.println(rowSeperator);
   }
   
   /***************************************************************/
   /* Method: explodeArray                                        */
   /* Purpose: creats string from all elements in an array        */
   /* Parameters:                                                 */
   /* int[] inputArr: the array to turn into an array             */
   /* String del: the string to put between each element          */
   /* Returns: String: the string produced from the array         */
   /***************************************************************/
   private static String explodeArray(String[] inputArr, String del){
      String explodeStr = "";
      
      for(int itter = 0; itter < inputArr.length; itter++){
         explodeStr += inputArr[itter] + del;   
      }  
      
      explodeStr = explodeStr.substring(0, explodeStr.length()- del.length());;
      
      return explodeStr;
   }

   /***************************************************************/
   /* Method: randomArrIntGen                                     */
   /* Purpose: generates an array of random ints based on given   */
   /* Parameters:                                                 */
   /* int len: the length the array should be                     */
   /* int minBound: the min the random numbers can be             */
   /* int maxBound: the max the contained numbers can be          */
   /* Returns: int[]: array of ints randomly creaed               */
   /***************************************************************/
   public static int[] randomArrIntGen(int len, int minBound, int maxBound){
      int[] ranArr = new int[len];
      int ranInt = 0;
      
      Random random = new Random();
      
      for(int itter = 0; itter < len; itter++){
         ranInt = random.nextInt(maxBound - minBound) + minBound;
         ranArr[itter] = ranInt;
      }
      
      return ranArr;
   }

   /***************************************************************/
   /* Method: copyArr                                             */
   /* Purpose: clones an array of ints without ref to the original*/
   /* Parameters:                                                 */
   /* int[] inputArr: the array to copy                           */
   /* Returns: int[]: a copy of the inputArr with no refrences    */
   /***************************************************************/
   public static int[] copyArr(int[] inputArr){
      int[] cpyArr = new int[inputArr.length];
      
      for(int itter = 0; itter < inputArr.length; itter++){
         cpyArr[itter] = inputArr[itter];
      }
      
      return cpyArr;
   }

   /***************************************************************/
   /* Method: stdStrLen                                           */
   /* Purpose: makes a string at least a specified length         */
   /* Parameters:                                                 */
   /* String inputStr: string to standardize the length of        */
   /* int len: the length to adjust to                            */
   /* Returns: String: a string of at least length len            */
   /***************************************************************/
   private static String stdStrLen(String inputStr, int len){
      if(inputStr == null) 
         inputStr = "";
         
      int addSpace = len - inputStr.length();
      
      for(int itter = 0; itter < addSpace; itter++){
         inputStr += " ";
      }
      
      return inputStr;
   }
}