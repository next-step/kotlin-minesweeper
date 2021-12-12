package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.Position
import minesweeper.domain.block.strategy.MineBlockGenerateStrategy
import minesweeper.exception.MinesCountOverAreaException
import minesweeper.fixture.BoardFixture
import minesweeper.fixture.BoardFixture.createBoardGenerateStrategy
import minesweeper.fixture.BoardFixture.createPositions
import minesweeper.fixture.BoardFixture.minesOrCell
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
        val minesCount = MineCount(minesCountInt)
        val strategy = MineBlockGenerateStrategy(BoardFixture::createBoardGenerateStrategy)

        val positions = createPositions(area.width, area.height)
        val minesPositions = createBoardGenerateStrategy(positions, minesCountInt)
        val expected = positions.map { minesOrCell(it, minesPositions) }

        val board = Board.of(area, minesCount, strategy)
        assertThat(board.blocks).isEqualTo((expected))
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}, {2}")
    @CsvSource(value = ["1:1:2", "10:1:11", "1:10:11", "10:10:101"], delimiter = ':')
    fun `지뢰수가 넓이 보다 클 수 없다`(widthInt: Int, heightInt: Int, minesCountInt: Int) {
        val area = Area(Width(widthInt), Height(heightInt))
        val minesCount = MineCount(minesCountInt)
        val strategy = MineBlockGenerateStrategy(BoardFixture::createBoardGenerateStrategy)

        val exception = assertThrows<MinesCountOverAreaException> { Board.of(area, minesCount, strategy) }
        assertThat(exception.message).isEqualTo(
            "'%s'는 area 의 크기(%s)를 넘었습니다.".format(minesCountInt, widthInt * heightInt)
        )
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}, {2}")
    @CsvSource(value = ["0:0", "0:1", "0:2:"], delimiter = ':')
    fun `지뢰를 클릭하면 null을 반환한다`(clickX: Int, clickY: Int) {
        val board = BoardFixture.createBoard(3, 3, 3)
        val clickPosition = Position(clickX, clickY)
        val actual = board.openBlock(clickPosition)

        assertThat(actual).isNull()
    }
}
