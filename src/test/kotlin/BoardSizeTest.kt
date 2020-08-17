import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardSizeTest {

    @Test
    fun `보드의 크기 구하기`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))

        assertThat(boardSize.get()).isEqualTo(100)
    }
}
