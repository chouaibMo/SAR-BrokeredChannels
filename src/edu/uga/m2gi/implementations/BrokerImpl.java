package edu.uga.m2gi.implementations;

import java.util.HashMap;
import edu.uga.m2gi.interfaces.IBroker;
import edu.uga.m2gi.interfaces.IChannel;


public class BrokerImpl implements IBroker{

    private HashMap<Integer,IChannel> connections;
    private int capacity;

    public BrokerImpl(int capacity) {
        this.capacity = capacity;
        this.connections = new HashMap<>();
    }


    public synchronized IChannel accept(int port) {
    	try {
        	//TODO
        }catch(Exception e) {
        	e.getMessage();
        }
        
        return null;
    }


    public synchronized IChannel connect(int port) {
        try {
        	//TODO
        }catch(Exception e) {
        	e.getMessage();
        }
        
        return null;
    }
}
