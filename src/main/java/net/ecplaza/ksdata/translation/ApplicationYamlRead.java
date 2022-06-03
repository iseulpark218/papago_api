package net.ecplaza.ksdata.translation;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//value를 통해 값이 있는 위치를 명시해준다.
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
// yml 파일에서 가져올 변수 이름을 명시해준다.
@ConfigurationProperties(prefix = "translation")
@Setter
@Getter
public class ApplicationYamlRead {
	private String url;

	@Value("${translation.url.pusan}")
	private String pusanUrl;

	@Value("${translation.url.naver}")
	private String naverUrl;

	@Value("${translation.url.naver_address}")
	private String naverAddressUrl;
}
