package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class MineSweeperGameTest : StringSpec({

    val mockRandomCoordinateGenerator = mockk<RandomCoordinateGenerator>()

    "지뢰찾기 게임 초기화 시 지뢰없는 칸 생성 테스트" {
        // given
        val expected = arrayListOf(
            arrayListOf(Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY)),
            arrayListOf(Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY)),
            arrayListOf(Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY))
        )
        // when
        val mineSweeperGame = MineSweeperGame(height = 3, width = 4, mineCount = 0, RandomCoordinateGenerator())
        val actual = mineSweeperGame.cells
        // then
        actual shouldBe expected
    }

    "지정된 위치에 지뢰 생성 테스트" {
        // given
        every { mockRandomCoordinateGenerator.generate(any(), any(), any()) } returns listOf(
            Coordinate(0, 0),
            Coordinate(1, 1),
            Coordinate(2, 2)
        )
        val expected = arrayListOf(
            arrayListOf(Cell(CellStatus.MINE), Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY)),
            arrayListOf(Cell(CellStatus.EMPTY), Cell(CellStatus.MINE), Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY)),
            arrayListOf(Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY), Cell(CellStatus.MINE), Cell(CellStatus.EMPTY))
        )
        // when
        val mineSweeperGame = MineSweeperGame(height = 3, width = 4, mineCount = 3, mockRandomCoordinateGenerator)
        val actual = mineSweeperGame.cells
        // then
        actual shouldBe expected
    }
})
