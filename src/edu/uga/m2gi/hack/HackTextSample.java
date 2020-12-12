package edu.uga.m2gi.hack;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class HackTextSample {

  public static void main(String args[]) {
    Frame frame = new Frame();
    frame.addWindowListener(new _WindowListener());

    new HackTextSample(frame);

    frame.setSize(600, 800);
    frame.pack();
    frame.setVisible(true);
  }

  TextArea m_texts[];

  HackTextSample(Frame frame) {

    frame.setLayout(new FlowLayout());
    _KeyListener l = new _KeyListener();

    m_texts = new TextArea[3];
    for (int i = 0; i < m_texts.length; i++) {
      TextArea text = new TextArea("Welcome!");
      text.setPreferredSize(new Dimension(400, 400));
      text.addKeyListener(l);
      frame.add(text);
      m_texts[i] = text;
    }
  }

  class _KeyListener implements KeyListener {

    private boolean ignoredKey(int code) {
      return (code == KeyEvent.VK_LEFT) || (code == KeyEvent.VK_RIGHT)
          || (code == KeyEvent.VK_UP) || (code == KeyEvent.VK_DOWN)
          || (code == KeyEvent.VK_PAGE_UP) || (code == KeyEvent.VK_PAGE_DOWN)
          || (code == KeyEvent.VK_SHIFT);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
      TextArea src = (TextArea) e.getSource();
      int offset = src.getCaretPosition();

      int code = e.getKeyCode();
      if (code == KeyEvent.VK_BACK_SPACE) {
        if (offset > 0) {
          for (int i = 0; i < m_texts.length; i++) {
            if (m_texts[i] != src)
              m_texts[i].replaceRange("", offset - 1, offset);
          }
        }
      } else if (code == KeyEvent.VK_DELETE) {
        String text = src.getText();
        if (offset < text.length())
          for (int i = 0; i < m_texts.length; i++) {
            if (m_texts[i] != src)
              m_texts[i].replaceRange("", offset, offset + 1);
          }
      } else if (!ignoredKey(code)) {
        char[] chars = new char[1];
        chars[0] = e.getKeyChar();
        String s = new String(chars);
        for (int i = 0; i < m_texts.length; i++) {
          if (m_texts[i] != src)
            m_texts[i].insert(s, offset);
        }
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

  }

  static class _WindowListener implements WindowListener {

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
      System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
      System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

  };

}