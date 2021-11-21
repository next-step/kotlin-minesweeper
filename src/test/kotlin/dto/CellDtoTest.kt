package dto

import domain.Cell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CellDtoTest {
    lateinit var cell: Cell

    @BeforeEach
    fun setUp() {
        cell = Cell()
    }

    @DisplayName("열려있지 않은 Cell 은 C 로 렌더링되어야 한다.")
    @Test
    fun closed() {
        assertAll(
            { assertThat(CellDto(cell).draw()).isEqualTo("C") },
            { assertThat(CellDto(cell).mineNumber()).isEqualTo("0") },
            { assertThat(CellDto(cell).render()).isEqualTo("C") }
        )
    }

    @DisplayName("Cell 이 열리면, mineNumber 가 보여야 한다.")
    @Test
    fun open() {
        cell.increaseMineNumber()
        cell.open()
        assertAll(
            { assertThat(CellDto(cell).draw()).isEqualTo("C") },
            { assertThat(CellDto(cell).mineNumber()).isEqualTo("1") },
            { assertThat(CellDto(cell).render()).isEqualTo("1") }
        )
    }

    @DisplayName("Cell 이 mine 이라면, mineNumber 가 * 로 렌더링이 되어야 한다.")
    @Test
    fun mine() {
        repeat(3) { cell.increaseMineNumber() }
        cell.layMine()
        cell.open()
        assertAll(
            { assertThat(CellDto(cell).draw()).isEqualTo("*") },
            { assertThat(CellDto(cell).mineNumber()).isEqualTo("*") },
            { assertThat(CellDto(cell).render()).isEqualTo("C") }
        )
    }
}
