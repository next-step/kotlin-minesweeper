import domain.BoardItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardItemTest {
    @Test
    fun `지뢰일때 지뢰타입이 맞는지 테스트`() {
        val isMine = true
        val retrieve = BoardItem.getItemType(isMine)

        assertThat(retrieve).isEqualTo(BoardItem.MINE)
    }

    @Test
    fun `지뢰가 아닐때 일반타입이 맞는지 테스트`() {
        val isMine = false
        val retrieve = BoardItem.getItemType(isMine)

        assertThat(retrieve).isEqualTo(BoardItem.NORMAL)
    }
}
