package minesweeper.application

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@Suppress("NonAsciiCharacters")
class GenerateMinesweeperCommandTest {
    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `높이는 0 보다 커야 한다`(height: Int) {
        assertThrows<IllegalArgumentException> {
            GenerateMinesweeperCommand(
                height = height,
                width = 10,
                mineCount = 10,
            )
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `너비는 0 보다 커야 한다`(width: Int) {
        assertThrows<IllegalArgumentException> {
            GenerateMinesweeperCommand(
                height = 10,
                width = width,
                mineCount = 10,
            )
        }
    }

    @Test
    fun `지뢰 개수는 0 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> {
            GenerateMinesweeperCommand(
                height = 10,
                width = 10,
                mineCount = -1,
            )
        }
    }

    @Test
    fun `지뢰 개수는 총 칸 개수를 넘을 수 없습니다`() {
        assertThrows<IllegalArgumentException> {
            GenerateMinesweeperCommand(
                height = 10,
                width = 10,
                mineCount = 101,
            )
        }
    }

    @Test
    fun `총 칸의 개수를 구한다`() {
        val command =
            GenerateMinesweeperCommand(
                height = 10,
                width = 10,
                mineCount = 10,
            )
        command.area shouldBe 100
    }
}
