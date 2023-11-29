package domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class FieldTest {
    class TestSelector(private val position: Position) : PositionSelector {
        override fun selectPosition() = position
    }

    @ParameterizedTest
    @CsvSource(value = ["0,1", "1,0", "-1,1", "1,-1"])
    fun `필드 사이즈가 0 또는 음수일 경우 예외를 던진다`(width: Int, height: Int) {
        Assertions.assertThatThrownBy { Field(width, height) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력값은 양의 정수여야 합니다.")
    }

    @Test
    fun `필드에 지뢰를 세팅한다`() {
        val targetPosition = Position(0, 0)
        val field = Field(10, 10)
        field.setMine(TestSelector(targetPosition))

        assertThat(field.isMine(targetPosition)).isTrue()
    }
}
