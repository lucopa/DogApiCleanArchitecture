package domain.usercase

import data.models.Dog
import data.models.DogRepositoryDao

class GetDogsBreedUseCase( val breed: String) {
    private val dogRepository = DogRepositoryDao()
    operator fun invoke() : List<Dog>{
        return dogRepository.getBreedDogs( breed)
    }
}
