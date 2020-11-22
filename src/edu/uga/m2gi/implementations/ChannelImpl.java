package edu.uga.m2gi.implementations;

import edu.uga.m2gi.interfaces.IChannel;

public class ChannelImpl implements IChannel {

    private ChannelImpl channel;
    private CircularBufferImpl circularBuf;
    private boolean closed;


    public ChannelImpl(int capacity){
        circularBuf = new CircularBufferImpl(capacity);
        closed = false;
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

    
    public int read(byte[] buffer, int offset, int count){
        return 0;
    }


    public int write(byte[] buffer, int offset, int count){
        return 0;
    }


    public void close() {
       
    }
}
