import model.CellType
import model.Game
import model.MapSize
import model.MineCount
import model.Size
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {
    private val mapSize = MapSize(Size(10), Size(10))
    private val game: Game = Game(mapSize, MineCount(10, mapSize.size))

    @Test
    fun `Game 생성 테스트`() {
        assertThat(game.viewMap.items.size).isEqualTo(mapSize.size)
        assertThat(game.countMap.items.size).isEqualTo(mapSize.size)
    }

    @Test
    fun `Game ViewMap Item Test`() {
        assertThat(game.viewMap.items.toList().shuffled().first().second).isEqualTo(CellType.NONE)
    }

    @Test
    fun `Game CountMap Item Test`() {
        assertThat(game.countMap.items.toList().shuffled().first().second).isNotEqualTo(CellType.NONE)
    }
}
