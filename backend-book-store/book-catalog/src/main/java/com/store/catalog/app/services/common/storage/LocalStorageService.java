package com.store.catalog.app.services.common.storage;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocalStorageService implements StorageService {
    @Value("${S3.bucketName}")
    private String bucketName;

    @Override
    public Set<String> getFiles(String path) throws IOException {
        final String directory = Paths.get(this.bucketName, path).toString();
        try(Stream<Path> stream = Files.list(Paths.get(directory))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public Set<String> getFiles(String path, String pattern) throws IOException {
        Pattern regex = Pattern.compile(pattern);
        return this.getFiles(path)
                .stream()
                .filter(regex.asPredicate())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean hasFile(String path) throws IOException {
        final String directory = Paths.get(this.bucketName, path).toString();
        return getFiles(directory)
                .isEmpty();
    }

    @Override
    public OutputStream getFile(String path) throws IOException {
        if (this.hasFile(path)) {
            final String filePath = Paths.get(this.bucketName, path).toString();
            return new FileOutputStream(filePath);
        }
        return null;
    }

    @Override
    public boolean deleteFile(String path) throws IOException {
        if (this.hasFile(path)) {
            final String filePath = Paths.get(this.bucketName, path).toString();
            return new File(filePath).delete();
        }
        return false;
    }

    @Override
    public void putFile(String path, File file) throws IOException {
        final String directory = Paths.get(this.bucketName, path).toString();
        FileUtils.copyFile(file, new File(directory));
    }

    @Override
    public void moveFile(String sourcePath, String destinationPath) throws IOException{
        final String completeSourcePath = Paths.get(this.bucketName, sourcePath).toString();
        final String completeDestinationPath = Paths.get(this.bucketName, destinationPath).toString();
        FileUtils.moveFile(new File(completeSourcePath), new File(completeDestinationPath));
    }

    @Override
    public void archiveFile(String path) throws IOException {
        final String archiveDirectory = Paths.get(this.bucketName, "ARCHIVE").toString();
        this.moveFile(path, archiveDirectory);
    }
}
