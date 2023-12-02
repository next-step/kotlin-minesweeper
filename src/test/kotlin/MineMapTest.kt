import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineMapTest {
    @Test
    fun `,행의 사이즈가 동일하지 않은 파라미터로 마인 맵이 생성 되었을 때, 예외를 던진다`() {
        // given :

        // when : 행의 사이즈가 동일하지 않은 파라미터로 마인 맵이 생성 되었을 때
        val actual = runCatching { MineMap(mutableListOf(mutableListOf(None, None, None), mutableListOf(None, None))) }.exceptionOrNull()

        // then : 예외를 던진다.
        assertThat(actual).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `리스트가 비어져있고, 마인 맵이 생성될 때, 예외를 던진다`() {
        // given : 빈 리스트 생성
        val emptyList: MutableList<MutableList<Cell>> = mutableListOf()

        // when : 마인 맵 생성
        val actual = runCatching { MineMap(emptyList) }.exceptionOrNull()

        // then : 예외를 던진다.
        assertThat(actual).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `마인 맵이 생성되고, 지정된 곳에 지뢰를 생성할때, 해당 위치는 지뢰가 된다`() {
        // given : 마인 맵이 생성된다.
        val mineMap = CreateFactory.createMineMap(5, 5)

        // when : 지정된 위치에 지뢰 세팅을 요청한다.
        mineMap.settingMine(15)
        val actual = mineMap.board[2][4]

        // then : 해당 위치는 지뢰로 변경된다.
        assertThat(actual).isInstanceOf(Mine::class.java)
    }
}
