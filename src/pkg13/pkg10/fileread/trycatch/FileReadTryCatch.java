/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg13.pkg10.fileread.trycatch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Anže
 */
public class FileReadTryCatch {

    /**
     * @param args the command line arguments
     */
    
    
    public static class MailReader{
        
        
        private final String filePath;
        
        public MailReader(String filePath){
            this.filePath = filePath;
        }
        
        public String[] readDomains(){
            BufferedReader reader = null;
            try{
            reader = new BufferedReader(new FileReader(filePath));
            ArrayList<String> domainList = new ArrayList<>();
            while(true){
                String line = reader.readLine();
                if(line == null) break;
                domainList.add(line.split("@")[1]);
                
            }
            reader.close();
            
            return domainList.toArray(new String[domainList.size()]);
            
            }
            catch(IOException ex){
                try {
                    reader.close();
                } 
                catch (IOException ex1) {
                }
            }
            return new String[0];
        }

            
    }
    
    public static class HistrogramViewer<Type>  {
        public void print(Histrogram<Type> histogram){
            for(Type type : histogram.keySet()){
                System.out.println(type+" -> " + histogram.get(type));
            }
        }
    }
    
   public static class Histrogram <Type> extends HashMap<Type, Integer>{

        @Override
        public Integer get(Object key) {
            if(containsKey(key))
                return super.get(key); //To change body of generated methods, choose Tools | Templates.
            return 0;
        }
        /*@Override
        public Integer get(Object key){
            if(this.containsKey(key))
                return super.get(key);
            return 0;
        }*/
       
    }
  
    
    public static class HistrogramBuilder <Type> {
        public Histrogram<Type> build(Type[] types){
            Histrogram<Type> histogram = new Histrogram<>();
            for(Type type : types){
                if(histogram.containsKey(type))
                    histogram.put(type, histogram.get(type));
                else
                    histogram.put(type, 1);
            }
            return histogram;
        }
    }
    
    
    public static void main(String[] args) {
        
        //Če hočem razrede tako klicat, morejo biti static
        
        
        
        MailReader mailReader = new MailReader("mails.txt");
        HistrogramBuilder builder = new HistrogramBuilder();
        Histrogram<String> histogram = builder.build(mailReader.readDomains());
        new HistrogramViewer<String>().print(histogram);
        
        
//BufferedReader br = new BufferedReader(new FileReader("file.txt"));
                
    }
    
}



 