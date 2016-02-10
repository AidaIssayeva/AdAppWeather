package com.videri.core.message;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yiminglin on 1/12/16.
 */
public class LiveFeedData {

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getInstagramId() {
        return instagramId;
    }

    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

    public ArrayList<String> getNextMaxIds() {
        return nextMaxIds;
    }

    public void setNextMaxIds(ArrayList<String> nextMaxIds) {
        this.nextMaxIds = nextMaxIds;
    }

    @Override
    public String toString() {
        return "LiveFeedData{" +
                "videoName='" + videoName + '\'' +
                ", youtubeVideoId='" + youtubeVideoId + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", image='" + image + '\'' +
                ", tweet='" + tweet + '\'' +
                ", instagramId='" + instagramId + '\'' +
                ", nextMaxIds=" + nextMaxIds +
                '}';
    }


    @SerializedName("video_name")
    private String videoName;

    @SerializedName("youtube_video_id")
    private String youtubeVideoId;

    @SerializedName("video_url")
    private String videoUrl;

    @SerializedName("image")
    private String image;


    @SerializedName("tweet")
    private String tweet;

    @SerializedName("instagram_id")
    private String instagramId;

    @SerializedName("next_max_ids")
    private ArrayList<String> nextMaxIds;

}
