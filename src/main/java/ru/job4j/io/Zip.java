package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.checkArgs;
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
}