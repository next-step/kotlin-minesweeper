import model.Map
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapTest {
    private val mineMap = Map(x = 10, y = 10, mine = 10)

    @Test
    fun `MineMap 생성 테스트`() {
        assertThat(mineMap.x).isEqualTo(10)
        assertThat(mineMap.y).isEqualTo(10)
        assertThat(mineMap.mine).isEqualTo(10)
    }
}
