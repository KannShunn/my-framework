package com.cesgroup.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * 文件操作工具类
 * <p>描述:文件操作工具类</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author 管俊 guan.jun@cesgroup.com.cn
 * @date 2015年7月28日 下午2:52:37
 * @version 1.0.2015年7月28日 下午2:52:37
 */
public class FileUtil {

	public static final String FILE_SEPARATOR = "/";
	public static final String FILE_ENCODING = "UTF-8";
	
	/**
	 * 复制文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException {
		if(srcFile.isDirectory()){
			FileUtils.copyDirectory(srcFile, destFile);
		}else{
			FileUtils.copyFile(srcFile, destFile);
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFile(String srcPath, String destPath) throws IOException {
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);
		FileUtil.copyFile(srcFile, destFile);
	}
	
	/**
	 * 剪切文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void moveFile(File srcFile, File destFile) throws IOException {
		if (srcFile.isDirectory()) {
			FileUtils.moveDirectory(srcFile, destFile);
		} else {
			FileUtils.moveFile(srcFile, destFile);
		}
	}

	/**
	 * 获取文件名后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		int index = fileName.lastIndexOf(".") + 1;
		if (index <= 0)
			return "";
		return fileName.substring(index);
	}

	/**
	 * 获取不带后缀的文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getOnlyFileName(String fileName) {
		int index = fileName.lastIndexOf("\\");
		if (index == -1) {
			index = fileName.lastIndexOf("/");
		}
		String temp = fileName.substring(index + 1);
		index = temp.indexOf(".");
		temp = temp.substring(0, index);
		return temp;
	}

	/**
	 * 判断路径是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isPathExist(String path) {
		File file = new File(path);
		boolean isExist = file.exists();
		return isExist;
	}

	/**
	 * 通过路径删除文件
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void deleteFile(String filePath) throws IOException {
		File file = new File(filePath);
		deleteFile(file);
	}
	/**
	 * 通过文件对象删除文件
	 * @param file
	 * @throws IOException
	 */
	public static void deleteFile(File file) throws IOException {
		if(StringUtils.isEmpty(file.getName())){
			throw new RuntimeException("被删除的文件，文件名为空");
		}
		
		if (!file.exists()) {
			throw new FileExistsException(file.getName()+"文件不存在");
		}
		if(file.isDirectory()){
			FileUtils.deleteDirectory(file);
		}else{
			file.delete();
		}
	}

	/**
	 * 计算文件大小
	 * 
	 * @param file
	 * @return
	 */
	public static BigInteger sizeOf(File file) {
		return FileUtils.sizeOfAsBigInteger(file);
	}

	/**
	 * 计算文件大小
	 * 
	 * @param filePath
	 * @return
	 */
	public static BigInteger sizeOf(String filePath) {
		return sizeOf(new File(filePath));
	}

	public static String readAsString(File file, String encoding) throws IOException {
		return FileUtils.readFileToString(file, Charset.forName(encoding));
	}
	public static String readAsString(File file, Charset charset) throws IOException {
		return FileUtils.readFileToString(file, charset);
	}

	public static String readAsString(File file) throws IOException {
		if(file.length()==0)return "";
		String encoding = EncodingDetect.getJavaEncode(file);
		return readAsString(file, encoding);
	}
	public static String readAsString(String filePath) throws IOException {
		return readAsString(new File(filePath));
	}

	/**
	 * 将字符串写入文件
	 * @param file
	 * @param data
	 * @param encoding
	 * @throws IOException
	 */
	public static void writeStringToFile(File file, String data, String encoding) throws IOException {
		FileUtils.writeStringToFile(file, data, Charset.forName(encoding));
	}
	public static void writeStringToFile(File file, String data) throws IOException {
		FileUtils.writeStringToFile(file, data, Charset.forName("UTF-8"));
	}

	/**
	 * 处理中文文件名
	 * <p>描述:处理中文文件名</p>
	 * <p>Company:上海中信信息发展股份有限公司</p>
	 * @author Niklaus(管俊 GuanJun<a href="mailto:guan.jun@cesgroup.com.cn">guan.jun@cesgroup.com.cn</a>)
	 * @date 2015-5-6 13:47:14
	 * @version 1.0.2015.0506 
	 * @param fileName 文件名
	 * @param request 浏览器请求对象
	 * @param response 浏览器响应对象
	 * @return 处理好的文件名
	 * @throws UnsupportedEncodingException
	 *
	 */
	public static String convertFileName(String fileName,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		  /* 处理中文文件名 */
		  String agent = request.getHeader("User-Agent");
	      boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
	      if( isMSIE ){
	    	  fileName = java.net.URLEncoder.encode(fileName,"UTF8");
	      }else{
	    	  fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	      }
	      
	      return fileName;
	}
	/**
	 * 文件重命名
	 * @param path
	 * @param oldName
	 * @param newName
	 * @throws Exception
	 */
	public static void renameFile(String path,String oldName,String newName) throws Exception{ 
        if(!oldName.equals(newName)){//新的文件名和以前文件名不同时,才有必要进行重命名 
            File oldfile=new File(path+"/"+oldName); 
            File newfile=new File(path+"/"+newName); 
            if(!oldfile.exists()){
            	throw new FileNotFoundException(oldName+" 文件不存在"); 
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
                throw new FileExistsException(newName+" 文件已经存在"); 
            else{ 
                oldfile.renameTo(newfile); 
            } 
        }else{
            throw new RuntimeException("两个文件名相同");
        }
	}
	/**
	 * 通过文件名前缀删除文件
	 * @param folder
	 * @param fileNamePrefix
	 * @throws IOException
	 */
	public static void deleteFileByNamePrefix(File folder,String fileNamePrefix) throws IOException{
		if(StringUtils.isEmpty(fileNamePrefix)){
			throw new RuntimeException("文件名前缀为空，不能删");
		}
		File[] files = folder.listFiles();
		//因为 listfiles 有可能会返回 null ！
		if (files != null)
		{
			for(File file:files){
				if(file.getName().startsWith(fileNamePrefix)){
					if(file.isFile()){
						FileUtil.deleteFile(file);
					}
				}
			}
		}
	}

	
	public static void mkDirs(String filePath){
		File file = new File(filePath);
		if(!file.exists()){			
			file.mkdirs();
		}	
	}
	
	/**
	 * 判断文件父文件夹是否存在，如果不存在则创建路径
	 * @param file 要判断的文件
	 */
	public static void mkParentDirs(String filePath){
		File file = new File(filePath);
		mkParentDirs(file);		
	}
	
	/**
	 * 判断文件父文件夹是否存在，如果不存在则创建路径
	 * @param file 要判断的文件
	 */
	public static void mkParentDirs(File file){
		File parent=file.getParentFile();		
		if(!parent.exists()){			
			parent.mkdirs();
		}			
	}
	
	/**
	 * 将文件路径中的分隔符替换成系统合法的.
	 */
	public static String ConvertPath(String s) {
		StringBuffer sbuf = new StringBuffer();
		int j = 0, k = 0;

		while (k < s.length()) {
			j = s.indexOf("\\", k);
			if (j < 0) {
				sbuf.append(s.substring(k, s.length()));
				k = s.length();
			} else {
				sbuf.append(s.substring(k, j));
				sbuf.append("/");
				k = j + "\\".length();
			}
		}

		return sbuf.toString();
	}
}
