package compress;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CyclicBarrier;

import compress.file.FileCompress;
import compress.image.JPEGCompress;
import compress.image.PNGCompress;

/**
 * @author Deepak
 *
 */
public class Compress{

	protected File source;
	protected File destination;
	public CyclicBarrier barrier;
	
	public Compress(File src,File dst){
		setSource(src);
		setDestination(dst);
	}
	
	public Compress(Compress comp) {
		// TODO Auto-generated constructor stub
		this.source=comp.source;
		this.barrier=comp.barrier;
	}
	public static String str="C:/work_files/temp/";
	public CyclicBarrier handleCompress(ArrayList<String> list){
		
		
		//ML_function
		
		if(true){
			int i;
			int num=list.size();
			Thread threads[] = new Thread[num];
			File destFile[] = new File[num];
			long totalTime[] = new long[num];
			barrier = new CyclicBarrier(num+1);
			Iterator<String> iter =  list.iterator();
			for(i=0; i<num;i++){
				switch (iter.next()){
				case "FileCompress":
					destFile[i]=new File(str+"compressed"+i+".txt");
					threads[i]=new Thread(new FileCompress(this,destFile[i]));
					break;
				case "JPEGCompress":
					destFile[i]=new File(str+"compressed"+i+".jpg");
					threads[i]=new Thread( new JPEGCompress(this,destFile[i]));
					break;
				case "PNGCompress":
					destFile[i]=new File(str+"compressed"+i+".png");
					threads[i]=new Thread( new PNGCompress(this,destFile[i]));
					break;
				}
				long startTime=System.nanoTime();
				System.out.println(startTime);
				threads[i].start();
				long endTime=System.nanoTime();
				totalTime[i]=endTime-startTime;
			}
			bestCalculate(totalTime,destFile,num);
		}
		return barrier;
	}
	
	private void bestCalculate(long[] totalTime, File[] destFile,int num) {
		// TODO Auto-generated method stub
		int best=0;
		for(int i=0;i<num;i++){
			
		}
		destination=destFile[best];
	}


//	public long compress(Thread thread, String alg,File destFile) {
//		// TODO Auto-generated method stub
//		
//		
//		return ();
//	}
	
	public File getSource() {
		return source;
	}
	public void setSource(File source) {
		this.source = source;
	}
	public File getDestination() {
		return destination;
	}
	public void setDestination(File destination) {
		this.destination = destination;
	}
}
