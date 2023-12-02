import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreateFactoryTest {

    @Test
    fun `높이와 너비를 받고, 팩토리 클래스로 보드 생성을 요청할 때, 보드의 모든 값은 NONE으로 초기화 되어있다`() {
        // given :
        val height = 3
        val width = 3

        // when :
        val mineMap = CreateFactory.createMineMap(width, height)

        // then :
        val actual = mineMap.board.all { cells -> cells.contains(None) }
        assertThat(actual).isTrue()
    }
}
