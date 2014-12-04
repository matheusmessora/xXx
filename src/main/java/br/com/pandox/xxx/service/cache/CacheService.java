package br.com.pandox.xxx.service.cache;

import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    private static final String CACHE_SERVER_INFO = "server-info";
    private static final String CACHE_AUTHENTICATION = "authentication";
    private static final String CACHE_CREATION_TOKEN = "authentication";


    @Autowired
    private EmbeddedCacheManager cacheManager;

    public Integer getAuthentication(String token) {
        return (Integer) cacheManager.getCache(CACHE_AUTHENTICATION).get(token);
    }

    public void putAuthentication(String token, Integer membGuid) {
        cacheManager.getCache(CACHE_AUTHENTICATION).put(token, membGuid, 10, TimeUnit.MINUTES);
    }

    public void putCreationToken(Integer membGuid, String token) {
        cacheManager.getCache(CACHE_CREATION_TOKEN).put(membGuid, token, 24, TimeUnit.HOURS);
    }

    public String getCreationToken(Integer membGuid) {
        return (String) cacheManager.getCache(CACHE_CREATION_TOKEN).get(membGuid);
    }

    public Integer getMembGuidFromToken(String token) {
        Cache<Object, Object> tokens = cacheManager.getCache(CACHE_CREATION_TOKEN);
        if (tokens != null) {
            for (Map.Entry<Object, Object> tokenEntry : tokens.entrySet()) {
                if (tokenEntry.getValue().equals(token)) {
                    return (Integer) tokenEntry.getKey();
                }
            }
        }

        return null;
    }


}
