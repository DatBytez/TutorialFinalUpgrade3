package ui;

import static ship.helpz.Constants.*;
import static ship.helpz.Format.getDashedString;
import static ship.helpz.Format.getMoneyString;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import scenes.BuildScene;
import ship.ShipCompartment;
import ship.systems.Armor;
import ship.systems.BaseSystem;
import ship.systems.Hull;
import ship.systems.ShipSystem;

public class ShipInfoBar extends Bar {

	private BuildScene building;

	private int width, height;
	private Rectangle bounds;
	private int titleOffset = 60;

	private Rectangle nameBounds; // Click area for the name
	private boolean renamingShip = false;
	private StringBuilder tempName = new StringBuilder(); // For live input

	private Map<String, Boolean> systemTypeExpanded = new TreeMap<>(); // Track expanded/collapsed
	private Map<String, Rectangle> typeClickZones = new TreeMap<>(); // Track clickable areas
	
	// Dropdown Menu
	private Map<ShipSystem<?>, Rectangle> compDropdownZones = new java.util.IdentityHashMap<>();
	private ShipSystem<?> activeDropdownSystem = null;
	private Rectangle dropdownBounds = null;
	private boolean showingDropdown = false;
	private int hoveredDropdownIndex = -1;


	public ShipInfoBar(int x, int y, int width, int height, BuildScene building) {
		super(x, y, width, height);
		this.building = building;
		this.width = width;
		this.height = height;
		this.building = building;
		setStyle("extra");
		hasTitleBar = false;
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

	@Override
	protected void drawTitle(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(PHB_TITLE);
		g.setFont(alternityHeadFont);
		g.setFont(g.getFont().deriveFont(Font.BOLD, 16F));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		String displayName = renamingShip ? tempName.toString() + "|" : building.getNewShip().getName();
		int titleWidth = g.getFontMetrics().stringWidth(displayName);
		int titleX = x + (width / 2) - titleWidth / 2;
		int titleY = y + TITLE_MARGIN;

		g.drawString(displayName, titleX, titleY);

		// Track clickable area
		nameBounds = new Rectangle(titleX, titleY - 16, titleWidth, 20);

		if (hasTitleBar) 
			drawTitleBar(g,TITLE_MARGIN);
	}

	protected void drawBackground(Graphics g) {
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
		ArrayList<ShipSystem<?>> rowToSystem = new ArrayList<>();

		addRow(rows, rowToSystem, new String[] { "TYPE", "COMP", "POW", "HULL", "SYSTEM", "COST" }, null);

		Hull hull = building.getNewShip().getHull();
		if (hull != null) {
			addRow(rows, rowToSystem, new String[] {
				"Hull", "-", getDashedString((int) hull.getCalculatedPowerCost()), getDashedString(hull.getCalculatedHullCost(hull)),
				hull.getName(), getMoneyString(hull.getCalculatedCost(hull))
			}, null);
		} else {
			addRow(rows, rowToSystem, new String[] { "Hull", "-", "-", "-", "Select a Hull", "-" }, null);
		}

		Armor armor = building.getNewShip().getArmor();
		if (armor != null) {
			addRow(rows, rowToSystem, new String[] {
				"Armor", "-", getDashedString((int) armor.getCalculatedPowerCost()), getDashedString(armor.getCalculatedHullCost(hull)),
				armor.getName(), getMoneyString(armor.getCalculatedCost(hull))
			}, null);
		} else {
			addRow(rows, rowToSystem, new String[] { "Armor", "-", "-", "-", "Select Armor", "-" }, null);
		}

		Map<String, List<ShipSystem<?>>> systemGroups = new TreeMap<>();
		for (ShipSystem<?> system : building.getNewShip().getSystemList()) {
			if (system == hull || system == armor) continue;
			String type = system.getClass().getSimpleName();
			systemGroups.computeIfAbsent(type, k -> new ArrayList<>()).add(system);
		}

		int padding = 5;
		int sidePadding = 20;
		int[] colWidths = new int[6];
		int[] colX = new int[6];
		colX[0] = x + sidePadding;
		
		// Calculate max width per column
		for (String[] row : rows) {
			for (int i = 0; i < row.length; i++) {
				colWidths[i] = Math.max(colWidths[i], g.getFontMetrics().stringWidth(row[i]));
			}
		}

		// Set column X positions
		for (int i = 1; i < 5; i++) {
			colX[i] = colX[i - 1] + colWidths[i - 1] + padding;
		}
		colWidths[5] = Math.max(colWidths[5], g.getFontMetrics().stringWidth("COST"));
		colX[5] = x + width - sidePadding - colWidths[5];



		for (String type : systemGroups.keySet()) {
			List<ShipSystem<?>> group = systemGroups.get(type);
			systemTypeExpanded.putIfAbsent(type, true);

			int headerY = infoY + (rows.size()) * lineHeight;
			Rectangle clickZone = new Rectangle(colX[0], headerY - lineHeight + 4, 100, lineHeight);
			typeClickZones.put(type, clickZone);

			String label = (systemTypeExpanded.get(type) ? "▼ " : "▶ ") + type;
			addRow(rows, rowToSystem, new String[] { label, "", "", "", "", "" }, null);

			if (systemTypeExpanded.get(type)) {
				for (ShipSystem<?> system : group) {
					// Safely get the compartment name
					String compName = "-";
					if (system.getCompartment() != null && system.getCompartment().getName() != null) {
						compName = system.getCompartment().getName();
					}

					rows.add(new String[] {
						"",
						compName,
						getDashedString((int) system.getCalculatedPowerCost()),
						getDashedString(system.getCalculatedHullCost(hull)),
						system.getName(),
						getMoneyString(system.getCalculatedCost(hull))
					});
					rowToSystem.add(system);
				}
			}
		}


		for (String[] row : rows) {
			for (int i = 0; i < row.length; i++) {
				colWidths[i] = Math.max(colWidths[i], g.getFontMetrics().stringWidth(row[i]));
			}
		}

		// Set TYPE through HULL positions (0–3)
		for (int i = 1; i < 4; i++) {
			colX[i] = colX[i - 1] + colWidths[i - 1] + padding;
		}

		// SYSTEM column should always follow HULL directly
		colX[4] = colX[3] + colWidths[3] + padding;

		// COST stays right-aligned
		colX[5] = x + width - sidePadding - colWidths[5];



		for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
			String[] row = rows.get(rowIndex);

			for (int i = 0; i < row.length; i++) {
				String text = row[i];
				String displayText = text;
				Color textColor = PHB_TEXT;

				if (i == 2 || i == 3) {
					try {
						int value = Integer.parseInt(text);
						if (value < 0) {
							displayText = "+" + Math.abs(value);
							textColor = PHB_LIST_TITLE;
						} else {
							displayText = String.valueOf(value);
						}
					} catch (NumberFormatException ignored) {}
				}
				
				if (rowIndex == 0) {
					g.setFont(alternityLiteFont.deriveFont(Font.BOLD, 14F)); // Column header
				} else if (row[0].startsWith("▼") || row[0].startsWith("▶")) {
					g.setFont(new Font("Dialog", Font.PLAIN, 14)); // Group header (collapsible)
				} else {
					g.setFont(alternityLiteFont.deriveFont(Font.PLAIN, 14F)); // Standard row
				}


				int displayTextWidth = g.getFontMetrics().stringWidth(displayText);
				int drawX;

				if (i == 1 || i == 2 || i == 3) {
					drawX = colX[i] + (colWidths[i] - displayTextWidth) / 2; // Center COMP, POW, HULL
				} else if (i == 5) {
					drawX = colX[i] + (colWidths[i] - displayTextWidth);     // Right-align COST
				} else if (i == 4) {
					drawX = colX[4]; // SYSTEM — left-aligned and full length
				} else {
					drawX = colX[i]; // Left-align TYPE
				}

				g.setColor(textColor);
				g.drawString(displayText, drawX, infoY + rowIndex * lineHeight);

				if (i == 1 && rowToSystem.get(rowIndex) != null) {
					ShipSystem<?> system = rowToSystem.get(rowIndex);
					int dropX = drawX - 15; // Move icon to the left of text
					int dropY = infoY + rowIndex * lineHeight - 14;
					g.setFont(new Font("Dialog", Font.PLAIN, 10));
					g.drawString("▼", dropX, dropY + 10);
					compDropdownZones.put(system, new Rectangle(dropX, dropY, 12, 12));
				}
			}
		}
		
		// MAYBE MAKE INTO IT'S OWN METHOD
		if (showingDropdown && activeDropdownSystem != null) {
			ArrayList<ShipCompartment> compartments = building.getNewShip().getCompartments();

			int dropX = compDropdownZones.get(activeDropdownSystem).x;
			int dropY = compDropdownZones.get(activeDropdownSystem).y + 12;
			int menuWidth = 60;
			int itemHeight = 18;
			int menuHeight = (compartments.size() + 1) * itemHeight; // +1 for DROP option
			
			dropdownBounds = new Rectangle(dropX, dropY, menuWidth, menuHeight);

			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect(dropX - 5, dropY - 5, menuWidth, menuHeight + 10, 12, 12); // Rounded corners
			g.setFont(new Font("Dialog", Font.PLAIN, 12));

			for (int i = 0; i <= compartments.size(); i++) {
				if (i == hoveredDropdownIndex) {
					// Draw hover background
					g.setColor(new Color(80, 80, 80));
					g.fillRoundRect(dropX -5, dropY + i * itemHeight, menuWidth, itemHeight, 6, 6);
				}
				g.setColor(Color.WHITE);
				String name = (i < compartments.size()) ? compartments.get(i).getName() : "DROP";
				g.drawString(name, dropX + 5, dropY + (i + 1) * itemHeight - 4);
			}


		}
	}
	
