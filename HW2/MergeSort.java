public class MergeSort {
     
   private int[] sortArray;
   private int length = 0;
   private int[] tempMergArr;
   
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
      this.length = this.sortArray.length;
      //acts as the second array to compare with
      this.tempMergArr = new int[length];
        
      split(0, this.length - 1);
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
         lowLow++;
       
         itterArr++;
      }
   }
}