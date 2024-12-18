package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RandomMineGeneratorTest : StringSpec({
    "주어진 지뢰 개수만큼 랜덤하게 지뢰를 생성" {
        val totalCells = 100
        val mineCount = 10
        val minePositions = RandomMineGenerator().generateMinePositions(totalCells, mineCount)

        minePositions.size shouldBe mineCount
        minePositions.all { it in 0 until totalCells } shouldBe true
    }

    "가용한 Cell 개수를 초과해서 지뢰를 생성하고자 하는 경우 예외를 던짐" {
        shouldThrow<IllegalArgumentException> {
            RandomMineGenerator().generateMinePositions(10, 20)
        }
    }
})
