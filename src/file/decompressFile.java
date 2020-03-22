package file;

import java.io.File;
import java.io.IOException;

import compress.file.FileDecompress;

/**
 * @author Deepak
 *
 */
public class decompressFile {

	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private String destPath;
	private String compressedFile;
	
	public String execute(){
		
		destPath = "C:/decompress/";
	      try {
	         System.out.println(myFile.length());
	         System.out.println(myFileContentType);
	         File destFile  = new File(destPath, myFileFileName);
//	         FileUtils.copyFile(myFile, destFile);
//	         StartClass.compress(myFile, destPath);
	         FileDecompress.decompress(myFile, destFile);
	         setCompressedFile(new StringBuilder(destPath).append(myFileFileName).toString());
	         
	         
	         setBeforeSize(destFile.length());
	         setAfterSize(myFile.length());
	         setCompressionPercentage((float)(getBeforeSize())/getAfterSize());
	         
	         
	         
	         
	         
	      }catch(IOException e) {
	          e.printStackTrace();
	          return ERROR;
	       }
	       return SUCCESS;
		
	}
	   
	   public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public String getCompressedFile() {
		return compressedFile;
	}

	public void setCompressedFile(String compressedFile) {
		this.compressedFile = compressedFile;
	}

	public float getCompressionPercentage() {
		return compressionPercentage;
	}

	public void setCompressionPercentage(float compressionPercentage) {
		this.compressionPercentage = compressionPercentage;
	}

	public float getAfterSize() {
		return afterSize;
	}

	public void setAfterSize(float afterSize) {
		this.afterSize = afterSize;
	}

	public float getBeforeSize() {
		return beforeSize;
	}

	public void setBeforeSize(float beforeSize) {
		this.beforeSize = beforeSize;
	}

	private float compressionPercentage;
	   private float afterSize;
	   private float beforeSize;

	   
	


}
