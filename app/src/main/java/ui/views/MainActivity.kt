package ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import com.example.dogapicleanarchitecture.R
import test.TestApi

import ui.viewmodel.DogViewModel
import androidx.activity.viewModels
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapicleanarchitecture.databinding.ActivityMainBinding
import com.example.filtrador_buscador_kotlin.DogAdapter
import data.models.Dog

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: DogAdapter
    val dogViewModel: DogViewModel by viewModels()

    var dogList = arrayListOf<Dog>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  // Aquí solo se llama a setContentView una vez

        binding.mySearch.setOnQueryTextListener(this)
        registerLiveData()
        loadData()
        initRecyclerView()

        llenarLista()
        setupRecyclerView()

    }

    fun llenarLista(){
        dogList.add(Dog("Perro de Agua", "https://upload.wikimedia.org/wikipedia/commons/9/9f/Perro_agua.jpg"))
        dogList.add(Dog("Shar Pei","https://upload.wikimedia.org/wikipedia/commons/3/3c/Shar_pei.jpg"))
        dogList.add(Dog("Samoyedo","https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/11.10.2015_Samoyed_%28cropped%29.jpg/1200px-11.10.2015_Samoyed_%28cropped%29.jpg"))
        dogList.add(Dog("Chow chow", "https://www.purina.com.ar/sites/default/files/2023-07/CHOW%20CHOW%20480X684.png"))
        dogList.add(Dog("Mastin","https://www.terranea.es/assets/images/razas/mastin_espanol2.jpg"))

    }

    private fun setupRecyclerView() {
       binding.myRecyclerPpal.layoutManager = LinearLayoutManager(this)
        adapter = DogAdapter(dogList)
        binding.myRecyclerPpal.adapter = adapter
    }

    private fun loadData() {
        dogViewModel.list()
    }

    private fun registerLiveData() {
        dogViewModel.dogListLiveData.observe(
            this, { myList ->
                adapter.dogList = (myList ?: emptyList()) as ArrayList<Dog>
                adapter.notifyDataSetChanged()
            })

        dogViewModel.progressBarLiveData.observe(
            this, { visible ->
                binding.progressBar.isVisible = visible
                Log.i("TAG-DOGS", "ProgressBar está $visible")
            }
        )

        dogViewModel.search.observe(
            this, { bread ->
                dogViewModel.listForBreed(bread)
                hideKeyBoard()
            }
        )
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty())
            dogViewModel.searchByBreed(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) {
            dogViewModel.list()
            hideKeyBoard()
        }
        return true
    }

    private fun hideKeyBoard() {
        val imn = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(binding.myRecyclerPpal.windowToken, 0)
    }


    private fun initRecyclerView() {
        adapter = DogAdapter()
        binding.myRecyclerPpal.adapter = adapter
    }


    private fun test() {
        TestApi.testDogApi()
    }
}
