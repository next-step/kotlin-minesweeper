package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.application.MineSweeperService
import minesweeper.fixture.TestMineBoardCreateStrategy
import minesweeper.request.MineBoardCreateRequest

class MineBoardTest : FunSpec({
    val service = MineSweeperService(mineBoardCreateStrategy = TestMineBoardCreateStrategy)

    test("지뢰찾기 보드의 각 좌표 정보는 인접한 8개의 좌표의 지뢰 갯수만큼 카운팅된다.") {
        val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 5)
        val inputLines = listOf(
            listOf(SymbolType.ZERO, SymbolType.MINE, SymbolType.ZERO),
            listOf(SymbolType.ZERO, SymbolType.ZERO, SymbolType.ZERO),
            listOf(SymbolType.MINE, SymbolType.ZERO, SymbolType.MINE)
        )
        val expectedLines = listOf(
            listOf(SymbolType.ONE, SymbolType.MINE, SymbolType.ONE),
            listOf(SymbolType.TWO, SymbolType.THREE, SymbolType.TWO),
            listOf(SymbolType.MINE, SymbolType.TWO, SymbolType.MINE)
        )

        TestMineBoardCreateStrategy.updateBoardSetUp(input = inputLines.flatten())

        service.createMineBoard(request).apply {
            height shouldBe request.height
            width shouldBe request.width
            lines shouldHaveSize request.height

            lines.zip(expectedLines) { line, expectedLine ->
                line.zip(expectedLine) { cell, expectedCell ->
                    cell.symbol shouldBe expectedCell
                }
            }
        }
    }
})
