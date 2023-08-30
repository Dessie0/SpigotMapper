package me.dessie.spigotmapper.tasks;

import me.dessie.spigotmapper.MapperExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Internal;

import javax.inject.Inject;

public abstract class MapTask extends DefaultTask {

    @Input
    private final MapperExtension extension;

    @Internal
    private final String homeDir;

    @Internal
    private final String toolingDir;

    @Internal
    private final String obfJarPath;

    @Inject
    public MapTask(MapperExtension extension) {
        this.extension = extension;
        this.setGroup("mapping");

        this.homeDir = this.getProject().getGradle().getGradleUserHomeDir().getParent();
        this.obfJarPath = this.getProject().getBuildDir().toPath().resolve("libs").resolve(extension.getObfuscatedMappingsOutput() + ".jar").toString();

        //Find the JAR file for SpecialSource
        this.toolingDir = this.getProject().getConfigurations().getByName("compileClasspath")
                .getFiles().stream()
                .filter(it -> it.getName().startsWith("SpecialSource-" + extension.getSpecialSourceVersion() + "-shaded"))
                .findFirst()
                .map(Object::toString)
                .orElse(null);

        if(toolingDir == null) throw new IllegalStateException("Unable to find the SpecialSource JAR.");
    }

    public String getHomeDir() {
        return homeDir;
    }

    public String getToolingDir() {
        return toolingDir;
    }

    public String getObfJarPath() {
        return obfJarPath;
    }

    public MapperExtension getExtension() {
        return extension;
    }
}
