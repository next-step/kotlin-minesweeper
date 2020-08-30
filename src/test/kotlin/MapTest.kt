import model.Map
import model.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapTest {
    private val mineMap = Map(width = 10, height = 10).apply { this.createDefaultMap(width, height) }

    @Test
    fun `기본 Map 생성 테스트`() {
        assertThat(mineMap.width).isEqualTo(10)
        assertThat(mineMap.height).isEqualTo(10)
        assertThat(mineMap.cells.size).isEqualTo(mineMap.width * mineMap.height)
    }

    @Test
    fun `Map 클릭 테스트`() {
        assertThat(mineMap.clickMap(Position(0, 0))).isFalse()
    }

    @Test
    fun `Map에 Mine 생성 테스트`() {
        assertThat(mineMap.mines.size).isEqualTo(0)
        mineMap.createRandomMines(5)
        assertThat(mineMap.mines.size).isEqualTo(0)
    }
}
