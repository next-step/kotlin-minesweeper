import model.Length
import model.MapSize
import model.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionTest {
    @Test
    fun `Position getAround 테스트`() {
        assertThat(Position(0, 0).getAroundPosition(MapSize(Length(10), Length(10))).size).isEqualTo(3)
    }
}
