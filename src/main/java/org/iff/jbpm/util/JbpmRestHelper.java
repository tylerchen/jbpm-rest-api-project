/*******************************************************************************
 * Copyright (c) Nov 23, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.AbstractExecutionAwareRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.util.EntityUtils;
import org.iff.infra.util.Assert;
import org.iff.infra.util.BaseCryptHelper;
import org.iff.infra.util.Exceptions;
import org.iff.infra.util.FCS;
import org.iff.infra.util.GsonHelper;
import org.iff.infra.util.JsonHelper;
import org.iff.infra.util.Logger;
import org.iff.infra.util.MapHelper;
import org.iff.infra.util.SocketHelper;
import org.iff.infra.util.StringHelper;
import org.iff.jbpm.vo.JbpmRestConfigVO;
import org.iff.jbpm.vo.instance.ProcessInstanceVO;
import org.iff.jbpm.vo.instance.WorkItemInstanceVO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.extended.NamedMapConverter;
import com.thoughtworks.xstream.mapper.Mapper;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 23, 2017
 */
@SuppressWarnings("unchecked")
public class JbpmRestHelper {

	public static final String CONTENT_TYPE_XML = "application/xml";
	public static final String CONTENT_TYPE_JSON = "application/json";

	private static HttpClient proxyClient = null;
	private static int connectTimeout = 10000;
	private static int readTimeout = 10000;
	private static XStream xstream = null;
	private static JbpmRestConfigVO config;
	private static Map<String, String> authHeader;

	public static void init(JbpmRestConfigVO vo) {
		Assert.notNull(vo);
		config = vo;
		proxyClient = proxyClient == null ? getProxyClient() : proxyClient;
		authHeader = null;
	}

	static HttpClient getProxyClient() {
		if (proxyClient == null) {
			Assert.notNull(config,
					"JbpmRestConfigVO is NULL, please invoke JbpmRestHelper.init() to set JbpmRestConfigVO first!!!");
			proxyClient = createHttpClient(buildRequestConfig());
		}
		return proxyClient;
	}

	public static JbpmRestConfigVO getConfig() {
		return config;
	}

