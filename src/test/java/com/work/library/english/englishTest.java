package com.work.library.english;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.mongodb.client.result.DeleteResult;
import com.work.library.LibraryApplication;
import com.work.library.config.global.GlobalCache;
import com.work.library.entity.DiaryEntity;
import com.work.library.entity.EnglishEntity;
import com.work.library.entity.UserEntity;
import com.work.library.util.EncodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Test
    public void test2() {
        List<Object> list1 = new ArrayList<Object>() {{
            add(EnglishEntity.newInstance());
        }};
        List<Object> list2 = new ArrayList<Object>() {{
            add(EnglishEntity.newInstance());
        }};
        //求交集
        List<Object> intersection = list1.stream()
                .filter(item -> list2.contains(item))
                .map(o -> {

                    return o;
                })
                .collect(Collectors.toList());
        //删除交集
        list1.removeAll(intersection);
        list2.removeAll(intersection);
    }

    @Test
    public void test3() {
        DiaryEntity diaryEntity = new DiaryEntity();
//        diaryEntity.content("11")
//                .title("22");
        DiaryEntity diaryEntity2 = new DiaryEntity();
//        BeanUtil.copyProperties(diaryEntity, diaryEntity2);
        BeanUtils.copyProperties(diaryEntity, diaryEntity2);
        System.out.println(JSONUtil.toJsonStr(diaryEntity2));
    }

    @Test
    public void testToken() {
        String token = "testToken";
        String token1 = GlobalCache.getToken(token);
        System.out.println(token1);
    }

    @Test
    public void testToken2() {
        UserEntity entity = new UserEntity().setUsername("mjy").setPassword("123456");

        String encodeMd5 = EncodeUtil.encodeMd5(entity.toString().getBytes());
        System.out.println("md5: " + encodeMd5);
    }

    @Test
    public void testToken4() {
        String str = "123456";
        String encodeMd5 = EncodeUtil.encodeMd5(str.getBytes());
        for (int i = 0; i <100 ; i++) {

            String encodeMd5_ = EncodeUtil.encodeMd5(str,"mjy");

            System.out.println("md5: " + encodeMd5_);
        }


    }
}
