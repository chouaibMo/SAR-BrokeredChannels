package edu.uga.m2gi.implementations;

import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;

import edu.uga.m2gi.interfaces.CircularBuffer;

public class CircularBufferImpl extends CircularBuffer {
	private byte m_buffer[];
    private int capacity;
    private int readIndex;
    private int writeIndex;
    private int nbElements;

    public CircularBufferImpl(int capacity) {
    	if (capacity < 2) 
    		throw new IllegalArgumentException("Minimum buffer capacity is 2");
    	
        m_buffer = new byte[capacity];
        this.capacity = capacity;
        this.nbElements = 0;
        this.readIndex = 0;
        this.writeIndex = -1;
    }

    @Override
    public boolean full() {
        return this.nbElements >= this.capacity;
    }

    @Override
    public boolean empty() {
        return this.nbElements <= 0;
    }

    @Override
    public void push(byte b) {
        if (full()) 
        	throw new IllegalStateException("Cannot push. Buffer is full!");
        
        this.writeIndex = ++this.writeIndex % this.capacity;
        this.m_buffer[this.writeIndex] = b;
        this.nbElements++;
    }

    @Override
    public byte pull() {
    	if (empty()) 
    		throw new IllegalStateException("Cannot pull. Buffer is empty!");
    	
    	byte b = this.m_buffer[this.readIndex % this.capacity];
    	this.readIndex = ++this.readIndex % this.capacity;
        this.nbElements--;
    	
        return b;
    }

}
