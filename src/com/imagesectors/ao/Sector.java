package com.imagesectors.ao;

public class Sector {
	
	private String name;
	private int w1;
	private int h1;
	private int w2;
	private int h2;
	private String label;
	
	public Sector(String name, int w1, int h1, int w2, int h2, String label) {
		this.setName(name);
		this.setPos1(w1, h1);
		this.setPos2(w2, h2);
		this.setLabel(label);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getW1() {
		return w1;
	}

	public int getH1() {
		return h1;
	}

	public void setPos1(int w, int h) {
		this.w1 = w;
		this.h1 = h;
	}

	public int getW2() {
		return w2;
	}

	public int getH2() {
		return h2;
	}

	public void setPos2(int w, int h) {
		this.w2 = w;
		this.h2 = h;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
