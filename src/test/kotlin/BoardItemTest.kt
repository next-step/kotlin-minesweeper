import domain.BoardItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardItemTest {
    @Test
    fun `지뢰이면 Mine이 된다`() {
        val isMine = true
        val retrieve = BoardItem.getItemType(isMine)

        assertThat(retrieve is BoardItem.Mine).isTrue
    }

    @Test
    fun `지뢰가 아니면 Normal이 된다`() {
        val isMine = false
        val retrieve = BoardItem.getItemType(isMine)

        assertThat(retrieve is BoardItem.Normal).isTrue
    }
}
