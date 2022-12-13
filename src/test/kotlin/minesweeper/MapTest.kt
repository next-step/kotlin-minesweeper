package minesweeper

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


internal class MapTest {
    @Test
    fun `지도는 높이와 너비와 지뢰들을 가진다`() {
        val map = Map(Position(10, 10), listOf(Mine(Position(10, 10))))

        map.maxSize.height shouldBe 10
        map.maxSize.width shouldBe 10
    }

    @Test
    fun `지도 출력`() {
        val map = Map(Position(10, 10), listOf(Mine(Position(10, 10))))
        val expectedString =
            "C C C C C C C C C C\n" +
            "C C C C C C C C C C\n" +
            "C C C C C C C C C C\n" +
            "C C C C C C C C C C\n" +
            "C C C C C C C C C C\n" +
            "C C C C C C C C C C\n" +
            "C C C C C C C C C C\n" +
            "C C C C C C C C C C\n" +
            "C C C C C C C C C C\n" +
            "C C C C C C C C C *"

        map.print() shouldBe expectedString
    }
}