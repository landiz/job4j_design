package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArgsNameTest {
    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeyIsEmpty() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-=значение"}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueIsEmpty() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-ключ="}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeyValueWithoutEqualSymbol() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-ключ:значение"}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMultipleInARowEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request===?msgExit"});
        assertThat(jvm.get("request")).isEqualTo("==?msgExit");
    }

    @Test
    void whenNoMinusSymbol() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"ключ=значение"}))
                .isInstanceOf(IllegalArgumentException.class);
    }
}