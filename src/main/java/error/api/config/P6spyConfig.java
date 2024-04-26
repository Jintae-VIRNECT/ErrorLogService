package error.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import error.api.common.p6spy.P6SpyEventListener;

/**
 * Project        : PF-Account
 * DATE           : 2022-12-06
 * AUTHOR         : VIRNECT (Jintae Kim)
 * EMAIL          : jtkim@virnect.com
 * DESCRIPTION    :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-06      VIRNECT          최초 생성
 */
@Configuration
public class P6spyConfig {
	@Bean
	public P6SpyEventListener p6SpyCustomEventListener() {
		return new P6SpyEventListener();
	}
}