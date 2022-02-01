package me.comas.application.routes

import MatchListDb
import MatchPlayerStatistics
import Player
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.litote.kmongo.MongoOperator
import org.litote.kmongo.eq
import players

fun Application.playerRoutes() {
    routing {
        getPlayersListRoute()
        createPlayerRoute()
        deletePlayer()
        updatePlayerRoute()
    }
}

fun Route.getPlayersListRoute() {
    get (Player.path){
        call.respond(players.find().toList())
    }
}
fun Route.createPlayerRoute() {
    post (Player.path){
        players.insertOne(call.receive<Player>())
        call.respond(HttpStatusCode.OK)
    }
}
fun Route.deletePlayer() {
    delete("${Player.path}/{id}") {
        val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
        players.deleteOne(Player::id eq id)
        call.respond(HttpStatusCode.OK)
    }
}

fun Route.updatePlayerRoute() {
    post(Player.path+"/update") {
        println("-------UPDATE----------")
        val matchPerformance = call.receive<MatchPlayerStatistics>()
        var player = players.find().toList().first { it.id == matchPerformance.playerId }
        player.playedMins+=matchPerformance.playedMins
        if(matchPerformance.playedMins>0) {
            player.playedMatches += 1
            player.madeGoals += matchPerformance.madeGoals
            player.concededGoals += matchPerformance.concedeGoals
            player.yellowCards += matchPerformance.yellowCards
            if (player.yellowCards == 3) player.diffidato = true
            if (player.yellowCards == 4) {
                player.squalificato = true
                player.yellowCards=0
            }
           if (matchPerformance.redCards == 1) {
               player.redCards += 1
                player.squalificato = true
           }
        } else if (player.squalificato) {
            player.squalificato=false
        }
        player.totVote += 1
        player.averageVote = Player.getAverageVote(player.averageVote,matchPerformance.vote,player.totVote)
        val match = MatchListDb.find().toList().first { it.id == matchPerformance.matchId }
        player.matches.add(match)
        players.updateOne(Player :: id eq matchPerformance.playerId,player)
        call.respond(HttpStatusCode.OK)
    }
}