package org.seasar.doma.internal.apt.dao;

import org.seasar.doma.internal.apt.entity.Emp;
import org.seasar.doma.internal.apt.entity.Person;

public interface ExpressionValidationDao {

  void testEmp(Emp emp);

  void testPerson(Person person);
}