	public static Map<String, String> getAuthHeader() {
		if (authHeader == null) {
			Assert.notNull(config,
					"JbpmRestConfigVO is NULL, please invoke JbpmRestHelper.init() to set JbpmRestConfigVO first!!!");

			authHeader = Collections
					.unmodifiableMap(
							(Map<String, String>) MapHelper.toMap(//
									"Authorization",
									"Basic " + BaseCryptHelper
											.encodeBase64(config.getUser() + ":" + config.getPassword()), //
							"Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
		}
		return authHeader;
	}

	public static <T> T json(String urlPrefix, String url, Map<String, String> conditions, Type type,
			Object... replaceIds) {
		return excute(JbpmRestHelper.CONTENT_TYPE_JSON, "GET", urlPrefix, url, null, conditions, null, type,
				replaceIds);
	}

	public static <T> List<T> xmlList(String urlPrefix, String url, Map<String, String> conditions, Class<T> type,
			String rootName, Object... replaceIds) {
		String xml = excute(JbpmRestHelper.CONTENT_TYPE_XML, "GET", urlPrefix, url, null, conditions, null, null,
				replaceIds);
		return (List<T>) JbpmRestHelper.toBeans(xml, type, rootName);
	}

	public static <T> List<T> xmlListFix(String urlPrefix, String url, Map<String, String> conditions, Class<T> type,
			String rootName, Map<String, String> replaces, Object... replaceIds) {
		String xml = excute(JbpmRestHelper.CONTENT_TYPE_XML, "GET", urlPrefix, url, null, conditions, null, null,
				replaceIds);
		if (replaces != null && !replaces.isEmpty()) {
			for (Entry<String, String> entry : replaces.entrySet()) {
				xml = StringUtils.replace(xml, entry.getKey(), entry.getValue());
			}
		}
		return (List<T>) JbpmRestHelper.toBeans(xml, type, rootName);
	}

	public static <T> T xml(String urlPrefix, String url, Map<String, String> conditions, Class<?> type,
			Object... replaceIds) {
		String xml = excute(JbpmRestHelper.CONTENT_TYPE_XML, "GET", urlPrefix, url, null, conditions, null, null,
				replaceIds);
		return (T) JbpmRestHelper.toBean(xml, type);
	}

	public static <T> T xmlFix(String urlPrefix, String url, Map<String, String> conditions, Class<?> type,
			Map<String, String> replaces, Object... replaceIds) {
		String xml = excute(JbpmRestHelper.CONTENT_TYPE_XML, "GET", urlPrefix, url, null, conditions, null, null,
				replaceIds);
		if (replaces != null && !replaces.isEmpty()) {
			for (Entry<String, String> entry : replaces.entrySet()) {
				xml = StringUtils.replace(xml, entry.getKey(), entry.getValue());
			}
		}
		return (T) JbpmRestHelper.toBean(xml, type);
	}

	public static <T> T post(String urlPrefix, String url, Map<String, String> headers, Map<String, String> conditions,
			Object content, Type type, Object... replaceIds) {
		return excute(JbpmRestHelper.CONTENT_TYPE_JSON, "POST", urlPrefix, url, headers, conditions, content, type,
				replaceIds);
	}

	public static <T> T put(String urlPrefix, String url, Map<String, String> headers, Map<String, String> conditions,
			Object content, Type type, Object... replaceIds) {
		return excute(JbpmRestHelper.CONTENT_TYPE_JSON, "PUT", urlPrefix, url, headers, conditions, content, type,
				replaceIds);
	}

	public static <T> T del(String urlPrefix, String url, Map<String, String> headers, Map<String, String> conditions,
			Object content, Type type, Object... replaceIds) {
		return excute(JbpmRestHelper.CONTENT_TYPE_JSON, "DELETE", urlPrefix, url, headers, conditions, content, type,
				replaceIds);
	}

	public static <T> T excute(String contentType, String method, String urlPrefix, String url,
			Map<String, String> headers, Map<String, String> conditions, Object content, Type type,
			Object... replaceIds) {
		String uri = JbpmRestHelper.getConfig().getContextUrl() + urlPrefix + "/" + url;
		uri = StringHelper.replaceBlock(uri, replaceIds == null ? new Object[] {} : replaceIds, "");
		if (headers == null) {
			headers = JbpmRestHelper.getAuthHeader();
		} else {
			headers.putAll(JbpmRestHelper.getAuthHeader());
		}

		String xml = JbpmRestHelper.excuteText(//
				method, //
				uri, //
				headers, //
				conditions, //
				content == null ? null : GsonHelper.toJsonString(content), //
				contentType, //
				JbpmRestHelper.getConfig().getHost(), //
				JbpmRestHelper.getConfig().getPort()//		
		);

		//System.out.println(xml);

		if (type != null) {
			try {
				return JsonHelper.GSON.fromJson(xml, type);
			} catch (Exception e) {
				Exceptions.runtime(xml);
			}
		}
		return (T) xml;
	}

	/** 
	 * 将svgCode转换成png文件，直接输出到流中 
	 * 
	 * @param svgCode svg代码 
	 * @param outputStream 输出流 
	 * @throws TranscoderException 异常 
	 * @throws IOException io异常 
	 */
	public static byte[] convertToPng(String svgCode) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			byte[] bytes = svgCode.getBytes("UTF-8");
			PNGTranscoder t = new PNGTranscoder();
			TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
			TranscoderOutput output = new TranscoderOutput(baos);
			t.transcode(input, output);
		} catch (Exception e) {
			Logger.error("Svg code CAN'T converto PNG!!!", e);
		}
		return baos.toByteArray();
	}

	public static <T> List<T> toBeans(String xml, Class<T> clazz, String mapRootToList) {
		if (StringUtils.isBlank(xml) || !xml.startsWith("<")) {
			return new ArrayList<T>();
		}
		getXStream().processAnnotations(clazz);
		getXStream().aliasType(mapRootToList, ArrayList.class);
		return (List<T>) getXStream().fromXML(xml);
	}

	public static <T> T toBean(String xml, Class<T> clazz, String aliasType) {
		if (StringUtils.isBlank(xml) || !xml.startsWith("<")) {
			return null;
		}
		getXStream().processAnnotations(clazz);
		getXStream().aliasType(aliasType, clazz);
		return (T) getXStream().fromXML(xml);
	}

