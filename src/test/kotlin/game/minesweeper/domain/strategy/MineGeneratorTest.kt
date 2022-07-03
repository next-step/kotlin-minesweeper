package game.minesweeper.domain.strategy

import game.minesweeper.domain.map.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("지뢰 생성기")
internal class MineGeneratorTest {

    @Test
    fun `지뢰 생성할 목록`() {
        val expectedCount = 10
        val generator = MineGenerator { count -> (1..count).map { Coordinate(1, it) } }
        val result = generator.generate(expectedCount)
        assertThat(result).hasSize(expectedCount)
    }
}
