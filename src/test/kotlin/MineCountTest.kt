import model.MapSize
import model.MineCount
import model.Size
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class MineCountTest {
    @Test
    fun `MineCountTest`() {
        assertThatExceptionOfType(Exception::class.java).isThrownBy {
            MineCount(101, MapSize(Size(10), Size(10)).size)
        }
    }
}
