package com.example.cadastrodeclientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastrodeclientes.Data.Veiculo

class AdapterVeiculo (

private val openVeiculoDetailView: (veiculo: Veiculo) -> Unit
) :
ListAdapter<Veiculo, VeiculoViewHolder>(AdapterVeiculo){
    private var listVeiculo: List<Veiculo> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VeiculoViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return VeiculoViewHolder(view)
    }
    override fun onBindViewHolder(holder: VeiculoViewHolder, position: Int) {
        val veiculo = getItem(position)
        holder.bind(veiculo, openVeiculoDetailView)
    }
    companion object: DiffUtil.ItemCallback<Veiculo>(){
        override fun areItemsTheSame(oldItem: Veiculo, newItem: Veiculo): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Veiculo, newItem: Veiculo): Boolean {
            return oldItem.brand == newItem.brand &&
                    oldItem.model == newItem.model &&
                    oldItem.year == newItem.year &&
                    oldItem.plate == newItem.plate
        }
    }
}


class VeiculoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val tvitemname = view.findViewById<TextView>(R.id.tv_name)
    private val tvitemregis = view.findViewById<TextView>(R.id.tv_registration)
    private val tvteleph = view.findViewById<TextView>(R.id.tv_telephone)
    private val tvaddress = view.findViewById<TextView>(R.id.tv_address)

    fun bind(veiculo: Veiculo, openTaskDetailView: (veiculo: Veiculo) -> Unit) {

        tvitemname.text = veiculo.brand
        tvitemregis.text = veiculo.model
        tvteleph.text = veiculo.year
        tvaddress.text = veiculo.plate

        view.setOnClickListener {

            openTaskDetailView.invoke(veiculo)
        }}}
