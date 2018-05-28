package com.imagesectors.component;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SectorsTableListSelectionListener implements ListSelectionListener {

	private JTable table;
	private ImageLabel imageLabel;
	
	public SectorsTableListSelectionListener(JTable table, ImageLabel imageLabel) {
		this.table = table;
		this.imageLabel = imageLabel;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (event.getValueIsAdjusting()) {

			//String selectedData = null;
			Object[] rowValues = new Object[table.getColumnCount()];

			int row = table.getSelectedRow();
			for (int j = 0; j < table.getColumnCount(); j++) {
				//selectedData = String.valueOf(table.getValueAt(row, j));
				//System.out.println("Selected: " + selectedData);
				rowValues[j] = table.getValueAt(row, j);
			}
			imageLabel.setData(rowValues);
			
			/*int[] selectedRow = table.getSelectedRows();
  	    	int[] selectedColumns = table.getSelectedColumns();

		    for (int i = 0; i < selectedRow.length; i++) {
			    for (int j = 0; j < selectedColumns.length; j++) {
				    selectedData = String.valueOf(table.getValueAt(selectedRow[i], selectedColumns[j]));
			    }
		    }
		    System.out.println("Selected: " + selectedData);*/
		}
	}
}
