package minesweeper.application

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.Line
import minesweeper.domain.Lines
import minesweeper.domain.SymbolPoint
import minesweeper.domain.SymbolType.BLIND
import minesweeper.domain.SymbolType.MINE
import minesweeper.fixture.TestMineBoardCreateStrategy
import minesweeper.request.MineBoardCreateRequest

class MineSweeperServiceTest : FunSpec({
    val service = MineSweeperService(mineBoardCreateStrategy = TestMineBoardCreateStrategy)

    test("유효한 요청 정보를 전달하면 지뢰찾기 보드를 생성해 반환한다.") {
        val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 5)

        /**
         *  C X C
         *  C X X
         *  X C X
         */
        val inputLines = Lines(
            listOf(
                Line(listOf(SymbolPoint(0, 0, BLIND), SymbolPoint(1, 0, MINE), SymbolPoint(2, 0, BLIND))),
                Line(listOf(SymbolPoint(0, 1, BLIND), SymbolPoint(1, 1, MINE), SymbolPoint(2, 1, MINE))),
                Line(listOf(SymbolPoint(0, 2, MINE), SymbolPoint(1, 2, BLIND), SymbolPoint(2, 2, MINE)))
            )
        )

        TestMineBoardCreateStrategy.updateBoardSetUp(input = inputLines)

        service.createMineBoard(request).apply {
            height shouldBe request.height
            width shouldBe request.width
            lines shouldHaveSize request.height
            lines shouldBe inputLines
        }
    }
})
