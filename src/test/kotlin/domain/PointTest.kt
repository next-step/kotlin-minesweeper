package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

internal class PointTest {

    @Test
    fun `Point 객체 생성 및 좌표 값 확인`() {
        val point = Point(1, 2)

        val actualX = point.pointX
        val actualY = point.pointY

        val expectedX = 1
        val expectedY = 2

        assertAll("프로퍼티 비교", {
            assertThat(actualX).isEqualTo(expectedX)
            assertThat(actualY).isEqualTo(expectedY)
        })
    }
}
