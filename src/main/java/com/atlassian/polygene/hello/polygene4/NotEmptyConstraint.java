package com.atlassian.polygene.hello.polygene4;

import org.qi4j.api.constraint.Constraint;

public class NotEmptyConstraint implements Constraint<NotEmpty, String> {
    @Override
    public boolean isValid(final NotEmpty annotation, final String s) {
        return s != null;
    }
}
