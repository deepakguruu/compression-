package compress;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CyclicBarrier;

import compress.file.FileCompress;
import compress.image.JPEGCompress;
import compress.image.PNGCompress;

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
				totalTime[i]=compress(threads[i],iter.next(),destFile[i]);
			}
			bestCalculate(totalTime,destFile);
		}
		return barrier;
	}
	
	private void bestCalculate(long[] totalTime, File[] destFile) {
		// TODO Auto-generated method stub
		
	}


	public long compress(Thread thread, String alg,File destFile) {
		// TODO Auto-generated method stub
		switch (alg){
		case "FileCompress":
			thread=new Thread(new FileCompress(this,destFile));
			break;
		case "JPEGCompress":
			thread=new Thread( new JPEGCompress(this,destFile));
			break;
		case "PNGCompress":
			thread=new Thread( new PNGCompress(this,destFile));
			break;
		}
		long startTime=System.currentTimeMillis();
		thread.start();
		long endTime=System.currentTimeMillis();
		return (endTime-startTime);
	}
	
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
