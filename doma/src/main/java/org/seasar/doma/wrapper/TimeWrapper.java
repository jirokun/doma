/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.doma.wrapper;

import java.sql.Time;

import org.seasar.doma.DomaNullPointerException;

/**
 * {@link Time} を値の型とするドメインの骨格実装です。
 * 
 * @author taedium
 * 
 */
public class TimeWrapper extends AbstractWrapper<Time> {

    public TimeWrapper() {
    }

    public TimeWrapper(Time value) {
        set(value);
    }

    @Override
    public <R, P, TH extends Throwable> R accept(
            WrapperVisitor<R, P, TH> visitor, P p) throws TH {
        if (visitor == null) {
            throw new DomaNullPointerException("visitor");
        }
        if (TimeWrapperVisitor.class.isInstance(visitor)) {
            @SuppressWarnings("unchecked")
            TimeWrapperVisitor<R, P, TH> v = TimeWrapperVisitor.class
                    .cast(visitor);
            return v.visitTimeWrapper(this, p);
        }
        return visitor.visitUnknownDomain(this, p);
    }

}