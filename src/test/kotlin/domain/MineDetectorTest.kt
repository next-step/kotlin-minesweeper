package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class MineDetectorTest : StringSpec({
    lateinit var boardInfo: BoardInfo

    beforeEach {
        boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
    }

    /**
     * C X C
     * C T C
     * C C C
     *
     * T 는 target
     * C 는 빈칸
     * X 는 지뢰를 뜻함
     */
    "팔방에 지뢰가 1개 존재한다면 1개로 카운팅 된다." {
        val mineCells = mineCellListOf(1 to 2)
        val board = Board(mineCells)

        val detector = MineDetector(boardInfo, board)
        val result = detector.getMinesAroundCount(coordinateOf(2, 2))

        result shouldBe 1
    }

    /**
     * C X C
     * X T C
     * C C X
     */
    "팔방에 지뢰가 3개 존재한다면 1개로 카운팅 된다." {
        val mineCells = mineCellListOf(1 to 2, 2 to 1, 3 to 3)
        val board = Board(mineCells)

        val detector = MineDetector(boardInfo, board)
        val result = detector.getMinesAroundCount(coordinateOf(2, 2))

        result shouldBe 3
    }

    /**
     * T X C
     * C C C
     * C C C
     */
    "타겟 좌표가 모서리에 위치하고 주위 지뢰가 1개라면 탐색할 수 없는 좌표는 패스하고 1개로 카운팅 한다." {
        val mineCells = mineCellListOf(1 to 2)
        val board = Board(mineCells)

        val detector = MineDetector(boardInfo, board)
        val result = detector.getMinesAroundCount(coordinateOf(1, 1))

        result shouldBe 1
    }

    /**
     * C C C
     * C T C
     * C C C
     */
    "주위에 지뢰가 한 개도 없다면 0개로 카운팅한다." {
        val blankCells =
            blankCellListOf(1 to 1, 1 to 2, 1 to 3, 2 to 1, 2 to 2, 2 to 3, 3 to 1, 3 to 2, 3 to 3)
        val board = Board(blankCells)

        val detector = MineDetector(boardInfo, board)
        val result = detector.getMinesAroundCount(coordinateOf(2, 2))

        result shouldBe 0
    }
})
