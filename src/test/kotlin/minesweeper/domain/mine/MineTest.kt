package minesweeper.domain.mine

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MineTest {

    @Test
    @DisplayName("지뢰를 생성")
    fun `Create a mine`() {
        val mines = Mine.generateMine()

        mines.mineType shouldBe MineType.MINE
    }


    @Test
    @DisplayName("땅을 생성")
    fun `Create land`() {
        val mines = Mine.generateLand()

        mines.mineType shouldBe MineType.LAND
    }
}
