package minesweeper.dto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameBoardRequestTest {

    @Test
    fun `지뢰의 갯수가 전체 게임판 보다 크면 IllegaArgumentException을 throw 한다`() {
        val height = 10
        val width = 10
        val minesNumber = 101
        assertThrows<IllegalArgumentException> { GameBoardRequest(height,width,minesNumber) }
    }

}