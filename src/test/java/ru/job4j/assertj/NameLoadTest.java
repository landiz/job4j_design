package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkArrayContainSymbolEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"keyvalue", "key1=value1", "key2=value2"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain the symbol \"=\"", names[0]);
    }

    @Test
    void checkArrayContainKey() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"=value", "key1=value1", "key2=value2"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a key", names[0]);
    }

    @Test
    void checkArrayContainValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"key=", "key1=value1", "key2=value2"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value", names[0]);
    }
}