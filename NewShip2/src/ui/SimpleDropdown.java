package ui;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import static ship.helpz.Constants.*;

public class SimpleDropdown {
	private int x, y, width;
	private int itemHeight = 18;
	private int hoveredIndex = -1;
	private List<String> items;
	private boolean visible = false;
	private Consumer<Integer> onSelect; // Called when item is clicked
	private Font font;

	public SimpleDropdown(List<String> items, int x, int y, int width, Font font, Consumer<Integer> onSelect) {
		this.items = items;
		this.x = x;
		this.y = y;
		this.width = width;
		this.onSelect = onSelect;
		this.font = font;
	}


	public void draw(Graphics g) {
		if (!visible) return;

		int height = items.size() * itemHeight;
		Graphics2D g2d = (Graphics2D) g;

		g2d.setFont(font); // Use the passed-in font


		g2d.setColor(PHB_TEXT);
		g2d.fillRoundRect(x - 5, y - 5, width + 10, height + 10, 12, 12);

		for (int i = 0; i < items.size(); i++) {
			if (i == hoveredIndex) {
				g2d.setColor(PHB_LIST_HOVERED);
				g2d.fillRoundRect(x - 2, y + i * itemHeight, width, itemHeight, 6, 6);
			}
			g2d.setColor(PHB_LIST_TITLE);
			g2d.drawString(items.get(i), x + 5, y + (i + 1) * itemHeight - 4);
		}
	}


	public void mouseMoved(int mx, int my) {
		if (!visible) return;

		hoveredIndex = -1;
		for (int i = 0; i < items.size(); i++) {
			Rectangle r = new Rectangle(x, y + i * itemHeight, width, itemHeight);
			if (r.contains(mx, my)) {
				hoveredIndex = i;
				break;
			}
		}
	}

	public void mouseClicked(int mx, int my) {
		if (!visible || hoveredIndex < 0 || hoveredIndex >= items.size()) return;

		onSelect.accept(hoveredIndex);
		hide();
	}

	public void show() {
		visible = true;
	}

	public void hide() {
		visible = false;
		hoveredIndex = -1;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle getBounds() {
		if (!visible) return new Rectangle(); // Return empty bounds if not visible

		int height = items.size() * itemHeight;
		return new Rectangle(x, y, width, height);
	}
}
