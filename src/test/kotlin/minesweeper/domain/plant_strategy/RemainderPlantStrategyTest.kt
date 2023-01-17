package minesweeper.domain.plant_strategy

import minesweeper.domain.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RemainderPlantStrategyTest {

    @Test
    fun `원하는 만큼의 지뢰가 나왔는지 테스트 한다`() {
        assertThat(RemainderPlantStrategy().createMines(width = 5, height = 5, mineCount = 10).size)
            .isEqualTo(10)
    }

    @Test
    fun `지뢰가 0개면 빈 배열을 반환한다`() {
        assertThat(RemainderPlantStrategy().createMines(width = 5, height = 5, mineCount = 0).size)
            .isEqualTo(0)
    }

    @Test
    fun `3 * 3의 배열에서 5개를 심으면 아래와 같은 모양으로 지뢰를 심는다`() {
        assertThat(RemainderPlantStrategy().createMines(width = 3, height = 3, mineCount = 5))
            .isEqualTo(setOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(1, 1), Point(0, 1)))
    }
}
