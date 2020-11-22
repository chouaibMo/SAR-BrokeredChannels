package edu.uga.m2gi.interfaces;

/**
 *  A broker that brokers byte-oriented communication channels between a server and clients.
 *  The client connects to the server via a known port.
 *  The server listens for and accepts connections from clients on the given port.
 *  A channel is established between a server and the client after a successful accept.
 */

public interface IBroker {

	/**
	 * Accepts a connection on the given port and returns a channel. 
	 * @param port the port number
	 *
	 * @return an the communication channel
	 * @throws Exception if port is not accessible
	 */
	public IChannel accept(int port);

	/**
	 * Connects to the given port and returns a channel. 
	 * @param port the port number
	 *
	 * @return the communication channel
	 * @throws Exception if port is not accessible
	 */
	public IChannel connect(int port); 

}