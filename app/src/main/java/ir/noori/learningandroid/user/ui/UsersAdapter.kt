package ir.noori.learningandroid.user.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.noori.learningandroid.MyApplication
import ir.noori.learningandroid.databinding.ItemUserBinding
import ir.noori.learningandroid.user.data.entity.UsersModel

class UsersAdapter(val list : ArrayList<UsersModel>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(MyApplication.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val user = list[position]

        holder.binding.txtName.text = user.name
        holder.binding.txtEmail.text = user.email

    }
}