package mineswipper.domain.map

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PositionFactoryTest {

    @Test
    fun `높이와 넓이와 마인 갯수로 마인의 위치를 만든다`() {
        val height = 10
        val width = 10
        val mineAmount = 10

        val positions: List<Position> = PositionFactory.generateMinePositions(
            height,
            width,
            mineAmount
        )

        positions.size shouldBe mineAmount
    }
}
