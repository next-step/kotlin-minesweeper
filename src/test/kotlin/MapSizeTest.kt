import model.MapSize
import model.Size
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapSizeTest {
    @Test
    fun `MapSize 테스트`() {
        val mapSize = MapSize(Size(10), Size(10))
        assertThat(mapSize.size).isEqualTo(100)
        assertThat(mapSize.maxX).isEqualTo(10)
        assertThat(mapSize.maxY).isEqualTo(10)
    }
}
