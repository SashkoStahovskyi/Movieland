package com.stahovskyi.movieland;

public class Runner {

    public static void main(String[] args) {
        
        int [] array = new int[] {1,3,5,7,9,13,16};
        int target = 15;

        int result  = findClosest(array, target);
        System.out.println(result);
    }

    private static int findClosest( int [] array, int target) {

        int closest = array[0];
        int minDiff = Math.abs(target - array[0]);

        for (int i = 1; i < array.length; i++) {

            int currentDiff = Math.abs(target - array[i]);
            
            if(currentDiff < minDiff) {
                minDiff = currentDiff;
                closest = array[i];
            }
        }
        return closest;
    }




}


