package test

import android.util.Log
import domain.usercase.GetDogsBreedUseCase

class TestApi {
    companion object {
        fun testDogApi() {
            var useCaseBreed = GetDogsBreedUseCase( "Mastin")
            val listDogs = useCaseBreed()
            listDogs?.forEach() {
                Log.i("TAG-DOGS", it.image)
            }
        }
    }
}