import model.cell.MineType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineTypeTest {
    @Test
    fun `Mine 출력 테스트`() {
        assertThat(MineType.MINE.toString()).isEqualTo("*")
    }

    @Test
    fun `NotMine 출력 테스트`() {
        assertThat(MineType.NOT_MINE.toString()).isEqualTo("C")
    }
}