	private void addRow(ArrayList<String[]> rows, ArrayList<ShipSystem<?>> rowToSystem, String[] rowData, ShipSystem<?> system) {
		rows.add(rowData);
		rowToSystem.add(system);
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
		
		// DROPDOWN ICON
		for (Map.Entry<ShipSystem<?>, Rectangle> entry : compDropdownZones.entrySet()) {
			if (entry.getValue().contains(mouseX, mouseY)) {
				activeDropdownSystem = entry.getKey();
				showingDropdown = true;
				return;
			}
		}
		// DROPDOWN MENU
		if (showingDropdown && dropdownBounds != null && dropdownBounds.contains(mouseX, mouseY)) {
			ArrayList<ShipCompartment> compartments = building.getNewShip().getCompartments();
			int index = (mouseY - dropdownBounds.y) / 18;

			if (index >= 0 && index < compartments.size()) {
				ShipCompartment newComp = compartments.get(index);
				if (activeDropdownSystem instanceof BaseSystem<?>) {
					ShipCompartment oldComp = activeDropdownSystem.getCompartment();
					if (oldComp != null) {
						oldComp.getShipSystems().remove(activeDropdownSystem);
					}
					newComp.getShipSystems().add(activeDropdownSystem);
					((BaseSystem<?>) activeDropdownSystem).setCompartment(newComp);
				}
			} else if (index == compartments.size()) {
				dropSystem(activeDropdownSystem);
			}

			showingDropdown = false;
			activeDropdownSystem = null;
			hoveredDropdownIndex = -1;
			return;
		}

		// CLICKED OUTSIDE DROPDOWN — close without changes
		if (showingDropdown) {
		    showingDropdown = false;
		    activeDropdownSystem = null;
		    hoveredDropdownIndex = -1;
		}
	}
	
		private void dropSystem(ShipSystem<?> system) {
			if (system instanceof BaseSystem<?>) {
				// Remove from its compartment
				ShipCompartment oldComp = system.getCompartment();
				if (oldComp != null) {
					oldComp.getShipSystems().remove(system);
				}
				((BaseSystem<?>) system).setCompartment(null);

				// Remove from ship's system list
				building.getNewShip().getSystemList().remove(system);
			}
		}



	public void mouseDoubleClicked(int mouseX, int mouseY) {
		if (nameBounds != null && nameBounds.contains(mouseX, mouseY)) {
			renamingShip = true;
			tempName = new StringBuilder(building.getNewShip().getName());
			return;
		}
	}

	public void mouseMoved(int mouseX, int mouseY) {
		if (showingDropdown && dropdownBounds != null) {
			if (dropdownBounds.contains(mouseX, mouseY)) {
				int itemHeight = 18;
				hoveredDropdownIndex = (mouseY - dropdownBounds.y) / itemHeight;
			} else {
				hoveredDropdownIndex = -1;
			}
		} else {
			hoveredDropdownIndex = -1;
		}
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
