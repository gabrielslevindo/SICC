package com.example.cadastrodeclientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastrodeclientes.Data.Aluno
import kotlin.reflect.KFunction1

class AdapterAluno(

     private val openAlunoDetailView: KFunction1<Aluno, Unit>
 ) :
     ListAdapter<Aluno, AlunoViewHolder>(AdapterAluno){

    private  var listAluno : List<Aluno> = emptyList()

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
         val view: View = LayoutInflater
             .from(parent.context)
             .inflate(R.layout.item_list, parent, false)
         return AlunoViewHolder(view)
     }
     override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
         val aluno = getItem(position)
         holder.bind(aluno, openAlunoDetailView)
     }
     companion object: DiffUtil.ItemCallback<Aluno>(){
         override fun areItemsTheSame(oldItem: Aluno, newItem: Aluno): Boolean {
             return oldItem == newItem
         }
         override fun areContentsTheSame(oldItem: Aluno, newItem: Aluno): Boolean {
             return oldItem.name == newItem.name &&
                     oldItem.registration == newItem.registration &&
                     oldItem.address == newItem.address &&
                     oldItem.telephone == newItem.telephone
         }
     }
 }


class AlunoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val tvitemname = view.findViewById<TextView>(R.id.tv_name)
    private val tvitemregis = view.findViewById<TextView>(R.id.tv_registration)
    private val tvteleph = view.findViewById<TextView>(R.id.tv_telephone)
    private val tvaddress = view.findViewById<TextView>(R.id.tv_address)

    fun bind(aluno: Aluno, openTaskDetailView:(aluno: Aluno) -> Unit) {

        tvitemname.text = aluno.name
        tvitemregis.text = aluno.registration
        tvteleph.text = aluno.telephone
        tvaddress.text = aluno.address

        view.setOnClickListener {

            openTaskDetailView.invoke(aluno)
        }
    }
}