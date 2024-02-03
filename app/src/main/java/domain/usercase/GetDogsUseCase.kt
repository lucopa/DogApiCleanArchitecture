package domain.usercase

import data.models.Dog
import data.models.DogRepositoryDao


class GetDogsUseCase {
    private val dogRepository = DogRepositoryDao()

    operator fun invoke(): List<Dog>? {
        return dogRepository.getDogs()
    }
}