import map.Board
import map.MapInfo
import map.Mine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `0부터 순차적으로 지뢰를 만드는 로직 포함할 때, 지뢰 개수 만큼 순차적으로 지뢰를 만든다`() {
        val mapInfo = MapInfo(5, 5, 4)
        val board = Board(mapInfo, FakeMineRandomLogic())
        val boardCell = board.mineBoard

        assertThat(boardCell[0][0]).isInstanceOf(Mine::class.java)
        assertThat(boardCell[0][1]).isInstanceOf(Mine::class.java)
        assertThat(boardCell[0][2]).isInstanceOf(Mine::class.java)
        assertThat(boardCell[0][3]).isInstanceOf(Mine::class.java)
    }
}
