package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.RandomPositionPicker
import minesweeper.domain.cell.Position

class RandomPositionPickerTest : StringSpec({
    "주어진 위치 목록에서 주어진 개수만큼 랜덤하게 각기 다른 위치를 뽑는다" {
        val positions = setOf(
            Position(0, 0),
            Position(0, 1),
            Position(1, 0),
            Position(1, 1),
        )
        val count = 2

        val result = RandomPositionPicker().pick(positions, count)

        result.size shouldBe count
        result.forEach { it in positions }
        result.distinct().size shouldBe count
    }
})
