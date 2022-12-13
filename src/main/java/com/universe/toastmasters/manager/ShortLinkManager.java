package com.universe.toastmasters.manager;

/**
 * @author Nick Liu
 * @date 2022/12/11
 */
public interface ShortLinkManager {

	void saveShortLink(String shortLink, long longLinkHash, String longLink);

	String getShortLink(long longLinkHash, String longLink);

	boolean isShortLinkRepeated(String shortLink);
}
