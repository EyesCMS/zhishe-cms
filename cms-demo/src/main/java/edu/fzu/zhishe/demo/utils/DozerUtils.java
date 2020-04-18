package edu.fzu.zhishe.demo.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;

/**
 * @author liang on 4/13/2020.
 */
public class DozerUtils {

    static Mapper dozerBeanMapper = DozerBeanMapperBuilder.buildDefault();

    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List destinationList = Lists.newArrayList();
        for (Object object : sourceList) {
            Object destinationObject = dozerBeanMapper.map(object, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }
}
