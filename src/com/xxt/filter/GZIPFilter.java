package com.xxt.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
/**
 * 改造HttpServletResponse对象的
 * getWrite()方法，让这个方法返回一个带缓存功能的
 * PrintWrite对象
 * @author 13983
 *
 */
public class GZIPFilter implements Filter {

	@Override
	public void destroy() {
		

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		/**
		 * 过滤请求
		 */
		//创建一个response的装饰者对象
		MyHttpResponse response = new MyHttpResponse((HttpServletResponse)arg1);
		//放行
		arg2.doFilter(arg0, response);
		/**
		 * 过滤响应
		 */
		char[] content=response.getCharArray();
		//gzip压缩
		ByteArrayOutputStream buf=new ByteArrayOutputStream();
		//创建GZIPOutputStream
		GZIPOutputStream gzip=new GZIPOutputStream(buf);
		//进行压缩
		gzip.write(new String(content).getBytes());
		//调用结束方法，把缓存内容刷新
		gzip.finish();
		byte[] result=buf.toByteArray();
		
		//输出
		response.setHeader("content-encoding", "gzip");
		response.getOutputStream().write(result);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
class MyHttpResponse extends HttpServletResponseWrapper{
    private HttpServletResponse response;
    //定义有个缓存容器对象
    private CharArrayWriter charArray=new CharArrayWriter();
    
    public char[] getCharArray(){
    	return charArray.toCharArray();
    }
    
	public MyHttpResponse(HttpServletResponse response) {
		super(response);
		this.response=response;
	}
	/**
	 * 重写getWrite（）方法,让其返回一个带有缓存功能的PrintWrite
	 */
	@Override
	public PrintWriter getWriter() throws IOException {
		
		return new PrintWriter(charArray);
	}
	
	
	
	
}

