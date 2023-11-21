import domain.MinesweeperGame
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinesweeperTest {

    @Test
    fun `높이와 너비, 지뢰 개수를 입력받을 수 있다`() {
        val width = 5
        val height = 5
        val mineSize = 3
        val game = MinesweeperGame(
            width = width,
            height = height,
            mineSize = mineSize
        )
        assertThat(width).isEqualTo(game.width)
        assertThat(height).isEqualTo(game.height)
        assertThat(mineSize).isEqualTo(game.mineSize)
    }
}
