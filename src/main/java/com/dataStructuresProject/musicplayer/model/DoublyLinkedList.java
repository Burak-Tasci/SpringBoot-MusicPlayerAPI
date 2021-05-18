package com.dataStructuresProject.musicplayer.model;

import java.util.List;


public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int length;

    public DoublyLinkedList(Song[] arr) {
        this.head = null;
        this.tail = null;
        this.length = 0;
        for (int i=0;i<arr.length;i++)
            this.add(arr[i]);
    }
    
    public DoublyLinkedList(List<Song> arr) {
        this.head = null;
        this.tail = null;
        this.length = 0;
        for (int i=0;i<arr.size();i++)
            this.add(arr.get(i));
    }

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }
    public DoublyLinkedList(Song song) {
        this.head = new Node(song);
        this.tail = null;
        this.length = 1;
    }

    public boolean isEmpty()
    {
        return length == 0;
    }

    public int getLength() {
        return length;
    }

    public void add(Song song)
    {
        Node temp = new Node(song);
        if(head == null)
            head = temp;
        else
        {
            Node iter = head;
            while (iter.getNext() != null)
                iter = iter.getNext();

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
            System.out.print(iter.song.toString()+"\t");
            iter = iter.getNext();
        }
        System.out.println("]");
    }

    public void reverseDisplay()
    {
        Node iter = tail;
        System.out.print("[");
        while(iter != null)
        {
            System.out.print(iter.song.toString()+"\t");
            iter = iter.prev;
        }
        System.out.println("]");
    }
    
    public boolean removeByIndex(int index)
    {
        if (index < 0)
        {
            System.out.println("Out of index");
            return false;
        }
        if (index >= length)
        {
            System.out.println("Out of index");
            return false;
        }

        if (index == length-1)
        {
            tail.prev.next = null;
            tail = tail.prev;
            length--;
            return true;
        }

        Node iter;
        int i;
        if (index == 0)
        {
            head.getNext().prev = null;
            head = head.getNext();
            length--;
            return true;
        }

        if (index > this.length / 2)
        {
            iter = tail;
            for (i = this.length-1; i>index;i--)
                iter = iter.prev;

            iter.getNext().prev = iter.prev;
            iter.prev.next = iter.getNext();
            length--;
            return true;
        }
        else if (index <= this.length / 2)
        {
            iter = head;
            for(i = 0; i<index;i++)
                iter = iter.getNext();

            iter.getNext().prev = iter.prev;
            iter.prev.next = iter.getNext();
            length--;
            return true;
        }
        return false;

    }

    public boolean insert(int index, Song song)
    {
        Node iter;
        Node temp = new Node(song);
        iter = takeNodebyIndex(index);
        if (index == 0)
        {
            temp.next = iter;
            iter.prev = temp;
            head = temp;
        }
        else if (index == length-1)
        {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        else
        {
            temp.next = iter;
            temp.prev = iter.prev;
            iter.prev.next = temp;
            iter.prev = temp;
            return true;
        }
        return false;


    }

    public boolean swap(int i, int j)
    {
        if (i == j)
        {
            System.out.println("You cannot swap same node");
            return false;
        }
        Node firstIter;
        Node secondIter;

        firstIter = takeNodebyIndex(i);
        secondIter = takeNodebyIndex(j);

        this.insert(j, firstIter.song);
        this.insert(i, secondIter.song);

        this.removeByIndex(i+1);
        this.removeByIndex(j+1);
        return true;



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

    public DoublyLinkedList subList(int end)
    {
        Node iter = head;
        DoublyLinkedList subList = new DoublyLinkedList(iter.song);
        for (int i =0; i<end;i++)
        {
            iter = iter.getNext();
            subList.add(iter.song);
        }
        return subList;
    }

    public DoublyLinkedList subList(int start, int end)
    {
        Node iter = takeNodebyIndex(start);
        DoublyLinkedList subList = new DoublyLinkedList(iter.song);
        for (int i =0; i<end-start;i++)
        {
            iter = iter.getNext();
            subList.add(iter.song);
        }
        return subList;
    }
      
    public Song nextSong(Long song_id) {
    	
    	System.out.println(song_id);
    	
    	Node node = this.takeNodebyIndex(0);
    	while(node != null) {
    		if(node.song.getId() == song_id) {
    			System.out.println("denk");
    			return node.next.song;
    		}
    		node = node.next;
    	}
    	if(node == null) {
    		System.out.println("denk değil");
    	}
    	return null;
    	
    
    }
    
    public class Node {
    	Song song;
        private Node next;
        private Node prev;

        public Node(Song song)
        {
            this.song = song;
            this.next = null;
            this.prev = null;
        }
        public Node(Song song, Node next, Node prev)
        {
            this.song = song;
            this.next = next;
            this.prev = prev;
        }

        public Node() {
            this.next = null;
            this.prev = null;
        }
        public Node(Node prev, Node next) {
            this.next = next;
            this.prev = prev;
        }
		public Node getNext() {
			return this.next;
		}
    }



}