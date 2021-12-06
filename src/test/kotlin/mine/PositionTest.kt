package mine

import mine.cell.Position
import mine.cell.Position.Companion.ofNullable
import mine.cell.Position.Companion.ofPosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PositionTest {

    @Test
    fun `셀의 위치 정보는 음수를 가지면 null을 반환한다`() {
        val x = -1
        val y = 2

        val position = Position(x, y).ofNullable()

        assertThat(position).isEqualTo(null)
    }

    @Test
    fun `문자열에 콤마가 없으면 Position으로 변환시 에러를 발생시킨다`() {
        val input = "123"

        assertThrows<IllegalArgumentException> { input.ofPosition() }
    }

    @Test
    fun `문자열에 숫자가 없으면 Position으로 변환시 에러를 발생시킨다`() {
        val input = "아하나"

        assertThrows<IllegalArgumentException> { input.ofPosition() }
    }

    @Test
    fun `문자열에 숫자가 하나만 있으면 Position으로 변환시 에러를 발생시킨다`() {
        val input = "1,이"

        assertThrows<IllegalArgumentException> { input.ofPosition() }
    }

    @Test
    fun `문자열이 빈 값이면 Position으로 변환시 에러를 발생시킨다`() {
        val input = ""

        assertThrows<IllegalArgumentException> { input.ofPosition() }
    }

    @Test
    fun `문자열아 null 값이면 Position으로 변환시 에러를 발생시킨다`() {
        val input = null

        assertThrows<IllegalArgumentException> { input.ofPosition() }
    }

    @Test
    fun `문자열을 Position(1,2)으로 변환한다`() {
        val input = "1,2"

        val position = input.ofPosition()

        assertThat(position).isEqualTo(Position(1, 2))
    }
}
