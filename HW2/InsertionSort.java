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
   /* Method: doTheAlgorithm                                      */
   /* Purpose: To start the sorting of the array                  */
   /* Parameters:                                                 */
   /* Returns: void: sorts the array lowest to highest            */
   /***************************************************************/
   public void doTheAlgorithm(){
      for(int itter = 1; itter < this.sortArray.length; itter++){
                  
         for(int innerItter = (itter - 1); innerItter >= 0; innerItter--){
            int temp = this.sortArray[innerItter + 1];
            
            if(this.sortArray[innerItter] > temp){
               this.sortArray[innerItter + 1] = this.sortArray[innerItter];
               this.sortArray[innerItter] = temp;
            }else
               break;
            }
      }
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
}