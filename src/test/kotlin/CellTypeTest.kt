import model.CellType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTypeTest {
    @Test
    fun `CellType Count 테스트`() {
        assertThat(CellType.MINE.value).isEqualTo("*")
    }

    @Test
    fun `CellType NextValue 테스트`() {
        assertThat(CellType.nextValue(CellType.ONE)).isEqualTo(CellType.TWO)
    }
}
