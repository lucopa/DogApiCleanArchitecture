package ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.models.Dog
import domain.usercase.GetDogsBreedUseCase
import domain.usercase.GetDogsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {
    var dogListLiveData = MutableLiveData<List<Dog>>() // repositorio observable.
    var progressBarLiveData = MutableLiveData<Boolean>() // progressbar observable
    var search = MutableLiveData<String>() // para el campo search observable
    lateinit var useCaseList: GetDogsUseCase
    lateinit var useCaseBreedList: GetDogsBreedUseCase

    fun searchByBreed(breed: String) {
        // Log.i("TAG-DOGS", "La raza elegida es $breed")
        search.value = breed // notificamos cambio
    }

    fun list() {
        viewModelScope.launch {
            progressBarLiveData.value = true
            delay(2000)
            useCaseList = GetDogsUseCase()
            var data: List<Dog>? = useCaseList()
            data.let {
                dogListLiveData.value = it
                progressBarLiveData.value = false
            }
        }
    }

    fun listForBreed(breed: String) {
        viewModelScope.launch {
            progressBarLiveData.value = true
            delay(2000)
            useCaseBreedList = GetDogsBreedUseCase(breed)
            var data: List<Dog>? = useCaseBreedList()
            data.let {
                dogListLiveData.value = it
                progressBarLiveData.value = false
            }
        }
    }
}
