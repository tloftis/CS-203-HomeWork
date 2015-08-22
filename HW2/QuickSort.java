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
}