/**
 * 
 */
package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解示例
 * <p>
 * 注解其他Annotation的Annotation叫做元注解。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
/*
 * @Target 元注解可以应用于一个注解，以限制该注解可以应用到哪些项上。
 * 
 * 其元素类型如下：
 * ElementType.ANNOTATION_TYPE - 注解类型声明
 * ElementType.PACKAGE - 包
 * ElementType.TYPE - 类(包括enum、interface和@interface)
 * ElementType.METHOD - 方法
 * ElementType.CONSTRUCTOR - 构造器
 * ElementType.FIELD - 成员变量
 * ElementType.PARAMETER - 方法或构造器参数
 * ElementType.LOCAL_VARIABLE - 本地变量
 * 
 * 没有@Target限制的注解可以应用到任何项上。
 */
@Target({ ElementType.TYPE, ElementType.METHOD })

/*
 * @Retention 元注解用于指定一条注解应该保留多长时间。
 * 
 * 其元素类型如下：
 * RetentionPolicy.SOURCE - 不包括在类文件中的注解
 * RetentionPolicy.CLASS - 类文件中的注解，但JVM不需要将它们载入
 * RetentionPolicy.RUNTIME - 类文件中的注解，并由JVM载入
 */
@Retention(RetentionPolicy.RUNTIME)

/*
 * @Documented 元注解为像Javadoc这样的文档工具提供了一些提示。
 */
@Documented

/*
 * @Inherited 元注解只能应用于对类的注解，如果一个类标注了继承注解，那么它的所有子类都自动具有同样的注解。
 */
@Inherited
public @interface MetaAnnotations {

}
