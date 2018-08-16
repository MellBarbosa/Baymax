package ihello.com.baymax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import ihello.com.baymax.Model.Doencas

class ResultadoAdapter(var doencas: Doencas) : RecyclerView.Adapter<ResultadoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultadoAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_resultado_adapter, parent,false)

        return ResultadoAdapter.ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return doencas.size
    }

    override fun onBindViewHolder(holder: ResultadoAdapter.ViewHolder, position: Int) {
        val doc = doencas[position]
        holder.tvDoenca?.text = doc.Nome
        holder.tvDescricao?.text = doc.Descricao
        holder.tvCid?.text = doc.Cid
        holder.tvInstrucoes?.text = doc.Instrucoes
    }


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        var tvDoenca : TextView? = row?.findViewById(R.id.tvDoenca)
        var tvDescricao : TextView? = row?.findViewById(R.id.tvDescricao)
        var tvCid : TextView? = row?.findViewById(R.id.tvCid)
        var tvInstrucoes : TextView?= row?.findViewById(R.id.tvInstrucoes)
    }

}
