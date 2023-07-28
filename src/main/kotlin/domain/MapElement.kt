package domain
// TODO : 얘가 Location을 갖도록 하고, EmptyElement는 해당 location 주위를 돌며 mine갯수 카운트하도록 설정
interface MapElement {
    val displayCharacter: String
    val location: Location
}
