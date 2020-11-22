package edu.uga.m2gi.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.uga.m2gi.interfaces.CircularBuffer;
import edu.uga.m2gi.implementations.CircularBufferImpl;



public class CircularBufferTest {
	final static int DEFAULT_CAPACITY = 5;  
	final byte DEFAULT_BYTE = 7;
	static CircularBuffer buffer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		buffer = new CircularBufferImpl(DEFAULT_CAPACITY);
	}

	@Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest(){
		new CircularBufferImpl(0);
    }
	
	@Test
	public void testFull() {
		assertFalse(buffer.full());
	}

	@Test
	public void testEmpty() {
		assertTrue(buffer.empty());
	}

	@Test
	public void testPush() {
		assertTrue(buffer.empty());
		buffer.push(DEFAULT_BYTE);
		assertFalse(buffer.empty());
	}

	@Test
	public void testPull() {
		assertTrue(buffer.empty());
		buffer.push(DEFAULT_BYTE);
		assertFalse(buffer.empty());
		byte b = buffer.pull();
		assertTrue(buffer.empty());
		assertEquals(DEFAULT_BYTE, b);
	}

	@Test(expected=IllegalStateException.class)
	public void testPushException() {
		for (byte i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
			buffer.push(i);
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPullException() {
		CircularBufferImpl buff = new CircularBufferImpl(DEFAULT_CAPACITY);
		assertTrue(buff.empty());
		buff.pull();
	}

}
