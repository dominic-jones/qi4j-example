package com.atlassian.polygene.commons;

import org.qi4j.api.structure.Application;

public class Qi4jCommons {
    public static void installShutdownHook(final Application application) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (application != null) {
                try {
                    application.passivate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
