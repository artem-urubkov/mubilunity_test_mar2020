/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.auru.mobilunity.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.auru.mobilunity.R
import com.auru.mobilunity.dto.RepoElement
import kotlinx.android.synthetic.main.list_row.view.*

class RecyclerAdapter(private var repoItems: List<RepoElement>) :
    RecyclerView.Adapter<RecyclerAdapter.RepoItemsHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.RepoItemsHolder {
        val inflatedView = parent.inflate(R.layout.list_row, false)
        return RepoItemsHolder(inflatedView)
    }

    override fun getItemCount(): Int = repoItems.size

    fun setItems(newRepoItems: List<RepoElement>) {
        //TODO merge lists in more elegant way
        repoItems = newRepoItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.RepoItemsHolder, position: Int) {
        val repoItem = repoItems[position]
        holder.bindElement(repoItem)
    }

    class RepoItemsHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private var repoElement: RepoElement? = null

        init {
            view.setOnClickListener(this)
        }

        fun bindElement(repoItem: RepoElement) {
            this.repoElement = repoItem
            view.title.text = repoItem.name
            view.description.text = repoItem.description
        }

        override fun onClick(v: View) {
        }

        companion object {
            private val REPO_ITEM_KEY = "REPO_ITEM"
        }
    }


    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

}