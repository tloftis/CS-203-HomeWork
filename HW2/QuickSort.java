/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 2                                    */
/* QuickSort class: This is a merge sort for an integer array    */
/***************************************************************/

import java.util.*;

public class QuickSort{
	 
	private int sortArray[];
	private int length;
   private int swapCount = 0;
   private int compCount = 0;
   
   /***************************************************************/
   /* Method: QuickSort                                           */
   /* Purpose: Sets the array and the array length to be used     */
   /* Parameters:                                                 */
   /* int[] intArray: The array to be sorted at some point        */
   /* Returns: void: just sets the array                          */
   /***************************************************************/
   public QuickSort(int[] newArray) {
      this.sortArray = newArray;
		this.length = newArray.length;
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
      
		doQuickSort(0, this.sortArray.length - 1);
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
   /* Method: doQuickSort                                         */
   /* Purpose: the recursive alg for quicksor                     */
   /* Parameters:                                                 */
   /* int lowerIndex: the lower bounds of the array to sort       */
   /* int higherIndex: the upper bounds of the array to sort      */
   /* Returns: void: sorts the internal array                     */
   /***************************************************************/
	private void doQuickSort(int lowerIndex, int higherIndex){
		int lowItter = lowerIndex;
		int highItter = higherIndex;
      
		// use the middle as a pivot numberr
      int middleIndex = lowerIndex + (higherIndex - lowerIndex)/2;
		int pivot = sortArray[middleIndex];
      
		while (lowItter <= highItter) {
			while (sortArray[lowItter] < pivot)
            lowItter++;
         
			while (sortArray[highItter] > pivot)
            highItter--;
         
         compCount++;
			if (lowItter <= highItter) {
				swap(lowItter, highItter);
            swapCount++;
            
				lowItter++;
				highItter--;
		   }
		}
      
      compCount++;
		if (lowerIndex < higherIndex)
			doQuickSort(lowerIndex, highItter);
         
      compCount++;
		if (lowItter < higherIndex)
			doQuickSort(lowItter, higherIndex);
         
	}

   /***************************************************************/
   /* Method: swap                                                */
   /* Purpose: swaps the value of two indexes in the main array   */
   /* Parameters:                                                 */
   /* int leftIndex: index to swap with right index               */
   /* int rightIndex: index to swap with left index               */
   /* Returns: void: sets a new array as the focus                */
   /***************************************************************/
   private void swap(int leftIndex, int rightIndex) {
		int temp = sortArray[leftIndex];
		sortArray[leftIndex] = sortArray[rightIndex];
		sortArray[rightIndex] = temp;
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