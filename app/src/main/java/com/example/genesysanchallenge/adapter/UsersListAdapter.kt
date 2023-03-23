package com.example.genesysanchallenge.adapter



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.GenesysAndroidChallenge.UsersDetailsFragment
import com.example.genesysanchallenge.databinding.CardViewLayoutBinding
import com.example.genesysanchallenge.model.results
import com.example.genesysanchallenge.viewmodel.UsersViewModel


class UsersListAdapter(val activity: AppCompatActivity,val viewModel: UsersViewModel) : RecyclerView.Adapter<UsersListAdapter.CardItemViewHolder>() {
    private var usersList: List<results>? = null

    fun setUsersLists(userList: List<results>) {
        this.usersList = userList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.CardItemViewHolder {
        val binding =
            CardViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersListAdapter.CardItemViewHolder, position: Int) {
        holder.bind(usersList?.get(position)!!,position, activity)
    }

    override fun getItemCount(): Int {
        if (usersList != null)
            return usersList?.size!!
        else return 0
    }


    inner class CardItemViewHolder(val binding: CardViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: results , position: Int, activity: AppCompatActivity) {

            Glide.with(activity).load(data.image?.thumbnail).into(binding.thumbnail)
            binding.fullname.text =data.name?.title+ " "+ data.name?.firstName + " "+data.name?.lastName
            binding.locationPLH.text =
                data?.location?.city + "," + data?.location?.state + "," + data?.location?.country
            binding.clTop.setOnClickListener {
                binding.clTop.setOnClickListener {
                    viewModel.select(data)
                    UsersDetailsFragment.newInstance().show(activity.supportFragmentManager, "UsersDetailsDialogFragment")

                }

            }

        }

    }

    fun setFilterdList(filteredList:List<results>){
        this.usersList = filteredList

    }

}
