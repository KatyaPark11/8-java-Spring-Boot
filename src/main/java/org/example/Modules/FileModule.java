package org.example.Modules;

public interface FileModule {
    boolean supportsFormat(String path);
    void getDescription();
}
