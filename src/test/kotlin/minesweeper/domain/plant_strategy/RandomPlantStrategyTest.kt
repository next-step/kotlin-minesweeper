package minesweeper.domain.plant_strategy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RandomPlantStrategyTest {

    @Test
    fun `원하는 만큼의 지뢰가 나왔는지 테스트 한다`() {
        assertThat(RandomPlantStrategy().createMines(width = 5, height = 5, mineCount = 10).size).isEqualTo(10)
    }
}
