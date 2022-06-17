import domain.field.Field
import domain.field.FieldInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FieldTest {

    @Test
    fun `지뢰는 랜덤하게 배치된다`(){
        val fieldInfo = FieldInfo(5, 5, 5)
        val mineField1 = Field().buildField(fieldInfo)
        val mineField2 = Field().buildField(fieldInfo)

        assertThat(mineField1).isNotEqualTo(mineField2)
    }
}