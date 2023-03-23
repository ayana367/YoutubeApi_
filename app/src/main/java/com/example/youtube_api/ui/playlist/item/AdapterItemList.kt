package com.example.youtube_api.ui.playlist.item

import androidx.core.view.isVisible
import com.example.youtube_api.core.network.ext.loadImage
import com.example.youtube_api.data.entity.model.ItemsItem
import com.example.youtube_api.databinding.ItemPlaylistsBinding
import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter


class AdapterItemList(private val onClick: (ItemsItem) -> Unit) :
    ViewBindingDelegateAdapter<ItemsItem, ItemPlaylistsBinding>(ItemPlaylistsBinding::inflate) {
    override fun isForViewType(item: Any): Boolean = item is ItemsItem
    override fun ItemPlaylistsBinding.onBind(item: ItemsItem) {
        tvTitle.text = item.snippet.title
        tvDesk.text = item.snippet.publishedAt
        ivItem.loadImage(item.snippet.thumbnails.default.url)
        constBar.isVisible = false
        itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun ItemsItem.getItemId(): Any = snippet
}