package data.service

import data.datasource.Dogs

class DogService : DogServiceInterface {
    override fun getDogs(): List<Pair<String, String>> {
        return Dogs.dogs
    }

    /*
    Aquí, estoy filtrando por raza. Simulo un acceso por filtro.
    */
    override fun getBreedDogs (breed: String): List<Pair<String,String>> {
        val newDogs = Dogs.dogs.filter {
            it.first.equals(breed)
        }
        return newDogs
    }
}