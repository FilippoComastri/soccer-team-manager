package me.comas.application.routes

import MatchPlayerStatistics
import Player
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import matchlist
import org.litote.kmongo.MongoOperator
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
        //call.respond(matches.find().toList())
        call.respond(players)
    }
}
fun Route.createPlayerRoute() {
    post (Player.path){
        //matches.insertOne(call.receive<Match>())
        players += call.receive<Player>()
        call.respond(HttpStatusCode.OK)
    }
}
fun Route.deletePlayer() {
    delete("${Player.path}/{id}") {
        val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
        //matches.deleteOne(Match::id eq id)
        //matchlist.removeIf()
        call.respond(HttpStatusCode.OK)
    }
}

fun Route.updatePlayerRoute() {
    post("${Player.path}/{id}") {
        val id = call.parameters["id"]?.toInt() ?: error("Invalid update request")
        val matchPerformance = call.receive<MatchPlayerStatistics>()
        players.first { it.id == id }.playedMins+=matchPerformance.playedMins
        if(matchPerformance.playedMins>0) {
            players.first { it.id == id }.playedMatches += 1
            players.first { it.id == id }.madeGoals += matchPerformance.madeGoals
            players.first { it.id == id }.concededGoals += matchPerformance.concedeGoals
            players.first { it.id == id }.yellowCards += matchPerformance.yellowCards
            if (players.first { it.id == id }.yellowCards == 3) players.first { it.id == id }.diffidato = true
            if (players.first { it.id == id }.yellowCards == 4) {
                players.first { it.id == id }.squalificato = true
                players.first { it.id == id }.yellowCards=0
            }
            if (matchPerformance.redCards == 1) {
                players.first { it.id == id }.redCards += 1
                players.first { it.id == id }.squalificato = true
            }
        } else if (players.first { it.id == id }.squalificato) {
            players.first { it.id == id }.squalificato=false
        }
        players.first { it.id == id }.totVote += 1
        players.first { it.id == id }.averageVote = getAverageVote(players.first { it.id == id }.averageVote,matchPerformance.vote,players.first { it.id == id }.totVote)
        val match = matchlist.first{it.id == matchPerformance.matchId}
        players.first { it.id == id }.matches.add(match)
        call.respond(HttpStatusCode.OK)
    }
}

fun getAverageVote(average: Double, vote: Double, tot: Int): Double {
    return average+vote/(tot+1)
}