package me.comas.application.routes

import MatchListDb
import Training
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.litote.kmongo.eq
import org.litote.kmongo.insertOne
import trainingListDb

fun Application.trainingRoutes() {
    routing {
        createTrainingRoute()
        getTrainingListRoute()
        deleteTrainingRoute()
    }
}

fun Route.createTrainingRoute() {
    post(Training.path) {
        trainingListDb.insertOne(call.receive<Training>())
        call.respond(HttpStatusCode.OK)
    }
}
fun Route.getTrainingListRoute() {
    get(Training.path) {
        call.respond(trainingListDb.find().toList())
    }
}
fun Route.deleteTrainingRoute(){
    delete("${Training.path}/{id}") {
        val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
        trainingListDb.deleteOne(Training::id eq id)
        call.respond(HttpStatusCode.OK)
    }
}