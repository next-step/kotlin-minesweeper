package minesweeper.dto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameBoardRequestTest {
    @Test
    fun `지뢰의 갯수가 전체 게임판 보다 크면 IllegaArgumentException을 throw 한다`() {
        // given
        val height = 10
        val width = 10
        val minesNumber = height * width + 1

        // then
        assertThrows<IllegalArgumentException> {
            GameBoardRequest(height, width, minesNumber)
        }
    }

    @Test
    fun `높이가 음수가 되면 IllegaArgumentException을 throw 한다`() {
        // given
        val height = -1
        val width = 10
        val minesNumber = 10

        // then
        assertThrows<IllegalArgumentException> {
            GameBoardRequest(height, width, minesNumber)
        }
    }

    @Test
    fun `너비가 음수가 되면 IllegaArgumentException을 throw 한다`() {
        // given
        val height = 10
        val width = -1
        val minesNumber = 10

        // then
        assertThrows<IllegalArgumentException> {
            GameBoardRequest(height, width, minesNumber)
        }
    }

    @Test
    fun `지뢰의 갯수가 음수가 되면 IllegaArgumentException을 throw 한다`() {
        // given
        val height = 10
        val width = 10
        val minesNumber = -1

        // then
        assertThrows<IllegalArgumentException> {
            GameBoardRequest(height, width, minesNumber)
        }
    }
}
