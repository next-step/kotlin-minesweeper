package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize

class CoordinateTest : StringSpec({

    "좌표를 생성한 뒤 주변 8개의 좌표를 받아올 수 있다." {
        val coordinate = Coordinate(x = 10, y = 5)
        val aroundCoordinates = coordinate.getEightDirections()

        aroundCoordinates shouldHaveSize 8
        aroundCoordinates shouldContainAll listOf(
            Coordinate(x = 10, y = 6),
            Coordinate(x = 10, y = 4),
            Coordinate(x = 11, y = 6),
            Coordinate(x = 11, y = 5),
            Coordinate(x = 11, y = 4),
            Coordinate(x = 9, y = 6),
            Coordinate(x = 9, y = 5),
            Coordinate(x = 9, y = 4),
        )
    }

    "좌표를 생성한 뒤 주변 4개의 좌표를 받아올 수 있다." {
        val coordinate = Coordinate(x = 10, y = 5)
        val aroundCoordinates = coordinate.getFourDirections()

        aroundCoordinates shouldHaveSize 4
        aroundCoordinates shouldContainAll listOf(
            Coordinate(x = 10, y = 6),
            Coordinate(x = 10, y = 4),
            Coordinate(x = 11, y = 5),
            Coordinate(x = 9, y = 5),
        )
    }
})
