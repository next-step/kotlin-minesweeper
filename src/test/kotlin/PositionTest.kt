import model.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionTest {
    @Test
    fun `Position getAround 테스트`() {
        assertThat(Position(0, 0).getAroundPosition().size).isEqualTo(9)
    }
}
