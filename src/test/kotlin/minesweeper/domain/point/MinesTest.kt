package minesweeper.domain.point

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.DefaultMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width

class MinesTest : StringSpec({
    "지뢰들은 입력받은 지뢰 개수 인자만 큼 지뢰정보를 갖고 있다." {
        val mines =
            Mines(
                height = Height(5),
                width = Width(4),
                count = MineCount(12),
                mineGenerator = DefaultMineGenerator(),
            )

        mines.placedMines.size shouldBe 12
    }

    "지뢰 개수는 전체 좌표 수보다 크면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> {
            Mines(
                height = Height(1),
                width = Width(1),
                count = MineCount(2),
                mineGenerator = DefaultMineGenerator(),
            )
        }
    }
})
