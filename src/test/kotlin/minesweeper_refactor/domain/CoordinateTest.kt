package minesweeper_refactor.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.Coordinate
import minesweeper_refactor.domain.coordinate.EightDirectionsDecision
import minesweeper_refactor.domain.coordinate.FourDirectionsDecision
import org.junit.jupiter.api.Assertions.*

class CoordinateTest : StringSpec({

    "좌표를 생성한 뒤 주변 좌표를 받아올 수 있다." {
        val coordinate = Coordinate(x = 10, y = 5)
        val aroundCoordinates = coordinate.toAroundDirections(aroundDecision = EightDirectionsDecision)

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
        val aroundCoordinates = coordinate.toAroundDirections(aroundDecision = FourDirectionsDecision)

        aroundCoordinates shouldHaveSize 4
        aroundCoordinates shouldContainAll listOf(
            Coordinate(x = 10, y = 6),
            Coordinate(x = 10, y = 4),
            Coordinate(x = 11, y = 5),
            Coordinate(x = 9, y = 5),
        )
    }

    "주변 좌표를 비교할 수 있다." {
        val firstCoordinate = Coordinate(x = 10, y = 5)
        val secondCoordinate = Coordinate(x = 10, y = 7)
        val thirdCoordinate = Coordinate(x = 11, y = 5)

        val sortedCoordinates = listOf(secondCoordinate, thirdCoordinate, firstCoordinate).sorted()

        sortedCoordinates shouldBe listOf(firstCoordinate, secondCoordinate, thirdCoordinate)
    }
})
