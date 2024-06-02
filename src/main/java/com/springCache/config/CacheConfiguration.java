package com.springCache.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CacheConfiguration {
	
	CacheManagerCustomizer<ConcurrentMapCacheManager> customizer(){
		
		return new ConcurrentCustomizer();
	}
	
	class ConcurrentCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager>{

		@Override
		public void customize(ConcurrentMapCacheManager cacheManager) {
			
			//ConcurrentMapCacheManager has different methods so set those values -customize them
			
			cacheManager.setAllowNullValues(false);
			cacheManager.setCacheNames(Arrays.asList("books"));
			cacheManager.setStoreByValue(true);//cache entry must be serializable-it serailaizes the values before storing in cache
			log.info("caching customizer is invoked");
			
			
		}
		
		
	}

}
