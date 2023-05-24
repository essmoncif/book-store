package com.store.catalog.app.services.common.storage;

import java.io.File;
import java.io.OutputStream;
import java.util.Set;

public interface StorageService {

    Set<String> getFiles(final String path) throws Exception;

    Set<String> getFiles(final String path, final String pattern) throws Exception;

    boolean hasFile(final String path) throws Exception;

    OutputStream getFile(final String path) throws Exception;

    boolean deleteFile(final String path) throws Exception;

    void putFile(final String path, File file) throws Exception;

    void moveFile(final String sourcePath,final String destinationPath) throws Exception;

    void archiveFile(final String path) throws Exception;
}
