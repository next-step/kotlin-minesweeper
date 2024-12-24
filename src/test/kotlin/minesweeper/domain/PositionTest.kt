package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class PositionTest : StringSpec({
    "특정 좌표의 근처에 해당하는 좌표를 반환한다." {
        val position = Position(1, 1)
        val nearbyPositions = position.nearbyPositions()

        nearbyPositions.size shouldBe 8
        nearbyPositions shouldContain Position(0, 0)
        nearbyPositions shouldContain Position(0, 1)
        nearbyPositions shouldContain Position(0, 2)
        nearbyPositions shouldContain Position(1, 0)
        nearbyPositions shouldContain Position(1, 2)
        nearbyPositions shouldContain Position(2, 0)
        nearbyPositions shouldContain Position(2, 1)
        nearbyPositions shouldContain Position(2, 2)
    }
})
