/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright: 2017
 *      Author: Pr. Olivier Gruber <olivier dot gruber at acm dot org>
 */
package edu.uga.m2gi.tests;

import edu.uga.m2gi.implementations.BrokerImpl;

/**
 * This test is to test a single connection, echoing messages of increasing
 * length, from 0 to a maximum length. The echoed messages are verified to be
 * correct.
 * 
 * We are using two threads, one for the "client" and the other for the
 * "server".
 */

public class Test00 {

  public static void main(String args[]) throws Exception {

    /*
     * with these values, a deadlock is unavoidable,
     * reverse them:
     * 
     *     int capacity = 512;
     *     int maxLength = 64;
     * 
     * and the deadlock does not happen...
     * In fact, the deadlock happens as soon as
     * the maximum length sent is larger than twice 
     * the capacity of the circular buffers.
     */
    int capacity = 64;
    int maxLength = 512;
    
    final BrokerImpl broker = new BrokerImpl(capacity);

    new Thread(new Runnable() {
      @Override
      public void run() {
        Client c = new Client(0, broker, maxLength);
        c.run(80);
      }
    }, "client").start();

    new Thread(new Runnable() {

      @Override
      public void run() {
        Server s = new Server(80, broker);
        s.run();
      }
    }, "server").start();
  }
}
