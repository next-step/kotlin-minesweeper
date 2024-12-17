package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardLinesGeneratorTest {

    @Test
    fun `지뢰를 내가 원하는 위치에 심을 수 있다`() {
        // given
        val boardSize = BoardSize(10, 10)
        val mines = listOf(
            Coordinate(0, 0),
            Coordinate(1, 2),
            Coordinate(2, 4),
            Coordinate(3, 0),
            Coordinate(4, 1),
            Coordinate(5, 7),
            Coordinate(6, 9),
        )
        val boardLinesGenerator = BoardLinesGenerator(
            object : MineCoordinatesGenerator {
                override fun generate(size: BoardSize, mineCount: Int): List<Coordinate> = mines
            },
            AroundMineCountJudge(),
        )

        // when
        val result = boardLinesGenerator.generate(boardSize, mines.size)

        // then
        assertThat(result.lines[0].cells[0]).isEqualTo(Cell.Mine)
        assertThat(result.lines[1].cells[2]).isEqualTo(Cell.Mine)
        assertThat(result.lines[2].cells[4]).isEqualTo(Cell.Mine)
        assertThat(result.lines[3].cells[0]).isEqualTo(Cell.Mine)
        assertThat(result.lines[4].cells[1]).isEqualTo(Cell.Mine)
        assertThat(result.lines[5].cells[7]).isEqualTo(Cell.Mine)
        assertThat(result.lines[6].cells[9]).isEqualTo(Cell.Mine)
    }

}
