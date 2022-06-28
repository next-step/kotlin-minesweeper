import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineSweeperBoardGeneratorTest {

    @Test
    fun `지뢰 찾기 보드판 생성`() {
        val mineSweeperBoardGenerator = MineSweeperBoardGenerator(GameSettingInfo(10, 9, 10))
        val board = mineSweeperBoardGenerator.create()
        assertThat(board.size).isEqualTo(90)
    }
}
