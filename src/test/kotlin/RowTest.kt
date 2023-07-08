import domain.Row
import domain.Space
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RowTest {
    @Test
    fun `emptyRow가 주어진 size만큼 생성되어야 한다`() {
        assertThat(
            Row.emptyRow(5).spaces
        )
            .size()
            .isEqualTo(5)
    }

    @Test
    fun `emptyRow에는 Space_Empty만 들어있어야 한다`() {
        assertThat(
            Row.emptyRow(5)
                .spaces
                .all { it is Space.Empty }
        ).isTrue()
    }
}
