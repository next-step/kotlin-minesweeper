package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardTest {
    @Test
    fun `지도에 지뢰를 설치할 수 있다`() {
        val board = Board.of(2, 2, 4)

        board.matrix[0][0].shouldBeInstanceOf<Mine>()
        board.matrix[0][1].shouldBeInstanceOf<Mine>()
        board.matrix[1][0].shouldBeInstanceOf<Mine>()
        board.matrix[1][1].shouldBeInstanceOf<Mine>()
    }

    @Test
    fun `지도에 설치할 지뢰 개수는 지도 크기를 넘지 못한다`() {
        assertThrows<IllegalArgumentException>("지뢰 개수는 지도에 존재하는 모든 필드의 수보다 클 수 없습니다.") {
            Board.of(2, 2, 5)
        }
    }

    @Test
    fun `지뢰가 설치된 지도에 안전지대를 설정하고 주변에 존재하는 지뢰 개수를 나타낼 수 있다`() {
        val board = Board.of(2, 1, 1)

        val safe = if (board.matrix[0][0] is Safe) board.matrix[0][0] else board.matrix[0][1]

        (safe as Safe).aroundMineCount shouldBe 1
    }

    @Test
    fun `플레이어는 좌표를 통해서 해당 칸을 열 수 있다`() {
        val board = Board.of(2, 2, 0)

        board.open(Coordinate(1, 1))

        board.matrix[1][1].isOpened() shouldBe true
    }

    @Test
    fun `지뢰가 없는 인접한 칸들이 같이 열린다`() {
        val board = Board.of(3, 3, 0)
        val coordinate = Coordinate(1, 1)

        board.open(coordinate)

        val allOpened = CoordinateDirection.around(coordinate)
            .all { board.matrix[it.rows][it.cols].isOpened() }

        allOpened shouldBe true
    }

    @Test
    fun `주변에 지뢰가 있다면 인접한 칸들은 같이 열리지 않는다`() {
        val board = Board.of(2, 2, 0)
        board.matrix[1][1] = Mine()

        board.open(Coordinate(0, 0))

        board.matrix[1][0].isOpened() shouldBe false
        board.matrix[0][1].isOpened() shouldBe false
    }

    @Test
    fun `크기와 지뢰 개수만 주어지면 해당 크기의 지뢰찾기 지도가 생성된다`() {
        val board = Board.of(2, 2, 2)

        board.matrix.size shouldBe 2
        board.matrix[0].size shouldBe 2
        board.matrix[1].size shouldBe 2
    }

    @Test
    fun `지뢰가 열려있으면 게임 패배`() {
        val board = Board.of(2, 2, 2)

        board.matrix[0][0] = Mine()
        board.open(Coordinate(0, 0))

        board.isLose() shouldBe true
    }

    @Test
    fun `지뢰를 제외한 모든 필드가 열려있으면 게임 승리`() {
        val board = Board.of(2, 2, 0)

        board.matrix[0][0] = Mine()
        board.open(Coordinate(0, 1))
        board.open(Coordinate(1, 1))
        board.open(Coordinate(1, 1))

        board.isWin() shouldBe true
    }
}
