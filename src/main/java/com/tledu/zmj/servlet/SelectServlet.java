package com.tledu.zmj.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tledu.zmj.dao.IIPLocationDao;
import com.tledu.zmj.dao.impl.IPLocationDao;

public class SelectServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String IPAddress = request.getParameter("IPAddress");
		IIPLocationDao iplocationDao = new IPLocationDao();
		String iplocation = iplocationDao.load(IPAddress);
		request.setAttribute("IPLocation", iplocation);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
