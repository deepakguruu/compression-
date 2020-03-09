package compress.image;

import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import compress.Compress;

public class JPEGCompress extends Compress implements Runnable {
	
	public JPEGCompress(Compress compress) {
		// TODO Auto-generated constructor stub
		super(compress);
	}
	public JPEGCompress(Compress compress, File destFile) {
		// TODO Auto-generated constructor stub
		super(compress);
		destination=destFile;
	}
	public void run() {
		// TODO Auto-generated method stub
		RenderedImage image;
		try {
			image = ImageIO.read(source);
		ImageWriter jpegWriter = ImageIO.getImageWritersByFormatName("jpg").next();
		ImageWriteParam jpegWriteParam = jpegWriter.getDefaultWriteParam();
		jpegWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegWriteParam.setCompressionQuality(0.5f);
		try(ImageOutputStream output = ImageIO.createImageOutputStream(destination)){
			jpegWriter.setOutput(output);
			IIOImage outputImage = new IIOImage(image,null,null);
			jpegWriter.write(null,outputImage,jpegWriteParam);
		}
		jpegWriter.dispose();
		barrier.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
