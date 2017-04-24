/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hyranasoftware.githubupdater;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.hyranasoftware.githubupdater.domain.Release;

/**
 *
 * @author danny_000
 */
public class Update {
    
    /*
    https://developer.github.com/v3/repos/releases/#get-the-latest-release
    */
    
    private String repoOwner;
    private String repoName;
    private String currentVersion;

    public Update(String repoOwner, String repoName, String currentVersion) {
        this.repoOwner = repoOwner;
        this.repoName = repoName;
        this.currentVersion = currentVersion;
    }
    
    public boolean checkForUpdates(){
        return true;
    }
    
    public Release getLatestRelease() throws UnirestException{
        String requestUrl = "https://api.github.com/repos/eternia16/javaGMR/releases/latest";
        String response = Unirest.get(requestUrl).asJson().getBody().toString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        try {
            Release release = mapper.readValue(response, Release.class);
            return release;
        } catch (IOException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public File downloadFile(){
        return null;
    }
    
}
