package org.tholv.Utils;

public class Search {
    public static Search getInstance(){
        return new Search();
    }
    //binary search
    private synchronized int binarySearch(int[]inputArray,int search,int low,int high){

        while(low<high){
            int midArray=(int)high/2;
            if(search==inputArray[midArray])return midArray;
            if(search<inputArray[midArray]){
                low=midArray+1;
                return binarySearch(inputArray, search, low, midArray-1);
            }
            else{
                high=midArray;
                return binarySearch(inputArray, search, midArray+1, high);
            }
        }
        return -1;

    }
    public synchronized int binarySearch(int[]inputArray,int search){
        return binarySearch(inputArray, search, 0, inputArray.length-1);
    }
    public synchronized int linearSearch(int[] arr, int key){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == key){
                return i;
            }
        }
        return -1;
    }

}
