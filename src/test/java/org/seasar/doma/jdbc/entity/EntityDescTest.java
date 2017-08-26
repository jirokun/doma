/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.jdbc.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import example.entity.Dept;
import example.entity.Emp;
import example.entity.ImmutableEmp;
import example.entity._Dept;
import example.entity._Emp;
import example.entity._ImmutableEmp;
import junit.framework.TestCase;

/**
 * @author nakamura-to
 * 
 */
public class EntityDescTest extends TestCase {

    public void test() throws Exception {
        EntityDesc<Emp> entityDesc = _Emp.getSingletonInternal();
        entityDesc.getName();
    }

    public void testImmutable_newEntity() throws Exception {
        ImmutableEmp emp = new ImmutableEmp(99, "hoge", BigDecimal.ONE, 1);
        EntityDesc<ImmutableEmp> entityDesc = _ImmutableEmp.getSingletonInternal();
        Map<String, Property<ImmutableEmp, ?>> args = new HashMap<>();

        EntityPropertyDesc<ImmutableEmp, ?> idType = entityDesc.getEntityPropertyDesc("id");
        Property<ImmutableEmp, ?> id = idType.createProperty();
        id.load(emp);
        args.put(idType.getName(), id);

        EntityPropertyDesc<ImmutableEmp, ?> salaryType = entityDesc.getEntityPropertyDesc("salary");
        Property<ImmutableEmp, ?> salary = salaryType.createProperty();
        salary.load(emp);
        args.put(salaryType.getName(), salary);

        ImmutableEmp newEmp = entityDesc.newEntity(args);

        assertEquals(Integer.valueOf(99), newEmp.getId());
        assertNull(newEmp.getName());
        assertEquals(BigDecimal.ONE, newEmp.getSalary());
        assertNull(newEmp.getVersion());
    }

    public void testGetTableName_naming() throws Exception {
        EntityDesc<Dept> entityDesc = _Dept.getSingletonInternal();
        assertEquals("dept", entityDesc.getTableName((namingType, text) -> text.toLowerCase()));
    }

    public void testGetQualifiedName_naming_quote() throws Exception {
        EntityDesc<Dept> entityDesc = _Dept.getSingletonInternal();
        assertEquals("[CATA].[dept]", entityDesc.getQualifiedTableName(
                (namingType, text) -> text.toLowerCase(), text -> "[" + text + "]"));
    }

}
