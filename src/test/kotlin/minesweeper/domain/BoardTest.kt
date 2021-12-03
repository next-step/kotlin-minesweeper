package minesweeper.domain

import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Cell
import minesweeper.domain.block.Mines
import minesweeper.domain.block.Position
import minesweeper.strategy.BoardGenerateStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("블록들(Blocks)")
internal class BoardTest {

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["1:1:1", "10:1:10", "1:10:1", "10:10:1"], delimiter = ':')
    fun `넓이와 지뢰개수 그리고 생성 전략을 통해 생성할 수 있다`(widthInt: Int, heightInt: Int, minesCountInt: Int) {
        val area = Area(Width(widthInt), Height(heightInt))
        val minesCount = MinesCount(minesCountInt)
        val strategy = BoardGenerateStrategy(this::testGenerateStrategy)

        val board = Board.of(area, minesCount, strategy)
        assertThat(board.blocks).isEqualTo(testGenerateStrategy(widthInt, heightInt, minesCountInt))
    }

    private fun testGenerateStrategy(width: Int, height: Int, minesCount: Int): List<Block> {
        val positions = (0 until width).flatMap { x -> (0 until height).map { y -> Position(x, y) } }
        val blocks: MutableList<Block> = positions.map { Cell(it) }.toMutableList()
        for (i in 0 until minesCount) {
            blocks[i] = Mines(blocks[i].position)
        }
        return blocks
    }
}
