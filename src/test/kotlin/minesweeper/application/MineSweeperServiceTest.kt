package minesweeper.application

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.SymbolType.BLIND
import minesweeper.domain.SymbolType.MINE
import minesweeper.fixture.TestMineBoardCreateStrategy
import minesweeper.request.MineBoardCreateRequest

class MineSweeperServiceTest : FunSpec({
    val service = MineSweeperService(mineBoardCreateStrategy = TestMineBoardCreateStrategy)

    test("유효한 요청 정보를 전달하면 지뢰찾기 보드를 생성해 반환한다.") {
        val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 5)
        val inputLines = listOf(
            listOf(BLIND, MINE, BLIND),
            listOf(BLIND, MINE, MINE),
            listOf(MINE, BLIND, MINE)
        )

        TestMineBoardCreateStrategy.updateBoardSetUp(input = inputLines.flatten())

        service.createMineBoard(request).apply {
            height shouldBe request.height
            width shouldBe request.width
            lines shouldHaveSize request.height

            lines.zip(inputLines) { line, expectedLine ->
                line.zip(expectedLine) { cell, expectedCell ->
                    cell.symbol shouldBe expectedCell
                }
            }
        }
    }
})
