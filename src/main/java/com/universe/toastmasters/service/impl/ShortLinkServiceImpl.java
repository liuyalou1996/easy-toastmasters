package com.universe.toastmasters.service.impl;

import com.google.common.hash.Hashing;
import com.universe.toastmasters.manager.ShortLinkManager;
import com.universe.toastmasters.service.ShortLinkService;
import com.universe.toastmasters.util.Base62Utils;
import com.universe.toastmasters.util.SnowFlakeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author Nick Liu
 * @date 2022/12/11
 */
@Service
public class ShortLinkServiceImpl implements ShortLinkService {

	@Autowired
	private ShortLinkManager shortLinkManager;

	@Override
	public String generateShortLink(String longLink) {
		long longLinkHash = Hashing.murmur3_32_fixed().hashString(longLink, StandardCharsets.UTF_8).padToLong();
		// 通过长链接Hash值和长链接检索
		String shortLink = shortLinkManager.getShortLink(longLinkHash, longLink);
		if (StringUtils.isNotBlank(shortLink)) {
			return shortLink;
		}

		// 如果Hash冲突则加随机盐重新Hash
		return regenerateOnHashConflict(longLink, longLinkHash);
	}

	private String regenerateOnHashConflict(String longLink, long longLinkHash) {
		long uniqueIdHash = Hashing.murmur3_32_fixed().hashLong(SnowFlakeUtils.nextId()).padToLong();
		String shortLink = Base62Utils.encodeToBase62String(Math.abs(longLinkHash - uniqueIdHash));
		if (!shortLinkManager.isShortLinkRepeated(shortLink)) {
			shortLinkManager.saveShortLink(shortLink, longLinkHash, longLink);
			return shortLink;
		}
		return regenerateOnHashConflict(longLink, longLinkHash);
	}

}
