package com.universe.toastmasters.manager;

/**
 * @author Nick Liu
 * @date 2022/12/11
 */
public interface ShortLinkManager {

	void saveShortLink(String shortLink, String hash, String longLink);

	String getShortLinkByHash(String hash);
}
