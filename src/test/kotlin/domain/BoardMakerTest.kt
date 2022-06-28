package domain

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
        assertThat(board.grounds.filter { it.value.isMine }.count()).isEqualTo(10)
    }
}
