package com.atlassian.polygene.hello.polygene6;

import org.qi4j.api.property.Property;

public interface HelloWorldState {

    Property<String> phrase();

    Property<String> name();
}
