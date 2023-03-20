package com.example.youtube_api.ui.playlist

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_api.R
import com.example.youtube_api.core.network.ext.isNetworkConnected
import com.example.youtube_api.core.network.result.Status
import com.example.youtube_api.core.network.ext.showToast
import com.example.youtube_api.core.network.ui.BaseActivity
import com.example.youtube_api.data.entity.model.ItemsItem
import com.example.youtube_api.databinding.PlaylistMainBinding
import com.example.youtube_api.ui.playlist.item.ItemPlaylistsActivity
import com.example.youtube_api.util.InternetConnectivity

class PlaylistsActivity : BaseActivity<PlaylistsViewModel, PlaylistMainBinding>() {

    private lateinit var adapterPlaylist : AdapterPlaylist
    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater): PlaylistMainBinding {
        return PlaylistMainBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        viewModel.loading.observe(this){
            binding.progressCircular.isVisible = it
        }
        viewModel.playlists().observe(this){
            adapterPlaylist = AdapterPlaylist(it,this::itemClick)
            when(it.status){
                Status.SUCCESS -> {
                    binding.recyclerMain.adapter = adapterPlaylist
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.ERROR ->{
                    viewModel.loading.postValue(true)
                    showToast(it.message.toString())
                }
            }
        }
    }

    override fun initListener() {
        binding.noInternet.btnTryAgain.setOnClickListener{
            showToast(getString(R.string.no_connection))
        }
    }

    private fun itemClick(title:String,itemsItem: ItemsItem) {
        val i = Intent(this, ItemPlaylistsActivity::class.java)
        i.putExtra(ID,itemsItem.id)
        i.putExtra(KEY,title)
        i.putExtra(IMAGE,itemsItem.snippet.thumbnails.default.url)
        registerForActivity.launch(i)
    }

    override fun checkInternet() {
        super.checkInternet()
        val connectivity = InternetConnectivity(application)
        connectivity.observe(this) {
            if (!it) {
                binding.noInternet.root.isVisible = true

                binding.noInternet.btnTryAgain.setOnClickListener {
                    if (!isNetworkConnected()) {
                        showToast(getString(R.string.no_connection))
                    } else {
                        binding.noInternet.root.isVisible = false
                    }
                }
            } else {
                initViewModel()
            }
        }
    }

    companion object{
        const val ID = "key"
        const val KEY = "title"
        const val IMAGE = "image"
    }
}