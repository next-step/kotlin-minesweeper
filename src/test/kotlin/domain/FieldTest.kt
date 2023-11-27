package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FieldTest {
    class TestSelector(private val position: Position) : PositionSelector {
        override fun selectPosition() = position
    }

    @Test
    fun `필드에 지뢰를 세팅한다`() {
        val targetPosition = Position(0, 0)
        val field = Field(10, 10)
        field.setMine(TestSelector(targetPosition))

        assertThat(field.isMine(targetPosition)).isTrue()
    }
}
