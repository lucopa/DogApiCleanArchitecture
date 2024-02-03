package ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogapicleanarchitecture.databinding.ItemDogBinding

class DogViewHDog(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dog: Pair<String, String>) {
        binding.apply {
            textViewName.text = dog.first

            Glide.with(root.context)
                .load(dog.second)
                .into(imageViewDog)
        }
    }
}
