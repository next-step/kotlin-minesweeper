import model.Length
import model.MapSize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapSizeTest {
    @Test
    fun `MapSize 테스트`() {
        assertThat(MapSize(Length(5), Length(5)).lengthX.value).isEqualTo(5)
        assertThat(MapSize(Length(5), Length(5)).lengthY.value).isEqualTo(5)
    }
}
