package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PositionTest : StringSpec({
    "특정 좌표의 근처에 해당하는 좌표를 반환한다." {
        val position = Position(1, 1)

        val expectedPositions =
            setOf(
                Position(0, 0),
                Position(0, 1),
                Position(0, 2),
                Position(1, 0),
                Position(1, 2),
                Position(2, 0),
                Position(2, 1),
                Position(2, 2),
            )
        val nearbyPositions = position.nearbyPositions()

        nearbyPositions shouldBe expectedPositions
    }
})
