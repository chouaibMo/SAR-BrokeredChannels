package edu.uga.m2gi.implementations;

import java.util.HashMap;
import edu.uga.m2gi.interfaces.IBroker;
import edu.uga.m2gi.interfaces.IChannel;


public class BrokerImpl implements IBroker{
	
	private enum PORT_STATUS {
		USED, WAITING
	}

    private HashMap<Integer,IChannel> connections;
    private HashMap<Integer,PORT_STATUS> waitingPorts;
    private int capacity;

    public BrokerImpl(int capacity) {
        this.capacity = capacity;
        this.connections = new HashMap<>();
        this.waitingPorts = new HashMap<>();
    }

    public synchronized IChannel accept(int port) {
    	try {
    		while (this.connections.get(port) == null) {
    			this.waitingPorts.put(port, PORT_STATUS.WAITING);
        		wait();
        	}
        }catch(Exception e) {
        	e.getMessage();
        }
    	
    	notify();
        return this.connections.get(port);
    }


    public synchronized IChannel connect(int port) {
    	 try {
             if (this.waitingPorts.get(port).equals(PORT_STATUS.USED))
    			 return null;
    			 
             while (this.waitingPorts.get(port) != null && this.waitingPorts.get(port) != PORT_STATUS.WAITING) {
                 wait();
             }
             
             this.waitingPorts.put(port, PORT_STATUS.USED);
             this.connections.put(port, new ChannelImpl(this.capacity));
             notify();

         }catch(Exception e) {
             e.getMessage();
         }
        return this.connections.get(port);
    }
}
