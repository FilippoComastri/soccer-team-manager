
import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.browser.window

val endpoint = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved

val jsonClient = HttpClient {
    install(JsonFeature) { serializer = KotlinxSerializer() }
}

//MATCH CRUD

suspend fun getMatchList(): List<Match> {
    return jsonClient.get(endpoint + Match.path)
}


suspend fun addMatch(match: Match) {
    jsonClient.post<Unit>(endpoint + Match.path) {
        contentType(ContentType.Application.Json)
        body = match
    }
}

suspend fun deleteMatch(match: Match) {
    jsonClient.delete<Unit>(endpoint + Match.path + "/${match.id}")
}

suspend fun updateMatch(match: Match) {
    jsonClient.post<Unit>(endpoint + Match.path+ "/${match.id}") {
        contentType(ContentType.Application.Json)
        body = match
    }
}

// TRAINING CRUD

suspend fun getTrainingList(): List<Training> {
    return jsonClient.get(endpoint + Training.path)
}

suspend fun addTraining(training: Training) {
    jsonClient.post<Unit>(endpoint + Training.path) {
        contentType(ContentType.Application.Json)
        body = training
    }
}

suspend fun deleteTraining(training: Training) {
    jsonClient.delete<Unit>(endpoint + Training.path + "/${training.id}")
}


// Player Crud

suspend fun getPlayers(): List<Player> {
    return jsonClient.get(endpoint + Player.path)
}

suspend fun addPlayer(player: Player) {
    jsonClient.post<Unit>(endpoint + Player.path) {
        contentType(ContentType.Application.Json)
        body = player
    }
}

suspend fun deletePlayer(player: Player) {
    jsonClient.delete<Unit>(endpoint + Player.path + "/${player.id}")
}

suspend fun updatePlayer(matchPlayerStat: MatchPlayerStatistics) {
    jsonClient.post<Unit>(endpoint+Player.path+"/update") {
        contentType(ContentType.Application.Json)
        body = matchPlayerStat
    }
}
