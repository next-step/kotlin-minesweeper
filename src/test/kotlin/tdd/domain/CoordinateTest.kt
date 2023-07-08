package tdd.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CoordinateTest {

    @Test
    fun up() {
        Coordinate(1, 1).up() shouldBe Coordinate(0, 1)
    }

    @Test
    fun down() {
        Coordinate(1, 1).down() shouldBe Coordinate(2, 1)
    }

    @Test
    fun left() {
        Coordinate(1, 1).left() shouldBe Coordinate(1, 0)
    }

    @Test
    fun right() {
        Coordinate(1, 1).right() shouldBe Coordinate(1, 2)
    }

    @Test
    fun upLeft() {
        Coordinate(1, 1).upLeft() shouldBe Coordinate(0, 0)
    }

    @Test
    fun upRight() {
        Coordinate(1, 1).upRight() shouldBe Coordinate(0, 2)
    }

    @Test
    fun downLeft() {
        Coordinate(1, 1).downLeft() shouldBe Coordinate(2, 0)
    }

    @Test
    fun downRight() {
        Coordinate(1, 1).downRight() shouldBe Coordinate(2, 2)
    }
}
