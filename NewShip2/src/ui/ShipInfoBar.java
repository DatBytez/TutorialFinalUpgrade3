package ui;

import static helpz.Constants.PHB_DARK;
import static helpz.Constants.PHB_TEXT;
import static helpz.Constants.PHB_TITLE;
import static helpz.Format.getDashedString;
import static helpz.Format.getMoneyString;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import scenes.BuildScene;
import ship.ShipCompartment;
import ship.ShipSystem;

public class ShipInfoBar extends Bar {

	private BuildScene building;

	private int width, height;
	private Rectangle bounds;
	private int yOffset = 30;
	private int titleOffset = 60;

	private Rectangle nameBounds; // Click area for the name
	private boolean renamingShip = false;
	private StringBuilder tempName = new StringBuilder(); // For live input

	private Map<String, Boolean> systemTypeExpanded = new TreeMap<>(); // Track expanded/collapsed
	private Map<String, Rectangle> typeClickZones = new TreeMap<>(); // Track clickable areas

	public ShipInfoBar(int x, int y, int width, int height, BuildScene building) {
		super(x, y, width, height);
		this.building = building;
		this.width = width;
		this.height = height;
		this.building = building;
		initBounds();
		initButtons();
	}

	private void initBounds() {
		this.bounds = new Rectangle(x, y, width, height);
	}

	private void initButtons() {
	}

	private void drawButtons(Graphics g) {
	}

	public void draw(Graphics g) {
		drawBackground(g);
		drawTitle(g);
		drawShipInfo(g);
		drawButtons(g);
	}

	private void drawTitle(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(PHB_TITLE);
		g.setFont(alternityHeadFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 16F));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		String displayName = renamingShip ? tempName.toString() + "|" : building.getNewShip().getName();
		int titleWidth = g.getFontMetrics().stringWidth(displayName);
		int titleX = x + (width / 2) - titleWidth / 2;
		int titleY = y + yOffset;

		g.drawString(displayName, titleX, titleY);

		// Track clickable area
		nameBounds = new Rectangle(titleX, titleY - 16, titleWidth, 20);

