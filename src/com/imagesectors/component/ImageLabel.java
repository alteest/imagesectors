package com.imagesectors.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.imagesectors.ao.Sector;

@SuppressWarnings("serial")
public class ImageLabel extends JLabel {

	private String filename;
	private ArrayList<Sector> sectors = new ArrayList<Sector>();
	
	public ImageLabel(String filename) {
		this.setFilename(filename);
        setPreferredSize(new Dimension(800, 800));		
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
		if (this.filename != null) {
			setIcon(scaleImage(loadImage().getImage(), 800, 800));
		}
	}

	public void setData(Object[] data) {
		sectors.clear();
		sectors.add(new Sector(String.valueOf(data[0]), (int) data[1], (int) data[2],
				(int) data[3], (int) data[4], String.valueOf(data[5])));
		setFilename(String.valueOf(data[0]));
	}
	
    private ImageIcon loadImage() {
        if (filename != null) {
        	Path filePath = Paths.get("src", "images", filename);
        	ImageIcon ii = new ImageIcon(filePath.toString());
        	return ii;
        }
        return null;
    }
    
    public ImageIcon scaleImage(Image image,
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
    	for (Sector sector : sectors) {
    		graphics2D.drawRect((int) (sector.getH1() * ratio), (int) (sector.getW1() * ratio),
    				(int) ((sector.getH2() - sector.getH1()) * ratio),
    				(int) ((sector.getW2() - sector.getW1()) * ratio));
    	}
    	graphics2D.dispose();    	
    	
    	return new ImageIcon(newImage);
    }    
}
