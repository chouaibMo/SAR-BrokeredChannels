package edu.uga.m2gi.interfaces;


/**
 * A channel is communication channel. 
 */
public interface IChannel {

	/**
	 * This method check whether the channel is empty or not.
	 * @return true if the channel is empty. 
	 */
	public boolean empty();

	/**
	 * This method check whether the channel is full or not.
	 * @return true if the channel is full. 
	 */
	public boolean full();

	/**
	 * This method reads bytes from this channel.
	 * The number of bytes read is at most equal to count.
	 * If count is zero, then no bytes are read and 0 is returned.
	 * The first element read is buffer[offset] then buffer[offset+1] and so on.
	 * @param buffer the buffer into which the data is read.
	 * @param offset the start offset the buffer at which the data is written.
	 * @param count Number of bytes to read
	 *
	 * @return the total number of bytes read into the buffer, or -1 if the channel is empty
	 * @throws Exception if offset and count are null or negative, or if count is greater than (buffer size - offset)
	 */
	public abstract int read(byte[] buffer, int offset, int count) throws Exception;


    /**
	 * This method writes bytes to this channel.
	 * The number of bytes written is at most equal to count.
	 * If count is zero, then no bytes are written and 0 is returned.
	 * The first element written is buffer[offset] then buffer[offset+1] and so on.
	 * @param buffer the buffer from where the data is written.
	 * @param offset the start offset the buffer at which the data is written.
	 * @param count Number of bytes to write
	 *
	 * @return the total number of bytes written from the buffer, or -1 if the channel is already full
	 * @throws Exception if offset and count are null or negative, or if count is greater than (buffer size - offset)
	 */
	public abstract int write(byte[] buffer, int offset, int count) throws Exception;
	
	/**
	 * Closes this channel. 
	 * Flush the bytes in the channel before closing
	 */
	public abstract void close(); 

}
