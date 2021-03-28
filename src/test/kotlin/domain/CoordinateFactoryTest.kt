package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CoordinateFactoryTest {

    @Test
    fun `좌표를 생성할 수 있다`() {
        val result = CoordinateFactory.create(maxX = 1, maxY = 1, nthNumber = 1)
        assertThat(result).isNotNull()
    }

    @ParameterizedTest
    @CsvSource(
        "1, -1",
        "0, 1"
    )
    fun `좌표 생성 시 최대 좌표의 X 혹은 Y 가 0보다 작거나 같은 경우 예외를 반환한다`(maxX: Int, maxY: Int) {
        val dummyNthNumber = 1
        val expectedMessage = "최대좌표의 X,Y 는 0보다 커야 합니다. maxX: $maxX, maxY: $maxY"

        val result = assertThrows<IllegalArgumentException> {
            CoordinateFactory.create(
                maxX = maxX,
                maxY = maxY,
                nthNumber = dummyNthNumber
            )
        }

        assertThat(result.message).isEqualTo(expectedMessage)
    }

    @ParameterizedTest(name = "3x4 좌표에서 ({1},{2}) 은 {0}번 째 좌표이다.")
    @CsvSource(
        "1,1,1",
        "2,2,1",
        "3,3,1"
    )
    fun `좌표 순서는 왼쪽위부터 1번으로 시작하여 오른쪽으로 흐른다`(nthNumber: Int, expectedX: Int, expectedY: Int) {
        val (maxX, maxY) = 3 to 4
        val expected = Coordinate(expectedX, expectedY)

        val result = CoordinateFactory.create(maxX = maxX, maxY = maxY, nthNumber = nthNumber)

        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest(name = "3x4 좌표에서 ({1},{2}) 은 {0}번 째 좌표이다.")
    @CsvSource(
        "3,3,1",
        "4,1,2"
    )
    fun `더이상 오른쪽 좌표가 없는 경우 아래줄의 가장 왼쪽 좌표가 다음 순서가 된다`(nthNumber: Int, expectedX: Int, expectedY: Int) {
        val (maxX, maxY) = 3 to 4
        val expected = Coordinate(expectedX, expectedY)

        val result = CoordinateFactory.create(maxX = maxX, maxY = maxY, nthNumber = nthNumber)

        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest(name = "3x4 좌표에서 ({1},{2}) 은 {0}번 째 좌표이다.")
    @CsvSource(
        "1,1,2",
        "5,6,31"
    )
    fun `좌표 범위를 벗어난 순서번호를 입력하는 경우 예외를 반환한다`(maxX: Int, maxY: Int, nthNumber: Int) {
        val expectedMessage = "총 좌표 개수보다 번째수가 더 클 수 없습니다. maxX: $maxX, maxY: $maxY, nthNumber: $nthNumber"

        val result = assertThrows<IllegalArgumentException> {
            CoordinateFactory.create(
                maxX = maxX,
                maxY = maxY,
                nthNumber = nthNumber
            )
        }

        assertThat(result.message).isEqualTo(expectedMessage)
    }
}
