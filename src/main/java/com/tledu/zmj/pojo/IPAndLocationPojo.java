package com.tledu.zmj.pojo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.tledu.zmj.util.Base64Util;
import com.tledu.zmj.util.IPUtil;

public class IPAndLocationPojo implements Comparable<IPAndLocationPojo>,
		Serializable {
	private static final long serialVersionUID = 1L;
	private transient String startIP;
	private transient String endIP;
	private String location;

	// 衍生字段

	private long startIPLong;
	private long endIPLong;

	public String getStartIP() {
		return startIP;
	}

	public void setStartIP(String startIP) {
		this.startIP = startIP;
	}

	public String getEndIP() {
		return endIP;
	}

	public void setEndIP(String endIP) {
		this.endIP = endIP;
	}

	public String getLocation() {
		try {
			return Base64Util.decode(location);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getStartIPLong() {
		return startIPLong;
	}

	public void setStartIPLong(long startIPLong) {
		this.startIPLong = startIPLong;
	}

	public long getEndIPLong() {
		return endIPLong;
	}

	public void setEndIPLong(long endIPLong) {
		this.endIPLong = endIPLong;
	}

	public IPAndLocationPojo(String startIP, String endIP, String location) {
		super();
		this.startIP = startIP;
		this.endIP = endIP;
		try {
			this.location = Base64Util.encode(location);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.startIPLong = IPUtil.ipToLong(startIP);
		this.endIPLong = IPUtil.ipToLong(endIP);
	}

	public IPAndLocationPojo() {
		super();
	}

	@Override
	public String toString() {
		return "IPAndLocationPojo [startIP=" + startIP + ", endIP=" + endIP
				+ ", location=" + location + ", startIPLong=" + startIPLong
				+ ", endIPLong=" + endIPLong + "]";
	}

	@Override
	public int compareTo(IPAndLocationPojo o) {
		// 因为两个IP段 的 起始 和 结束 相互都没有交集
		// 所以我们用 起始和起始比较 或者 结束和结束比较 都可以
		// 这里不能直接把long强制转换为int
		// 比如 相减之后的值为 2147483648 已经超出int上限,强制转换之后 为 -2147483648 本来是正数 转换之后 成了 负数
		long status = this.startIPLong - o.startIPLong;
		// if (status > 0 ) {
		// return 1;
		// }else if (status < 0) {
		// return -1;
		// }else{
		// return 0;
		// }
		return status > 0 ? 1 : (status < 0 ? -1 : 0);
	}

}
