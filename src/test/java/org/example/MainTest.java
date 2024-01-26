package org.example;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class MainTest {

    double a = 2.4;
    Offset<Double> EPS = Offset.offset(1e-6);

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({
            "2.4,-1.19914051948",
            "2.4000000000001,0.0",
            "31,165.784800268",
            "1,-0.0668948486424",
            "2.39999999999999,0.00272959390369",
            "-0.65449846949,0.0",
            "-0.327,1.55088264223"
    })
    void testFunction(double x, double expected) {
        Assertions.assertThat(Main.function(a, x)).isCloseTo(expected, EPS);
    }

    @ParameterizedTest
    @CsvSource({
            "5.0,7.0,0.1,21",
            "5.0,7.005,0.1,21",
            "-10,-5,0.1,51",
            "-10,5,0.0001,150001",
    })
    void testCountSteps(double start, double end, double step, int expected) {
        Assertions.assertThat(Main.countSteps(start, end, step)).isEqualTo(expected);
    }

    @Test
    void testCreateArrayForArgumentX_1() {
        double[] expectedArray = {0.0, 0.1, 0.2, 0.3, 0.4};
        double start = 0;
        double end = 0.4;
        double step = 0.1;

        double[] actualArray = Main.createArrayForArgumentX(start, end, step);

        compareDecimalArrays(expectedArray, actualArray);
    }


    @Test
    void testCreateArrayForArgumentX_2() {
        double[] expectedArray = {2.3, 2.4, 2.5};
        double start = 2.3;
        double end = 2.5;
        double step = 0.1;

        double[] actualArray = Main.createArrayForArgumentX(start, end, step);

        compareDecimalArrays(expectedArray, actualArray);
    }

    private  void compareDecimalArrays(double[] expectedArray, double[] actualArray) {
        Assertions.assertThat(actualArray.length).isEqualTo(expectedArray.length);
        for (int i = 0; i < actualArray.length; i++) {
            Assertions.assertThat(actualArray[i]).isCloseTo(expectedArray[i], EPS);
        }
    }

    @Test
    void testCalculateArrayYForArrayOfX() {

        double[] array = {-3, -2, -1, 0, 1, 2, 3};

        double[] expected = {814.844466244, 10.6320380102, -8.12842098839, 1, -0.0668948486424, 0.000720094500753, 2.32379000772};

        double[] actual = Main.calculateArrayYForArrayOfX(a, array);

        compareDecimalArrays(expected, actual);
    }

    @Test
    void testCalculateYForRangeX() {
        double[] expectedX = {-3, -2, -1, 0, 1, 2, 3};
        double[] expectedY = {814.844466244, 10.6320380102, -8.12842098839, 1, -0.0668948486424, 0.000720094500753, 2.32379000772};

        double[][] actual = Main.calculateYForRangeX(a, -3, 3,1);
        compareDecimalArrays(expectedX, actual[0]);
        compareDecimalArrays(expectedY, actual[1]);
    }

    @Test
    void testFindMaxValueIndex() {
        double[] values = {241, 241.124, -2152, -0., 1, 0, -1242152155};
        int expectedIndex = 1;
        Assertions.assertThat(Main.findMaxValueIndex(values)).isEqualTo(expectedIndex);
    }

    @Test
    void testFindMinValueIndex() {

        double[] values = {814.844466244, 10.6320380102, -8.12842098839, 1, -0.0668948486424, 0.000720094500753, 2.32379000772};
        int expectedIndex = 2;
        Assertions.assertThat(Main.findMinValueIndex(values)).isEqualTo(expectedIndex);
    }

    @Test
    void testCalculateSum() {
        double[] values = {134134341.11, -124241.5132, 241.152, 395.0000000001, 13333195.521521, 98687883.125};
        double expectedResult = 2.4603181439532098e8;
        Assertions.assertThat(Main.calculateSum(values)).isCloseTo(expectedResult, EPS);

    }

    @Test
    void testCalculateAverage() {
        double[] values = {451.31511, -1.5132, 241.152, 395.0000000004, 13195.52113411521, 18687.125};
        double expectedResult = 5494.766674019268;

        Assertions.assertThat(Main.calculateAverage(values)).isCloseTo(expectedResult, EPS);

    }
}