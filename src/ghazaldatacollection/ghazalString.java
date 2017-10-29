/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghazaldatacollection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
/**
 *
 * @author yetItCompiles
 */
public class ghazalString {
    static Integer fileCount = 0;
    public static void main(String[] args) {
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        BufferedReader br = null;
        String line,errorURL="";
        XMLClass xmlClass = new XMLClass();
        nullFileReport nFR = new nullFileReport();
        ghazalString nc = new ghazalString();
        int lastCsvFile = 7;
        for(int i=0;i<lastCsvFile;i++)
        {
            String csvFile = "D:\\Ghazals\\Ghazal"+i+".csv";
            try {
                int rowNumber = 0;
                br = new BufferedReader(new FileReader(csvFile));
                while((line = br.readLine()) != null){
                    //comma seperated
                    rowNumber++;
                    if(rowNumber==1)
                    {
                        continue;
                    }
                    String[] vals = line.split(",");
                    System.out.println(i+" "+rowNumber);
                    if(vals[5]==null)
                    {
                        continue;
                    }
                    if(hm.containsKey(vals[5]))
                    {
                     continue;
                    }
                    hm.put(vals[5], 1);
                    if(vals[4].length()!=0)
                        xmlClass.createNewXML(vals[0], vals[1], nc.getGhazalString(vals[4]), "Ghazal-"+String.format("%05d", fileCount++));
                    else
                        errorURL+=vals[5]+"\n";
                    nFR.createNewXML(errorURL.trim());
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ghazalString.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ParserConfigurationException | URISyntaxException | TransformerException ex) {
                Logger.getLogger(ghazalString.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                if(br != null){
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ghazalString.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    public String correction(String s){
        int i = 0, cnt = 0, f =0;
        for(;i<s.length();i++){
            if(s.charAt(i)=='<'){
                if(s.charAt(i+1)=='/'){
                    cnt--;
                }
                else{
                    cnt++;
                }
            }
            if(cnt>2){
                f=1;
            }
            if(cnt < 2 && f == 1){
                break;
            }
        }
        s=s.substring(0, i);
        return s;
    }
    public String getGhazalString(String s){
        int cnt = 0, f = 0, sf = 0;
        s = correction(s);
        Pattern MY_PATTERN = Pattern.compile("\\>(.*?)\\<");
        //Pattern Hindi = Pattern.compile("[\\u0900-\\u097F]*");
        Matcher m = MY_PATTERN.matcher(s);
        //Matcher x;
        String ans = "";
        while(m.find()){
            String sa = m.group(1);
            sa=sa.trim();
            if(sa.contentEquals("")){
                cnt++;
                if(cnt == 2 && f!=2 && sf==1){
                    cnt = 0;
                    f++;
                    ans+="\n";
                }
            }else{
                sf = 1;
                cnt = 0;
                f = 0;
                ans+=sa+" ";
            }
        }
        return ans.trim();
    }
}