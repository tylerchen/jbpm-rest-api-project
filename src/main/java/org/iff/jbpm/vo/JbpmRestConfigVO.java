/*******************************************************************************
 * Copyright (c) Nov 28, 2017 @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a>.
 * All rights reserved.
 *
 * Contributors:
 *     <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> - initial API and implementation
 ******************************************************************************/
package org.iff.jbpm.vo;

import java.io.Serializable;

import org.iff.infra.util.Assert;
import org.iff.jbpm.vo.JbpmRestConfigVO.BuilderInterface.SetConnectTimeout;
import org.iff.jbpm.vo.JbpmRestConfigVO.BuilderInterface.SetContextUrl;
import org.iff.jbpm.vo.JbpmRestConfigVO.BuilderInterface.SetHost;
import org.iff.jbpm.vo.JbpmRestConfigVO.BuilderInterface.SetPassword;
import org.iff.jbpm.vo.JbpmRestConfigVO.BuilderInterface.SetPort;
import org.iff.jbpm.vo.JbpmRestConfigVO.BuilderInterface.SetReadTimeout;
import org.iff.jbpm.vo.JbpmRestConfigVO.BuilderInterface.SetUser;

/**
 * @author <a href="mailto:iffiff1@gmail.com">Tyler Chen</a> 
 * @since Nov 28, 2017
 */
@SuppressWarnings("serial")
public class JbpmRestConfigVO implements Serializable {

	private String user;
	private String password;
	private String host;
	private int port;
	private String contextUrl;
	//
	private int connectTimeout = 10000;
	private int readTimeout = 10000;

	public JbpmRestConfigVO() {
	}

	public JbpmRestConfigVO(String user, String password, String host, int port, String contextUrl) {
		this.user = user;
		this.password = password;
		this.host = host;
		this.port = port;
		this.contextUrl = contextUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getContextUrl() {
		return contextUrl;
	}

	public void setContextUrl(String contextUrl) {
		this.contextUrl = contextUrl;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public String toString() {
		return "JbpmRestConfigVO [user=" + user + ", password=" + password + ", host=" + host + ", port=" + port
				+ ", contextUrl=" + contextUrl + ", connectTimeout=" + connectTimeout + ", readTimeout=" + readTimeout
				+ "]";
	}

	public static class BuilderInterface {
		public interface SetUser {
			SetPassword user(String user);
		}

		public interface SetPassword {
			SetHost password(String password);
		}

		public interface SetHost {
			SetPort host(String host);
		}

		public interface SetPort {
			SetContextUrl port(int port);
		}

		public interface SetContextUrl {
			SetConnectTimeout contextUrl(String contextUrl);
		}

		public interface SetConnectTimeout {
			SetReadTimeout connectTimeout(int connectTimeout);
		}

		public interface SetReadTimeout {
			Builder readTimeout(int readTimeout);
		}
	}

	public static class Builder
			implements SetUser, SetPassword, SetHost, SetPort, SetContextUrl, SetConnectTimeout, SetReadTimeout {
		private JbpmRestConfigVO vo;

		Builder() {
		}

		public static SetUser create() {
			Builder builder = new Builder();
			builder.vo = new JbpmRestConfigVO();
			return builder;
		}

		public JbpmRestConfigVO build() {
			return vo;
		}

		public Builder readTimeout(int readTimeout) {
			vo.setReadTimeout(readTimeout > 0 ? readTimeout : vo.readTimeout);
			return this;
		}

		public SetReadTimeout connectTimeout(int connectTimeout) {
			vo.setConnectTimeout(connectTimeout > 0 ? connectTimeout : vo.connectTimeout);
			return this;
		}

		public SetConnectTimeout contextUrl(String contextUrl) {
			Assert.notBlank(contextUrl);
			vo.setContextUrl(contextUrl);
			return this;
		}

		public SetContextUrl port(int port) {
			Assert.isTrue(port > 0);
			vo.setPort(port);
			return this;
		}

		public SetPort host(String host) {
			Assert.notBlank(host);
			vo.setHost(host);
			return this;
		}

		public SetHost password(String password) {
			Assert.notBlank(password);
			vo.setPassword(password);
			return this;
		}

		public SetPassword user(String user) {
			Assert.notBlank(user);
			vo.setUser(user);
			return this;
		}
	}
}
