package pawan.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/breeds") {
            val dogsBreedList: MutableList<String> = mutableListOf()

            File("assets").walk().maxDepth(1).forEach {
                dogsBreedList.add(it.absoluteFile.name)
            }

            dogsBreedList.remove("assets")
            call.respond(dogsBreedList)
        }

        get("breeds/{breed}") {
            val breed = call.parameters["breed"]
            var imageList: MutableList<String> = mutableListOf()
            File("assets/$breed").walk().forEach {
                imageList.add(it.absolutePath)
            }

            call.respond(imageList.random())
        }
    }
}
