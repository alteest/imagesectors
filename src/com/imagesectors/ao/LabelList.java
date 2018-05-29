package com.imagesectors.ao;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LabelList {

	private ArrayList<Label> labels;
	
	public LabelList() {
		this.labels = new ArrayList<Label>();
	}

	public Label getLabelByName(String name) {
		for (Label label : labels) {
			if (name.equals(label.getName())) {
				return label;
			}
		}
		return null;
	}
	
	public void readFromCSV(String filename) {
		File file = new File(filename);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		BufferedReader infile = new BufferedReader(reader);
		String line = "";
		try {
			boolean done = false;
			while (!done) {
				line = infile.readLine();
				if (line == null) {
					done = true;
				} else {
	               String[] tokens = line.trim().split(",");
	               String name = tokens[0].trim();
	               int id = Integer.parseInt(tokens[1].trim());
	               Label label = new Label(id, name);
	               label.setColor(new Color((int)(Math.random() * 0x1000000)));
	               labels.add(label);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}		
	}
	
	public Object[][] convert2Data() {
		Object[][] data = new Object[labels.size()][3];
		for (int i = 0; i < labels.size(); i++) {
			data[i][0] = labels.get(i).getName();
			data[i][1] = labels.get(i).getId();
			data[i][2] = labels.get(i).getColor();
		}
		return data;
	}
}
