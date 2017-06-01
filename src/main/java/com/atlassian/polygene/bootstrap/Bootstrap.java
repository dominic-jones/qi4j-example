package com.atlassian.polygene.bootstrap;

import org.qi4j.api.activation.ActivationException;
import org.qi4j.api.structure.Application;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.Energy4Java;
import org.qi4j.bootstrap.LayerAssembly;

public class Bootstrap {

    public static void main(String[] args) throws AssemblyException, ActivationException {
        Energy4Java polygene = new Energy4Java();

        Application application = polygene.newApplication(factory -> {
            ApplicationAssembly assembly = factory.newApplicationAssembly();
            LayerAssembly domain = assembly.layer("domain");
            LayerAssembly persistence = assembly.layer("persistence ");

            domain.uses(persistence);

            return assembly;
        });

        installShutdownHook(application);

        application.activate();
    }

    private static void installShutdownHook(final Application application) {
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
