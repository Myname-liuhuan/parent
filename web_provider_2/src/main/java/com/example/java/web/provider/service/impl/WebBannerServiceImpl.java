package com.example.java.web.provider.service.impl;

import com.example.java.web.model.entity.WebBannerEntity;
import com.example.java.web.model.service.WebBannerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author huan
 *    WebBanner业务层实现类
 * @date 2019-12-09 19:07:03
 */
@Service
@Transactional(readOnly = false)
public class WebBannerServiceImpl extends BaseServiceImpl<WebBannerEntity> implements WebBannerService {
	
}
