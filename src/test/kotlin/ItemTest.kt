import model.Item
import model.Position
import model.Type
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ItemTest {
    private val item = Item(Position(0, 0), Type.TWO)

    @Test
    fun `Item Open Test`() {
        assertThat(item.toString()).isEqualTo("C")
        item.isOpen()
        assertThat(item.toString()).isEqualTo(Type.TWO.toString())
    }
}
