package com.cesgroup.core.utils;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

public class ImageUtil {
	public static void main(String[] args) throws IOException {
		//rotateImg("E:/test1.jpg","E:/test1.jpg",180);
		rotateImg("E:/test1.jpg","E:/test1.jpg","2");
	}

	public static final String HORIZONTALLY = "1";					
	public static final String VERTICALLY = "2";					
	/**
	 * 旋转后保存文件
	 * @param src
	 * @param dest
	 * @param degree
	 * @throws IOException
	 */
	public static void rotateImg(String src, String dest, int degree) throws IOException {

		String ext = src.substring(src.lastIndexOf(".") +1);
		BufferedImage image = ImageIO.read(new File(src));
		
		int iw = image.getWidth();//原始图象的宽度 
		int ih = image.getHeight();//原始图象的高度
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		degree = degree % 360;
		if (degree < 0)
			degree = 360 + degree;//将角度转换到0-360度之间
		double ang = Math.toRadians(degree);//将角度转为弧度

		/**
		 *确定旋转后的图象的高度和宽度
		 */

		if (degree == 180 || degree == 0 || degree == 360) {
			w = iw;
			h = ih;
		} else if (degree == 90 || degree == 270) {
			w = ih;
			h = iw;
		} else {
			int d = iw + ih;
			w = (int) (d * Math.abs(Math.cos(ang)));
			h = (int) (d * Math.abs(Math.sin(ang)));
		}

		x = (w / 2) - (iw / 2);//确定原点坐标
		y = (h / 2) - (ih / 2);
		BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		AffineTransform at = new AffineTransform();
		at.rotate(ang, w / 2, h / 2);//旋转图象
		at.translate(x, y);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		op.filter(image, rotatedImage);
		
		ImageIO.write(rotatedImage, ext, new File(dest));
		
	}
	
	/**
	 * 旋转后保存到同一文件
	 * @param src
	 * @param degree
	 * @throws IOException
	 */
	public static void rotateImg(String src, int degree) throws IOException {
		
		String ext = src.substring(src.lastIndexOf(".") +1);
		File file = new File(src);
		
		BufferedImage image = ImageIO.read(file);
		
		int iw = image.getWidth();//原始图象的宽度 
		int ih = image.getHeight();//原始图象的高度
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		degree = degree % 360;
		if (degree < 0)
			degree = 360 + degree;//将角度转换到0-360度之间
		double ang = Math.toRadians(degree);//将角度转为弧度

		/**
		 *确定旋转后的图象的高度和宽度
		 */

		if (degree == 180 || degree == 0 || degree == 360) {
			w = iw;
			h = ih;
		} else if (degree == 90 || degree == 270) {
			w = ih;
			h = iw;
		} else {
			int d = iw + ih;
			w = (int) (d * Math.abs(Math.cos(ang)));
			h = (int) (d * Math.abs(Math.sin(ang)));
		}

		x = (w / 2) - (iw / 2);//确定原点坐标
		y = (h / 2) - (ih / 2);
		BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		AffineTransform at = new AffineTransform();
		at.rotate(ang, w / 2, h / 2);//旋转图象
		at.translate(x, y);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		op.filter(image, rotatedImage);
		
		ImageIO.write(rotatedImage, ext, file);
		
		
	}
	
	/**
	 * 返回旋转后的图片
	 * @param file
	 * @param degree
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage rotateImg(File file, int degree) throws IOException {

		BufferedImage image = ImageIO.read(file);
		
		int iw = image.getWidth();//原始图象的宽度 
		int ih = image.getHeight();//原始图象的高度
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		degree = degree % 360;
		if (degree < 0)
			degree = 360 + degree;//将角度转换到0-360度之间
		double ang = Math.toRadians(degree);//将角度转为弧度

		/**
		 *确定旋转后的图象的高度和宽度
		 */

		if (degree == 180 || degree == 0 || degree == 360) {
			w = iw;
			h = ih;
		} else if (degree == 90 || degree == 270) {
			w = ih;
			h = iw;
		} else {
			int d = iw + ih;
			w = (int) (d * Math.abs(Math.cos(ang)));
			h = (int) (d * Math.abs(Math.sin(ang)));
		}

		x = (w / 2) - (iw / 2);//确定原点坐标
		y = (h / 2) - (ih / 2);
		BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		AffineTransform at = new AffineTransform();
		at.rotate(ang, w / 2, h / 2);//旋转图象
		at.translate(x, y);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		op.filter(image, rotatedImage);
		
