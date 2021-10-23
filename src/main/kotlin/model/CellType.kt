package model

enum class CellType {
    Mine,
    Plain;

    fun safe(): Boolean {
        return this == Plain
    }
}