		g2d.setPaint(PHB_DARK);
		g2d.fillRect(x + 40, y + 35, width - 80, 5);
	}

	private void drawBackground(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(PHB_DARK);
		g2d.fillRoundRect(x, y, width, height, 50, 50);
	}

	private void drawShipInfo(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(PHB_TEXT);

		int lineHeight = 20;
		int infoY = y + 20 + titleOffset;

		ArrayList<String[]> rows = new ArrayList<>();

		// Header row
		rows.add(new String[] { "TYPE", "POW", "HULL", "SYSTEM", "COST" });

		// Hull
		ShipSystem hull = building.getNewShip().getHull();
		if (hull != null) {
			rows.add(new String[] { "Hull", getDashedString(hull.getPowerReq()), getDashedString(hull.getHullPts()),
					hull.getName(), getMoneyString(hull.getCost()) });
		} else {
			rows.add(new String[] { "Hull", "-", "-", "Select a Hull", "-" });
		}

		// Armor
		ShipSystem armor = building.getNewShip().getArmor();
		if (armor != null) {
			rows.add(new String[] { "Armor", getDashedString(armor.getPowerReq()), getDashedString(armor.getHullPts()),
					armor.getName(), getMoneyString(armor.getCost()) });
		} else {
			rows.add(new String[] { "Armor", "-", "-", "Select Armor", "-" });
		}

		// Group other systems
		Map<String, List<ShipSystem>> systemGroups = new TreeMap<>();
		for (ShipSystem system : building.getNewShip().getSystemList()) {
			if (system == hull || system == armor)
				continue;
			String type = system.getClass().getSimpleName();
			systemGroups.computeIfAbsent(type, k -> new ArrayList<>()).add(system);
		}

		// Column widths (initial guess — updated later)
		int[] colWidths = new int[5];
		int padding = 20;
		int[] colX = new int[5];
		colX[0] = x + padding;

		// Placeholder for now: header and hull/armor
		for (String[] row : rows) {
			for (int i = 0; i < row.length; i++) {
				colWidths[i] = Math.max(colWidths[i], g.getFontMetrics().stringWidth(row[i]));
			}
		}

		// Dynamic sections per system type
		for (String type : systemGroups.keySet()) {
			List<ShipSystem> group = systemGroups.get(type);

			systemTypeExpanded.putIfAbsent(type, true);

			int headerY = infoY + (rows.size()) * lineHeight;
			Rectangle clickZone = new Rectangle(colX[0], headerY - lineHeight + 4, 100, lineHeight);
			typeClickZones.put(type, clickZone);

			// Label row
			String label = (systemTypeExpanded.get(type) ? "▼ " : "▶ ") + type;
			rows.add(new String[] { label, "", "", "", "" });

			if (systemTypeExpanded.get(type)) {
				for (ShipSystem system : group) {
					rows.add(new String[] { "", // don't repeat type
							getDashedString(system.getPowerReq()), getDashedString(system.getHullPts()),
							system.getName(), getMoneyString(system.getCost()) });
				}
			}
		}

		// Final width calculations with all rows
		for (String[] row : rows) {
			for (int i = 0; i < row.length; i++) {
				colWidths[i] = Math.max(colWidths[i], g.getFontMetrics().stringWidth(row[i]));
			}
		}

		for (int i = 1; i < 4; i++) {
			colX[i] = colX[i - 1] + colWidths[i - 1] + padding;
		}

		// Right-align COST to the right edge of the ShipInfoBar
		colX[4] = x + width - padding - colWidths[4];

		// Draw the rows
		for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
			String[] row = rows.get(rowIndex);
			boolean isHeaderRow = rowIndex == 0;

			for (int i = 0; i < row.length; i++) {
				String text = row[i];
				int drawX = colX[i];

				if (row[0].startsWith("▼") || row[0].startsWith("▶")) {
					g.setFont(new Font("Dialog", Font.PLAIN, 14)); // Use fallback font for arrows
				} else {
					g.setFont(alternityLiteFont.deriveFont(isHeaderRow ? Font.BOLD : Font.PLAIN, 14F));
				}

				int textWidth = g.getFontMetrics().stringWidth(text);

				if (i == 1 || i == 2) {
					drawX = colX[i] + (colWidths[i] - textWidth) / 2; // center POW, HULL

				} else if (i == 4) {
					drawX = colX[i] + (colWidths[i] - textWidth); // no shift, already anchored right
				}

				g.drawString(text, drawX, infoY + rowIndex * lineHeight);
			}
		}
	}

	private void drawCompInfo(Graphics g, int infoX, int infoY, int i) {
		Graphics2D g2d = (Graphics2D) g;

		int infoTab = 50;
		int infoGap = 20;

		g.setColor(PHB_TEXT);
		g.setFont(alternityLiteFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 14F));
		i++;

		if (building.getNewShip().getHull() != null) {
			for (ShipCompartment compartment : building.getNewShip().getCompartments()) {
				g.drawString("ZONE " + compartment.getName() + " >>", infoX + infoTab, infoY + infoGap * i);
				i++;
			}
		}
	}

	public void mouseClicked(int mouseX, int mouseY) {
		for (Map.Entry<String, Rectangle> entry : typeClickZones.entrySet()) {
			if (entry.getValue().contains(mouseX, mouseY)) {
				String type = entry.getKey();
				boolean current = systemTypeExpanded.get(type);
				systemTypeExpanded.put(type, !current);
				break;
			}
		}
	}

	public void mouseDoubleClicked(int mouseX, int mouseY) {
		if (nameBounds != null && nameBounds.contains(mouseX, mouseY)) {
			renamingShip = true;
			tempName = new StringBuilder(building.getNewShip().getName());
			return;
		}
	}

	public void mouseMoved(int x, int y) {

	}

	public void mousePressed(int x, int y) {

	}

	public void mouseReleased(int x, int y) {

	}

	public void keyPressed(KeyEvent e) {
		if (!renamingShip)
			return;

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			building.getNewShip().setName(tempName.toString());
			renamingShip = false;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			renamingShip = false;
		} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (tempName.length() > 0)
				tempName.deleteCharAt(tempName.length() - 1);
		} else {
			char c = e.getKeyChar();
			if (Character.isLetterOrDigit(c) || Character.isSpaceChar(c) || "-_".indexOf(c) != -1) {
				tempName.append(c);
			}
		}
	}

	public void update() {
	}
}
