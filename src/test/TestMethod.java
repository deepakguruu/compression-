package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import compress.Compress;
import compress.file.FileCompress;

public class TestMethod {
	public static void main(String[] args) {
		try {
			Class c = Class.forName("compress.file.FileCompress");
			Method method = c.getMethod("run");
//			FileCompress x = new FileCompress(null,null);
			try {
				method.invoke(null,null);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// VehicleConfiguration.class can be replaced by
		// Class.forName("VehicleConfiguration") 
		// if VehicleConfiguration is the fully qualified, ie. with packages, name of the class
		// other you need Class.forName("com.yourpackage.VehicleConfiguration")
	}
}
