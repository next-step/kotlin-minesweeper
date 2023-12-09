import map.Board
import map.MapInfo
import map.Mine
import map.Open
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `0부터 순차적으로 지뢰를 만드는 로직 포함하는 보드를 생성할 때, 지뢰 개수 만큼 순차적으로 지뢰를 만든다`() {
        // given :

        // when : 0부터 순차적으로 지뢰를 만드는 로직 포함하는 보드를 생성한다.
        val mapInfo = MapInfo(5, 5, 4)
        val board = Board(mapInfo, FakeMineRandomLogic())
        val boardCell = board.mineBoard

        // then : 지뢰의 수 만큼 순차적으로 지뢰가 생성된다.
        assertThat(boardCell[0][3]).isInstanceOf(Mine::class.java)
    }

    @Test
    fun `,보드가 생성 되었을 때, 지뢰 이외의 셀에는 주변 지뢰의 개수 정보를 가지고있다`() {
        // given :

        // when : 보드가 생성된다.
        val mapInfo = MapInfo(5, 5, 4)
        val board = Board(mapInfo, FakeMineRandomLogic())

        // then : 지뢰가 아닌 셀은 주변 지뢰의 개수 정보를 가지고 있다.
        val actual = (board.mineBoard[1][0] as Open).mineCnt
        assertThat(actual).isEqualTo(2)
    }
}
