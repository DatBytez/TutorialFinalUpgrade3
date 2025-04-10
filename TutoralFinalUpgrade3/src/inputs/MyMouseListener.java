package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import managers.SceneManager;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
        SceneManager.getCurrentScene().getScene().mouseDragged(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        SceneManager.getCurrentScene().getScene().mouseMoved(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            SceneManager.getCurrentScene().getScene().mouseClicked(e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        SceneManager.getCurrentScene().getScene().mousePressed(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        SceneManager.getCurrentScene().getScene().mouseReleased(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}