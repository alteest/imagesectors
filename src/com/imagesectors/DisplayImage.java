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
        
        createLayout();

        setSize(1500,  900);
        setTitle("Image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private ImageIcon loadImage() {

        ImageIcon ii = new ImageIcon("src/images/test1.png");
        return ii;
    }

    private void createLayout() {
    	setLayout(new FlowLayout());
    	
        sectors = new SectorList();
        sectors.readFromCSV("src/data.csv");
        data = sectors.convert2Data();
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(240);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table); 
        scrollPane.setPreferredSize(new Dimension(500, 800)); 
        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout()); 
        panel.add(scrollPane, BorderLayout.WEST);
        
        ImageIcon ii = loadImage();
        JLabel label = new JLabel(DisplayImage.scaleImage(ii.getImage(), 800, 800));
        label.setPreferredSize(new Dimension(800, 800));

        panel.add(label, BorderLayout.CENTER);

        outerPanel.add(panel, BorderLayout.BEFORE_FIRST_LINE);
        
        add(outerPanel);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            DisplayImage ex = new DisplayImage();
            ex.setVisible(true);
        });
    }

    public static ImageIcon scaleImage(Image image,
    		int newWidth, int newHeight) {
    	// Make sure the aspect ratio is maintained, so the image is not distorted
    	double thumbRatio = (double) newWidth / (double) newHeight;
    	int imageWidth = image.getWidth(null);
    	int imageHeight = image.getHeight(null);
    	double aspectRatio = (double) imageWidth / (double) imageHeight;

    	if (thumbRatio < aspectRatio) {
    		newHeight = (int) (newWidth / aspectRatio);
    	} else {
    		newWidth = (int) (newHeight * aspectRatio);
    	}

    	float ratio = (float) newWidth / imageWidth;
    	// Draw the scaled image
    	BufferedImage newImage = new BufferedImage(newWidth, newHeight,
    			BufferedImage.TYPE_INT_RGB);
    	System.out.println("Original : " + Integer.toString(imageWidth) + ',' + Integer.toString(imageHeight));
    	System.out.println("Ratio : " + Float.toString(ratio));
    	Graphics2D graphics2D = newImage.createGraphics();
    	graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    	graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);
    	
    	graphics2D.setColor(Color.RED);
    	graphics2D.drawRect((int) (1 * ratio), (int) (1 * ratio), (int) (100 * ratio), (int) (100 * ratio));
    	graphics2D.dispose();    	
    	
    	return new ImageIcon(newImage);
    }
}