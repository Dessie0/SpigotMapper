package me.dessie.spigotmapper;

import org.gradle.api.Project;
import org.gradle.api.tasks.Input;

public class MapperExtension {

    @Input
    private String specialSourceVersion = "1.11.0";

    /**
     * Defines which version of Minecraft you're building against.
     */
    @Input
    private String minecraftVersion = "1.20.1-R0.1-SNAPSHOT";

    /**
     * Changes the output name for the JAR with obfuscated mappings.
     * You generally will probably have no use for this JAR.
     */
    @Input
    private String obfuscatedMappingsOutput;

    /**
     * Changes the output name for the JAR with Spigot Mappings.
     * Remember, this version is the one that should be placed in your server's plugins folder.
     */
    @Input
    private String spigotMappingsOutput;

    /**
     * Uses a semicolon (;) instead of a colon (:) to separate classpath arguments when using the java command.
     * Linux machines will use a colon, so set this to false if you're getting errors building on a Linux machine.
     */
    @Input
    private boolean useWindowsSeparator = true;

    private String localMinecraftRepo = "/.m2/repository/org/spigotmc/minecraft-server/";
    private String localSpigotRepo = "/.m2/repository/org/spigotmc/spigot/";

    public MapperExtension(Project project) {
        this.obfuscatedMappingsOutput = project.getName() + "-" + project.getVersion() + "-obf";
        this.spigotMappingsOutput = project.getName() + "-" + project.getVersion() + "-remapped";
    }

    public String getSpecialSourceVersion() {
        return specialSourceVersion;
    }

    public void setSpecialSourceVersion(String specialSourceVersion) {
        this.specialSourceVersion = specialSourceVersion;
    }

    public String getMinecraftVersion() {
        return minecraftVersion;
    }

    public void setMinecraftVersion(String minecraftVersion) {
        this.minecraftVersion = minecraftVersion;
    }

    public boolean isUseWindowsSeparator() {
        return useWindowsSeparator;
    }

    public void setUseWindowsSeparator(boolean useWindowsSeparator) {
        this.useWindowsSeparator = useWindowsSeparator;
    }

    public String getLocalMinecraftRepo() {
        return localMinecraftRepo;
    }

    public void setLocalMinecraftRepo(String localMinecraftRepo) {
        this.localMinecraftRepo = localMinecraftRepo;
    }

    public String getObfuscatedMappingsOutput() {
        return obfuscatedMappingsOutput;
    }

    public void setObfuscatedMappingsOutput(String obfuscatedMappingsOutput) {
        this.obfuscatedMappingsOutput = obfuscatedMappingsOutput;
    }

    public String getSpigotMappingsOutput() {
        return spigotMappingsOutput;
    }

    public void setSpigotMappingsOutput(String spigotMappingsOutput) {
        this.spigotMappingsOutput = spigotMappingsOutput;
    }

    public String getLocalSpigotRepo() {
        return localSpigotRepo;
    }

    public void setLocalSpigotRepo(String localSpigotRepo) {
        this.localSpigotRepo = localSpigotRepo;
    }
}
