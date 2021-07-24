package black.lyg.blog.service;

import black.lyg.blog.modelEntity.TypeTops;
import black.lyg.blog.po.Type;
import com.github.pagehelper.Page;

import java.util.List;

public interface TypeService {

    Type saveType(Type type);

    Type getType(Integer typeId);

    Page<Type> listType();

    Type updateType(Type type);

    int deleteType(Integer id);

    Type getTypeByName(String typeName);

    List<Type> allType();

    List<TypeTops> findSeveralTypes(Integer number);

    Integer findCount();
}

