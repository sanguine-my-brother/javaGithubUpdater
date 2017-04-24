/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hyranasoftware.githubupdater.domain;

import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author danny_000
 */
public class Release {
    
    String url;
    String html_url;
    String assets_url;
    String upload_url;
    String tarball_url;
    String zipball_url;
    int id;
    String tag_name;
    String target_commitish;
    String name;
    String body;
    boolean draft;
    boolean prerelease;
    DateTime created_at;
    DateTime published_at;
    GithubUser author;
    List<Asset> assets;

    public Release() {
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getAssets_url() {
        return assets_url;
    }

    public String getUpload_url() {
        return upload_url;
    }

    public String getTarball_url() {
        return tarball_url;
    }

    public String getZipball_url() {
        return zipball_url;
    }

    public int getId() {
        return id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public String getTarget_commitish() {
        return target_commitish;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public boolean isDraft() {
        return draft;
    }

    public boolean isPrerelease() {
        return prerelease;
    }

    public DateTime getCreated_at() {
        return created_at;
    }

    public DateTime getPublished_at() {
        return published_at;
    }

    public GithubUser getAuthor() {
        return author;
    }

    public List<Asset> getAssets() {
        return assets;
    }


    
    
    
    
}
