package edu.fzu.zhishe.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 被该注解标记的方法需要社团成员(member, chief)才能访问
 *
 * @author xjliang
 * @date 2020-05-03
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsClubMember {

}
