package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows

class MineCountTest : StringSpec({
    "지뢰 개수가 0 보다 크다면 유효한 지뢰 개수" {
        val mineCount = MineCount(10)
        assertEquals(10, mineCount.count)
    }

    "지뢰 개수가 0 이라면 예외를 던진다" {
        assertThrows<IllegalArgumentException> {
            MineCount(0)
        }
    }
})
