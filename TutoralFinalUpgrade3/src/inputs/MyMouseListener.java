package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import managers.SceneManager;
import main.Main; // Import Main to use getVirtualX/Y methods

public class MyMouseListener implements MouseListener, MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
        int vx = Main.getVirtualX(e.getX());
        int vy = Main.getVirtualY(e.getY());
        SceneManager.getCurrentScene().getScene().mouseDragged(vx, vy);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int vx = Main.getVirtualX(e.getX());
        int vy = Main.getVirtualY(e.getY());
        SceneManager.getCurrentScene().getScene().mouseMoved(vx, vy);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int vx = Main.getVirtualX(e.getX());
        int vy = Main.getVirtualY(e.getY());
        if (e.getButton() == MouseEvent.BUTTON1) {
            SceneManager.getCurrentScene().getScene().mouseClicked(vx, vy);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            SceneManager.getCurrentScene().getScene().rightMousePressed(vx, vy);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int vx = Main.getVirtualX(e.getX());
        int vy = Main.getVirtualY(e.getY());
        if (e.getButton() == MouseEvent.BUTTON1) {
            SceneManager.getCurrentScene().getScene().mousePressed(vx, vy);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            SceneManager.getCurrentScene().getScene().rightMousePressed(vx, vy);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int vx = Main.getVirtualX(e.getX());
        int vy = Main.getVirtualY(e.getY());
        SceneManager.getCurrentScene().getScene().mouseReleased(vx, vy);
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
