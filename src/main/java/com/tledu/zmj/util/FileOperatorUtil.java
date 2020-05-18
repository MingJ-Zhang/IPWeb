package com.tledu.zmj.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取IP地址库,并封装到集合中
 * 
 * @author 天亮教育-帅气多汁你泽哥
 * @Date 2020年5月14日
 */
public class FileOperatorUtil {

	/**
	 * 读取文件,并返回list集合
	 * 
	 * @param txtFilePath
	 *            文件地址
	 * @param encoding
	 *            字符编码
	 * @return
	 * @throws IOException
	 */
	public static List<String> getLineList(String txtFilePath, String encoding)
			throws IOException {
		FileInputStream fis = new FileInputStream(txtFilePath);
		Reader reader = new InputStreamReader(fis, encoding);
		BufferedReader br = new BufferedReader(reader);

		String tempLine = null;
		List<String> lineList = new ArrayList<String>();
		while ((tempLine = br.readLine()) != null) {
			// System.out.println(tempLine);
			lineList.add(tempLine);
		}
		br.close();
		return lineList;
	}
}
