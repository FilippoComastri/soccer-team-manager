package me.comas.application.routes

import Training
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import traininglist

fun Application.trainingRoutes() {
    routing {
        createTrainingRoute()
        getTrainingListRoute()
        deleteTrainingRoute()
    }
}

fun Route.createTrainingRoute() {
    post(Training.path) {
        //players.insertOne(call.receive<Player>())
        traininglist += call.receive<Training>()
        call.respond(HttpStatusCode.OK)
    }
}
fun Route.getTrainingListRoute() {
    get(Training.path) {
        //call.respond(players.find().toList())
        call.respond(traininglist)
    }
}
fun Route.deleteTrainingRoute(){
    delete("${Training.path}/{id}") {
        val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
        //players.deleteOne(Player::id eq id)
        call.respond(HttpStatusCode.OK)
    }
}