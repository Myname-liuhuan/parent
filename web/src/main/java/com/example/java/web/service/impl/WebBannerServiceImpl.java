package com.example.java.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.java.web.entity.WebBannerEntity;
import com.example.java.web.service.WebBannerService;

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