		return rotatedImage;
		
	}
	
	/**
	 * 返回旋转后的图片
	 * @param file
	 * @param degree
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage rotateImg(BufferedImage image, int degree) throws IOException {
		
		int iw = image.getWidth();//原始图象的宽度 
		int ih = image.getHeight();//原始图象的高度
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		degree = degree % 360;
		if (degree < 0)
			degree = 360 + degree;//将角度转换到0-360度之间
		double ang = Math.toRadians(degree);//将角度转为弧度
		
		/**
		 *确定旋转后的图象的高度和宽度
		 */
		
		if (degree == 180 || degree == 0 || degree == 360) {
			w = iw;
			h = ih;
		} else if (degree == 90 || degree == 270) {
			w = ih;
			h = iw;
		} else {
			int d = iw + ih;
			w = (int) (d * Math.abs(Math.cos(ang)));
			h = (int) (d * Math.abs(Math.sin(ang)));
		}
		
		x = (w / 2) - (iw / 2);//确定原点坐标
		y = (h / 2) - (ih / 2);
		BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		AffineTransform at = new AffineTransform();
		at.rotate(ang, w / 2, h / 2);//旋转图象
		at.translate(x, y);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		op.filter(image, rotatedImage);
		
		return rotatedImage;
		
	}
	
	/**
	 * 水平翻转或者垂直翻转
	 * 
	 * @author 管俊
	 * 
	 * @param src
	 *            原图地址
	 * @param dest
	 *            保存地址
	 * @param rotateType
	 *            1:水平翻转 ;2:垂直翻转
	 * @throws IOException
	 */
	public static void rotateImg(String src, String dest, String rotateType)
			throws IOException {
		if (StringUtils.isBlank(rotateType))
		{
			//给个默认值，否则外部不传 此参数，new AffineTransformOp(transform,会失败
			rotateType = ImageUtil.HORIZONTALLY;
		}
		String ext = src.substring(src.lastIndexOf(".") + 1);
		BufferedImage image = ImageIO.read(new File(src));
		int w = image.getWidth();// 原始图象的宽度
		int h = image.getHeight();// 原始图象的高度

		AffineTransform transform = null;
		if (ImageUtil.HORIZONTALLY.equals(rotateType)) {
			transform = new AffineTransform(-1, 0, 0, 1, w, 0);// 水平翻转
		} else if (ImageUtil.VERTICALLY.equals(rotateType)) {
			transform = new AffineTransform(1, 0, 0, -1, 0, h);// 垂直翻转
		}

		if (transform != null)
		{
			BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
			AffineTransformOp op = new AffineTransformOp(transform,
					AffineTransformOp.TYPE_BICUBIC);
			op.filter(image, rotatedImage);

			ImageIO.write(rotatedImage, ext, new File(dest));
		}
		
	}
	
	/**
	 * 压缩图片
	 * 
	 * @author 黄翔宇
	 * @throws IOException 
	 */
	public static void compressPic(File source, File dest,double maxWidth,int rotation) throws IOException {
		BufferedImage src = ImageIO.read(source);
		double rate = ((double) src.getWidth(null)) / maxWidth + 0.1;
		// 根据缩放比率大的进行缩放控制
		int newWidth = (int) (((double) src.getWidth(null)) / rate);
		int newHeight = (int) (((double) src.getHeight(null)) / rate);
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < newHeight; y++) {
			for (int x = 0; x < newWidth; x++) {
				newImage.setRGB(x,y,src.getRGB(x * src.getWidth() / newWidth,y * src.getHeight() / newHeight));
			}
		}
		newImage = rotateImg(newImage,rotation);
		ImageIO.write(newImage, "jpg", dest);
	}
	
	/**
	 * 图片切割, 覆盖原图
	 * 
	 * @param imagePath
	 *            原图地址
	 * @param destPath
	 *            保存地址
	 * @param x
	 *            目标切片坐标 X轴起点
	 * @param y
	 *            目标切片坐标 Y轴起点
	 * @param w
	 *            目标切片 宽度
	 * @param h
	 *            目标切片 高度
	 * @throws IOException
	 *             图片路径异常
	 */
	public static void cutImage(String imagePath, String destPath,int x, int y, int w, int h
			) throws IOException {
		Image img;
		ImageFilter cropFilter;
		String ext = imagePath.substring(imagePath.lastIndexOf(".") + 1);
		
		// 读取源图像
		BufferedImage bi = ImageIO.read(new File(imagePath));
		int srcWidth = bi.getWidth(); // 源图宽度
		int srcHeight = bi.getHeight(); // 源图高度

		// 若原图大小大于切片大小，则进行切割
		if (srcWidth >= w && srcHeight >= h) {
			Image image = bi.getScaledInstance(srcWidth, srcHeight,
					Image.SCALE_DEFAULT);

			cropFilter = new CropImageFilter(x, y, w, h);
			img = Toolkit.getDefaultToolkit().createImage(
					new FilteredImageSource(image.getSource(), cropFilter));
			BufferedImage tag = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(img, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			// 输出为文件
			ImageIO.write(tag, ext, new File(destPath));
		}
	}
	
	 /**  
     * 图像缩放  
     * @param source  
     * @param targetW  
     * @param targetH  
     * @return  
     */
    public static BufferedImage resize(BufferedImage source, int targetW,int targetH) {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        target = new BufferedImage(targetW, targetH, type);
        Graphics2D g = target.createGraphics();
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }
}
