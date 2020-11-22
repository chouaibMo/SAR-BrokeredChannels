package edu.uga.m2gi.interfaces;

/**
 * This class represents a buffer of a given fixed capacity
 * that can be used to push and pull bytes
 *
 * @author Chouaib Mounaime & Emeziem Uwalaka
 */
public abstract class CircularBuffer {
    /**
     *  This method is used to check if the circular buffer is full
     *  @return true if the circularBuffer is full, false otherwise
     */
    public abstract boolean full();

    /**
     * This method is used to check if the circular buffer is empty
     * @return true if the circularBuffer is empty, false otherwise
     */
    public abstract boolean empty();

    /**
     * This method is used to add a byte to the circular buffer
     * @param b : a byte to push to the buffer
     */
    public abstract void push(byte b);

    /**
     * This method is used to remove a byte from the circular buffer
     * @return a byte pulled from the circular buffer
     */
    public abstract byte pull();
}
