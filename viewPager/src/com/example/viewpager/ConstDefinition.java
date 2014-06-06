package com.example.viewpager;


public class ConstDefinition {
	public static final String AndroidRoot= new FileHandle().GetRootSDcard();   //获取根目录
	public static final String ProjectRoot = AndroidRoot + "/Android/data/com.example.viewpager/"; //工程文件夹路径
	public static final String PictureRoot = ProjectRoot + "picture/";  
	public static final String CacheRoot = ProjectRoot + "cache/"; //图片缓存的路径
}
