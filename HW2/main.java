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
	public static void main() {
      int[] sortMerge = randomArrIntGen(15, 1, 100);
      int[] sortQuick = copyArr(sortMerge);
      int[] sortInsert = copyArr(sortMerge);
      
      System.out.println(explodeArray(sortMerge, " - "));

      MergeSort sortAlg = new MergeSort(sortMerge);
      
      long startTime = System.nanoTime();
      
      sortAlg.doTheAlgorithm();
      
      long endTime = System.nanoTime();
      long elapsedTime = endTime - startTime;
      
      System.out.println(explodeArray(sortAlg.getArray(), " - "));
      System.out.println(elapsedTime);
   }

   /***************************************************************/
   /* Method:                                              */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   /***************************************************************/
   public static String explodeArray(int[] inputArr, String del){
      String explodeStr = "";
      
      for(int itter = 0; itter < inputArr.length; itter++){
         explodeStr += inputArr[itter] + del;   
      }  
      
      explodeStr = explodeStr.substring(0, explodeStr.length()- del.length());;
      
      return explodeStr;
   }

   /***************************************************************/
   /* Method:                                              */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
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
   /* Method:                                              */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   /***************************************************************/
   public static int[] copyArr(int[] inputArr){
      int[] cpyArr = new int[inputArr.length];
      
      for(int itter = 0; itter < inputArr.length; itter++){
         cpyArr[itter] = inputArr[itter];
      }
      
      return cpyArr;
   }
}