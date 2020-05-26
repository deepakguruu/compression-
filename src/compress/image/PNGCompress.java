package compress.image;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import compress.Compress;

/**
 * @author Deepak
 *
 */
public class PNGCompress extends Compress implements Runnable {
	File destFile;
	public PNGCompress(Compress compress) {
		// TODO Auto-generated constructor stub
		super(compress);
	}
	
	public PNGCompress(Compress compress, File destFilex) {
		// TODO Auto-generated constructor stub
		super(compress);
		destFile=destFilex;
	}
	
	public void run() {

		BufferedImage image;
		IIOMetadata metadata;

		try (ImageInputStream in = ImageIO.createImageInputStream(source)) {
		    ImageReader reader = ImageIO.getImageReadersByFormatName("png").next();
		    reader.setInput(in, true, false);
		    image = reader.read(0);
		    metadata = reader.getImageMetadata(0);
		    reader.dispose();

		try (ImageOutputStream out = ImageIO.createImageOutputStream(destFile)) {
		    ImageTypeSpecifier type = ImageTypeSpecifier.createFromRenderedImage(image);
		    ImageWriter writer = ImageIO.getImageWriters(type, "png").next();

		    ImageWriteParam param = writer.getDefaultWriteParam();
		    if (param.canWriteCompressed()) {
		        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		        param.setCompressionQuality(0.0f);
		    }
		    writer.setOutput(out);
		    writer.write(null, new IIOImage(image, null, metadata), param);
		    writer.dispose();
		    barrier.await();
		}
	}
	 catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
