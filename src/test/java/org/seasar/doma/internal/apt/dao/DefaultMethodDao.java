package org.seasar.doma.internal.apt.dao;

import java.math.BigDecimal;
import org.seasar.doma.Dao;

@Dao(config = MyConfig.class)
public interface DefaultMethodDao {

  default BigDecimal execute(String aaa, Integer bbb) {
    return BigDecimal.ONE;
  }
}