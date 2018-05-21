package com.cj.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 获取异常的堆栈信息工具类
 * @author cj
 */
public class ExceptionUtil {
	
	/**
	 * 获取异常的堆栈信息,将其转成String.
	 * 把所有的堆栈信息,全部转成一个String字符串,并返回.
	 * 通常的使用方式:
	 * 		handler的catch中:
	 * 		return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
	 *
	 * 客户端看到的数据形式是这样的(以by zero异常为例):
	 *
	 * 	{"status":500,"msg":"java.lang.ArithmeticException: / by zero\r\n\tat com.cj.core.controller.ItemController
	 * 	.getItemById(ItemController.java:36)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat
	 * 	sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect
	 * 	.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method
	 * 	.invoke(Method.java:498)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke
	 * 	(InvocableHandlerMethod.java:221)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod
	 * 	.invokeForRequest(InvocableHandlerMethod.java:137)\r\n\tat org.springframework.web.servlet.mvc.method
	 * 	.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:110)\r\n\tat
	 * 	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandleMethod
	 * 	(RequestMappingHandlerAdapter.java:777)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.
	 * 	RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:706)\r\n\tat org.springframework
	 * 	.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\r\n\tat
	 * 	org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:943)\r\n\tat
	 * 	org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:877)\r\n\tat
	 * 	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:966)\r\n\tat
	 * 	org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:857)\r\n\tat javax.servlet.http
	 * 	.HttpServlet.service(HttpServlet.java:622)\r\n\tat org.springframework.web.servlet.FrameworkServlet.service
	 * 	(FrameworkServlet.java:842)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:729)\r\n\tat
	 * 	org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:292)\r\n\tat
	 * 	org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.apache
	 * 	.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n\tat org.apache.catalina.core
	 * 	.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core
	 * 	.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.springframework.web.filter
	 * 	.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:88)\r\n\tat org.springframework.web
	 * 	.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.apache.catalina.core
	 * 	.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core
	 * 	.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.apache.catalina.core
	 * 	.StandardWrapperValve.invoke(StandardWrapperValve.java:212)\r\n\tat org.apache.catalina.core
	 * 	.StandardContextValve.invoke(StandardContextValve.java:106)\r\n\tat org.apache.catalina.authenticator
	 * 	.AuthenticatorBase.invoke(AuthenticatorBase.java:502)\r\n\tat org.apache.catalina.core.StandardHostValve
	 * 	.invoke(StandardHostValve.java:141)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke
	 * 	(ErrorReportValve.java:79)\r\n\tat org.apache.catalina.valves.AbstractAccessLogValve.invoke
	 * 	(AbstractAccessLogValve.java:616)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke
	 * 	(StandardEngineValve.java:88)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service
	 * 	(CoyoteAdapter.java:528)\r\n\tat org.apache.coyote.http11.AbstractHttp11Processor.process
	 * 	(AbstractHttp11Processor.java:1099)\r\n\tat org.apache.coyote.AbstractProtocol$AbstractConnectionHandler
	 * 	.process(AbstractProtocol.java:670)\r\n\tat org.apache.tomcat.util.net.AprEndpoint$SocketProcessor.doRun
	 * 	(AprEndpoint.java:2508)\r\n\tat org.apache.tomcat.util.net.AprEndpoint$SocketProcessor.run
	 * 	(AprEndpoint.java:2497)\r\n\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	 * 	\r\n\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n\tat org.apache
	 * 	.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.lang.Thread.run
	 * 	(Thread.java:745)\r\n","data":null}
	 *
	 * 	可以看到,所有的异常堆栈信息,都以一个字符串的形式,封装在了json中的"msg"这个key中.
	 *
	 * @param t 传入异常e.
	 * @return String
	 * @author cj
	 */
	public static String getStackTrace(Throwable t) {
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
}
