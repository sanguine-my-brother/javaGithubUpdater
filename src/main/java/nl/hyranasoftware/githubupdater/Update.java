/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hyranasoftware.githubupdater;

import java.io.File;

/**
 *
 * @author danny_000
 */
public class Update {
    
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
    
    public File downloadFile(){
        return null;
    }
    
}
