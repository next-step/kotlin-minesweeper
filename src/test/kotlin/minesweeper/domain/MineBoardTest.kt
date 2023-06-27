package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.application.MineSweeperService
import minesweeper.domain.SymbolType.BLIND
import minesweeper.domain.SymbolType.MINE
import minesweeper.domain.SymbolType.ONE
import minesweeper.domain.SymbolType.THREE
import minesweeper.domain.SymbolType.TWO
import minesweeper.domain.SymbolType.ZERO
import minesweeper.fixture.TestMineBoardCreateStrategy
import minesweeper.request.MineBoardCreateRequest

class MineBoardTest : FunSpec({
    val service = MineSweeperService(mineBoardCreateStrategy = TestMineBoardCreateStrategy)

    test("지뢰찾기 보드의 각 좌표 정보는 인접한 8개의 좌표의 지뢰 갯수만큼 카운팅된다.") {
        val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 3)

        /**
         *  0 X 0
         *  0 0 0
         *  X 0 X
         */
        val inputLines = Lines(
            listOf(
                Line(listOf(SymbolPoint(0, 0, ZERO), SymbolPoint(1, 0, MINE), SymbolPoint(2, 0, ZERO))),
                Line(listOf(SymbolPoint(0, 1, ZERO), SymbolPoint(1, 1, ZERO), SymbolPoint(2, 1, ZERO))),
                Line(listOf(SymbolPoint(0, 2, MINE), SymbolPoint(1, 2, ZERO), SymbolPoint(2, 2, MINE)))
            )
        )

        /**
         *  1 X 1
         *  2 3 2
         *  X 2 X
         */
        val expectedLines = Lines(
            listOf(
                Line(listOf(SymbolPoint(0, 0, ONE), SymbolPoint(1, 0, MINE), SymbolPoint(2, 0, ONE))),
                Line(listOf(SymbolPoint(0, 1, TWO), SymbolPoint(1, 1, THREE), SymbolPoint(2, 1, TWO))),
                Line(listOf(SymbolPoint(0, 2, MINE), SymbolPoint(1, 2, TWO), SymbolPoint(2, 2, MINE)))
            )
        )

        TestMineBoardCreateStrategy.updateBoardSetUp(input = inputLines)

        service.createMineBoard(request).apply {
            area.height shouldBe request.height
            area.width shouldBe request.width
            lines shouldHaveSize request.height

            lines shouldContainExactly expectedLines
        }
    }

    test("지뢰찾기 보드 생성시 보드의 크기와 동일한 커버 프로퍼티를 가진다. ") {
        val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 3)

        /**
         *  0 X 0
         *  0 0 0
         *  X 0 X
         */
        val inputLines = Lines(
            listOf(
                Line(listOf(SymbolPoint(0, 0, ZERO), SymbolPoint(1, 0, MINE), SymbolPoint(2, 0, ZERO))),
                Line(listOf(SymbolPoint(0, 1, ZERO), SymbolPoint(1, 1, ZERO), SymbolPoint(2, 1, ZERO))),
                Line(listOf(SymbolPoint(0, 2, MINE), SymbolPoint(1, 2, ZERO), SymbolPoint(2, 2, MINE)))
            )
        )

        /**
         *  C C C
         *  C C C
         *  C C C
         */
        val expectedLines = Lines(
            listOf(
                Line(listOf(SymbolPoint(0, 0, BLIND), SymbolPoint(1, 0, BLIND), SymbolPoint(2, 0, BLIND))),
                Line(listOf(SymbolPoint(0, 1, BLIND), SymbolPoint(1, 1, BLIND), SymbolPoint(2, 1, BLIND))),
                Line(listOf(SymbolPoint(0, 2, BLIND), SymbolPoint(1, 2, BLIND), SymbolPoint(2, 2, BLIND)))
            )
        )

        TestMineBoardCreateStrategy.updateBoardSetUp(input = inputLines)

        service.createMineBoard(request).apply {
            coverLines shouldContainExactly expectedLines
        }
    }
})
