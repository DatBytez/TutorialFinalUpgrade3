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
        // Left-click processing remains unchanged.
        if (e.getButton() == MouseEvent.BUTTON1) {
            SceneManager.getCurrentScene().getScene().mouseClicked(e.getX(), e.getY());
        }
        // Optionally, handle right-click here as well.
        else if (e.getButton() == MouseEvent.BUTTON3) {
            // This line is optional if you want right-click actions also on the click event.
            SceneManager.getCurrentScene().getScene().rightMousePressed(e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            SceneManager.getCurrentScene().getScene().mousePressed(e.getX(), e.getY());
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            SceneManager.getCurrentScene().getScene().rightMousePressed(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        SceneManager.getCurrentScene().getScene().mouseReleased(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // No additional behavior.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No additional behavior.
    }
}
