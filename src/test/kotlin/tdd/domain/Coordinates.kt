package tdd.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CoordinatesTest {

    @Test
    fun `Given height and width, create all coordinates`() {
        Coordinates.all(height = 3, width = 3) shouldBe Coordinates(
            listOf(
                Coordinate(0, 0),
                Coordinate(0, 1),
                Coordinate(0, 2),
                Coordinate(1, 0),
                Coordinate(1, 1),
                Coordinate(1, 2),
                Coordinate(2, 0),
                Coordinate(2, 1),
                Coordinate(2, 2),
            )
        )
    }
}
