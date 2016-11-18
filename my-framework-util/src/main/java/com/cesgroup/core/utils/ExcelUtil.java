package com.cesgroup.core.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ExcelUtil {

	public static final int MSEXCEL2003_MAX_ROW = 65536;


	/**
	 * 导出excel入口
	 * @param response 浏览器响应流
	 * @param excel excel数据(包括字段名)
	 * @param fileName excel文件名
	 * @return true导出成功; false导出失败
	 * @throws IOException
	 */
	public static boolean exportExcel(HttpServletRequest request, HttpServletResponse response,Map<String,List<String[]>> excel,String fileName) throws IOException{
		if(excel == null || excel.size() == 0){
			return false;
		}
		if(fileName == null){ fileName = "";}
		ServletOutputStream os = null;

		  /* 处理中文文件名 */
		fileName = convertFileName(fileName,request,response);

		DateTime dt = new DateTime();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName  + "-" + dt.toString("dd-MM-yyyy HH:mm:ss") +".xls" + "\"");
		response.setContentType("application/msexcel;");
		os = response.getOutputStream();

		try {
			ExcelUtil.writeTable_(os, excel);
			os.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
//			if(null!=os){
//				os.close();
//			}
		}
	}

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
	 * 把PNG 图片写入到excel�?
	 *
	 * @param excelPath
	 * @param imagePath
	 * @throws Exception
	 */
	private void writeImg(String excelPath, String imagePath, int rows, int cells,String title) throws Exception {
		FileOutputStream fileOut = null;
		BufferedImage bufferImg = null;
		try {
			// 先把读进来的图片放到�?个ByteArrayOutputStream中，以便产生ByteArray
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			bufferImg = ImageIO.read(new File(imagePath));
			ImageIO.write(bufferImg, "jpg", byteArrayOut);

			// 创建�?个工作薄
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelPath)));
			HSSFSheet sheet1 = wb.getSheetAt(0);
			HSSFRow row = sheet1.createRow(1);
			row.createCell(1);
			row.createCell(2);
			row.createCell(3);
			row.createCell(4);
			row.createCell(5);
			row.createCell(6);
			row.createCell(7);
			CellRangeAddress region = new CellRangeAddress(1, (short) 1, 1, (short) 7);
			sheet1.addMergedRegion(region);
			HSSFCellStyle setBorder = wb.createCellStyle();
			setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
			HSSFFont font = wb.createFont();
			font.setFontName("黑体");
			font.setFontHeightInPoints((short) 13);//设置字体大小
			setBorder.setFont(font);
			row.getCell(1).setCellStyle(setBorder);
			row.getCell(1).setCellValue(title);
			HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 512, 255,
					(short) 0, 4, (short) rows, cells);
			// 插入图片
			patriarch.createPicture(anchor, wb.addPicture(
					byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

			fileOut = new FileOutputStream(excelPath);
			// 写入excel文件
			wb.write(fileOut);
			fileOut.close();

		} catch (IOException io) {
			io.printStackTrace();
			System.out.println("io erorr :  " + io.getMessage());
		} finally {
			if (fileOut != null) {

				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void writeTable(String excelPath, List<String[]> datas) throws Exception {
		OutputStream os = null;
		try {
			// 创建�?个工作薄
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelPath)));
			HSSFCellStyle setBorder = wb.createCellStyle();
			setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边�?
			setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边�?
			setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边�?
			setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边�?
			setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
			HSSFFont font = wb.createFont();
			font.setFontName("黑体");
			font.setFontHeightInPoints((short) 10);//设置字体大小
			setBorder.setFont(font);
			HSSFSheet sheet = wb.getSheetAt(0);
			CellRangeAddress region = null;
			for (int i = 0; i < datas.size(); i++) {
				String[] cells = datas.get(i);
				int m = 0;
				for (int j = 0; j < cells.length; j++) {
					region = new CellRangeAddress(30+i, (short) m, 30+i, (short) (m+1));
					sheet.addMergedRegion(region);
					m = m +2;
				}
			}

			for (int i = 0; i < datas.size(); i++) {
				HSSFRow row = sheet.createRow(30+i);
				String[] cells = datas.get(i);
				for (int j = 0; j < cells.length; j++) {
					HSSFCell cell1 = row.createCell(j*2+1);
					cell1.setCellStyle(setBorder);
					HSSFCell cell = row.createCell(j*2);
					cell.setCellStyle(setBorder);
					cell.setCellValue(cells[j]);
				}
			}
			os = new FileOutputStream(new File(excelPath));
			wb.write(os);

		} catch (IOException io) {
			io.printStackTrace();
			System.out.println("io erorr :  " + io.getMessage());
		} finally {
			os.close();
		}
	}

	/**
	 *
	 * @param os
	 * @param excel
	 * @throws Exception
	 */
	private static void writeTable_(OutputStream os, Map<String,List<String[]>> excel) throws Exception {

		// 创建1个excel
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFCellStyle headStyle = wb.createCellStyle(); //表头样式
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		headStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//设置背景颜色
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFFont headFont = wb.createFont();
		headFont.setFontName("微软雅黑");
		headFont.setFontHeightInPoints((short) 10);//设置字体大小
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		headStyle.setFont(headFont);

		HSSFCellStyle bodyStyle = wb.createCellStyle(); //表内容样式
		bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		HSSFFont bodyFont = wb.createFont();
		bodyFont.setFontName("微软雅黑");
		bodyFont.setFontHeightInPoints((short) 10);//设置字体大小
		bodyStyle.setFont(bodyFont);

		int sheetCount = 1;
		ExcelUtil.writeSheet(os, excel, wb, headStyle, bodyStyle, sheetCount);

	}

	private static void writeSheet(List<String[]> datas,String sheetName,HSSFWorkbook wb,HSSFCellStyle headStyle,HSSFCellStyle bodyStyle, int sheetCount){
		//创建一个sheet
		if(sheetCount > 1){
			sheetName = sheetName + sheetCount;
		}
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.createFreezePane(0,1,0,1);// 冻结  前两个参数是你要用来拆分的列数和行数。后两个参数是下面窗口的可见象限，其中第三个参数是右边区域可见的左边列数，第四个参数是下面区域可见的首行。
		sheet.setDefaultColumnWidth(25);

		//创建表头
		for (int i = 0; i < 1; i++) {
			HSSFRow row = sheet.createRow(i);
			String[] cells = datas.get(i);
			for (int j = 0; j < cells.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellStyle(headStyle);
				cell.setCellValue(cells[j]);
			}
		}
		//创建表内容
		int size = datas.size();
		if(size < MSEXCEL2003_MAX_ROW){
			for (int i = 1; i < size; i++) {
				HSSFRow row = sheet.createRow(i);
				String[] cells = datas.get(i);
				for (int j = 0; j < cells.length; j++) {
					HSSFCell cell = row.createCell(j);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(cells[j]);
				}
			}
		} else {
			for (int i = 1; i < MSEXCEL2003_MAX_ROW; i++) {
				HSSFRow row = sheet.createRow(i);
				String[] cells = datas.get(i);
				for (int j = 0; j < cells.length; j++) {
					HSSFCell cell = row.createCell(j);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(cells[j]);
				}
			}
			List<String[]> remainDatas = datas.subList(MSEXCEL2003_MAX_ROW, size);
			writeSheet(remainDatas,sheetName,wb,headStyle,bodyStyle,sheetCount+1);
		}
	}

	private static void writeSheet(OutputStream os,Map<String,List<String[]>> excel,HSSFWorkbook wb,HSSFCellStyle headStyle,HSSFCellStyle bodyStyle, int sheetCount){
		try {

			Set<Map.Entry<String, List<String[]>>> entries = excel.entrySet();

			for (Map.Entry<String, List<String[]>> entry : entries) {

				writeSheet(entry.getValue(), entry.getKey(), wb, headStyle, bodyStyle, sheetCount);
			}

			wb.write(os);

		} catch (IOException io) {
			io.printStackTrace();
			System.out.println("io erorr :  " + io.getMessage());
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}


	private String getDocSize(String docSize){
		long size = 0;
		if(Util.notNull(docSize)){
			size = Long.parseLong(docSize.substring(0,docSize.length()-1));
		}
		String sizeStr = "";
		if(size < 1024){
			sizeStr = size+"B";
		}else if(size < 1024 * 1024){
			size = (size/1024)+(size%1024 == 0?0:1);
			sizeStr = size+"KB";
		}else if(size < 1024 * 1024 * 1024){
			size = (size/(1024*1024))+(size%1024 == 0?0:1);
			sizeStr = size+"M";
		}else{
			size = (size/(1024*1024*1024))+(size%1024 == 0?0:1);
			sizeStr = size+"G";
		}
		return sizeStr;
	}

	/**
	 * 创建�?个空的excel
	 * @param excelPath
	 * @return
	 * @throws Exception
	 */
	private void createExcel(String excelPath){
		try{
			HSSFWorkbook wb = new HSSFWorkbook();// 建立新HSSFWorkbook对象
			wb.createSheet("sheet");
			FileOutputStream fileOut = new FileOutputStream(excelPath);
			wb.write(fileOut);// 把Workbook对象输出到文件workbook.xls�?
			fileOut.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void isChartPathExist(String chartPath) {
		File file = new File(chartPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

}
