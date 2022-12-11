package com.universe.toastmasters.manager.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.universe.toastmasters.constant.CommonConst;
import com.universe.toastmasters.manager.ShortLinkManager;
import com.universe.toastmasters.mapper.ShortLinkMapper;
import com.universe.toastmasters.pojo.domain.ShortLinkDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nick Liu
 * @date 2022/12/11
 */
@Repository
public class ShortLinkManagerImpl implements ShortLinkManager {

	@Autowired
	private ShortLinkMapper shortLinkMapper;


	@Override
	public void saveShortLink(String shortLink, String hash, String longLink) {
		ShortLinkDO shortLinkDO = ShortLinkDO.builder()
			.shortLink(shortLink)
			.hash(hash)
			.longLink(longLink)
			.build();
		shortLinkMapper.insert(shortLinkDO);
	}

	@Override
	public String getShortLinkByHash(String hash){
		Wrapper<ShortLinkDO> wrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
			.select(ShortLinkDO::getShortLink)
			.eq(ShortLinkDO::getHash, hash)
			.last(CommonConst.LIMIT_SQL);
		ShortLinkDO shortLinkDO = shortLinkMapper.selectOne(wrapper);
		return Optional.ofNullable(shortLinkDO).map(ShortLinkDO::getShortLink).orElse(null);
	}

}
