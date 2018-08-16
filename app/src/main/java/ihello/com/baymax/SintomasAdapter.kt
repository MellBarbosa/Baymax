package ihello.com.baymax

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ihello.com.baymax.Model.Sintoma
import ihello.com.baymax.Model.Sintomas
import ihello.com.baymax.R.id.*
import kotlinx.android.synthetic.main.activity_sintomas_adapter.view.*

class SintomasAdapter(var items: Sintomas) : RecyclerView.Adapter<SintomasAdapter.ViewHolder>(), Filterable {

    private var itensFiltrados = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_sintomas_adapter, parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position != 0){
            if(itensFiltrados[position].Regiao != itensFiltrados[position - 1].Regiao)
                holder.tvRegiao!!.visibility = View.VISIBLE
            else
                holder.tvRegiao!!.visibility = View.GONE
            }
        else
            holder.tvRegiao!!.visibility = View.VISIBLE

        holder.cbSelecionado.isChecked = itensFiltrados[position].Selecionado

        holder.cbSelecionado.setOnClickListener {
            itensFiltrados[position].Selecionado = !itensFiltrados[position].Selecionado
        }

        val sintomas = itensFiltrados[position]
        holder.tvSintoma?.text = sintomas.Sintoma
        holder.tvRegiao?.text = sintomas.Regiao
    }

    override fun getItemCount(): Int {
        return itensFiltrados.size
    }

    override fun getFilter(): Filter {

        return object : Filter(){
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val pesquisa = constraint.toString().toLowerCase()
                var resultado = Sintomas()

                if (pesquisa.isBlank()) {
                    resultado = items
                }else{
                    val filtrados = items.filter { it.Sintoma.toLowerCase().contains(pesquisa) }
                    resultado.addAll(filtrados)
                }

                val filterResults = FilterResults()
                filterResults.values = resultado
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                itensFiltrados = results.values as? Sintomas ?: return
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        var tvSintoma : TextView? = row?.findViewById(R.id.tvSintoma)
        var tvRegiao : TextView? = row?.findViewById(R.id.tvRegiao)
        var cbSelecionado : CheckBox = row?.findViewById(R.id.cbSelecionado)
    }
}










