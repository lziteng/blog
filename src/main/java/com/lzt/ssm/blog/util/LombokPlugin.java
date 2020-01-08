package com.lzt.ssm.blog.util;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 集成lombok插件
 * 1、不生成get、set方法
 * 2、使用lombok注解替代
 *
 * @author lzt
 * @date 2020/1/8 14:32
 */
public class LombokPlugin extends PluginAdapter {
    public LombokPlugin() {
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //添加domain的import
        topLevelClass.addImportedType("lombok.Data");
//        topLevelClass.addImportedType("lombok.Builder");
//        topLevelClass.addImportedType("lombok.NoArgsConstructor");
//        topLevelClass.addImportedType("lombok.AllArgsConstructor");

        //添加domain的注解
        topLevelClass.addAnnotation("@Data");
//        topLevelClass.addAnnotation("@Builder");
//        topLevelClass.addAnnotation("@NoArgsConstructor");
//        topLevelClass.addAnnotation("@AllArgsConstructor");

        //添加domain的注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * Created by Mybatis Generator on " + date2Str(new Date()));
        topLevelClass.addJavaDocLine(" * @author lzt ");
        topLevelClass.addJavaDocLine(" */");

        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        //Mapper文件的注释
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine(" * Created by Mybatis Generator on " + date2Str(new Date()));
        interfaze.addJavaDocLine(" * @author lzt ");
        interfaze.addJavaDocLine(" */");
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

    private String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
}
