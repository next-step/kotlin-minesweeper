import model.cell.MINE
import model.cell.MineStatus
import model.cell.NOT_MINE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineStatusTest {
    @Test
    fun `Mine 출력 테스트`() {
        assertThat(MineStatus(true).toString()).isEqualTo("*")
    }

    @Test
    fun `NotMine 출력 테스트`() {
        assertThat(MineStatus(false).toString()).isEqualTo("C")
    }
}
