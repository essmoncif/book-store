package com.store.catalog.app.services.common.storage;

import java.io.File;
import java.io.OutputStream;
import java.util.Set;

public class S3StorageService implements StorageService{
    @Override
    public Set<String> getFiles(String path) throws Exception {
        return null;
    }

    @Override
    public Set<String> getFiles(String path, String pattern) throws Exception {
        return null;
    }

    @Override
    public boolean hasFile(String path) throws Exception {
        return false;
    }

    @Override
    public OutputStream getFile(String path) throws Exception {
        return null;
    }

    @Override
    public boolean deleteFile(String path) throws Exception {
        return false;
    }

    @Override
    public void putFile(String path, File file) throws Exception {

    }

    @Override
    public void moveFile(String sourcePath, String destinationPath) throws Exception {

    }

    @Override
    public void archiveFile(String path) throws Exception {

    }
}
