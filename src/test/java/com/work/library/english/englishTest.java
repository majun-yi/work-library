package com.work.library.english;

import cn.hutool.json.JSONUtil;
import com.mongodb.client.result.DeleteResult;
import com.work.library.LibraryApplication;
import com.work.library.entity.EnglishEntity;
import com.work.library.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @Description TODO
 * @Date 2021/1/13 17:07
 */
@SpringBootTest(classes = LibraryApplication.class)
@RunWith(SpringRunner.class)
public class englishTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void test() {
//        EnglishEntity englishEntity = new EnglishEntity();
//        englishEntity.buildBase();
//        EnglishEntity englishEntity1 = Optional.ofNullable(englishEntity).orElse(null);
//        System.out.println(JSONUtil.toJsonStr(englishEntity1));
        String id = null;
//        EnglishEntity entity = mongoTemplate.findById(id, EnglishEntity.class);
//        System.out.println(1);

        DeleteResult result = mongoTemplate.remove(Query.query(Criteria.where("id").is("5ff6c68990c0bd66446cb103")), UserEntity.class);
        System.out.println(1);
    }
}
