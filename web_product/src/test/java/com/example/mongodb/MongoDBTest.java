package com.example.mongodb;

import com.example.product.utils.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 刘欢
 * @Date 2019/12/19
 */
public class MongoDBTest {

    //先获取mongoDB的客户端对象
    private static MongoClient client = null;

    //连接的某个mongoDB数据库对象
    private static MongoDatabase mongoDatabase = null;

    //得到指定数据库中的某一集合(mysql 表)对象 增删改查都是通过该对象中的方法实现
    private static MongoCollection<Document> collection = null;

    //连接mongoDB数据库的IP
    private static String host = null;

    //连接mongoDB数据库的端口
    private static String port = null;

    //要连接的集合的名称
    private static String collectionName = null;

    @Before
    public void before(){
        //初始化客户端对象 有参构造的参数是 host 地址
        client = new MongoClient("127.0.0.1");
        //通过客户端获取到指定数据库对象，方法的参数为数据库名称
        mongoDatabase = client.getDatabase("test");
        //获取当前链接的数据库中的指定集合对象  通过数据库对象调用方法得到，参数为集合名
        collection = mongoDatabase.getCollection("book");
    }

    @Test
    public void test01(){
        //查询集合中所有数据
        FindIterable<Document> documents = collection.find();
        /*//遍历迭代器 显示数据
        MongoCursor<Document> iterator = documents.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }*/
        //还有一种方法 可以将遍历简写
        documents.iterator().forEachRemaining(temp -> System.out.println(temp));

        /*//将bson 数据转化为List类型 因为list 可以被spring mvc转化为json 格式的数据。而前端接受数据使用的是json格式
        List<Document> list = new ArrayList<>();
        documents.iterator().forEachRemaining(temp -> list.add(temp));
        for (Document document : list) {
            System.out.println(document);
        }*/
    }

    /**
     * 添加单个数据 将整个语句作为字符串得到一个document 写法1
     */
    @Test
    public void test02(){
        //将json 格式的字符串作为参数 解析为document 对象
        Document document = Document.parse("{'name':'羊脂球','author':'契诃夫','price':'34.5'}");
        collection.insertOne(document);
    }

    /**
     * 添加单个数据 向document对象中追加字段 写法2 (不推荐 因为无法处理比较复杂的插入 比如嵌套bson)
     */
    @Test
    public void test03(){
        Document document = new Document();
        document.append("name","变色龙");
        document.append("author","契诃夫");
        document.append("price","9.15");
    }

    @After
    public void after(){
        client.close();
    }


    /**
     * 向mongoDB的 discuss集合中 添加数据
     * 需要添加的field 有 userImg(评论所带图片) userName color(文字) type(商品型号) content(文字评论) dateTime
     */
    @Test
    public void insertDiscuss(){
        MongoCollection<Document> collection = MongoUtil.getCollection();
        Document document1 = Document.parse("{'userImg':'http://q2ps51wx0.bkt.clouddn.com/04d26c3b-046e-4690-98e0-67f98e6609b0.png'," +
                "'userName':'张三','color':'红色','type':'MacBook Pro i5 8GB 256GB'," +
                "'content':'产品很好，允许很流畅，电池续航久'," +
                "'dataTime':'"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"'}");

        Document document2 = Document.parse("{'userImg':'http://q2ps51wx0.bkt.clouddn.com/0cbfbddb-3c46-47b5-b53a-74e47c0b8249.png'," +
                "'userName':'李四','color':'黄色','type':'棒球帽'," +
                "'content':'颜色很亮 戴在头上闪闪发光 感觉整个人都精神了'," +
                "'dataTime':'"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"'}");

        Document document3 = Document.parse("{'userImg':'http://q2ps51wx0.bkt.clouddn.com/1f898fed-bc77-403f-a83e-03ad36c028f5.png'," +
                "'userName':'赵六','color':'红色','type':'MacBook Pro i5 4GB 256GB'," +
                "'content':'用了半个月才来评论 产品很好 就是又点小贵'," +
                "'dataTime':'"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"'}");

        collection.insertMany(Arrays.asList(document1,document2,document3));
        MongoUtil.close();
    }

}
