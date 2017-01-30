/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package league.stat.checker;

/**
 * League of Legends Info Checker v1
 * @author Richard Cai
 * Date: 01/29/2017
 * API Parsing
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.io.*;

public class nameToID{
    
    //This code takes in summoner name as string and returns summoner id as string
    public String nameToID(String name) throws IOException{
        //HTTP response input code taken WhiteFang34 on Stack overflow ; http://stackoverflow.com/questions/5769717/how-can-i-get-an-http-response-body-as-a-string-in-java
        URL url = new URL("https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/" + name + "?api_key=RGAPI-f2ba678c-7c42-4265-8c36-ae1364c3e9b9") ;
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = IOUtils.toString(in,encoding);

        //Make this code more efficient by using String slicing
        int index = body.indexOf("id") + 4;
        String sID = "";
        
        while(body.charAt(index) != ','){
            sID += body.charAt(index);
            index++;
        }
        
        return sID;
    }
    
    //This method takes in summoner id and summoner name as input, parses the API for wins and losses and returns an array containg wins,losses,win%,name,id
    public String[] findInfo(String input,String name) throws IOException{
        String[] output = new String[5];
        output[0] = name;
        output[1] = input;
        
        URL url = new URL("https://na.api.pvp.net/api/lol/na/v1.3/stats/by-summoner/" + input +  "/summary?season=SEASON2017&api_key=RGAPI-f2ba678c-7c42-4265-8c36-ae1364c3e9b9") ;
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = IOUtils.toString(in,encoding);
        String rankedInfo = body.substring(body.indexOf("RankedSolo5x5"),body.lastIndexOf("}"));
            
        //Wins Number
        output[2] = rankedInfo.substring(rankedInfo.indexOf("wins")+6,rankedInfo.length()).substring(0,rankedInfo.substring(rankedInfo.indexOf("wins")+6,rankedInfo.length()).indexOf(","));
        //Loss Number
        output[3] = rankedInfo.substring(rankedInfo.indexOf("losses")+8,rankedInfo.length()).substring(0,rankedInfo.substring(rankedInfo.indexOf("losses")+8,rankedInfo.length()).indexOf(","));
        //Win percent
        output[4] = (String.format("%.2f",(Double.parseDouble(output[2])/(Double.parseDouble(output[2]) + Double.parseDouble(output[3])))*100));
       
        return output;
    }
}

