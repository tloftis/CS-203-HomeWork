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

   public QuickSort(int[] newArray) {
      this.sortArray = newArray;
		this.length = newArray.length;
	}

   public void doTheAlgorithm() {
		doQuickSort(0, this.sortArray.length - 1);
	}
   
   public int[] getArray(){
      return this.sortArray;
   }

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

	private void swap(int leftIndex, int rightIndex) {
		int temp = sortArray[leftIndex];
		sortArray[leftIndex] = sortArray[rightIndex];
		sortArray[rightIndex] = temp;
	}
}