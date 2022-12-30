package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class AdjacentBlanksTest : StringSpec({
    "인접한 빈칸들을 모두 개방한다." {
        val blanks = blankCellListOf(1 to 1, 1 to 2, 1 to 3, 2 to 1)
        val adjacentBlanks = AdjacentBlanks(blanks)

        adjacentBlanks.open()
        val openBlanksSize = adjacentBlanks.cells.count { it.status == Status.OPEN }

        openBlanksSize shouldBe blanks.size
    }
})
