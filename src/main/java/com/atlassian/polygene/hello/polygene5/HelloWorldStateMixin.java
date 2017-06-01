package com.atlassian.polygene.hello.polygene5;

import org.qi4j.api.injection.scope.State;
import org.qi4j.api.property.Property;

public class HelloWorldStateMixin implements HelloWorldState {

    @State
    Property<String> phrase;

    @State
    Property<String> name;

    public Property<String> phrase() {
        return phrase;
    }

    public Property<String> name() {
        return name;
    }
}