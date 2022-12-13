package com.universe.toastmasters.service.impl;

import com.universe.toastmasters.manager.ShortLinkManager;
import com.universe.toastmasters.service.ShortLinkService;
import com.universe.toastmasters.util.SnowFlakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		long id = SnowFlakeUtils.nextId();
		return null;
	}

	public static void main(String[] args) {
		long uniqueId = SnowFlakeUtils.nextId();
		System.out.println(String.valueOf(uniqueId).length());
	}
}
