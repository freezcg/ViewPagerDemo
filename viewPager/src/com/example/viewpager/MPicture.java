package com.example.viewpager;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class MPicture  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pictureNumber;
	private String pictureName;
	private String picturePath;
	
	/** default constructor */
	public MPicture() {
	}

	/** minimal constructor */
	public MPicture(Integer pictureNumber) {
		this.pictureNumber = pictureNumber;
	}

	/** 
	 * full constructor 
	 * @throws JSONException 
	 */
	public MPicture(JSONObject jo) throws JSONException {
		this.pictureNumber = jo.getInt("pictureNumber");
		if(!jo.isNull("pictureName"))
			this.pictureName = jo.getString("pictureName");
		else
			this.pictureName = "picture_"+this.pictureNumber+".jpg";
		this.picturePath = jo.getString("picturePath");
	}
	
	public MPicture(JSONObject jo, String s) throws JSONException {
		this.pictureNumber = jo.getInt("pictureNumber");
		if(!jo.isNull("pictureName"))
			this.pictureName = jo.getString("pictureName");
		else
			this.pictureName = s+this.pictureNumber+".jpg";
		this.picturePath = jo.getString("picturePath");
	}
	

	/** full constructor */
	public MPicture(Integer pictureNumber, String pictureName,
			String picturePath) {
		this.pictureNumber = pictureNumber;
		this.pictureName = pictureName;
		this.picturePath = picturePath;
	}
	
	public Integer getPictureNumber() {
		return pictureNumber;
	}
	public void setPictureNumber(Integer pictureNumber) {
		this.pictureNumber = pictureNumber;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

}
