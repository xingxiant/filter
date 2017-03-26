package com.xxt.servlet;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.StandardSocketOptions;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/ContentServlet")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<4000;i++){
			sb.append("abc");
			
		}
		
		System.out.println("压缩前的网页数据大小："+sb.toString().getBytes().length);
		/**
		 * 使用GZIP进行压缩
		 *//*
		//创建一个临时缓存容器
		ByteArrayOutputStream buf=new ByteArrayOutputStream();
		//创建GZIPOutputStream
		GZIPOutputStream gzip=new GZIPOutputStream(buf);
		//进行压缩
		gzip.write(sb.toString().getBytes());
		//调用结束方法，把缓存内容刷新
		gzip.finish();
		//得到压缩后的内容
		byte[] result=buf.toByteArray();
		System.out.println("压缩后的数据大小:"+result.length);
		
		
		
		
		
		
		//输出到浏览器
		response.getOutputStream().write(result);  //浏览器不认识
		//告诉浏览器是gzip压缩格式
		response.setHeader("content-encoding", "gzip");*/
		response.getWriter().write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}







