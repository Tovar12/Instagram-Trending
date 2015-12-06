package com.kogimobile.kogitest.model;

/**
 * Created by FelipeTovarMac on 12/6/15.
 *
 * This class represent a person who has an account on Instagram and has published a photo.
 */
public class InstagramUser {

    /** The name of the user. **/
    private final String name;

    /* TODO Set as URL */
    /** The url to the user's profile on Instagram. **/
    private final String urlProfile;

    /** Constructor of the class. **/
    private InstagramUser(InstagramUserBuiler builder){
        this.name = builder.name;
        this.urlProfile = builder.urlProfile;
    }

    /**
     * Get the name of the user.
     * @return  the name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Get a url that sends to user's profile on internet.
     * @return  the url.
     */
    public String getUrlProfile() {
        return urlProfile;
    }

    public static class InstagramUserBuiler{

        private String name;
        private String urlProfile;

        public InstagramUserBuiler(){
        }

        public InstagramUserBuiler name(String name){
            this.name = name;
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
