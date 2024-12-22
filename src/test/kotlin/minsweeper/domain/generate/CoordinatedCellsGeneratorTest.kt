package minsweeper.domain.generate

import minsweeper.domain.BoardSize
import minsweeper.domain.Cell
import minsweeper.domain.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoordinatedCellsGeneratorTest {

    @Test
    fun `좌표화된 Cell들을 생성할 수 있다`() {
        // given
        val mineCoordinate = Coordinate.of(1, 3)
        val mineGenerator = object : MineGenerator {
            override fun generate(boardSize: BoardSize, mineAmount: Int): List<Coordinate> = listOf(mineCoordinate)
        }
        val generator = CoordinatedCellsGenerator(mineGenerator = mineGenerator)

        // when
        val result = generator.generate(BoardSize(10, 10), 1)

        assertThat(result[mineCoordinate]).isInstanceOf(Cell.Mine::class.java)
        assertThat(result[Coordinate.of(2, 3)]).isInstanceOf(Cell.Island::class.java)
        assertThat(result[Coordinate.of(3, 2)]).isInstanceOf(Cell.Island::class.java)
    }

}