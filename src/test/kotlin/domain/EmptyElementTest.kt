package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class EmptyElementTest {
    @Test
    fun `주어진 위치 주위의 지뢰 갯수를 셀 수 있다`() {
        /**
         *  C C C
         *  * C *
         *  C C C
         */
        val elements = listOf(
            listOf(EmptyElement.create(0, 0), EmptyElement.create(0, 1), EmptyElement.create(0, 2)),
            listOf(Mine.create(1, 0), EmptyElement.create(1, 1), Mine.create(1, 2)),
            listOf(EmptyElement.create(2, 0), EmptyElement.create(2, 1), EmptyElement.create(2, 2))
        )
        val mineMap = MineMap(elements)
        val elementWithMineCntOne = elements[0][0] as EmptyElement
        val elementWithMineCntTwo = elements[0][1] as EmptyElement

        elementWithMineCntOne.countMine(mineMap)
        elementWithMineCntTwo.countMine(mineMap)

        elementWithMineCntOne.mineCnt shouldBe 1
        elementWithMineCntTwo.mineCnt shouldBe 2
    }
}
