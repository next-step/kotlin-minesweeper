import domain.Board
import domain.Space
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `주어진 개수만큼 지뢰 심어졌는지 확인`() {
        assertThat(
            Board(2, 3, 3).board().count { it is Space.Mine }
        ).isEqualTo(3)
    }

    @Test
    fun `주어진 크기만큼 보드 생성되었는지 확인`() {
        assertThat(
            Board(2, 3, 0).board()
        ).size().isEqualTo(2 * 3)
    }
}
