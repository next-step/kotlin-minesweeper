package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BoardTest : FunSpec({
    test("cells() 테스트") {
        // given
        val cells = Cells.create(3, 3)
        val board = Board.from(cells)

        // when
        val result = board.cells()

        // then
        result shouldBe cells
    }

    test("placeMines() 테스트") {
        // given
        val cells = Cells.create(3, 3)
        val board = Board.from(cells)
        val minePlacer = RandomMinePlacer()
        val mineCount = 2

        // when
        val result = board.placeMines(minePlacer, mineCount)

        // then
        result.cells().allCells().filter { it.hasMine }.size shouldBe 2
    }

    test("openAllCells() 테스트") {
        // given
        val cells = Cells.create(3, 3)
        val board = Board.from(cells)
        val mineCount = 3
        val boardWithMines = board.placeMines(RandomMinePlacer(), mineCount)

        // when
        val openedCells = boardWithMines.openAllCells()

        // then
        openedCells.size shouldBe cells.allCells().size

        val numberOfMineSymbols = openedCells.count { it.symbol == "*" }
        numberOfMineSymbols shouldBe mineCount

        val numberOfNumberSymbols = openedCells.count { it.symbol != "*" }
        numberOfNumberSymbols shouldBe (openedCells.size - numberOfMineSymbols)
    }
})
