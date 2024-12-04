package minesweeper.domain.point

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.DefaultMineGenerator

class MinesTest : StringSpec({
    "지뢰들은 입력받은 지뢰 개수 인자만 큼 지뢰정보를 갖고 있다." {
        val mines =  Mines(
            height = 5,
            width = 4,
            count = 12,
            mineGenerator = DefaultMineGenerator(),
        )

        mines.placedMines.size shouldBe 12
    }
    "지뢰 개수는 음수면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> {
            Mines(
                height = 1,
                width = 1,
                count = -1,
                mineGenerator = DefaultMineGenerator(),
            )
        }
    }

    "지뢰 개수는 전체 좌표 수보다 크면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> {
            Mines(
                height = 1,
                width = 1,
                count = 2,
                mineGenerator = DefaultMineGenerator(),
            )
        }
    }
})
