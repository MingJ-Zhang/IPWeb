package com.tledu.zmj.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tledu.zmj.pojo.IPAndLocationPojo;
import com.tledu.zmj.util.FileOperatorUtil;
import com.tledu.zmj.util.IPUtil;
import com.tledu.zmj.util.RegexUtil;
import com.tledu.zmj.util.SerDeUtil;
import com.tledu.zmj.util.StaticValue;

/**
 * 该类为项目数据处理管理类,衔接各个模块的输入输出 进行有机组合
 * 
 * @author 天亮教育-帅气多汁你泽哥
 * @Date 2020年5月14日
 */
public class DataProcessManager {

	private static IPAndLocationPojo[] pojoArray = null;
	static {
		// 业务二分法测试
		List<IPAndLocationPojo> pojoList;

		// 判断序列化文件是否存在
		boolean isInitFlag = new File(StaticValue.serde_obj_filepath).exists();
		if (isInitFlag) {
			long startMS = System.currentTimeMillis();
			// 存在就读取
			Object obj;
			try {
				obj = SerDeUtil.getObj(StaticValue.serde_obj_filepath,
						StaticValue.cacheByteArrayLength);
				pojoArray = (IPAndLocationPojo[]) obj;

				long endMS = System.currentTimeMillis();
				System.out.println("反序列化用时 : " + (endMS - startMS));
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} else {
			// 不存在 就初始化 并写出
			try {
				long startMS = System.currentTimeMillis();
				pojoList = DataProcessManager.getPojoList(
						StaticValue.ip_lib_filepath,
						StaticValue.default_encoding);
				pojoArray = DataProcessManager
						.convertListToArrayAndSort(pojoList);
				// 序列化
				SerDeUtil.saveObj(pojoArray, StaticValue.serde_obj_filepath,
						StaticValue.cacheByteArrayLength);
				long endMS = System.currentTimeMillis();
				System.out.println("序列化用时 : " + (endMS - startMS));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 把 List<String> 结构化 , 返回 List<IPAdnLocationPojo>
	 * 
	 * @param filePath
	 *            IP地址库的文件地址
	 * @param encoding
	 *            字符编码
	 * @return
	 * @throws IOException
	 */
	public static List<IPAndLocationPojo> getPojoList(String filePath,
			String encoding) throws IOException {

		List<String> lineList = FileOperatorUtil
				.getLineList(filePath, encoding);

		List<IPAndLocationPojo> ipAndLocationPojos = new ArrayList<IPAndLocationPojo>();
		for (String line : lineList) {
			// 如果有空行 直接跳过
			if (line == null || line.trim().equals("")) {
				continue;
			}
			String[] columnArray = line.split("\t");
			String startIP = columnArray[0];
			String endIP = columnArray[1];
			String location = columnArray[2];

			// 封装到对象中
			IPAndLocationPojo ipAndLocationPojo = new IPAndLocationPojo(
					startIP, endIP, location);

			ipAndLocationPojos.add(ipAndLocationPojo);
		}

		return ipAndLocationPojos;
	}

	/**
	 * 将对象的集合形式 转换为 数组形式 并 进行排序
	 * 
	 * @param pojoList
	 * @return
	 */
	public static IPAndLocationPojo[] convertListToArrayAndSort(
			List<IPAndLocationPojo> pojoList) {
		IPAndLocationPojo[] ipAndLocationPojoArray = new IPAndLocationPojo[pojoList
				.size()];
		// 转换数组
		pojoList.toArray(ipAndLocationPojoArray);
		// 排序
		Arrays.sort(ipAndLocationPojoArray);
		return ipAndLocationPojoArray;
	}

	/**
	 * 二分法查找IP
	 * 
	 * @param ipAndLocationPojoArray
	 * @param aidIP
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public static int binaraySearch4IPAndLocationPojo(
			IPAndLocationPojo[] ipAndLocationPojoArray, String aidIP,
			int startIndex, int endIndex) {
		// 终止条件
		if (startIndex > endIndex) {
			return -1;
		}

		int middle = (startIndex + endIndex) / 2;

		/**
		 * 思路 :
		 * 
		 * IP的值 如果 小于 起始IP段 说明在左边
		 * 
		 * 如果 大于 结束IP段 说明在右边
		 */
		// 转换为long类型
		long ipLong = IPUtil.ipToLong(aidIP);
		if (ipLong > ipAndLocationPojoArray[middle].getEndIPLong()) {
			startIndex = middle + 1;
		} else if (ipLong < ipAndLocationPojoArray[middle].getStartIPLong()) {
			endIndex = middle - 1;
		} else {
			return middle;
		}
		return binaraySearch4IPAndLocationPojo(ipAndLocationPojoArray, aidIP,
				startIndex, endIndex);
	}

	/**
	 * 对外提供接口,入参IP 出参归属地
	 * 
	 * @param ip
	 * @return
	 */
	public static String getLocation(String ip) {
		if (!RegexUtil.isValidIP(ip)) {
			return "请保证输入IP地址的格式正确性!!";
		}
		int startIndex = 0;
		int endIndex = pojoArray.length - 1;

		long startMS = System.currentTimeMillis();
		int resultIndex = DataProcessManager.binaraySearch4IPAndLocationPojo(
				pojoArray, ip, startIndex, endIndex);
		long endMS = System.currentTimeMillis();
		System.out.println("二分法查找耗时 : " + (endMS - startMS));
		if (resultIndex == -1) {
			return null;
		}
		return pojoArray[resultIndex].getLocation();
	}
}
