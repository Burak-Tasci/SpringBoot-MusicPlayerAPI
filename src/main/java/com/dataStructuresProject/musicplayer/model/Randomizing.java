package com.dataStructuresProject.musicplayer.model;

import java.util.Random;
import java.util.Arrays;
import java.util.List;

// Fisher–Yates shuffle Algorithm
public class Randomizing
{
	private List<Song> arr;
	
	public Randomizing(List<Song> arr) {
		this.arr = arr;
	}
	
    
    public List<Song> randomize(int n)
    {
        
        Random r = new Random();
         

        for (int i = n-1; i > 0; i--) {
             

            int j = r.nextInt(i+1);
             

            Song temp = arr.get(i);
            arr.set(i,arr.get(j));
            arr.set(j,temp);
        }

        return this.arr;
    }
     

}