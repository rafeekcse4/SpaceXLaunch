package spacexlaunch.com.spacexlaunch.ApiMapper;

import java.util.ArrayList;
import java.util.List;

public class Links {
	 private String mission_patch;
	  private String mission_patch_small;
	  private String article_link;
	  private String wikipedia;
	  private String video_link;
	  private List<Object> flickr_images;
	public String getMission_patch() {
		return mission_patch;
	}
	public void setMission_patch(String mission_patch) {
		this.mission_patch = mission_patch;
	}
	public String getMission_patch_small() {
		return mission_patch_small;
	}
	public void setMission_patch_small(String mission_patch_small) {
		this.mission_patch_small = mission_patch_small;
	}
	public String getArticle_link() {
		return article_link;
	}
	public void setArticle_link(String article_link) {
		this.article_link = article_link;
	}
	public String getWikipedia() {
		return wikipedia;
	}
	public void setWikipedia(String wikipedia) {
		this.wikipedia = wikipedia;
	}
	public String getVideo_link() {
		return video_link;
	}
	public void setVideo_link(String video_link) {
		this.video_link = video_link;
	}
	public List<Object> getFlickr_images() {
		return flickr_images;
	}
	public void setFlickr_images(List<Object> flickr_images) {
		this.flickr_images = flickr_images;
	}
	  
	  
}
