package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;


public class Zip {

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        checkArgs(new String[]{argsName.get("d"), argsName.get("e"), argsName.get("o")});
        List<Path> filesToZip = search(Path.of(argsName.get("d")), p -> !(p.toFile().getName().endsWith(argsName.get("e"))));
        zip.packFiles(filesToZip, new File(argsName.get("o")));
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry((file.toFile().getPath())));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Enter the folder for archiving, file extensions that will not be included in the archive and the file name for the archive.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Folder not exist: %s", args[0]));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not a directory: %s", args[0]));
        }
        Pattern pattern = Pattern.compile("^\\.\\S.+");
        Matcher matcher = pattern.matcher(args[1]);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Not an extension of the files: %s", args[1]));
        }
        pattern = Pattern.compile(".+\\.zip$");
        matcher = pattern.matcher(args[2]);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Not a file format like \"file.zip\": %s", args[2]));
        }
    }
}