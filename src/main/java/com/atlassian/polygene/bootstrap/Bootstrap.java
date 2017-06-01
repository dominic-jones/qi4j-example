package com.atlassian.polygene.bootstrap;

import com.atlassian.polygene.hello.polygene2.HelloWorldComposite;
import org.qi4j.api.activation.ActivationException;
import org.qi4j.api.structure.Application;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.Energy4Java;
import org.qi4j.bootstrap.LayerAssembly;
import org.qi4j.bootstrap.ModuleAssembly;

public class Bootstrap {

    public static void main(String[] args) throws AssemblyException, ActivationException {
        Energy4Java polygene = new Energy4Java();

        Application application = polygene.newApplication(factory -> {
            ApplicationAssembly assembly = factory.newApplicationAssembly();
            LayerAssembly domain = assembly.layer("domain");
            LayerAssembly persistence = assembly.layer("persistence");

            domain.uses(persistence);
            ModuleAssembly helloWorld = domain.module("hello-world");
            helloWorld.entities(HelloWorldComposite.class);

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
