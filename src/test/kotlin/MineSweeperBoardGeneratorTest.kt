import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MineSweeperBoardGeneratorTest {

    @Test
    fun `지뢰 찾기 보드판 생성`() {
        val mineSweeperBoardGenerator = MineSweeperBoardGenerator(GameSettingInfo(10, 9, 10))
        val board = mineSweeperBoardGenerator.create()

        assertAll("보드 사이즈 확인", {
            assertThat(board.size).isEqualTo(10)
            assertThat(board[0].squares.size).isEqualTo(9)
        })
    }
}
