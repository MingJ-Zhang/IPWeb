package com.tledu.zmj.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerDeUtil {
	/**
	 * 对象写出 加入缓冲流
	 * 
	 * @param obj
	 * @param obFilePath
	 * @throws IOException
	 */
	public static void saveObj(Object obj, String objFilePath,
			int cacheByteArrayLength) throws IOException {
		FileOutputStream fos = new FileOutputStream(objFilePath);

		// 字节数组缓冲流
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				cacheByteArrayLength);
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);

		byte[] byteArray = baos.toByteArray();
		oos.close();
		fos.write(byteArray);
		fos.close();
	}

	/**
	 * 对象读取 加入缓冲流
	 * 
	 * @param objFilePath
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object getObj(String objFilePath, int cacheByteArrayLength)
			throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(objFilePath);
		byte[] byteArray = new byte[cacheByteArrayLength];

		fis.read(byteArray);
		fis.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteArray);
		ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
		Object o = ois.readObject();
		ois.close();

		return o;
	}

	/**
	 * 对象写出
	 * 
	 * @param obj
	 * @param obFilePath
	 * @throws IOException
	 */
	public static void saveObj(Object obj, String objFilePath)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(objFilePath);

		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();
	}

	/**
	 * 对象读取
	 * 
	 * @param objFilePath
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object getObj(String objFilePath) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(objFilePath);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object o = ois.readObject();
		ois.close();
		return o;
	}
}
