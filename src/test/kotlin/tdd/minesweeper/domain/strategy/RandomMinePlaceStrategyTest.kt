package tdd.minesweeper.domain.strategy

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RandomMinePlaceStrategyTest {
    @Test
    fun `랜덤한 전략으로 지뢰 위치를 만든다`() {
        val strategy = RandomMinePlaceStrategy()

        val mineCount = 10
        val height = 10
        val width = 10

        val places = strategy.calcMinePlace(mineCount, height, width)

        places.size shouldBe mineCount
    }
}
