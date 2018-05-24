package com.imagesectors;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.imagesectors.ao.SectorList;

@SuppressWarnings("serial")
public class DisplayImage extends JFrame {
    
	private Object[][] data; 
	private String[] columnNames = {"Name","W1","H1","W2","H2","Label"}; 
	private DefaultTableModel tableModel; 
	private JTable table; 
	private SectorList sectors;	

	public DisplayImage() {

        initUI();
    }

    private void initUI() {       
        
        ImageIcon ii = loadImage();

        JLabel label = new JLabel(ii);

        createLayout(label);

        setSize(700,  700);
        setTitle("Image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private ImageIcon loadImage() {

        ImageIcon ii = new ImageIcon("src/images/test1.png");
        return ii;
    }

    private void createLayout(JComponent... arg) {
    	setLayout(new FlowLayout());
    	
        //Container pane = getContentPane();
        //GroupLayout gl = new GroupLayout(pane);
        //pane.setLayout(gl);

        sectors = new SectorList();
        sectors.readFromCSV("src/data.csv");
        data = sectors.convert2Data();
        tableModel = new DefaultTableModel(data, columnNames); 
        table = new JTable(tableModel); 
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table); 
        scrollPane.setPreferredSize(new Dimension(380,280)); 
        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout()); 
        panel.add(scrollPane, BorderLayout.BEFORE_LINE_BEGINS);
        panel.add(arg[0], BorderLayout.CENTER);
        outerPanel.add(panel, BorderLayout.BEFORE_FIRST_LINE);
        
        add(outerPanel);
        
        //gl.setAutoCreateContainerGaps(true);
        //gl.setHorizontalGroup(gl.createSequentialGroup()
        //        .addComponent(panel)
        //);
        //gl.setHorizontalGroup(gl.createSequentialGroup()
        //        .addComponent(arg[0])
        //);
        //gl.setVerticalGroup(gl.createParallelGroup()
        //        .addComponent(panel)
        //);

        //gl.setVerticalGroup(gl.createParallelGroup()
        //        .addComponent(arg[0])
        //);

        //pack();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            DisplayImage ex = new DisplayImage();
            ex.setVisible(true);
        });
    }
}