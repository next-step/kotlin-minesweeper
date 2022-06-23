package view.input.converter

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputConverterTest {
    @ParameterizedTest
    @ValueSource(strings = ["input1", "input2", "input3"])
    fun `InputConverter는 String 타입으로 들어온 input을 지정한 타입으로 변환하여 반환한다`(input: String?) {
        val inputConverter = object : InputConverter<String> {
            override fun convert(input: String?): String {
                return "input입니다 : $input"
            }
        }

        Assertions.assertThat(inputConverter.convert(input)).isEqualTo("input입니다 : $input")
    }
}
