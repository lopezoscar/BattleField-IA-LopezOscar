package com.ia.pathfinder;

import ia.battle.camp.FieldCell;

public class Node {
	private int g;
	private int h;
	private Node parent;

	private FieldCell cell;
	
	private int x, y;
	
	public Node(FieldCell cell) {
		this.x = cell.getX();
		this.y = cell.getY();
		this.cell = cell;
	}
	
	public int getF() {
		return g + h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public FieldCell getCell() {
		return cell;
	}

	public boolean equals(Object obj) {
		Node other = (Node)obj;
		
		return this.x == other.getX() && this.y == other.getY();
	}
	
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
