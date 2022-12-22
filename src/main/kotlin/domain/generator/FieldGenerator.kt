package domain.generator

import domain.Field

interface FieldGenerator {
    fun generate(height: Int, width: Int, mineCount: Int): Field
}
