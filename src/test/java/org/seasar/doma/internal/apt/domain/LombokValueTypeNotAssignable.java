package org.seasar.doma.internal.apt.domain;

import org.seasar.doma.Domain;
import org.seasar.doma.internal.apt.lombok.Value;

@Domain(valueType = String.class)
@Value
public class LombokValueTypeNotAssignable {

  @SuppressWarnings("unused")
  private Integer value;
}