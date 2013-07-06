package com.lwl.biz.manager;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component("baseManager")
public class BaseManager {
	
	public String getKeyFromPrefix(String prefix, Object... pros) {
		String key = "";
		String[] pres = prefix.split("_");
		
		if (pros.length -pres.length + 1 != 0) {
			key = prefix + StringUtils.join(pros, "_");
			return key;
		}
		
		
		key = pres[0];

		for (int i = 1; i < pres.length; i++) {
			key += "_" + pros[i-1];
		}

		return key;
	}

/*	public static final String CATEGORYCACHE = "categorycache";
	public static final String CATEGORY_ID = "category_id";
	public static final String PRIVILEGESENDTIMES_AREAID = "privilegesendtimes_areaid";
	
	private EhCacheManager ehCacheManager;
	
	public void putToCache(Serializable value, String cacheName, String keyPrefix, String... keys) {
		ehCacheManager.putToCache(value, cacheName, keyPrefix, keys);
	}
	
	public Serializable getFromCache(String cacheName, String keyPrefix, String... keys) {
		return ehCacheManager.getFromCache(cacheName, keyPrefix, keys);
	}
	
	public void invoidCache(String cacheName, String keyPrefix, String... keys) {
		ehCacheManager.invoidCache(cacheName, keyPrefix, keys);
	}

	public void setEhCacheManager(EhCacheManager ehCacheManager) {
		this.ehCacheManager = ehCacheManager;
	}*/
}
