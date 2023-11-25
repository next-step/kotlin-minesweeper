import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MinesTest {

    // * G G G G
    // G * G G G
    // G G * G G
    // G G G * G
    // G G G G *
    @ParameterizedTest
    @CsvSource(value = ["1,1,2", "0,3,0", "1,3,1"])
    fun `주변의 지뢰를 확인하는 기능`(x: Int, y: Int, expected: Int) {
        // given
        val mines = Mines(
            listOf(
                Point(0, 0),
                Point(1, 1),
                Point(2, 2),
                Point(3, 3),
                Point(4, 4),
            ).map { Mine(it) }
        )

        // when
        val actual = mines.countMineAround(Point(x, y))

        // then
        actual shouldBe expected
    }
}
