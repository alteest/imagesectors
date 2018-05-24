package com.imagesectors.ao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SectorList {

	private ArrayList<Sector> sectors;
	
	public SectorList() {
		this.sectors = new ArrayList<Sector>();
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
				}
				else {
	               String[] tokens = line.trim().split(",");
	               String name = tokens[0].trim();
	               int w1 = Integer.parseInt(tokens[1].trim());
	               int h1 = Integer.parseInt(tokens[2].trim());
	               int w2 = Integer.parseInt(tokens[3].trim());
	               int h2 = Integer.parseInt(tokens[4].trim());
	               String label = tokens[5].trim();
	               Sector sector = new Sector(name, w1, h1, w2, h2, label);
	               sectors.add(sector);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}		
	}
	
	public Object[][] convert2Data() {
		Object[][] data = new Object[sectors.size()][6];
		for (int i = 0; i < sectors.size(); i++) {
			data[i][0] = sectors.get(i).getName();
			data[i][1] = sectors.get(i).getW1();
			data[i][2] = sectors.get(i).getH1();
			data[i][3] = sectors.get(i).getW2();
			data[i][4] = sectors.get(i).getH2();
			data[i][5] = sectors.get(i).getLabel();
		}
		return data;
	}
}
