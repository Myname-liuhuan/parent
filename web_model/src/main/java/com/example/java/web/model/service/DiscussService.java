package com.example.java.web.model.service;

import org.bson.Document;

import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/19
 */
public interface DiscussService {

    /**
     * 从mongoDB中加载
     * @return
     */
    List<Document> loadDiscuss();

}
