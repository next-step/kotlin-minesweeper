import model.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionTest {
    @Test
    fun `주변 좌표 구하기 테스트`() {
        val position = Position(0, 0)
        assertThat(position.getAroundPositions(10, 10).size).isEqualTo(3)
    }
}
