package com.work.library.config;

import org.bson.types.Decimal128;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Administrator
 * @Description TODO
 * @Date 2021/1/13 16:26
 */
@Configuration
public class MongoConfig {
    /**
     * 处理mongo的BigDecimal类型为题:
     * BigDecimal类型入库会自动转成String类型,会导致排序不准确,入库前进行类型转换
     * 在读和写中都进行类型转换,确保数值和功能的完整性.
     */
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        Converter<Decimal128, BigDecimal> decimal128ToBigDecimal = new Converter<Decimal128, BigDecimal>() {
            @Override
            public BigDecimal convert(Decimal128 decimal128) {
                return decimal128.bigDecimalValue();
            }
        };

        Converter<BigDecimal, Decimal128> bigDecimalToDecimal128 = new Converter<BigDecimal, Decimal128>() {
            @Override
            public Decimal128 convert(BigDecimal bigDecimal) {
                return new Decimal128(bigDecimal);
            }
        };
        return new MongoCustomConversions(Arrays.asList(decimal128ToBigDecimal, bigDecimalToDecimal128));
    }

    /**
     * MongoDB不生成_class字段
     */
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory mongoDbFactory, MongoMappingContext mongoMappingContext, MongoCustomConversions conversions) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        //不保存_class字段
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        //转换器
        mappingConverter.setCustomConversions(conversions);
        return mappingConverter;
    }

    /**
     * 事务配置
     */
    @Bean
    @ConditionalOnProperty(name = "spring.data.mongodb.transaction-enabled", havingValue = "true")
    public MongoTransactionManager transactionManager(MongoDatabaseFactory factory) {
        return new MongoTransactionManager(factory);
    }

}