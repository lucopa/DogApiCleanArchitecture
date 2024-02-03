package data.models

import android.util.Log
import data.service.DogService

class DogRepositoryDao : DogRepositoryInterfaceDao {
    private val service : DogService = DogService()
    /*
    Método que a partir de los datos nativos, devuelve la lista
    de objetos que necesita el modelo.
    */
    override fun getDogs(): List<Dog> {
        var mutableDogs : MutableList <Dog> = mutableListOf()
        val dataSource = service.getDogs()
        dataSource.forEach{ dog->
            mutableDogs .add(Dog(dog. first, dog.second))
        }
        Repository.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository.dogs
        // En DogRepositoryDao, dentro de getDogs() y getBreedDogs()
        Log.d("TAG-DOGS", "Dogs: $mutableDogs")

    }
    override fun getBreedDogs (breed: String): List<Dog> {
        var mutableDogs : MutableList <Dog> = mutableListOf()
        val dataSource = service.getBreedDogs(breed)
        dataSource.forEach{ dog->
            mutableDogs .add(Dog(dog. first, dog.second))
        }
        Repository.dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository.dogs
        // En DogRepositoryDao, dentro de getDogs() y getBreedDogs()
        Log.d("TAG-DOGS", "Dogs: $mutableDogs")

    }
}