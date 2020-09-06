import model.CellType
import model.Map
import model.Position
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class MapTest {
    private val items: MutableMap<Position, CellType> = mutableMapOf(Position(0, 0) to CellType.MINE, Position(0, 1) to CellType.MINE, Position(1, 0) to CellType.MINE, Position(1, 1) to CellType.MINE)
    private val testMap = Map(items)

    @Test
    fun `Map 생성`() {
        assertThat(testMap.items.size).isEqualTo(4)
    }

    @Test
    fun `Map getPositionValue - 성공`() {
        assertThat(testMap.getPositionValue(Position(0, 0))).isEqualTo(CellType.MINE)
    }

    @Test
    fun `Map getPositionValue - 실패`() {
        assertThatExceptionOfType(Exception::class.java).isThrownBy {
            testMap.getPositionValue(Position(2, 2))
        }
    }
}
