import model.Item
import model.Position
import model.Type
import model.Map
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class MapTest {
    private val items: List<Item> = mutableListOf(Item(Position(0, 0), Type.MINE), Item(Position(0, 1), Type.MINE), Item(Position(1, 0), Type.MINE), Item(Position(0, 0), Type.MINE))
    private val testMap = Map(items)

    @Test
    fun `Map 생성`() {
        assertThat(testMap.items.size).isEqualTo(4)
    }

    @Test
    fun `Map getPositionValue - 성공`() {
        assertThat(testMap.getPositionValue(Position(0, 0))).isEqualTo(Type.MINE)
    }

    @Test
    fun `Map getPositionValue - 실패`() {
        assertThatExceptionOfType(Exception::class.java).isThrownBy {
            testMap.getPositionValue(Position(2, 2))
        }
    }
}
