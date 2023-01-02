package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.field.Mine
import minesweeper.domain.field.Safe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardTest {
    @Test
    fun `지도에 지뢰를 설치할 수 있다`() {
        val board = Board.of(2, 2, 4)

        board.matrix.rows[0][0].land.shouldBeInstanceOf<Mine>()
        board.matrix.rows[0][1].land.shouldBeInstanceOf<Mine>()
        board.matrix.rows[1][0].land.shouldBeInstanceOf<Mine>()
        board.matrix.rows[1][1].land.shouldBeInstanceOf<Mine>()
    }

    @Test
    fun `지도에 설치할 지뢰 개수는 지도 크기를 넘지 못한다`() {
        assertThrows<IllegalArgumentException>("지뢰 개수는 지도에 존재하는 모든 필드의 수보다 클 수 없습니다.") {
            Board.of(2, 2, 5)
        }
    }

    @Test
    fun `플레이어는 좌표를 통해서 해당 칸을 열 수 있다`() {
        val board = Board.of(2, 2, 0)

        board.open(Coordinate(1, 1))

        board.matrix.rows[1][1].isOpened() shouldBe true
    }

    @Test
    fun `크기와 지뢰 개수만 주어지면 해당 크기의 지뢰찾기 지도가 생성된다`() {
        val board = Board.of(2, 2, 2)

        board.matrix.rows.size shouldBe 2
        board.matrix.rows[0].size shouldBe 2
        board.matrix.rows[1].size shouldBe 2
    }

    @Test
    fun `지뢰가 열려있으면 게임 패배`() {
        val board = Board.of(2, 2, 4)
        board.open(Coordinate(0, 0))

        board.isLose() shouldBe true
    }

    @Test
    fun `지뢰를 제외한 모든 필드가 열려있으면 게임 승리`() {
        val board = Board.of(2, 2, 0)

        board.open(Coordinate(0, 1))
        board.open(Coordinate(1, 1))
        board.open(Coordinate(1, 1))

        board.isWin() shouldBe true
    }
}
