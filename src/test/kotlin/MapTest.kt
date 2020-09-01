import model.Map
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapTest {
    private val width = 10
    private val height = 10
    private val mineMap = Map(width = width, height = height).apply { this.createDefaultMap(width, height) }

    @Test
    fun `기본 Map 생성 테스트`() {
        assertThat(mineMap.width).isEqualTo(10)
        assertThat(mineMap.height).isEqualTo(10)
        assertThat(mineMap.cells.size).isEqualTo(mineMap.width * mineMap.height)
    }

    @Test
    fun `Map에 Mine 생성 테스트`() {
        assertThat(mineMap.mines.size).isEqualTo(0)
        mineMap.createRandomMines(5)
        assertThat(mineMap.mines.size).isEqualTo(0)
    }

    @Test
    fun `승리 true 테스트`() {
        val winMap = Map(width = width, height = height).apply {
            createDefaultMap(width, height)
            createCountMap()
            createRandomMines(width * height)
            calculateCount()
        }
        assertThat(winMap.winCheck()).isTrue()
    }

    @Test
    fun `승리 false 테스트`() {
        val winMap = Map(width = width, height = height).apply {
            createDefaultMap(width, height)
            createCountMap()
            createRandomMines(width * height - 1)
            calculateCount()
        }
        assertThat(winMap.winCheck()).isFalse()
    }
}
