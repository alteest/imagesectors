package com.imagesectors;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.imagesectors.ao.LabelList;
import com.imagesectors.ao.SectorList;
import com.imagesectors.component.ColorRenderer;
import com.imagesectors.component.ImageLabel;
import com.imagesectors.component.SectorsTableListSelectionListener;

@SuppressWarnings("serial")
public class DisplayImage extends JFrame {

	private static SectorList sectors = new SectorList();
	private static LabelList labels = new LabelList();

	//private DefaultTableModel tableModel; 
	private JTable labelsTable; 
	private JTable sectorsTable; 
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
    	
        sectors.readFromCSV("src/data.csv");
        labels.readFromCSV("src/labels.csv");

        imageLabel = new ImageLabel(null);

        initSectorTable();
        initLabelTable();
        
        JScrollPane sectorsScrollPane = new JScrollPane(sectorsTable); 
        sectorsScrollPane.setPreferredSize(new Dimension(500, 600)); 

        JScrollPane labelsScrollPane = new JScrollPane(labelsTable); 
        labelsScrollPane.setPreferredSize(new Dimension(500, 200)); 

        JPanel tablePanel = new JPanel(new BorderLayout()); 
        tablePanel.add(sectorsScrollPane, BorderLayout.NORTH);
        tablePanel.add(labelsScrollPane, BorderLayout.SOUTH);
        
        JPanel panel = new JPanel(new BorderLayout()); 
        panel.add(tablePanel, BorderLayout.WEST);
        panel.add(imageLabel, BorderLayout.CENTER);

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.add(panel, BorderLayout.BEFORE_FIRST_LINE);
        
        add(outerPanel);
    }
    
    private void initSectorTable() {
        Object[][] data = sectors.convert2Data();
    	String[] columnNames = {"Name","W1","H1","W2","H2","Label"}; 
    	DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        sectorsTable = new JTable(tableModel);
        sectorsTable.getColumnModel().getColumn(0).setPreferredWidth(240);
        sectorsTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        sectorsTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        sectorsTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        sectorsTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        sectorsTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        sectorsTable.setAutoCreateRowSorter(true);
        sectorsTable.getSelectionModel().addListSelectionListener(new SectorsTableListSelectionListener(sectorsTable, imageLabel));
    }

    private void initLabelTable() {
        Object[][] data = labels.convert2Data();
    	String[] columnNames = {"Label", "Index", "Color"}; 
    	DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        labelsTable = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {                
                return false;
            }
        };
        labelsTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        labelsTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        labelsTable.getColumnModel().getColumn(2).setPreferredWidth(260);
        labelsTable.setAutoCreateRowSorter(true);
        
        labelsTable.getColumn("Color").setCellRenderer(new ColorRenderer());
    }

    public static LabelList getLabels() {
    	return labels;
    }

    public static SectorList getSectors() {
    	return sectors;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            DisplayImage ex = new DisplayImage();
            ex.setVisible(true);
        });
    }
}