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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.hyranasoftware.githubupdater.domain.Asset;
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
    private String repoUrl;
    
    /**
     * Initializes the updater
     * @param repoOwner The username/organisation of the repoowner
     * @param repoName The name of the repo
     * @param currentVersion The tag-name of the current version. Can be null, if null there will always be a new release available.
     */
    public Update(String repoOwner, String repoName, String currentVersion) {
        this.repoOwner = repoOwner;
        this.repoName = repoName;
        if(currentVersion != null){
        this.currentVersion = currentVersion;
        }else this.currentVersion = "";
        this.repoUrl = String.format("https://api.github.com/repos/%s/%s/releases/", repoOwner, repoName);
    }
    
    public boolean checkForUpdates(){
        return true;
    }
    
    public Release getLatestRelease() throws UnirestException{
        StringBuilder builder = new StringBuilder();
        String requestUrl = repoUrl + "latest";       
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
    
    public File downloadAsset(Asset asset) throws UnirestException, IOException{
        StringBuilder builder = new StringBuilder();
        String requestUrl = repoUrl + "latest";       
        String response = Unirest.get(requestUrl).asJson().getBody().toString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        Release release = null;
        try {
            release = mapper.readValue(response, Release.class);
        } catch (IOException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedInputStream stream = new BufferedInputStream(Unirest.get(asset.getBrowser_download_url()).asBinary().getBody());
        byte[] buffer = new byte[8 * 1024];
        File file = File.createTempFile(asset.getName(), "");
        int bytesRead;
        BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(file));
        while((bytesRead = stream.read(buffer)) != -1){
            outStream.write(buffer,0, bytesRead);
        }
        outStream.close();
        return file;
    }

    public List<Release> getAllReleases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public File downloadSpecificRelease(String specificRelease) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public File downloadtoSpecificLocation(String location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public File downloadtoSpecificLocationFromSpecificRelease(String location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
