/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hyranasoftware.githubupdater.tests;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.hyranasoftware.githubupdater.Update;
import nl.hyranasoftware.githubupdater.domain.Release;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author danny_000
 */
public class UpdateTest {

    final String specificRelease = "v0.3.4alpha";
    Update update = new Update("eternia16", "javaGMR", specificRelease);

    @Test
    public void getLatestRelease() {

        Release release = null;
        try {
            release = update.getLatestRelease();
        } catch (UnirestException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Couldn't connect or failed to download release information");
        }
        assertNotNull(release.getAuthor());

    }

    @Test
    public void getAllReleases() {
        List<Release> releases = update.getAllReleases();
        assertFalse(releases.isEmpty());
    }

    @Test
    public void downloadLatestRelease() {
        Release release = null;
        try {
            release = update.getLatestRelease();
        } catch (UnirestException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Couldn't connect or failed to download release information");
        }
        File file = null;
        try {
            file = update.downloadAsset(release.getAssets().get(1));
        } catch (UnirestException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Unirest exception");
        } catch (IOException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("IOException exception");
        }
        assertTrue(file.exists());
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void downloadSpecificRelease() {
        File file = update.downloadSpecificRelease(specificRelease);
        assertTrue(file.exists());
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void downloadLatestToSpecificDirectory() {
        String location = System.getProperty("user.home");
        File file = update.downloadtoSpecificLocation(location);
        assertTrue(file.exists());
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void downloadToSpecificDirectoryFromSpecificRelease() {
        String location = System.getProperty("user.home");
        File file = update.downloadtoSpecificLocationFromSpecificRelease(location);
        assertTrue(file.exists());
        if (file.exists()) {
            file.delete();
        }
    }

}
