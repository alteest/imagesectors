package com.imagesectors.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class ColorRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component  comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Color color = (Color) table.getValueAt(row, 2);
        comp.setBackground(color);
        comp.setForeground(color);
        return comp;
    }
}
