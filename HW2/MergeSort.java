/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 2                                    */
/* MergeSort class: Uses the merge sort method for an          */
/*                      int array                              */
/***************************************************************/

import java.util.*;

public class MergeSort {
     
   private int[] sortArray;
   private int length = 0;
   private int[] tempMergArr;
   private int swapCount = 0;
   private int compCount = 0;
   
   /***************************************************************/
   /* Method: MergeSort                                           */
   /* Purpose: Sets the array and the array length to be used     */
   /* Parameters:                                                 */
   /* int[] intArray: The array to be sorted at some point        */
   /* Returns: void: just sets the array                          */
   /***************************************************************/
   public MergeSort(int[] intArray){
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
      long startTime = System.nanoTime();
      
      this.doTheAlgorithm();
      
      long endTime = System.nanoTime();
      long elapsedTime = endTime - startTime;
      
      return elapsedTime;
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
   /* Method: doTheAlgorithm                                      */
   /* Purpose: To start the sorting of the array                  */
   /* Parameters:                                                 */
   /* Returns: void: sorts the array lowest to highest            */
   /***************************************************************/
   public void doTheAlgorithm() {
      this.swapCount = 0;
      this.compCount = 0;
      
      this.length = this.sortArray.length;
      //acts as the second array to compare with
      this.tempMergArr = new int[length];
        
      split(0, this.length - 1);
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
   /* Method: split                                               */
   /* Purpose: to split an array between the upper and lower bound*/
   /*          up to groups of two and call the sorting merge     */
   /* Parameters:                                                 */
   /* int lowerIndex: the lower bounds of the array to sort       */
   /* int higherIndex: the upper bounds of the array to sort      */
   /* Returns: void: sorts the content of the internal array      */
   /***************************************************************/
   private void split(int lowerIndex, int higherIndex){
      if (lowerIndex < higherIndex) {
         //find middle point
         int middle = lowerIndex + ((higherIndex - lowerIndex)/2);
         
         //seperate it by both halfs
         split(lowerIndex, middle);
         split(middle + 1, higherIndex);
         
         //put the two parts back together
         merge(lowerIndex, middle, higherIndex);
      }
   }

   /***************************************************************/
   /* Method: merge                                               */
   /* Purpose: merges two arrays comparing the left most elements */
   /* Parameters:                                                 */
   /* int lowerIndex: the lower bounds of the array to sort       */
   /* int lowHigh: the middle index of the array                  */
   /* int higherIndex: the upper bounds of the array to sort      */
   /* Returns: void: merges two array indexes together            */
   /***************************************************************/
   private void merge(int lowerIndex, int lowHigh, int higherIndex) {
      for (int itter = lowerIndex; itter <= higherIndex; itter++) {
         tempMergArr[itter] = this.sortArray[itter];
      }
                
      int lowLow = lowerIndex;
      //  lowHigh
      int highLow = lowHigh + 1;
      int highHigh = higherIndex;
      int itterArr = lowerIndex;
        
      while(lowLow <= lowHigh && highLow <= highHigh){     
      
         compCount++;   
         swapCount++; 
         if(tempMergArr[lowLow] <= tempMergArr[highLow]){
            this.sortArray[itterArr] = tempMergArr[lowLow];
            lowLow++;
         }else{
            this.sortArray[itterArr] = tempMergArr[highLow];
            highLow++;
         }  
         
         
         itterArr++;
      }
      
      while(lowLow <= lowHigh){         
         this.sortArray[itterArr] = tempMergArr[lowLow];
         swapCount++; 
         lowLow++;
       
         itterArr++;
      }
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