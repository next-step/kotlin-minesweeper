import model.Map
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapTest {
    private val mineMap = Map(width = 10, height = 10, mine = 10)

    @Test
    fun `MineMap 생성 테스트`() {
        assertThat(mineMap.width).isEqualTo(10)
        assertThat(mineMap.height).isEqualTo(10)
        assertThat(mineMap.mine).isEqualTo(10)
    }
}
