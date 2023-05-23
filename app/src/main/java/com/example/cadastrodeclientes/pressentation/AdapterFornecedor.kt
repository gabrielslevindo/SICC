package com.example.cadastrodeclientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastrodeclientes.Data.Fornecedor

class AdapterFornecedor (

    private val openFornecedorDetailView: (fornecedor: Fornecedor) -> Unit
) :
    ListAdapter<Fornecedor, FornecedorViewHolder>(AdapterFornecedor){
    private var listFornecedor: List<Fornecedor> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FornecedorViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return FornecedorViewHolder(view)
    }
    override fun onBindViewHolder(holder: FornecedorViewHolder, position: Int) {
        val fornecedor = getItem(position)
        holder.bind(fornecedor, openFornecedorDetailView)
    }
    companion object: DiffUtil.ItemCallback<Fornecedor>(){
        override fun areItemsTheSame(oldItem: Fornecedor, newItem: Fornecedor): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Fornecedor, newItem: Fornecedor): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.company == newItem.company &&
                    oldItem.address == newItem.address &&
                    oldItem.telephone == newItem.telephone
        }
    }
}


class FornecedorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val tvitemname = view.findViewById<TextView>(R.id.tv_name)
    private val tvitemregis = view.findViewById<TextView>(R.id.tv_registration)
    private val tvteleph = view.findViewById<TextView>(R.id.tv_telephone)
    private val tvaddress = view.findViewById<TextView>(R.id.tv_address)

    fun bind(fornecedor: Fornecedor, openTaskDetailView: (fornecedor: Fornecedor) -> Unit) {

        tvitemname.text = fornecedor.name
        tvitemregis.text = fornecedor.company
        tvteleph.text = fornecedor.telephone
        tvaddress.text = fornecedor.address

        view.setOnClickListener {

            openTaskDetailView.invoke(fornecedor)
        }}}
