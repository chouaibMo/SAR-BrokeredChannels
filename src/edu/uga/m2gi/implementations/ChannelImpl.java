package edu.uga.m2gi.implementations;

import edu.uga.m2gi.interfaces.IChannel;

public class ChannelImpl implements IChannel {

    private ChannelImpl channel;
    private CircularBufferImpl circularBuf;
    private boolean closed;
    private CircularBufferImpl trashBuffer;


    public ChannelImpl(int capacity){
        circularBuf = new CircularBufferImpl(capacity);
        closed = false;
        this.trashBuffer = new CircularBufferImpl(capacity);
    }

    public CircularBufferImpl getBuffer(){
        return this.circularBuf;
    }

    public boolean isClosed() {
    	return closed;
    }

    public boolean empty() {
        return circularBuf.empty();
    }

    
    public boolean full() {
        return channel.getBuffer().full();
    }

    
    public int read(byte[] buffer, int offset, int count) throws Exception{
    	if (count == 0) return 0;
    	
    	if (count > buffer.length - offset) throw new Exception("Not enough space in buffer to read from channel");
    	
    	if (count < 0) throw new Exception("Cannot read a negative number of bytes");
    	
    	if (offset < 0) throw new Exception("Cannot read from a negative offset");
    	
    	int bytesRead = 0, i = offset;   	
    	while (bytesRead < count) {
    		if (channel.empty()) {
    			return -1;
    		}
    		
			buffer[i] = this.circularBuf.pull();
			i++;
			bytesRead++;    		
    	}
    	
        return bytesRead;
    }


    public int write(byte[] buffer, int offset, int count) throws Exception{
    	if (count == 0) return 0;
    	
    	if (count > buffer.length - offset) throw new Exception("Cannot write the desired number of bytes to the channel");
    	
    	if (count < 0) throw new Exception("Cannot write a negative number of bytes");
    	
    	if (offset < 0) throw new Exception("Cannot write to a negative offset");
    	
    	int bytesWritten = 0, i = offset;   	
    	while (bytesWritten < count) {
    		if (channel.full()) {
    			return -1;
    		}
    		
    		this.circularBuf.push(buffer[i]);
			i++;
			bytesWritten++;    		
    	}
    	
        return bytesWritten;
    }


    public void close() {
    	while (!this.circularBuf.empty()) {
    		this.trashBuffer.push(this.circularBuf.pull());
    	}
		this.closed = true;
    }
}
