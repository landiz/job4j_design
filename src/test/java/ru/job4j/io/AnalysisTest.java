package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void whenSourceFileThenTargetFile(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:59:0111:01:02;11:02:02").isEqualTo(rsl.toString());
    }

    @Test
    void whenSourceFileEmpty(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("").isEqualTo(rsl.toString());
    }

    @Test
    void whenSourceFileDoesNotContainLogs(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("50010:57:01");
            out.println("40010:58:01");
            out.println("3000:59:01");
            out.println("50011:01:02");
            out.println("20011:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("").isEqualTo(rsl.toString());
    }

    @Test
    void whenServerDownAllTime(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("500 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("500 11:01:02");
            out.println("400 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:56:01;").isEqualTo(rsl.toString());
    }

    @Test
    void whenServerRunningAllTime(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("200 10:57:01");
            out.println("300 10:58:01");
            out.println("300 10:59:01");
            out.println("200 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("").isEqualTo(rsl.toString());
    }
}