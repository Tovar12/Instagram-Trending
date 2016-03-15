package com.ftovaro.instagramtrending.model;

import java.io.Serializable;

/**
 * Created by FelipeTovarMac on 12/6/15.
 *
 * This class represent a person who has an account on Instagram and has published a photo.
 */
public class InstagramUser implements Serializable{

    /** The full name of the user. **/
    private final String fullName;

    /* TODO Set as URL */
    /** The url to the user's profile on Instagram. **/
    private final String urlProfile;

    /** The username of the user **/
    private final String userName;

    /** Constructor of the class. **/
    private InstagramUser(InstagramUserBuiler builder){
        this.fullName = builder.fullName;
        this.userName = builder.userName;
        this.urlProfile = builder.urlProfile;
    }

    /**
     * Get the full name of the user.
     * @return  the full name of the user.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Get the user name of the user.
     * @return  the user name of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get a url that sends to user's profile on internet.
     * @return  the url.
     */
    public String getUrlProfile() {
        return urlProfile;
    }

    public static class InstagramUserBuiler{

        private String fullName;
        private String userName;
        private String urlProfile;

        public InstagramUserBuiler(){
        }

        public InstagramUserBuiler fullName(String fullName){
            this.fullName = fullName;
            return this;
        }

        public InstagramUserBuiler userName(String userName){
            this.userName = userName;
            return this;
        }

        public InstagramUserBuiler urlProfile(String urlProfile){
            this.urlProfile = urlProfile;
            return this;
        }

        public InstagramUser build(){
            return new InstagramUser(this);
        }

    }
}
