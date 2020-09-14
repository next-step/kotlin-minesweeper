import model.Length
import model.MapSize
import model.MineCount
import model.MineGame
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineGameTest {
    private val mapSize = MapSize(Length(10), Length(10))
    private val game: MineGame = MineGame(mapSize, MineCount(10, mapSize.lengthX.value * mapSize.lengthY.value))

    @Test
    fun `Game 생성 테스트`() {
        assertThat(game.map.items.size).isEqualTo(mapSize.lengthX.value * mapSize.lengthY.value)
    }
}
