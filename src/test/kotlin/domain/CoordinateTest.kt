package domain

import domain.block.Land
import domain.block.Mine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CoordinateTest {

    @Test
    fun `블록을 만드려는 곳에 지뢰가 존재하면 Mine이 반환횐다`() {
        // given
        val coordinate = Coordinate(1, 2)
        val mines: List<Coordinate> = listOf(Coordinate(1, 2))
        // when
        val block = coordinate.toBlock(mines)

        // then
        assertThat(block).isInstanceOf(Mine::class.java)
    }

    @Test
    fun `블록을 만드려는 곳에 지뢰가 없으면 Land 가 반환횐다`() {
        // given
        val coordinate = Coordinate(1, 2)
        val mines: List<Coordinate> = listOf(Coordinate(0, 0))
        // when
        val block = coordinate.toBlock(mines)

        // then
        assertThat(block).isInstanceOf(Land::class.java)
    }
}
