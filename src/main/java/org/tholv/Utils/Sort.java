package org.tholv.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sort {
    public  static Sort getInstance(){
        return new Sort();
    }
    public synchronized int[] bubbleSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - 1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    private synchronized void quickSort(int[]numbers,int lowIndex,int heighIndex){
        if(lowIndex>=heighIndex)return;
        int pivotIndex=new Random().nextInt(heighIndex-lowIndex)+lowIndex;
        int pivot=numbers[pivotIndex];
        int leftPointer=lowIndex;
        int righPointer=heighIndex;
        swap(numbers, pivotIndex, heighIndex);
        while(leftPointer<righPointer){
            while(numbers[leftPointer]<=pivot&&leftPointer<righPointer){
                leftPointer++;
            }
            while(numbers[righPointer]>=pivot&&leftPointer<righPointer){
                righPointer--;
            }
            swap(numbers,leftPointer, righPointer);
        }
        swap(numbers, leftPointer,heighIndex);
        quickSort(numbers,lowIndex,leftPointer-1);
        quickSort(numbers, leftPointer+1, heighIndex);

    }
    private synchronized void swap(int[]numbers,int index1,int index2){
        int  temp=numbers[index1];
        numbers[index1]=numbers[index2];
        numbers[index2]=temp;
    }
   public synchronized int[] quickSort(int arr[]){
        quickSort(arr,0,arr.length-1);
        return arr;
   }
   public synchronized int[] selectionSort(int arr[]){
       for(int i = 0; i < arr.length; i++){
           int min = i;
           for(int j = i+1; j < arr.length; j++){
               if(arr[j]<arr[min]){
                   min = j;
               }
           }
           swap(arr,i,min);
       }
       return arr;
   }
    public synchronized int[] insertionSort(int arr[]){
         for(int i = 1; i < arr.length; i++){
              int j = i;
              while(j>0&&arr[j]<arr[j-1]){
                swap(arr,j,j-1);
                j--;
              }
         }
         return arr;
    }
    public synchronized int[] mergeSort(int []inputArray) throws Exception{
        int inputLength=inputArray.length;
        if(inputLength<2){
            throw new Exception("Array size must be greater than 1");
        }
        int midIndex=(int)inputLength/2;

        int []leftHaft=new int [midIndex];
        int []rightHaft=new int [inputLength-midIndex];
        for(int i=0; i<midIndex;i++){
            leftHaft[i]=inputArray[i];
        }
        for(int i=midIndex; i<inputLength;i++){
            rightHaft[i-midIndex]=inputArray[i];
        }
        mergeSort(leftHaft);
        mergeSort(rightHaft);
        merge(inputArray, leftHaft, rightHaft);
        return inputArray;
    }
   private synchronized void merge(int []inputArray,int[] leftHaft,int[] rightHaft){
        int leftSize=leftHaft.length;
        int  rightSize=rightHaft.length;
        int i=0,j=0,k=0;
        while (i<leftSize&&j<rightSize) {
            if(leftHaft[i]<=rightHaft[j]){
                inputArray[k]=leftHaft[i];
                i++;

            }else{
                inputArray[k]=rightHaft[j];
                j++;
            }
            k++;
        }
        while (i<leftSize) {
            inputArray[k]=leftHaft[i];
            i++;
            k++;
        }
        while (j<rightSize) {
            inputArray[k]=rightHaft[j];
            j++;
            k++;
        }
    }
    public synchronized int[] heapSort(int []inputArray){
        int inputLength=inputArray.length;
        for(int i=inputLength/2-1;i>=0;i--){
            heapify(inputArray,inputLength,i);
        }
        for(int i=inputLength-1;i>0;i--){
            swap(inputArray,0,i);
            heapify(inputArray,i,0);
        }
        return inputArray;
    }
    private synchronized void heapify(int []inputArray,int inputLength,int i){
        int largest=i;
        int left=2*i+1;
        int right=2*i+2;
        if(left<inputLength&&inputArray[left]>inputArray[largest]){
            largest=left;
        }
        if(right<inputLength&&inputArray[right]>inputArray[largest]){
            largest=right;
        }
        if(largest!=i){
            swap(inputArray,i,largest);
            heapify(inputArray,inputLength,largest);
        }
    }
    public synchronized List sortListInteger(List list){
        list.sort( (o1, o2) -> {
            if(o1 instanceof Integer && o2 instanceof Integer){
                return (Integer)o1 - (Integer)o2;
            }
            return 0;
        });
        return list;
    }
    public synchronized  List sortListString(List list){
        list.sort( (o1, o2) -> {
            if(o1 instanceof String && o2 instanceof String){
                return ((String)o1).compareTo((String)o2);
            }
            return 0;
        });
        return list;
    }
   public synchronized List sortListObject(List obj,Object criteria) {
       if (criteria instanceof Integer) {
           return sortListInteger(obj);
       }
       if (criteria instanceof String) {
           return sortListString(obj);
       }
       for (int i = 0; i < obj.size(); i++) {
           if (obj.get(i) instanceof Comparable) {
               Comparable temp = (Comparable) obj.get(i);
               for (int j = i + 1; j < obj.size(); j++) {
                   if (obj.get(j) instanceof Comparable) {
                       Comparable temp2 = (Comparable) obj.get(j);
                       if (temp.compareTo(temp2) > 0) {
                           obj.set(i, temp2);
                           obj.set(j, temp);
                       }
                   }
               }
           }
       }
       return obj;
   }


}
