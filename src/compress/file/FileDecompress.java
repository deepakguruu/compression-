package compress.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;


/**
 * @author Deepak
 *
 */
public class FileDecompress {
	
	public static void decompress(File source, File destination) throws IOException{
		// TODO Auto-generated method stub
		byte[] buffer = new byte[1024];
		FileInputStream fis= new FileInputStream(source);
		FileOutputStream fos= new FileOutputStream(destination);
		GZIPInputStream gzis	=	new GZIPInputStream(fis);
		int read;
		while((read=gzis.read(buffer))!=-1){
			fos.write(buffer,0,read);
		}
		gzis.close();
		fis.close();
		fos.close();
	}
	
	
	public static void main(String[] args) {
		File source	=	new File("C:\\work_files\\opt.txt");	
		File destination =	new File("C:\\work_files\\opt2.txt");
		
		try{
			decompress(source,destination);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
