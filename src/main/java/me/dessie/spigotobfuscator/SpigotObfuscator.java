package me.dessie.spigotobfuscator;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;

class SpigotObfuscator implements Plugin<Project> {
    public void apply(Project project) {
        ObfuscationExtension spigotobfuscator = project.getExtensions().create("spigotobfuscator", ObfuscationExtension.class, project);
        TaskProvider<ObfuscateTask> task = project.getTasks().register("obfuscate-spigot", ObfuscateTask.class, spigotobfuscator);

        //Add the SpecialSource dependency to the project so we know it's there.
        project.getDependencies().add("compileOnly", "net.md-5:SpecialSource:" + spigotobfuscator.getSpecialSourceVersion() + ":shaded");

        if(spigotobfuscator.isFinalizedByJar()) {
            project.getTasks().getByName("jar").finalizedBy(task);
        }
    }
}