package com.example.demo1.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "audience")
public class Audience {
		private String clientId;
		private String base64Secret;
		private String name;
		private String expiresSecond;

		public String getClientId() {
				return clientId;
		}

		public void setClientId(String clientId) {
				this.clientId = clientId;
		}

		public String getBase64Secret() {
				return base64Secret;
		}

		public void setBase64Secret(String base64Secret) {
				this.base64Secret = base64Secret;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getExpiresSecond() {
				return expiresSecond;
		}

		public void setExpiresSecond(String expiresSecond) {
				this.expiresSecond = expiresSecond;
		}
}
