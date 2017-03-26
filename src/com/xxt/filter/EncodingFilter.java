package com.xxt.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * ����������������Ĺ���
 * 
 * 
 */
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest request1, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		//ǿ��ת��
		HttpServletRequest request=(HttpServletRequest)request1;
		//�����и�װ�����࣬�̳�HttpservletRequestWrapper����дgetParameter����
		
		request.setCharacterEncoding("UTF-8");
		//ʹ��װ������
		MyHttpRequest myRequest=new MyHttpRequest(request);
		//����
		arg2.doFilter(myRequest, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
/**
 * װ������
 */
class MyHttpRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	public MyHttpRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	//��д����
	@Override
	public String getParameter(String name) {
		/**
		 * ��get��ʽ����
		 */
		//�õ�ԭ���Ĳ���
		String value=request.getParameter(name);
		//����
		if(request.getMethod().equals("GET")){
			try {
				value=new String(value.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}
	
}








