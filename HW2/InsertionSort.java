/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 2                                    */
/* InsertionSort class: Uses the insterion sort method for an  */
/*                      int array                              */
/***************************************************************/

import java.util.*;

public class InsertionSort{
   private int[] sortArray;
   private int length = 0;
   private int swapCount = 0;
   private int compCount = 0;
   
   /***************************************************************/
   /* Method: InsertionSort                                       */
   /* Purpose: Sets the array and the array length to be used     */
   /* Parameters:                                                 */
   /* int[] intArray: The array to be sorted at some point        */
   /* Returns: void: just sets the array                          */
   /***************************************************************/
   public InsertionSort(int[] intArray){
      this.sortArray = intArray;
      this.length = intArray.length;
   }
   
   /***************************************************************/
   /* Method: timeAlg                                             */
   /* Purpose: times the runtime of the alg                       */
   /* Parameters:                                                 */
   /* Returns: long: time in nano seconds to run alg              */
   /***************************************************************/
   public long timeAlg(){
      long startTime;
      long endTime;
      
      startTime = System.nanoTime();
      
      this.doTheAlgorithm();
      
      endTime = System.nanoTime();
      long elapsedTime = endTime - startTime;
      
      return elapsedTime;
   }

   /***************************************************************/
   /* Method: doTheAlgorithm                                      */
   /* Purpose: To start the sorting of the array                  */
   /* Parameters:                                                 */
   /* Returns: void: sorts the array lowest to highest            */
   /***************************************************************/
   public void doTheAlgorithm(){
      this.swapCount = 0;
      this.compCount = 0;
      
      for(int itter = 1; itter < this.sortArray.length; itter++){
                  
         for(int innerItter = (itter - 1); innerItter >= 0; innerItter--){
            int temp = this.sortArray[innerItter + 1];
            
            compCount++;
            if(this.sortArray[innerItter] > temp){
               this.sortArray[innerItter + 1] = this.sortArray[innerItter];
               this.sortArray[innerItter] = temp;
               this.swapCount++;
            }else{
               break;
            }
            
         }
      }
   }

   /***************************************************************/
   /* Method: getSwapCount                                        */
   /* Purpose: to get the amount of swaps of last alg run         */
   /* Parameters:                                                 */
   /* Returns: int[] : the swap count                             */
   /***************************************************************/
   public int getSwapCount(){
      return this.swapCount;
   }

   /***************************************************************/
   /* Method: getCompCount                                        */
   /* Purpose: to get the amount of comparisons of last alg run   */
   /* Parameters:                                                 */
   /* Returns: int[] : the comparision count                      */
   /***************************************************************/
   public int getCompCount(){
      return this.compCount;
   }

   /***************************************************************/
   /* Method: getArray                                            */
   /* Purpose: to get the array                                   */
   /* Parameters:                                                 */
   /* Returns: int[] getArray: returns weither arraysorted or not */
   /***************************************************************/
   public int[] getArray(){
      return this.sortArray;
   }

   /***************************************************************/
   /* Method: setArray                                            */
   /* Purpose: to get the array                                   */
   /* Parameters:                                                 */
   /* int[] newArr: the new array to set the internal focus to    */
   /* Returns: void: sets a new array as the focus                */
   /***************************************************************/
   public void setArray(int[] newArr){
      this.sortArray = newArr;
   }
}