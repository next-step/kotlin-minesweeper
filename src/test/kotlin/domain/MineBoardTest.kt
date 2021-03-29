package domain

import domain.block.Block
import domain.block.Nothing
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineBoardTest {
    @Test
    fun `MineBoard 생성한다`() {
        val map = HashMap<Coordinate, Block>()
        map[Coordinate(1, 1)] = Nothing()

        val result = MineBoard(map)

        assertThat(result).isNotNull
    }
}
