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
}
