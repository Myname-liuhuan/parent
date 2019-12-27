package com.example.java.admin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.java.admin.entity.AdminUserAuthorityEntity;
import com.example.java.admin.service.AdminUserAuthorityService;

/**
 * 
 * @author huan
 *    AdminUserAuthority业务层实现类
 * @date 2019-12-09 10:04:24
 */
@Service
@Transactional
public class AdminUserAuthorityServiceImpl extends BaseServiceImpl<AdminUserAuthorityEntity> implements AdminUserAuthorityService {
	
}
