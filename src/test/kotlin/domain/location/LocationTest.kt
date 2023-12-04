package domain.location

import domain.setting.Height
import domain.setting.MineCount
import domain.setting.Size
import domain.setting.Width
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LocationTest {
    @ParameterizedTest
    @CsvSource("100,100", "1,1")
    fun `지뢰 개수는 높이와 너비를 곱한 수 이하이다`(heightValue: Int, widthValue: Int) {
        val height = Height(heightValue)
        val width = Width(widthValue)
        val size = Size(height, width)
        val mineCount = MineCount(heightValue * widthValue + 1)
        assertThrows<IllegalArgumentException> { Location.generateMineLocations(size, mineCount) }
    }
}
