package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.Coordinate
import minesweeper.Land
import minesweeper.MineBoard
import minesweeper.domain.cell.Mine

internal class MineBoardTest : FreeSpec({

    "지뢰판의 셀이 비어있을 경우 예외가 발생한다." {
        val exception =
            shouldThrowExactly<IllegalArgumentException> { MineBoard(emptyMap()) }
        exception.message shouldBe "지뢰판은 빌 수 없습니다."
    }

    "높이, 너비, 지뢰 개수를 입력 받아 지뢰판을 만들 수 있다." {
        val mineBoard = MineBoard.create(
            height = 5,
            width = 5,
            mineCount = 5
        )

        mineBoard.cells.shouldHaveSize(25)
        mineBoard.cells.count { it.value == Mine } shouldBe 5
    }

    "좌표를 입력받아 지뢰 유무를 확인 할수 있다." - {
        val cells = mapOf(
            Pair(Coordinate(0, 0), Mine),
            Pair(Coordinate(0, 1), Land(1)),
            Pair(Coordinate(0, 2), Land(0)),
            Pair(Coordinate(1, 0), Land(1)),
            Pair(Coordinate(1, 1), Land(1)),
            Pair(Coordinate(1, 2), Land(0)),
            Pair(Coordinate(2, 0), Land(0)),
            Pair(Coordinate(2, 1), Land(0)),
            Pair(Coordinate(2, 2), Land(0)),
        )

        val mineBoard = MineBoard(cells = cells)


        "정상적인 좌표를 입력하면 지뢰, 빈땅을 확인할 수 있다." {
            mineBoard.open(Coordinate(0, 0)) shouldBe Mine
            mineBoard.open(Coordinate(1, 0)) shouldBe Land(1)
            mineBoard.open(Coordinate(2, 2)) shouldBe Land(0)
        }

        "지뢰판 바깥의 좌표를 입력하면 예외가 발생한다." {
            shouldThrowExactly<IllegalArgumentException> { mineBoard.open(Coordinate(9, 9)) }
        }
    }
})
