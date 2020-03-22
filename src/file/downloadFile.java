package file;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
 
import com.opensymphony.xwork2.ActionSupport;
 
/**
 * @author Deepak
 *
 */
public class downloadFile extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private InputStream inputStream;
    private String fileName;
    private String fileLocation;
    private long contentLength;
 
    public String execute() throws FileNotFoundException {
         
        File fileToDownload = new File(fileLocation);
        inputStream = new FileInputStream(fileToDownload);
        fileName = fileToDownload.getName();
        contentLength = fileToDownload.length();
         
        return SUCCESS;
    }
     
    public long getContentLength() {
        return contentLength;
    }
 
    public String getFileName() {
        return fileName;
    }
 
    public InputStream getInputStream() {
        return inputStream;
    }

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}  
}