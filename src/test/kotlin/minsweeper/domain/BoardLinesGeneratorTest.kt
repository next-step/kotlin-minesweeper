package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardLinesGeneratorTest {

    @Test
    fun `지뢰를 내가 원하는 위치에 심을 수 있다`() {
        // given
        val boardSize = BoardSize(10, 10)
        val mines = listOf(
            Position(0, 0),
            Position(1, 2),
            Position(2, 4),
            Position(3, 0),
            Position(4, 1),
            Position(5, 7),
            Position(6, 9),
        )
        val boardLinesGenerator = BoardLinesGenerator(
            object : MinePositionsGenerator {
                override fun generate(size: BoardSize, mineCount: Int): List<Position> = mines
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
