package com.example.youtube_api.ui.playlist.item

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.youtube_api.core.network.ext.loadImage
import com.example.youtube_api.core.network.result.Status
import com.example.youtube_api.core.network.ext.showToast
import com.example.youtube_api.core.network.ui.BaseActivity
import com.example.youtube_api.data.entity.model.ItemsItem
import com.example.youtube_api.databinding.ActivityItemPlaylistBinding
import com.example.youtube_api.ui.playlist.PlaylistsActivity
import com.example.youtube_api.ui.playlist.video.VideosActivity
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemPlaylistsActivity : BaseActivity<ItemViewModel, ActivityItemPlaylistBinding>() {
    private val adapterItem = CompositeDelegateAdapter(AdapterItemList(this::onClick))
    private val registerForActivity =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    override val viewModel: ItemViewModel by viewModel()
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityItemPlaylistBinding {
        return ActivityItemPlaylistBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        viewModel.loading.observe(this) {
            binding.progressCircular.isVisible = it
        }
        val id = intent.getStringExtra(PlaylistsActivity.ID)
        showToast(id.toString())
        binding.containerToolbar.tvBack.setOnClickListener {
            finish()
        }
        viewModel.playlists(id.toString()).observe(this) {
            it.data?.items?.let { it1 ->
                adapterItem.swapData(it1)
                binding.tvTitle.text = intent.getStringExtra (PlaylistsActivity.KEY)
                binding.ivImage.loadImage(
                    intent.getStringExtra("image").toString()
                )
                when (it.status) {
                Status.SUCCESS -> {
                    binding.rvPlaylistItem.adapter = adapterItem
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(true)
                    showToast (it.message.toString())
                }
            }
            }
        }
    }

    private fun onClick(item: ItemsItem) {
        val i = Intent(this, VideosActivity::class.java)
        i.putExtra(DESC, item.snippet.description)
        i.putExtra (KEY, item.snippet.title)
        registerForActivity.launch(i)
    }

    companion object {
        const val DESC = "desc"
        const val KEY = "tit"
    }
}