package compress.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import compress.Compress;

/**
 * @author Deepak
 *
 */
public class FileCompress extends Compress implements Runnable {
	File destFile;
	public FileCompress(Compress compress) {
		// TODO Auto-generated constructor stub
		super(compress);
	}
	public FileCompress(Compress compress, File destFilex) {
		// TODO Auto-generated constructor stub
		super(compress);
		destFile=destFilex;
	}

	public void run(){
		// TODO Auto-generated method stub
		byte[] buffer = new byte[1024];
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(source);
			fos = new FileOutputStream(destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		GZIPOutputStream gzos	=	new GZIPOutputStream(fos);
		int read;
		while((read=fis.read(buffer))!=-1){
			gzos.write(buffer,0,read);
		}
		gzos.finish();
		gzos.close();
		fis.close();
		fos.close();
		barrier.await();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
