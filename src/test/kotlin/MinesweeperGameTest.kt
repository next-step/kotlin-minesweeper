import model.MineCount
import model.Number
import model.Square
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MinesweeperGameTest() {
    @Test
    @DisplayName("지뢰수만큼 지뢰가 생성된다")
    fun start() {
        val square = Square(Number(5), Number(5))
        val minesweeperGame = MinesweeperGame()
        val result = minesweeperGame.start(square.make(), MineCount(10))
        assertThat(result.value.flatten().count { !it.isBlock() }).isEqualTo(10)
    }
}
