package org.seasar.doma.it.domain;

import doma.domain.AbstractIntegerDomain;

public class VersionDomain extends AbstractIntegerDomain<VersionDomain> {

    public VersionDomain() {
        super();
    }

    public VersionDomain(Integer value) {
        super(value);
    }

}
