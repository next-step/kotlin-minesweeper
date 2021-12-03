package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.Block
import minesweeper.domain.block.Cell
import minesweeper.domain.block.Mines
import minesweeper.domain.block.Position
import minesweeper.exception.MinesCountOverAreaException
import minesweeper.strategy.BoardGenerateStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("블록들(Blocks)")
internal class BoardTest {

    @ParameterizedTest(name = "입력 값: {0}, {1}, {2}")
    @CsvSource(value = ["1:1:1", "10:1:10", "1:10:1", "10:10:1"], delimiter = ':')
    fun `넓이와 지뢰수 그리고 생성 전략을 통해 생성할 수 있다`(widthInt: Int, heightInt: Int, minesCountInt: Int) {
        val area = Area(Width(widthInt), Height(heightInt))
        val minesCount = MinesCount(minesCountInt)
        val strategy = BoardGenerateStrategy(this::createBoardGenerateStrategy)

        val board = Board.of(area, minesCount, strategy)
        assertThat(board.blocks).isEqualTo(createBoardGenerateStrategy(widthInt, heightInt, minesCountInt))
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}, {2}")
    @CsvSource(value = ["1:1:2", "10:1:11", "1:10:11", "10:10:101"], delimiter = ':')
    fun `지뢰수가 넓이 보다 클 수 없다`(widthInt: Int, heightInt: Int, minesCountInt: Int) {
        val area = Area(Width(widthInt), Height(heightInt))
        val minesCount = MinesCount(minesCountInt)
        val strategy = BoardGenerateStrategy(this::createBoardGenerateStrategy)

        val exception = assertThrows<MinesCountOverAreaException> { Board.of(area, minesCount, strategy) }
        assertThat(exception.message).isEqualTo(
            "'%s'는 area 의 크기(%s)를 넘었습니다.".format(
                minesCountInt,
                widthInt * heightInt
            )
        )
    }

    private fun createBoardGenerateStrategy(width: Int, height: Int, minesCount: Int): List<Block> {
        val positions = (0 until width).flatMap { x -> (0 until height).map { y -> Position(x, y) } }
        val blocks: MutableList<Block> = positions.map { Cell(it) }.toMutableList()
        for (i in 0 until minesCount) {
            blocks[i] = Mines(blocks[i].position)
        }
        return blocks
    }
}
