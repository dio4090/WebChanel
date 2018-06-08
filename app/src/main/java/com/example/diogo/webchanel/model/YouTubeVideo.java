package com.example.diogo.webchanel.model;

public class YouTubeVideo {
    String videoUrl;
    public YouTubeVideo() {
    }
    public YouTubeVideo(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    public String getVideoUrl() {
        return videoUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}