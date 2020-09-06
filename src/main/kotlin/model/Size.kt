package model

class Size(val size: Int) {
    init {
        if (size !in 5..15) throw IllegalArgumentException("사이즈는 5~15사이만 가능합니다.")
    }
}
