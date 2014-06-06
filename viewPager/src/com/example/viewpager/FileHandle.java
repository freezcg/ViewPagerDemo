package com.example.viewpager;

/**  
 *文件操作类fileutils 
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileHandle {

	static private int FILESIZE = 4 * 1024;

	/**
	 * 获取外部存储设备SD卡地根目录
	 */
	public String GetRootSDcard() {
		File RootSDcard = null; // 保存手机SD卡地根目录
		RootSDcard = Environment.getExternalStorageDirectory();
		String pathString = RootSDcard.getAbsolutePath(); // 获得SD卡根目录,
															// pathString=/sdcard
															// 或者/mnt/sdcard
		return pathString;
	}

	/**
	 * 在SD卡上创建文件
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public File createSDFile(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 * @return
	 */
	public File createSDDir(String dirName) {
		File dir = new File(dirName);
		boolean ok = dir.mkdirs();
		return dir;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean isFileExist(String fileName) {
		if ((fileName) == null) {
			return false;
		}
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 * 
	 * @param path
	 * @param fileName
	 * @param input
	 * @return
	 */
	public File write2SDFromInput(String path, String fileName,
			InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			createSDDir(path);
			file = createSDFile(path + fileName);
			output = new FileOutputStream(file);
			byte[] buffer = new byte[FILESIZE];
			int length;
			while ((length = (input.read(buffer))) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
			file.delete();
		} finally {
			try {
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean delSDFile(String fileName) {
		File file = new File(fileName);
		if (file == null || !file.exists() || file.isDirectory())
			return false;
		file.delete();
		return true;
	}

	/**
	 * 判断SD卡是否存在
	 */
	public boolean haveSDCard() {
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断SD卡是否存在
		return sdCardExist;
	}

	public void Move(String srcFile, String destPath) {
		InputStream in = null;
		OutputStream out = null;
		try {
			try {
				in = new BufferedInputStream(new FileInputStream(srcFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
			} //
			try {
				out = new BufferedOutputStream(new FileOutputStream(destPath));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] buff = new byte[4096]; // 这里是缓冲的大小，可以根据实际情况指定
			int len;
			try {
				while ((len = in.read(buff, 0, buff.length)) != -1) {
					out.write(buff, 0, len);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException ex) {
				}
			}
		}

	}
	
	

}
