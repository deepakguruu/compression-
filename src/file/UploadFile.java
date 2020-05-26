package file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;

import com.opensymphony.xwork2.ActionSupport;

import compress.Compress;

/**
 * @author Deepak
 *
 */
public class UploadFile extends ActionSupport {
   
	private static final long serialVersionUID = 1L;
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private String destPath= "C:/work_files/";
	private String compressedFile;
	private float compressionPercentage;
	private float afterSize;
	private float beforeSize;
	private long timeTaken;
	private static HashMap<String,ArrayList<String>> algorithms=null;
    
	public String execute() {
     
	try {
    	 if(algorithms==null){
    		 loadAlgorithms();
    	 }
    	 File destFile  = new File(destPath, myFileFileName);
         long startTime = System.currentTimeMillis();
         ArrayList<String> list =getCompressionAlgorithms(myFileContentType);
         Compress c=new Compress(myFile,destFile);
         CyclicBarrier barrier=c.handleCompress(list);
         barrier.await();
         
         setTimeTaken(startTime);
         setCompressedFile(c.getDestination().getPath());
         setBeforeSize(myFile.length());
         setAfterSize(c.getDestination().length());
         setCompressionPercentage((float)(getBeforeSize())/getAfterSize());
      } catch(Exception e) {
         e.printStackTrace();
         return ERROR;
      }
      return SUCCESS;
   }

	@SuppressWarnings("unchecked")
	public void loadAlgorithms(){
		algorithms = new HashMap<>();
		ArrayList<String> compression = new ArrayList<>();
		compression.add("JPEGCompress");
		compression.add("JPEGCompress");
		compression.add("JPEGCompress");
		algorithms.put("image/jpeg",(ArrayList<String>)compression.clone() );
		compression.clear();
		compression.add("PNGCompress");
		algorithms.put("image/png",(ArrayList<String>)compression.clone() );
		compression.clear();
		compression.add("FileCompress");
		algorithms.put("application/text",(ArrayList<String>)compression.clone() );
		compression.clear();
		compression.add("FileCompress");
		algorithms.put("text/plain",(ArrayList<String>)compression.clone() );
		compression.clear();
	}
	
	public static ArrayList<String> getCompressionAlgorithms(String contentType){
		return algorithms.get(contentType);
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

public long getTimeTaken() {
	return timeTaken;
}

public void setTimeTaken(long timeTaken) {
	this.timeTaken = timeTaken;
}


public static HashMap<String,ArrayList<String>> getAlgorithms() {
	return algorithms;
}


public static void setAlgorithms(HashMap<String,ArrayList<String>> algorithms) {
	UploadFile.algorithms = algorithms;
}
}