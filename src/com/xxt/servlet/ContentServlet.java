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
		
		System.out.println("ѹ��ǰ����ҳ���ݴ�С��"+sb.toString().getBytes().length);
		/**
		 * ʹ��GZIP����ѹ��
		 *//*
		//����һ����ʱ��������
		ByteArrayOutputStream buf=new ByteArrayOutputStream();
		//����GZIPOutputStream
		GZIPOutputStream gzip=new GZIPOutputStream(buf);
		//����ѹ��
		gzip.write(sb.toString().getBytes());
		//���ý����������ѻ�������ˢ��
		gzip.finish();
		//�õ�ѹ���������
		byte[] result=buf.toByteArray();
		System.out.println("ѹ��������ݴ�С:"+result.length);
		
		
		
		
		
		
		//����������
		response.getOutputStream().write(result);  //���������ʶ
		//�����������gzipѹ����ʽ
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







