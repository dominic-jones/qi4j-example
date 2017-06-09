package com.atlassian.polygene.commons;

import org.qi4j.api.common.Visibility;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.LayerAssembly;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.bootstrap.layered.ModuleAssembler;
import org.qi4j.entitystore.memory.MemoryEntityStoreAssembler;
import org.qi4j.index.rdf.assembly.RdfMemoryStoreAssembler;
import org.qi4j.valueserialization.jackson.JacksonValueSerializationAssembler;

public class MemoryAssembler implements ModuleAssembler {

    @Override
    public ModuleAssembly assemble(final LayerAssembly layer, final ModuleAssembly module) throws AssemblyException {

        new MemoryEntityStoreAssembler().visibleIn(Visibility.layer).assemble(module);
        new JacksonValueSerializationAssembler().assemble(module);
        new RdfMemoryStoreAssembler().assemble(module);

        return module;
    }
}
