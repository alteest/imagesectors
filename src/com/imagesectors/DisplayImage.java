package com.imagesectors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.imagesectors.ao.SectorList;
import com.imagesectors.component.ImageLabel;
import com.imagesectors.component.SectorsTableListSelectionListener;

@SuppressWarnings("serial")
public class DisplayImage extends JFrame {
    
	private Object[][] data; 
	private String[] columnNames = {"Name","W1","H1","W2","H2","Label"}; 
	private DefaultTableModel tableModel; 
	private JTable table; 
	private SectorList sectors;
	private ImageLabel imageLabel;

	public DisplayImage() {

        initUI();
    }

    private void initUI() {       
        
        createLayout();

        setSize(1500,  900);
        setTitle("Image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createLayout() {
    	setLayout(new FlowLayout());
    	
        sectors = new SectorList();
        sectors.readFromCSV("src/data.csv");
        data = sectors.convert2Data();
        tableModel = new DefaultTableModel(data, columnNames);

        imageLabel = new ImageLabel(null);

        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(240);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(new SectorsTableListSelectionListener(table, imageLabel));
        
        JScrollPane scrollPane = new JScrollPane(table); 
        scrollPane.setPreferredSize(new Dimension(500, 800)); 
        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout()); 
        panel.add(scrollPane, BorderLayout.WEST);
        
        panel.add(imageLabel, BorderLayout.CENTER);

        outerPanel.add(panel, BorderLayout.BEFORE_FIRST_LINE);
        
        add(outerPanel);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            DisplayImage ex = new DisplayImage();
            ex.setVisible(true);
        });
    }
}