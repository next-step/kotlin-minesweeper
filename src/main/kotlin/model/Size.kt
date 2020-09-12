package model

class Size(val value: Int) {
    init {
        if (value !in 5..15) throw IllegalArgumentException("사이즈는 5~15사이만 가능합니다.")
    }
}
