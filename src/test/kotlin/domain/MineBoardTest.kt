package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineBoardTest {
    @Test
    fun `MineBoard 생성한다`() {
        val map = HashMap<Coordinate, Block>()
        map[Coordinate(1, 1)] = Block.NOTHING

        val result = MineBoard(map)

        assertThat(result).isNotNull
    }
}
