public class MergeSort {
     
   private int[] sortArray;
   private int length = 0;
   private int[] array;
   private int[] tempMergArr;
 
   public MergeSort(int[] intArray){
      this.sortArray = intArray;
      this.length = intArray.length;
   }
   
   public int[] getArray(){
      return this.sortArray;
   }
     
   public void doTheAlgorithm() {
      this.length = this.sortArray.length;
      //acts as the second array to compare with
      this.tempMergArr = new int[length];
        
      split(0, this.length - 1);
   }
 
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