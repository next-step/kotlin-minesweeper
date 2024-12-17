package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertThrows

class RandomMineGeneratorTest : StringSpec({
    "주어진 지뢰 개수만큼 랜덤하게 지뢰를 생성" {
        val totalCells = 100
        val mineCount = 10
        val minePositions = RandomMineGeneratorImpl().generateMinePositions(totalCells, mineCount)

        assertEquals(mineCount, minePositions.size)
        assertTrue(minePositions.all { it in 0 until totalCells })
    }

    "가용한 Cell 개수를 초과해서 지뢰를 생성하고자 하는 경우 예외를 던짐" {
        assertThrows<IllegalArgumentException> {
            RandomMineGeneratorImpl().generateMinePositions(10, 20)
        }
    }
})
