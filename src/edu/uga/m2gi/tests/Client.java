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

import java.io.IOException;

import edu.uga.m2gi.interfaces.IBroker;
import edu.uga.m2gi.interfaces.IChannel;



public class Client {
  int m_no;
  IChannel m_channel = null;
  IBroker m_broker;
  int m_maxLength;

  Client(int no, IBroker broker, int maxLength) {
    m_no = no;
    m_broker = broker;
    m_maxLength = maxLength;
  }

  private void connect(int port) {
    while (m_channel == null) {
      m_channel = m_broker.connect(port);
      try {
        Thread.sleep(100);
      } catch (InterruptedException ex) {
        // nothing to do, just loop
      }
    }
    System.out.printf("Client %d is connected \n", m_no);
  }

  void run(int port) {

    connect(port);
    try {

      for (int i = 0; i < m_maxLength; i++)
        echo(i);

    } catch (IOException ex) {
      System.out.printf("Client %d got unexpected EOF.\n", m_no);
    } finally {
      System.out.printf("Client %d Done.\n", m_no);
      m_channel.close();
    }
  }

  private void echo(int length) throws IOException {
    byte[] buf = new byte[length];
    byte[] rcv = new byte[length];
    for (int i = 0; i < length; i++)
      buf[i] = (byte) i;
    send(m_channel, buf);

    rcv(m_channel, rcv);

    for (int i = 0; i < buf.length; i++)
      if (buf[i] != rcv[i])
        System.err.println("Failed");
  }

  private static void send(IChannel ch, byte buf[]) throws IOException {
    int off = 0;
    int remaining = buf.length;
    while (remaining > 0) {
      int n = ch.write(buf, off, remaining);
      if (n < 0)
        throw new IOException("closed");
      off += n;
      remaining -= n;
    }
  }

  private static boolean rcv(IChannel ch, byte buf[]) throws IOException {
    int off = 0;
    int remaining = buf.length;
    while (remaining > 0) {
      int n = ch.read(buf, off, remaining);
      if (n < 0)
        throw new IOException("closed");
      off += n;
      remaining -= n;
    }
    return true;
  }

}
