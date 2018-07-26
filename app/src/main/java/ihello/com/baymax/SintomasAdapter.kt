package ihello.com.baymax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import ihello.com.baymax.Model.Sintoma
import ihello.com.baymax.Model.Sintomas
import kotlinx.android.synthetic.main.activity_sintomas_adapter.view.*

class SintomasAdapter(var items: Sintomas, val sintomas: Sintomas) : RecyclerView.Adapter<SintomasAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_sintomas_adapter, parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.cbSelecionado.tag = position
        holder.cbSelecionado.isChecked = sintomas[position].Selecionado

        holder.cbSelecionado.setOnClickListener {
            val pos = holder.cbSelecionado.tag as Int
            sintomas[pos].Selecionado = !sintomas[pos].Selecionado
        }

        var sintomas = items[position]
        holder?.tvSintoma?.text = sintomas.Sintoma
        holder?.tvRegiao?.text = sintomas.Regiao
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        var tvSintoma : TextView? = row?.findViewById(R.id.tvSintoma)
        var tvRegiao : TextView? = row?.findViewById(R.id.tvRegiao)
        var cbSelecionado : CheckBox = row?.findViewById(R.id.cbSelecionado)
    }
}










