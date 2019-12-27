package com.example.java.admin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.java.admin.entity.AdminUsersEntity;
import com.example.java.admin.service.AdminUsersService;

/**
 * 
 * @author huan
 *    AdminUsers业务层实现类
 * @date 2019-12-09 10:04:24
 */
@Service
@Transactional
public class AdminUsersServiceImpl extends BaseServiceImpl<AdminUsersEntity> implements AdminUsersService {
	
}
