package org.seasar.doma.internal.apt.entity;

import org.seasar.doma.jdbc.id.BuiltinSequenceIdGenerator;

public class NoDefaultConstructorSequenceIdGenerator extends BuiltinSequenceIdGenerator {

  private NoDefaultConstructorSequenceIdGenerator() {}
}