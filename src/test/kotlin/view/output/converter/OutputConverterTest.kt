package view.output.converter

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OutputConverterTest {
    @Test
    fun `OutputConverter는 데이터를 출력 가능한 텍스트로 변환해 준다`() {
        val outputConverter = object : OutputConverter<String> {
            override fun convert(printable: String): String {
                return "$printable!!"
            }
        }

        Assertions.assertThat(outputConverter.convert("test")).isEqualTo("test!!")
    }
}
