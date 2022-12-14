package domain

interface FieldGenerator {

    fun generate(height: Int, width: Int, mineCount: Int): Field
}
