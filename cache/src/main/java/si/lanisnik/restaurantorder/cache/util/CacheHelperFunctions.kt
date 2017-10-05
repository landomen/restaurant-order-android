package si.lanisnik.restaurantorder.cache.util

fun isCacheExpired(lastCacheTime: Long): Boolean = System.currentTimeMillis() - lastCacheTime > CacheConstants.EXPIRATION_TIME