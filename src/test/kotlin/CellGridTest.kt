import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellGridTest {
    @Test
    fun `특정 위치에 지뢰를 심고 CellGrid를 반환한다`() {
        // given
        val cellGrid = CellGrid.of(2)

        // given, when
        val actual = cellGrid.plantMine(0)

        // when
        actual[0].isMine() shouldBe true
    }
}
