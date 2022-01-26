import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val shirtNumber: Int,
    val name: String ,
    val surname: String,
    val roles: List<String>,
    var madeGoals: Int, //
    var concededGoals: Int, //
    var playedMins: Int, //
    var playedMatches: Int, //
    var averageVote: Double,
    val nationality: String,
    val dateOfBirth: String,
    val marketvalue: Int,
    val characteristics: String,
    var yellowCards: Int,//
    var redCards: Int, //
    var diffidato: Boolean,
    var squalificato: Boolean,
    var totVote: Int,
    var matches: MutableList<Match>
)
{
    val id: Int = surname.hashCode()
    companion object {
        const val path = "/playerLists"
    }
}