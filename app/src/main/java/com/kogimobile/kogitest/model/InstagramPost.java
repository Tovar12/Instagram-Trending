package com.kogimobile.kogitest.model;

import java.util.List;

/**
 * Created by FelipeTovarMac on 12/6/15.
 * This class represents an Instagram User. Its main goal map the information coming from the
 * Instagram's web service.
 */
public class InstagramPost {

    /** Represents the date of creation of the publication **/
    private final String timeStamp;

    /** It's the information about the user **/
    private final InstagramUser instagramUser;

    /** A list of the tags that the user sets on It's pictures **/
    private final List<String> tags;

    /** The title of the picture **/
    private final String title;

    /**
     * Constructor of the class.
     * @param builder   an object to construct the Instagram post.
     */
    private InstagramPost(InstagramPostBuilder builder){
        this.timeStamp = builder.timeStamp;
        this.instagramUser = builder.instagramUser;
        this.tags = builder.tags;
        this.title = builder.title;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public InstagramUser getInstagramUser() {
        return instagramUser;
    }


    public List<String> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Builder class of an Instragram post
     */
    public static class InstagramPostBuilder{

        private String timeStamp;
        private InstagramUser instagramUser;
        private List<String> tags;
        private String title;

        public InstagramPostBuilder(){
        }

        public InstagramPostBuilder timeStamp(String timeStamp){
            this.timeStamp = timeStamp;
            return this;
        }

        public InstagramPostBuilder instagramUser(InstagramUser instagramUser){
            this.instagramUser = instagramUser;
            return this;
        }

        public InstagramPostBuilder tags(List<String> tags){
            this.tags = tags;
            return this;
        }

        public InstagramPostBuilder title(String title){
            this.title = title;
            return this;
        }

        public InstagramPost build(){
            return new InstagramPost(this);
        }

    }
}
