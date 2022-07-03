package domain

import domain.BoardMaker.Companion.ARROW_COUNT
import domain.BoardMaker.Companion.X_MARGINS
import domain.BoardMaker.Companion.Y_MARGINS
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Created by Jaesungchi on 2022.06.28..
 */
class BoardMakerTest {
    @Test
    fun `10x10 설정시 10x10보드판을 잘 만든다`() {
        val gameSettingInfo = GameSettingInfo(10, 10, 10)
        val board = BoardMaker(gameSettingInfo).makeBoard()
        assertThat(board.grounds[Position(0, 0)]).isNotNull
        assertThat(board.grounds[Position(9, 0)]).isNotNull
        assertThat(board.grounds[Position(0, 9)]).isNotNull
        assertThat(board.grounds[Position(9, 9)]).isNotNull
        assertThat(board.grounds[Position(10, 10)]).isNull()
    }

    @Test
    fun `지뢰를 10개 설정시 10개의 지뢰가 보드판에 설치된다`() {
        val gameSettingInfo = GameSettingInfo(10, 10, 10)
        val board = BoardMaker(gameSettingInfo).makeBoard()
        assertThat(board.grounds.count { it.value.isMine }).isEqualTo(10)
    }

    @Test
    fun `자리의 숫자가 0 보다 크다면, 8방위중에 숫자만큼 지뢰가 있다`() {
        val gameSettingInfo = GameSettingInfo(5, 5, 3)
        val board = BoardMaker(gameSettingInfo).makeBoard()
        val numberGrounds = board.grounds.filter { it.value.mineCount > 0 }

        numberGrounds.map { (position, ground) ->
            var mineCount = 0
            repeat(ARROW_COUNT) { index ->
                val tempX = position.x + X_MARGINS[index]
                val tempY = position.y + Y_MARGINS[index]
                val tempPosition = Position.makePositionOrNull(tempX, tempY, 5, 5) ?: return@repeat

                if (board.grounds[tempPosition]?.isMine == true)
                    mineCount++
            }
            assertThat(mineCount == ground.mineCount).isTrue
        }
    }

    @Test
    fun `5x5보드에 테스트를 위한 2,2 자리에 지뢰 설치시 2,2에 지뢰가 잘 설치된다`() {
        val gameSettingInfo = GameSettingInfo(5, 5, 0)
        val board = BoardMaker(gameSettingInfo).makeBoard(listOf(Position(2, 2)))

        assertThat(board.grounds[Position(2, 2)]?.isMine).isTrue
    }

    @Test
    fun `5x5보드에 2,2 자리에 지뢰가 있다면 1,1에 지뢰 카운트는 1이다`() {
        val gameSettingInfo = GameSettingInfo(5, 5, 0)
        val board = BoardMaker(gameSettingInfo).makeBoard(listOf(Position(2, 2)))

        assertThat(board.grounds[Position(1, 1)]?.mineCount).isEqualTo(1)
    }

    @Test
    fun `5x5보드에 2,2 자리, 1,2에 지뢰가 있다면 1,1에 지뢰 카운트는 2이다`() {
        val gameSettingInfo = GameSettingInfo(5, 5, 0)
        val board = BoardMaker(gameSettingInfo).makeBoard(listOf(Position(2, 2), Position(1, 2)))

        assertThat(board.grounds[Position(1, 1)]?.mineCount).isEqualTo(2)
    }

    @Test
    fun `5x5보드에 2,2 자리, 1,2에 지뢰가 있다면 0,0에 지뢰 카운트는 0이다`() {
        val gameSettingInfo = GameSettingInfo(5, 5, 0)
        val board = BoardMaker(gameSettingInfo).makeBoard(listOf(Position(2, 2), Position(1, 2)))

        assertThat(board.grounds[Position(0, 0)]?.mineCount).isEqualTo(0)
    }

    @Test
    fun `5x5보드에 2,2 자리, 1,2에 지뢰가 있다면 3,2에 지뢰 카운트는 1이다`() {
        val gameSettingInfo = GameSettingInfo(5, 5, 0)
        val board = BoardMaker(gameSettingInfo).makeBoard(listOf(Position(2, 2), Position(1, 2)))

        assertThat(board.grounds[Position(3, 2)]?.mineCount).isEqualTo(1)
    }
}
