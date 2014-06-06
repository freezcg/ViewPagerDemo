package com.example.viewpager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;



/**   
 * 自定义的手机端网络管理类类，
 * 用于检查手机端网络是否打开，启动手机定位服务等操作  
 */
public class NetworkHandle {
	   /*
	    * 检查手机端的网络连接是否打开。如果手机的网络连接打开，返回true，否则，返回false
	    */
	
	private Context mContext;
	static private int FILESIZE = 4 * 1024; 
	public volatile static boolean flag = true;
    public NetworkHandle(Context context){
        this.mContext = context;
    }
    
    /**
     * 检查网络连接状态。网络已连接，返回true，否则，返回False
     * @return
     */
    public boolean isNetConnOpen(){
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
          if (connectivity != null)
          {
              NetworkInfo[] info = connectivity.getAllNetworkInfo();
              if (info != null)
                  for (int i = 0; i < info.length; i++)
                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
                      {
                          return true;
                      }
          }
          return false;
    }

	

	 /**
	  * 获取客户端IP
	  */
	 public static String getClientIP() {  
	     try {  
	         for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();en.hasMoreElements();){  
	             NetworkInterface intf = en.nextElement();  
	             for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();enumIpAddr.hasMoreElements();){  
	                 InetAddress inetAddress = enumIpAddr.nextElement();  
	                 if (!inetAddress.isLoopbackAddress()){  
	                     return inetAddress.getHostAddress().toString();  
	                 }  
	             }  
	        }  
	 	 } catch (SocketException ex) {  
	     }  
	
	     return null;  
	 }
	 
	 /**
	  * 获取客户端类型
	  * @return
	  */
	 public static String getClientType(){
		 return Build.MODEL;
	 }
	 
	
	 /**
	  * 获取网络图片
	  * @param imgUrl
	  * @return
	  */
	 public static synchronized  boolean getBitmapFromUrl(String imgUrl, String pictureName) {
        URL url;
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            url = new URL(imgUrl);
           // InputStream is = url.openConnection().getInputStream();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(2000);
            con.setReadTimeout(6000);
            con.connect();
            is = con.getInputStream();
           // BufferedInputStream bis = new BufferedInputStream(is);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
           /* byte[] buffer = new byte[FILESIZE];  
	        int length;  
	        while((length=(is.read(buffer))) >0){  
	             //output.write(buffer,0,length);  
	             bis.read(buffer);
	       	}  */

            File file = null;  
            OutputStream output = null;    
            try {  
            	createSDDir(ConstDefinition.CacheRoot);  
                file = createSDFile(ConstDefinition.CacheRoot + pictureName);  
                output = new FileOutputStream(file);  
    	        byte[] buffer = new byte[FILESIZE];  
    	        int length;  
    	        while(flag && (length=(is.read(buffer))) >0){  
    	             output.write(buffer,0,length);  
    	       	}  
                output.flush();  
                if( file.exists() && !flag){
                	file.delete();
                	return false;
                }
            }   
            catch (Exception e) {  
                e.printStackTrace();  
                file.delete();
            }  
            finally{  
                try {  
                	if(output != null)
                		output.close();  
                    is.close();
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
         
            
           
            //bitmap = BitmapFactory.decodeStream(bis,null,options);
          //  bis.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }catch(OutOfMemoryError e){
        	return false;
        } 
        return true;
    }
	 
	 /**  
	     * 在SD卡上创建文件  
	     * @param fileName  
	     * @return  
	     * @throws IOException  
	     */  
		public static File createSDFile(String fileName) throws IOException{  
	        File file = new File(fileName);  
	        file.createNewFile();  
	        return file;  
	    }  
	      
	    /**  
	     * 在SD卡上创建目录  
	     * @param dirName  
	     * @return  
	     */  
		public static File createSDDir(String dirName){  
	        File dir = new File(dirName);  
	        boolean ok = dir.mkdirs();  
	        return dir;  
	    }  
		
		 /**
		  * 获取网络图片
		  * @param imgUrl
		  * @return
		  */
		 public static synchronized  Bitmap getBitmapFromUrl1(String imgUrl) {
	        URL url;
	        Bitmap bitmap = null;
	        try {
	            url = new URL(imgUrl);
	           // InputStream is = url.openConnection().getInputStream();
	            HttpURLConnection con = (HttpURLConnection) url.openConnection();
	            con.setConnectTimeout(2000);
	            con.setReadTimeout(6000);
	            con.connect();
	            InputStream is = con.getInputStream();
	            BufferedInputStream bis = new BufferedInputStream(is);
	            BitmapFactory.Options options=new BitmapFactory.Options();
	            options.inJustDecodeBounds = false;
	           /* byte[] buffer = new byte[FILESIZE];  
		        int length;  
		        while((length=(is.read(buffer))) >0){  
		             //output.write(buffer,0,length);  
		             bis.read(buffer);
		       	}  */

	           
	            bitmap = BitmapFactory.decodeStream(bis,null,options);
	            bis.close();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }catch(OutOfMemoryError e){
	        	return null;
	        } 
	      
	        return bitmap;
	    }


	 
	 
}
