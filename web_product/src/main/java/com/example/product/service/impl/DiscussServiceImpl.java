package com.example.product.service.impl;

import com.example.java.web.model.service.DiscussService;
import com.example.product.utils.MongoUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/19
 */
@Service
public class DiscussServiceImpl implements DiscussService {
    /**
     * 将评论数据 查询出来 显示到页面的评论区
     * @return
     */
    @Override
    public List<Document> loadDiscuss() {
        MongoCollection<Document> collection = MongoUtil.getCollection();
        FindIterable<Document> documents = collection.find();
        List<Document> documentList = new ArrayList<>();
        documents.iterator().forEachRemaining(discuss -> documentList.add(discuss));
        MongoUtil.close();
        return documentList;
    }
}
