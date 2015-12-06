package com.kogimobile.kogitest.model;

/**
 * Created by FelipeTovarMac on 12/6/15.
 *
 * This class represent a person who has an account on Instagram and has published a photo.
 */
public class InstagramUser {

    /** The name of the user. **/
    private String name;

    /** The url to the user's profile on Instagram. **/
    private String urlProfile;

    /** Constructor of the class. **/
    public InstagramUser(String name, String urlProfile){
        this.name = name;
        this.urlProfile = urlProfile;
    }

    /**
     * Get the name of the user.
     * @return  the name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the user.
     * @param name  the name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get a url that sends to user's profile on internet.
     * @return  the url.
     */
    public String getUrlProfile() {
        return urlProfile;
    }

    /**
     * Set the url of the user's profile.
     * @param urlProfile    the url to set.
     */
    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }
}
