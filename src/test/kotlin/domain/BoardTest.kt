package domain

import domain.strategy.CellGenerateStrategy
import domain.strategy.RandomGenerateStrategy
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class BoardTest : StringSpec({
    "인자로 받은 좌표가 지뢰라면 true 를 반환한다." {
        val mineCells = mineCellListOf(1 to 1)
        val board = Board(mineCells)
        val result = board.isMineCell(coordinateOf(1, 1))

        result shouldBe true
    }

    "인자로 받은 좌표가 빈칸이거나 없는 좌표라면 false 를 반환한다." {
        val blankCells = blankCellListOf(1 to 2, 1 to 3)
        val board = Board(blankCells)

        val coordinates = coordinateListOf(
            1 to 1, 5 to 3, 3 to 3
        )
        coordinates.forAll {
            val result = board.isMineCell(it)
            result shouldBe false
        }
    }

    /**
     * 1 X 1
     * 1 1 1
     * 0 0 0
     */
    "3x3 보드에 (1,2) 좌표에 지뢰가 있다면 1 지뢰 카운트를 가진 빈칸이 5개, 0 지뢰 카운트를 가진 빈칸이 3개이다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(1))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(1)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val blankCells = board.cells.filterIsInstance<Blank>()
        val countOne = blankCells.count { it.minesAroundCount == 1 }
        val countZero = blankCells.count { it.minesAroundCount == 0 }

        countOne shouldBe 5
        countZero shouldBe 3
    }

    /**
     * 1 X 1
     * 1 2 2
     * 0 1 X
     */
    "3x3 보드에 (1,2), (3,3) 좌표에 지뢰가 있다면 2 지뢰 카운트를 가진 빈칸 2개, 1 지뢰 카운트를 가진 빈칸 4개, 0 지뢰 카운트를 가진 빈칸이 1개이다. " {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(1, 8)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val blankCells = board.cells.filterIsInstance<Blank>()
        val countTwo = blankCells.count { it.minesAroundCount == 2 }
        val countOne = blankCells.count { it.minesAroundCount == 1 }
        val countZero = blankCells.count { it.minesAroundCount == 0 }

        countTwo shouldBe 2
        countOne shouldBe 4
        countZero shouldBe 1
    }

    /**
     * C C C
     * C C C
     * C C C
     */
    "좌표로 조회하고 존재하는 셀이라면 셀을 반환한다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
        val game = Game(boardInfo, RandomGenerateStrategy())
        val board = game.createBoard()

        val result = board.findOrNull(Coordinate(2 to 2))
        result!!.coordinate shouldBe Coordinate(2 to 2)
    }

    /**
     * C C C
     * C C C
     * C C C
     */
    "좌표로 조회하고 존재하지 않는 셀이라면 null 을 반환한다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
        val game = Game(boardInfo, RandomGenerateStrategy())
        val board = game.createBoard()

        val result = board.findOrNull(Coordinate(5 to 2))
        result shouldBe null
    }

    "처음 보드를 생성했을 때 모든 셀은 닫혀있다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
        val game = Game(boardInfo, RandomGenerateStrategy())
        val board = game.createBoard()

        board.cells.forEach {
            val result = it.status
            result shouldBe Status.CLOSE
        }
    }

    "보드의 모든 빈칸이 open 되어있다면 true 를 반환한다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
        val game = Game(boardInfo, RandomGenerateStrategy())
        val board = game.createBoard()

        board.cells.filterIsInstance<Blank>().map { it.open() }
        val result = board.isOpenAllBlank

        result shouldBe true
    }

    "보드의 모든 빈칸이 open 되어 있지 않다면 false 를 반환한다." {
        val openBlankCells = openBlankCellListOf(1 to 1, 1 to 2)
        val closeBlankCells = blankCellListOf(1 to 3, 1 to 4)
        val board = Board(openBlankCells + closeBlankCells)

        val result = board.isOpenAllBlank
        result shouldBe false
    }

    /**
     * T: Target
     * C: Close
     * X: Mine
     * O: Open
     *
     * AS-IS
     * T C C C C
     * X C C X C
     * C X X X C
     * C X C X C
     * C X C C X
     *
     * TO-BE
     * O O O O O
     * X O O X O
     * C X X X O
     * C X C X O
     * C X C C X
     */
    "(1,1) 이 빈칸일 때 자신 포함 인접한 위치의 빈칸들이 open 된다." {
        val boardInfo = BoardInfo(Row(5), Column(5), MineCount(9))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(5, 8, 11, 12, 13, 16, 18, 21, 24)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val blank = board.findOrNull(Coordinate(1 to 1))!!
        board.openAdjacentBlanksBy(blank as Blank)
        val expected = openBlankCellListOf(1 to 1, 1 to 2, 1 to 3, 1 to 4, 1 to 5, 2 to 2, 2 to 3, 2 to 5, 3 to 5, 4 to 5)

        board.cells.forEach { cell ->
            if (cell in expected) {
                val result = cell.status
                result shouldBe Status.OPEN
            }
        }
        board.cells.filter { it.status == Status.OPEN }.size shouldBe expected.size
    }

    /**
     * TO-BE
     * C C C C C
     * X C C X C
     * T X X X C
     * O X C X C
     * O X C C X
     */
    "(3,1) 이 빈칸일 때 자신 포함 인접한 위치의 빈칸들이 open 된다." {
        val boardInfo = BoardInfo(Row(5), Column(5), MineCount(9))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(5, 8, 11, 12, 13, 16, 18, 21, 24)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val blank = board.findOrNull(Coordinate(3 to 1))!!
        board.openAdjacentBlanksBy(blank as Blank)
        val expected = openBlankCellListOf(3 to 1, 4 to 1, 5 to 1)

        board.cells.forEach { cell ->
            if (cell in expected) {
                val result = cell.status
                result shouldBe Status.OPEN
            }
        }
        board.cells.filter { it.status == Status.OPEN }.size shouldBe expected.size
    }

    "개방되지 않은 모든 셀을 open 한다." {
        val board = get5x5RandomBoard()

        val closedCellSize = board.cells.filter { it.status == Status.CLOSE }
        board.openAllCells()
        val openCellSize = board.cells.filter { it.status == Status.OPEN }

        openCellSize shouldBe closedCellSize
    }
})
