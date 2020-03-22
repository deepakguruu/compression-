package test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import javax.script.ScriptException;

/**
 * @author Deepak
 *
 */
public class pythonTest {
	
	public static void main (String args[]) throws ScriptException
    {

//        try {
//            ScriptEngine pyEngine = new ScriptEngineManager().getEngineByName("python");
//            ScriptContext sc =pyEngine.getContext();
//            pyEngine.eval("print \"Python - Hello, world!\"",sc);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//		 public static void main(String[] args) {
		    	Entity en = new Entity(110,0,0);
		    	pyScript p=new pyScript(en);
		    	
		    	Writer r=p.update(en);
		    	

		    	
//			}
    }
}
