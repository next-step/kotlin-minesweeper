package domain.block

import domain.LocationOfMines
import domain.coord.AbstractCoordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlockFactoryTest {

    @Test
    fun `블록을 만드려는 곳에 지뢰가 존재하면 Mine이 반환횐다`() {
        // given
        val coordinate = AbstractCoordinate(1, 2)
        val mines = LocationOfMines(listOf(AbstractCoordinate(1, 2)))
        // when
        val block = BlockFactory.create(coordinate, mines)

        // then
        assertThat(block).isInstanceOf(Mine::class.java)
    }

    @Test
    fun `블록을 만드려는 곳에 지뢰가 없으면 Land 가 반환횐다`() {
        // given
        val coordinate = AbstractCoordinate(1, 2)
        val mines = LocationOfMines(listOf(AbstractCoordinate(0, 0)))
        // when
        val block = BlockFactory.create(coordinate, mines)

        // then
        assertThat(block).isInstanceOf(Land::class.java)
    }
}
