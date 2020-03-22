package test;

import java.io.*;
import java.rmi.server.ExportException;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Deepak
 *
 */
public class StartClass {
//    final static byte DOT=46;
    final static int SPACE=-2;
    static Connection c = null;
    static Statement stmt = null;

    public static boolean insert(int ID,String Value) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO MATCH(ID,VALUE) VALUES('"+ID+"','"+Value+"')";
            stmt.executeUpdate(sql);
        }catch(java.sql.SQLException e){e.printStackTrace();return false;}
        return true;
    }

    public static String fetch(int ID){

       try {
           stmt = c.createStatement();
           String sql = "SELECT VALUE FROM MATCH WHERE ID = " + ID + "";
           ResultSet rs=stmt.executeQuery(sql);
           while(rs.next()){
               return rs.getString("VALUE");
           }
       }catch(java.sql.SQLException e){e.printStackTrace();}
        return "f";
    }

    public static int fetch(String val){
        try {
            stmt = c.createStatement();
            String sql = "SELECT ID FROM MATCH WHERE VALUE = '" + val + "'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                return rs.getInt("ID");
            }
        }catch(java.sql.SQLException e){e.printStackTrace();}
        return -1;
    }

    public static void compress(File iFile,String destPath) throws IOException {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "postgres", "2465");
            System.out.println("Opened database successfully");

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        HashMap<String,Integer> hm=new HashMap<String,Integer>();
        HashMap<Integer,String> hm2=new HashMap<Integer,String>();
        int b=0;
        Scanner f = null;
        FileOutputStream o = null;
//        String s=new String("Hello");
        try {
            f = new Scanner(iFile); //input file path
            o = new FileOutputStream(destPath+"\\output.bin");   //output file path
//            f = new Scanner(new File("C:\\Users\\Deepak\\docs\\alice29.txt")); //input file path

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
//        Pattern p=Pattern.compile("\\p{Space}|\\p{Punct}");
//        f.useDelimiter(p);
        boolean flag=false;
        char ch = 0;
        while (f.hasNext()) {
            String s = f.next();
            if(s.matches("(.*)\\p{Punct}")){
                ch=s.charAt(s.length()-1);
                flag=true;
                s=s.substring(0,s.length()-1);
            }
            int xa;
//            System.out.println(s);
            if((xa=fetch(s))==-1){
                xa=b++;
                insert(xa,s);
            }
            System.out.println(s+" "+xa);
            o.write(xa);
            if(flag) o.write((byte)ch);
            o.write(SPACE);
        }

        o.close();
        f.close();

        FileInputStream f2=null;
        try {
            f2 = new FileInputStream(new File(destPath+"\\output.bin")); //input byte file path
            o = new FileOutputStream(destPath+"\\output2.txt");   //output file path
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        int x;
        String xa=null;
//        System.out.println(hm2.toString());
        try {
            while ((x = f2.read()) != -1) {
                if(x==-2){o.write(32);continue;}
                xa = fetch(x);
                System.out.println(x+" "+xa);
//                System.out.println(xa);
                o.write(xa.getBytes());
            }
        }catch (NullPointerException e){e.printStackTrace();}
    }

}
