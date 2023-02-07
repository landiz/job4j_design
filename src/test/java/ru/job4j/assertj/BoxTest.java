package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEqualTo("Unknown object")
                .isNotEqualTo("Tetrahedron")
                .isNotEqualTo("Cube");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotEqualTo("Unknown object")
                .isNotEqualTo("Tetrahedron")
                .isNotEqualTo("Sphere");
    }

    @Test
    void checkVertexUnknown() {
        Box box = new Box(7, 12);
        assertThat(box.getNumberOfVertices()).isNotZero()
                .isNegative()
                .isGreaterThan(-2)
                .isEqualTo(-1);
    }

    @Test
    void checkVertexZero() {
        Box box = new Box(0, 12);
        assertThat(box.getNumberOfVertices()).isZero()
                .isEven()
                .isNotNegative()
                .isLessThan(9)
                .isEqualTo(0);
    }

    @Test
    void checkArea() {
        Box box = new Box(4, 8);
        assertThat(box.getArea()).isEqualTo(110.85d, withPrecision(0.006d))
                .isCloseTo(110.85d, withPrecision(0.01d))
                .isCloseTo(110.85d, Percentage.withPercentage(1.0d))
                .isGreaterThan(110.84d)
                .isLessThan(110.86d);
    }

    @Test
    void whenBoxUnknownAreaIsZero() {
        Box box = new Box(7, 8);
        System.out.println(box.getArea());
        assertThat(box.getArea()).isEqualTo(0.0d, withPrecision(0.001d))
                .isCloseTo(0.0d, withPrecision(0.01d))
                .isCloseTo(0.0d, Percentage.withPercentage(1.0d))
                .isGreaterThan(-0.99d)
                .isLessThan(0.01d);
    }

    @Test
    void isVertexExist() {
        Box box = new Box(8, 8);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isVertexNotExist() {
        Box box = new Box(0, 0);
        assertThat(box.isExist()).isFalse();
    }
}