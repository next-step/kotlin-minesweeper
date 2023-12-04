package view

import domain.Cell
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StringHandlerKtTest {
    @ParameterizedTest
    @ValueSource(strings = ["", " "])
    fun `정수 변환 메서드에 빈값 전달시 예외를 던진다`(input: String) {
        assertThatThrownBy { inputToInt(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("빈 값이 입력되었습니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["3.14", "15$", "1.7E+3"])
    fun `정수 변환 메서드에 전달된 문자열이 숫자 외의 문자를 포함할 경우 예외를 던진다`(input: String) {
        assertThatThrownBy { inputToInt(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("숫자만 입력되어야 합니다.")
    }

    @Test
    fun `닫힌 cell을 알맞은 문자로 변환한다`() {
        val cell = Cell()
        assertThat(cell.toStr()).isEqualTo(CLOSED_SYMBOL)
    }

    @Test
    fun `열린 지뢰 cell을 알맞은 문자로 변환한다`() {
        val cell = Cell(isMine = true, isOpened = true)
        assertThat(cell.toStr()).isEqualTo(MINE_SYMBOL)
    }

    @Test
    fun `열린 비 지뢰 cell을 힌트에 해당하는 문자로 변환한다`() {
        val cell = Cell(isMine = false, hint = 8, isOpened = true)
        assertThat(cell.toStr()).isEqualTo(cell.hint?.toString())
    }

    @Test
    fun `열려있고 지뢰가 아니면서 힌트가 null인 cell을 문자로 변환할 경우 예외를 던진다`() {
        val cell = Cell(isMine = false, hint = null, isOpened = true)
        assertThatThrownBy { cell.toStr() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("null 값에 대한 String 변환이 요청되었습니다")
    }
}
