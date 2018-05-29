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
	
	public ArrayList<Sector> getSectorsByName(String name) {
		ArrayList<Sector> s = new ArrayList<Sector>();
		for (Sector sector: sectors) {
			if (name.equals(sector.getName()) && !sector.getLabel().equals("")) {
				s.add(sector);
			}
		}
		return s;
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
	               int w1, h1, w2, h2;
	               String label;
	               if (tokens.length > 1) {
	            	   w1 = SectorList.getInt(tokens[1].trim());
	            	   h1 = SectorList.getInt(tokens[2].trim());
	            	   w2 = SectorList.getInt(tokens[3].trim());
	            	   h2 = SectorList.getInt(tokens[4].trim());
	            	   label = tokens[5].trim();
	               } else {
	            	   w1 = -1;
	            	   h1 = -1;
	            	   w2 = -1;
	            	   h2 = -1;
	            	   label = "";
	               }
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
	
	public static int getInt(String value) {
		if (value.equals("") || value == null) {
			return -1;
		}
		return Integer.parseInt(value);		
	}
}
