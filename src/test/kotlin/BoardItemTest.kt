import domain.BoardItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardItemTest {
    @Test
    fun `지뢰값을 true로 줬을때 지뢰 enum값이 넘어와야함`() {
        val isMine = true
        val retrieve = BoardItem.getItemType(isMine)

        assertThat(retrieve).isEqualTo(BoardItem.MINE)
    }

    @Test
    fun `지뢰값을 false로 줬을때 일반 enum값이 넘어와야함`() {
        val isMine = false
        val retrieve = BoardItem.getItemType(isMine)

        assertThat(retrieve).isEqualTo(BoardItem.NORMAL)
    }
}
