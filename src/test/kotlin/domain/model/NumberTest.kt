package domain.model

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class NumberTest {
    @Test
    fun `value를 가질 수 있다`() {
        val numberTile = NumberTile(Point.from(0, 0), value = 1)
        numberTile.value shouldBe 1
    }

    @Test
    fun `맵의 지뢰 갯수를 계산해서 value를 갱신시킬 수 있다`() {
        val numberTile = NumberTile(Point.from(1, 1))
        val field = listOf(
            listOf(
                Mine(Point.from(0, 0)),
                Mine(Point.from(1, 0)),
            ),
            listOf(
                Mine(Point.from(0, 1)),
                numberTile
            )
        )
        val map = GameMap(field)
        val mineCount = map.mineCountInSquare(numberTile.point)

        numberTile.updateValue(mineCount)
        numberTile.value shouldBe 3
    }

    @Test
    fun `타일을 열 수 있다`() {
        val numberTile = NumberTile(Point.from(0, 0))
        numberTile.isOpened shouldBe false

        numberTile.open()
        numberTile.isOpened shouldBe true
    }
}
