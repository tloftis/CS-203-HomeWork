/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 2                                    */
/* QuickSort class: This is a merge sort for an integer array  */
/***************************************************************/

import java.util.*;

public class QuickSort{
	 
	private char sortArray[];
   
   /***************************************************************/
   /* Method: QuickSort                                           */
   /* Purpose: Sets the array and the array length to be used     */
   /* Parameters:                                                 */
   /* int[] intArray: The array to be sorted at some point        */
   /* Returns: void: just sets the array                          */
   /***************************************************************/
   public QuickSort() {
      
	}

   /***************************************************************/
   /* Method: quickSortStr                                        */
   /* Purpose: To start the sorting of the array                  */
   /* Parameters:                                                 */
   /* Returns: void: sorts the array lowest to highest            */
   /***************************************************************/
   public String quickSortStr(String word) {
      this.sortArray = word.toCharArray();
      
		doQuickSort(0, this.sortArray.length - 1);
      
      String sortWord = new String(this.sortArray);
      
      return sortWord;
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
		int pivot = (int) sortArray[middleIndex];
      
		while (lowItter <= highItter) {
			while ((int) sortArray[lowItter] < pivot)
            lowItter++;
         
			while ((int) sortArray[highItter] > pivot)
            highItter--;
         
			if (lowItter <= highItter) {
				swap(lowItter, highItter);
            
				lowItter++;
				highItter--;
		   }
		}
      
		if (lowerIndex < higherIndex)
			doQuickSort(lowerIndex, highItter);
         
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
		char temp = sortArray[leftIndex];
		sortArray[leftIndex] = sortArray[rightIndex];
		sortArray[rightIndex] = temp;
	}
}