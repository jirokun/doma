package org.seasar.doma.internal.apt.processor.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

@Dao(config = MyConfig.class)
public interface ParameterizedParamDao {

  @Select
  int select(Height<String> height);
}
