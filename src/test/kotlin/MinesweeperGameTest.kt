import model.Line
import model.Mine
import model.Square
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MinesweeperGameTest() {
    @Test
    @DisplayName("지뢰수만큼 지뢰가 생성된다")
    fun start() {
        val square = Square(Line(5), Line(5))
        val minesweeperGame = MinesweeperGame()
        val result = minesweeperGame.start(square.make(), Mine(10))
        assertThat(result.value.flatten().count { it == "*" }).isEqualTo(10)
    }
}
