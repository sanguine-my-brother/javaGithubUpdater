/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hyranasoftware.githubupdater.tests;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.hyranasoftware.githubupdater.GithubUtility;
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
    GithubUtility update = new GithubUtility("eternia16", "javaGMR", specificRelease);

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
    public void checkForUpdate() {
        try {
            Release release = update.getLatestRelease();
            if (release.getTag_name().equals(specificRelease)) {
                assertFalse(update.checkForUpdates());
            } else {
                assertTrue(update.checkForUpdates());
            }
        } catch (UnirestException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Got a UnirestException");
        }
    }

    @Test
    public void getAllReleases() {
        try {
            List<Release> releases = update.getAllReleases();
            assertFalse(releases.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
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
    public void downloadLatestToSpecificDirectory() {
        File location = new File(System.getProperty("user.home"));
                Release release = null;
        try {
            release = update.getLatestRelease();
        } catch (UnirestException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Couldn't connect or failed to download release information");
        }
        File file = null;
        try {
            file = update.downloadAssetToSpecificLocation(location.toPath(), release.getAssets().get(1));
        } catch (UnirestException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(file.exists());
        if (file.exists()) {
            file.delete();
        }
    }


    @Test
    public void overwriteCurrentApplication() {
        File file = new File(GithubUtility.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath());
                Release release = null;
        try {
            release = update.getLatestRelease();
        } catch (UnirestException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Couldn't connect or failed to download release information");
        }
        try {
            assertTrue(update.updateCurrentJar(file, release.getAssets().get(1)));
        } catch (UnirestException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UpdateTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
