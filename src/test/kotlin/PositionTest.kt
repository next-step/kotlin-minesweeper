import model.MapSize
import model.Position
import model.Size
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionTest {
    @Test
    fun `Position getAround 테스트`() {
        assertThat(Position(0, 0).getAround(MapSize(Size(10), Size(10))).size).isEqualTo(3)
    }
}
