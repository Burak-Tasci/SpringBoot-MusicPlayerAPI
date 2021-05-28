package com.dataStructuresProject.musicplayer.model;

import java.util.List;


public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int length;


    public DoublyLinkedList(List<Song> arr) {
        this.head = null;
        this.tail = null;
        this.length = 0;
        for (int i=0;i<arr.size();i++)
            this.add(arr.get(i));
    }


    public int getLength() {
        return length;
    }

    public void add(Song data)
    {
        Node temp = new Node(data);
        if(head == null)
            head = temp;
        else
        {
            Node iter = head;
            while (iter.next != null)
                iter = iter.next;

            iter.next = temp;
            temp.prev = iter;
            tail = temp;
        }

        this.length++;
    }

    public void display()
    {
        Node iter = head;
        System.out.print("[");
        while(iter != null)
        {
            System.out.print(iter.data+"\t");
            iter = iter.next;
        }
        System.out.println("]");
    }

    public void reverseDisplay()
    {
        Node iter = tail;
        System.out.print("[");
        while(iter != null)
        {
            System.out.print(iter.data+"\t");
            iter = iter.prev;
        }
        System.out.println("]");
    }


    public Node takeNodebyIndex(int i)
    {
        Node iter = head;
        int iteratorIndex;

        iteratorIndex = 0;
        while(iteratorIndex++<i)
        	iter = iter.next;


        return iter;
    }
    
    public Song nextSong(Long songId) {
    	
    	Node iter = head;
    	
    	for(int i = 0; i<this.length;i++) {
    		if(iter.data.getId().toString().equals(songId.toString()) && iter.next != null) {
    			return iter.next.data;
    		}
    		iter = iter.next;
    	}
    	return null;
    }
    
    public Song previousSong(Long songId) {
    	
    	Node iter = head;
    	
    	for(int i = 0; i<this.length;i++) {
    		if(iter.data.getId().toString().equals(songId.toString()) && iter.prev != null) {
    			return iter.prev.data;
    		}
    		iter = iter.next;
    	}
    	return null;
    }


    public class Node {
    	Song data;
        private Node next;
        private Node prev;

        public Node(Song data)
        {
            this.data = data;
            this.next = null;
            this.prev = null;
        }


        public Node() {
            this.next = null;
            this.prev = null;
        }
        public Node(Node prev, Node next) {
            this.next = next;
            this.prev = prev;
        }
		
		public Song getdata() {
			return this.data;
		}
    }



}