	public static <T> T toBean(String xml, Class<T> clazz) {
		if (StringUtils.isBlank(xml) || !xml.startsWith("<")) {
			return null;
		}
		getXStream().processAnnotations(clazz);
		return (T) getXStream().fromXML(xml);
	}

	public static void aliasType(Class<?> clazz, String aliasType) {
		getXStream().aliasType(aliasType, clazz);
	}

	static XStream getXStream() {
		if (xstream == null) {
			xstream = new XStream();
			xstream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss",
					new String[] { "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "yyyy-MM-dd HH:mm:ss.S z",
							"yyyy-MM-dd HH:mm:ss.S 'UTC'", "yyyy-MM-dd HH:mm:ss.S z", "yyyy-MM-dd HH:mm:ss.S a",
							"yyyy-MM-dd HH:mm:ssz", "yyyy-MM-dd HH:mm:ss z", "yyyy-MM-dd HH:mm:ss 'UTC'",
							"yyyy-MM-dd HH:mm:ssa", "yyyy-MM-dd G HH:mm:ss.S z", "yyyy-MM-dd G HH:mm:ss.S 'UTC'" }));
			//xstream.registerConverter(new StringStringMapConverter(xstream.getMapper());
			//xstream.registerConverter(new StringStringsMapConverter(xstream.getMapper(), "entry", "key", String.class,"value", String[].class));
		}
		return xstream;
	}

	public static class StringStringMapConverter extends NamedMapConverter {
		public StringStringMapConverter(Mapper mapper) {
			super(mapper, "entry", "key", String.class, "value", String.class);
		}
	}

	public static class StringStringsMapConverter extends NamedMapConverter {
		public StringStringsMapConverter(Mapper mapper) {
			super(mapper, "entry", "key", String.class, "value", String[].class);
		}
	}

	public static String excuteText(String method, String uri, Map<String, String> headers,
			Map<String, String> parameters, Object content, String contentType, String host, int port) {
		return (String) excuteText(buildRequest(method, uri, headers, parameters, content, contentType), host, port);
	}

	public static byte[] excuteBinnary(String method, String uri, Map<String, String> headers,
			Map<String, String> parameters, Object content, String contentType, String host, int port) {
		return (byte[]) excuteBinnary(buildRequest(method, uri, headers, parameters, content, contentType), host, port);
	}

	static String excuteText(HttpRequest proxyRequest, String host, int port) {
		return (String) excute(proxyRequest, host, port, false);
	}

	static byte[] excuteBinnary(HttpRequest proxyRequest, String host, int port) {
		return (byte[]) excute(proxyRequest, host, port, true);
	}

	static Object excute(HttpRequest proxyRequest, String host, int port, boolean isBinnary) {
		HttpResponse proxyResponse = null;
		try {
			// Execute the request
			proxyResponse = getProxyClient().execute(new HttpHost(host, port), proxyRequest);
			// Process the response:

			// Pass the response code. This method with the "reason phrase" is deprecated but it's the
			//   only way to pass the reason along too.
			//int statusCode = proxyResponse.getStatusLine().getStatusCode();
			//noinspection deprecation
			//proxyResponse.getStatusLine().getReasonPhrase();
			Header[] headers = proxyResponse.getAllHeaders();
			for (Header header : headers) {
				//System.out.println(header.getName() + "=" + header.getValue());
			}
			HttpEntity entity = proxyResponse.getEntity();
			if (isBinnary) {
				return entity == null ? new byte[0] : SocketHelper.getByte(entity.getContent(), false);
			} else {
				return entity == null ? null : SocketHelper.getContent(entity.getContent(), false);
			}
		} catch (Exception e) {
			if (proxyRequest instanceof AbstractExecutionAwareRequest) {
				try {
					((AbstractExecutionAwareRequest) proxyRequest).abort();
				} catch (Exception ee) {
				}
			}
			Exceptions.runtime(
					FCS.get("excute request error: {0}:{1}/{2}", host, port, proxyRequest.getRequestLine().getUri()),
					e);
		} finally {
			// make sure the entire entity was consumed, so the connection is released
			if (proxyResponse != null) {
				consumeQuietly(proxyResponse.getEntity());
			}
			//Note: Don't need to close servlet outputStream:
			// http://stackoverflow.com/questions/1159168/should-one-call-close-on-httpservletresponse-getoutputstream-getwriter
		}
		return null;
	}

	/** HttpClient v4.1 doesn't have the
	 * {@link org.apache.http.util.EntityUtils#consumeQuietly(org.apache.http.HttpEntity)} method. */
	static void consumeQuietly(HttpEntity entity) {
		try {
			EntityUtils.consume(entity);
		} catch (IOException e) {//ignore
			Logger.debug(e.getMessage(), e);
		}
	}

	static HttpRequest buildRequest(String method, String uri, Map<String, String> headers,
			Map<String, String> parameters, Object content, String contentType) {

		Assert.notBlank(uri);
		method = StringUtils.defaultIfBlank(method, "GET");
		headers = headers == null ? new HashMap<String, String>() : headers;
		parameters = parameters == null ? new HashMap<String, String>() : parameters;
		uri = urlParamToString(uri, parameters);

		Logger.debug("==URI==" + uri);

		HttpRequest request = null;
		if (content != null) {
			Logger.debug("==CONTENT==" + content);
			request = newRequestWithEntity(method, uri, content, contentType);
		} else {
			request = new BasicHttpRequest(method, uri);
		}

		request.setHeader("Content-Type", contentType);

		copyRequestHeaders(headers, request);

		return request;
	}

	static HttpRequest newRequestWithEntity(String method, String uri, Object content, String contentType) {
		BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest(method, uri);
		if (content instanceof String) {
			// 构建消息实体
			StringEntity entity = new StringEntity(content.toString(), Charset.forName("UTF-8"));
			entity.setContentEncoding("UTF-8");
			// 发送格式的数据请求
			entity.setContentType(contentType);
			request.setEntity(entity);
		} else if (content instanceof byte[]) {
			// 构建消息实体
			ByteArrayEntity entity = new ByteArrayEntity((byte[]) content);
			// 发送格式的数据请求
			entity.setContentType(contentType);
			request.setEntity(entity);
		}
		return request;
	}

	/** 
	 * Copy request headers from the servlet client to the proxy request. 
	 * This is easily overridden to add your own.
	 */
	static void copyRequestHeaders(Map<String, String> headers, HttpRequest proxyRequest) {
		for (Entry<String, String> entry : headers.entrySet()) {
			String headerName = entry.getKey();
			//Instead the content-length is effectively set via InputStreamEntity
			if (headerName.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH)) {
				continue;
			}
			proxyRequest.addHeader(headerName, entry.getValue());
		}
	}

	/**
	 * Sub-classes can override specific behaviour of {@link org.apache.http.client.config.RequestConfig}.
	 */
	static RequestConfig buildRequestConfig() {
		RequestConfig.Builder builder = RequestConfig.custom().setRedirectsEnabled(true)
				.setCookieSpec(CookieSpecs.IGNORE_COOKIES) // we handle them in the servlet instead
				.setConnectTimeout(connectTimeout).setSocketTimeout(readTimeout);
		return builder.build();
	}

	/** Called from {@link #init(javax.servlet.ServletConfig)}.
	 *  HttpClient offers many opportunities for customization.
	 *  In any case, it should be thread-safe.
	 **/
	static HttpClient createHttpClient(final RequestConfig requestConfig) {
		return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
	}

	static String urlParamToString(String url, Map<String, String> parameters) {
		if (url != null && url.length() > 0 && parameters.size() > 0) {
			StringBuilder sb = new StringBuilder(512).append(url);
			if (url.endsWith("?") || url.endsWith("&")) {
			} else if (url.indexOf('?') > -1) {
				sb.append('&');
			} else {
				sb.append('?');
			}
			for (Entry<String, String> entry : parameters.entrySet()) {
				sb.append(entry.getKey()).append('=').append(StringUtils.defaultString(entry.getValue())).append('&');
			}
			sb.setLength(sb.length() - 1);
			url = sb.toString();
		}
		return url;
	}

	static void destroy() {
		//Usually, clients implement Closeable:
		SocketHelper.closeWithoutError(proxyClient);
	}
}
