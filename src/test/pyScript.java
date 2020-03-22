package test;

import java.io.Writer;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Deepak
 *
 */
public class pyScript {
	    
	
	String source ="loc_x += 1\n" + "loc_z = loc_x\n"+"print(loc_x)\n"+"print(loc_y)\n"+"print(loc_z)";
	 ScriptEngine se;   

	    public pyScript (Entity entity) {
	        ScriptEngineManager sem = new ScriptEngineManager ();
	        se = sem.getEngineByName ( "python" );
	        Bindings b = se.createBindings();
	        b.put("entity", entity);
	        se.setBindings(b, ScriptContext.GLOBAL_SCOPE);
	    }

	    public Writer update (Entity en) throws ScriptException {
	    	ScriptContext con = se.getContext();
	    	con.setAttribute("loc_x", en.loc_x, ScriptContext.GLOBAL_SCOPE);
	    	con.setAttribute("loc_y", en.loc_y, ScriptContext.GLOBAL_SCOPE);
	    	con.setAttribute("loc_z", en.loc_z, ScriptContext.GLOBAL_SCOPE);
	    	Writer writer = null;
			con.setWriter(writer);
	    	se.eval ( source );
			Bindings b=se.getBindings(ScriptContext.GLOBAL_SCOPE);
			System.out.println(b.get("entity").toString());
			System.out.println(en.loc_x +" "+en.loc_y+" "+en.loc_z);
			return writer;
	    }
	    
	   
}
