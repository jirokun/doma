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
package org.seasar.doma.internal.apt.reflection;

import static org.seasar.doma.internal.util.AssertionUtil.assertNotNull;
import static org.seasar.doma.internal.util.AssertionUtil.assertNotNullValue;

import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;

import org.seasar.doma.internal.apt.AptIllegalStateException;
import org.seasar.doma.internal.apt.util.AnnotationValueUtil;

/**
 * 
 * @author nakamura-to
 *
 */
public class ValueReflection extends AbstractReflection {

    public static final String STATIC_CONSTRUCTOR = "staticConstructor";

    private final AnnotationValue staticConstructor;

    ValueReflection(AnnotationMirror annotationMirror, Map<String, AnnotationValue> values) {
        super(annotationMirror);
        assertNotNull(values);
        this.staticConstructor = assertNotNullValue(values, STATIC_CONSTRUCTOR);
    }

    public AnnotationValue getStaticConstructor() {
        return staticConstructor;
    }

    public String getStaticConstructorValue() {
        String value = AnnotationValueUtil.toString(staticConstructor);
        if (value == null) {
            throw new AptIllegalStateException(STATIC_CONSTRUCTOR);
        }
        return value;
    }

}
