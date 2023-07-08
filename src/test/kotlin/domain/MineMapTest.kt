package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MineMapTest {
    @Test
    fun `MineMap을 생성할 수 있다`() {
        val height = 10
        val width = 10
        val numOfMine = height * width - 1
        val mineMap = MineMapGenerator.createMineMap(height, width, numOfMine)

        mineMap.elements.flatten().size shouldBe height * width

    }

    @Test
    fun `지뢰의 갯수가 가로 세로의 곱보다 크게 MineMap을 생성할 수 없다`() {
        val height = 10
        val width = 10
        val numOfMine = height * width + 1
        shouldThrow<IllegalArgumentException> { MineMapGenerator.createMineMap(height, width, numOfMine) }
    }
}
