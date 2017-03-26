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
 * 参数中文乱码问题的过滤
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
		//强制转换
		HttpServletRequest request=(HttpServletRequest)request1;
		//创建有个装饰者类，继承HttpservletRequestWrapper，重写getParameter方法
		
		request.setCharacterEncoding("UTF-8");
		//使用装饰者类
		MyHttpRequest myRequest=new MyHttpRequest(request);
		//放行
		arg2.doFilter(myRequest, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
/**
 * 装饰者类
 */
class MyHttpRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	public MyHttpRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	//重写方法
	@Override
	public String getParameter(String name) {
		/**
		 * 对get方式处理
		 */
		//得到原来的参数
		String value=request.getParameter(name);
		//解码
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








