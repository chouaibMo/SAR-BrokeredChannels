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

import edu.uga.m2gi.interfaces.IBroker;
import edu.uga.m2gi.interfaces.IChannel;
import edu.uga.m2gi.implementations.ChannelImpl;
import edu.uga.m2gi.implementations.BrokerImpl;

public class Server {

  IBroker broker;
  int port;
  
  
  Server(int port, IBroker broker) {
    this.port = port;
    this.broker = (BrokerImpl) broker;
  }
  
  
  void run() throws Exception {
    byte[] buf = new byte[10];
    while (true) {
      System.out.printf("Server-%d accepting...\n",port);
      IChannel sock = (ChannelImpl) broker.accept(port);
      System.out.printf("Server-%d accepted...\n",port);
      while (true) {
        int n = sock.read(buf, 0, buf.length);
        if (n < 0)
          break;
        System.out.printf("Server-%d read %d bytes\n", port,n);
        int off = 0;
        int remaining = n;
        while (remaining > 0) {
          n = sock.write(buf, off, remaining);
          if (n < 0)
            break;
          System.out.printf("  wrote %d bytes\n", n);
          off += n;
          remaining -= n;
        }
      }
      System.out.printf("Server-%d disconnected.\n",port);
    }
  }

}
