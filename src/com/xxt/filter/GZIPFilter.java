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
 * ����HttpServletResponse�����
 * getWrite()�������������������һ�������湦�ܵ�
 * PrintWrite����
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
		 * ��������
		 */
		//����һ��response��װ���߶���
		MyHttpResponse response = new MyHttpResponse((HttpServletResponse)arg1);
		//����
		arg2.doFilter(arg0, response);
		/**
		 * ������Ӧ
		 */
		char[] content=response.getCharArray();
		//gzipѹ��
		ByteArrayOutputStream buf=new ByteArrayOutputStream();
		//����GZIPOutputStream
		GZIPOutputStream gzip=new GZIPOutputStream(buf);
		//����ѹ��
		gzip.write(new String(content).getBytes());
		//���ý����������ѻ�������ˢ��
		gzip.finish();
		byte[] result=buf.toByteArray();
		
		//���
		response.setHeader("content-encoding", "gzip");
		response.getOutputStream().write(result);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
class MyHttpResponse extends HttpServletResponseWrapper{
    private HttpServletResponse response;
    //�����и�������������
    private CharArrayWriter charArray=new CharArrayWriter();
    
    public char[] getCharArray(){
    	return charArray.toCharArray();
    }
    
	public MyHttpResponse(HttpServletResponse response) {
		super(response);
		this.response=response;
	}
	/**
	 * ��дgetWrite��������,���䷵��һ�����л��湦�ܵ�PrintWrite
	 */
	@Override
	public PrintWriter getWriter() throws IOException {
		
		return new PrintWriter(charArray);
	}
	
	
	
	
}

