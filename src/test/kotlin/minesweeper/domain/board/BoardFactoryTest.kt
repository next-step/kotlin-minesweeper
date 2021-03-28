package minesweeper.domain.board

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class BoardFactoryTest {

    @Test
    fun `높이, 너비, 지뢰 개수가 0 보다 커야 생성이 가능하다 정상 생성`() {
        assertDoesNotThrow { BoardFactory.create(height = 1, width = 1, mineCount = 1) }
    }

    @Test
    fun `높이, 너비, 지뢰 개수가 0 보다 커야 생성이 가능하다 높이가 0 인 경우`() {
        assertThrows<IllegalArgumentException> { BoardFactory.create(height = 0, width = 1, mineCount = 1) }
    }

    @Test
    fun `높이, 너비, 지뢰 개수가 0 보다 커야 생성이 가능하다 너비가 0 인 경우`() {
        assertThrows<IllegalArgumentException> { BoardFactory.create(height = 1, width = 0, mineCount = 1) }
    }

    @Test
    fun `높이, 너비, 지뢰 개수가 0 보다 커야 생성이 가능하다 지뢰 개수가 0 인 경우`() {
        assertThrows<IllegalArgumentException> { BoardFactory.create(height = 1, width = 1, mineCount = 0) }
    }
}
