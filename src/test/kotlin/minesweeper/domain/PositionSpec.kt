package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PositionSpec : DescribeSpec({
    describe("인접 위치(Position) 목록 검증 (3x3 보드)") {
        val thresholdWidth = 3
        val thresholdHeight = 3

        withData(
            nameFn = { (position, expectedAdjacentPositions) ->
                "위치가 ${position}이면 인접 위치 목록은 ${expectedAdjacentPositions.joinToString { "(${it.x}, ${it.y})" }}이다."
            },
            ts = listOf(
                Position(0, 0) to listOf(
                    Position(0, 1),
                    Position(1, 0),
                    Position(1, 1),
                ),

                Position(1, 1) to listOf(
                    Position(0, 0),
                    Position(0, 1),
                    Position(0, 2),
                    Position(1, 0),
                    Position(1, 2),
                    Position(2, 0),
                    Position(2, 1),
                    Position(2, 2),
                ),
            ),
        ) { (position, expectedAdjacentPositions) ->
            val adjacentPositions = position.getAdjacentPositions(thresholdWidth, thresholdHeight)

            adjacentPositions shouldBe expectedAdjacentPositions
        }
    }
})
