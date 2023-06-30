package minesweeper.application

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Row
import minesweeper.domain.Rows
import minesweeper.domain.SymbolType.BLIND
import minesweeper.domain.SymbolType.BOUNDARY
import minesweeper.domain.SymbolType.MINE
import minesweeper.domain.point.SymbolPoint
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
        val inputRows = Rows(
            listOf(
                Row(
                    listOf(
                        SymbolPoint(0, 0, BOUNDARY),
                        SymbolPoint(1, 0, BOUNDARY),
                        SymbolPoint(2, 0, BOUNDARY),
                        SymbolPoint(3, 0, BOUNDARY),
                        SymbolPoint(4, 0, BOUNDARY)
                    )
                ),
                Row(
                    listOf(
                        SymbolPoint(0, 1, BOUNDARY),
                        SymbolPoint(1, 1, BLIND),
                        SymbolPoint(2, 1, MINE),
                        SymbolPoint(3, 1, BLIND),
                        SymbolPoint(4, 1, BOUNDARY)
                    )
                ),
                Row(
                    listOf(
                        SymbolPoint(0, 2, BOUNDARY),
                        SymbolPoint(1, 2, BLIND),
                        SymbolPoint(2, 2, MINE),
                        SymbolPoint(3, 2, MINE),
                        SymbolPoint(4, 2, BOUNDARY)
                    )
                ),
                Row(
                    listOf(
                        SymbolPoint(0, 3, BOUNDARY),
                        SymbolPoint(1, 3, MINE),
                        SymbolPoint(2, 3, BLIND),
                        SymbolPoint(3, 3, MINE),
                        SymbolPoint(4, 3, BOUNDARY)
                    )
                ),
                Row(
                    listOf(
                        SymbolPoint(0, 4, BOUNDARY),
                        SymbolPoint(1, 4, BOUNDARY),
                        SymbolPoint(2, 4, BOUNDARY),
                        SymbolPoint(3, 4, BOUNDARY),
                        SymbolPoint(4, 4, BOUNDARY)
                    )
                )
            )
        )

        TestMineBoardCreateStrategy.updateBoardSetUp(input = inputRows)

        service.createMineBoard(request).apply {
            area.height shouldBe request.height
            area.width shouldBe request.width
            rows.realSize shouldBe request.height
            rows shouldBe inputRows
        }
    }
})
