package domain.field

import domain.block.Position
import domain.field.Rectangle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RectangleTest {
    @Test
    fun `사각형 범위 내의 좌표를 뱉어준다`() {
        // given
        val rectangle = Rectangle(3, 2)
        val expectedPositions = listOf(
            Position.of(1, 1),
            Position.of(2, 1),
            Position.of(3, 1),
            Position.of(1, 2),
            Position.of(2, 2),
            Position.of(3, 2)
        )

        // then
        assertThat(rectangle.getAllPositions()).containsAll(expectedPositions)
    }
}
