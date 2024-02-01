package com.example.demo2.config;

import com.baomidou.mybatisplus.core.injector.methods.DeleteById;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * mybatis-plus 高级用法：自定义方法注入
 * 注入一个逻辑删除的方法
 */
@Slf4j
public class OmitById extends DeleteById  {
    /**
     * @since 3.5.0
     */
    protected OmitById() {
        super("omitById");
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        // 仅对demo的user表生效
        if(!"user_info".equals(tableInfo.getTableName())) {
            return super.injectMappedStatement(mapperClass, modelClass, tableInfo);
        }
        String sql = String.format("<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>", tableInfo.getTableName(),
                "SET " + "del" + " = #{" + tableInfo.getKeyProperty() + "}",
                tableInfo.getKeyColumn(), tableInfo.getKeyProperty(),
                "AND " + "del" + " = 0");
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
        return addUpdateMappedStatement(mapperClass, modelClass, "omitById", sqlSource);
    }
}