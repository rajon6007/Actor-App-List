package com.example.actorapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actorapp.Adapter.HeroAdapter
import com.example.actorapp.Model.Hero
import com.example.actorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var heroAdapter: HeroAdapter
    val hero= ArrayList<Hero>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.heroRv.layoutManager=LinearLayoutManager(this)

        hero.add(Hero("Vijay Thalapathi","GOAT","$14M",R.drawable.vijay,"A south Indian Actor"))
        hero.add(Hero("Allu Arjun","Pushpa","$15M",R.drawable.allu,"A south Indian Actor"))
        hero.add(Hero("Dhanush","Maari","$20M",R.drawable.dhanush,"A south Indian Actor"))
        hero.add(Hero("Mahesh Babu","Srimanthudu","$16M",R.drawable.mahesh,"A south Indian Actor"))
        hero.add(Hero("Ajit Kumar","Vedalam","$14M",R.drawable.ajit,"A south Indian Actor"))
        hero.add(Hero("NTR jr","RRR","$30M",R.drawable.ntr,"A south Indian Actor"))
        hero.add(Hero("Naga Chaitanya","The Nagesh","$20M",R.drawable.naga,"A south Indian Actor"))
        hero.add(Hero("Ram Charan","RRR","$14M",R.drawable.ram,"A south Indian Actor"))
        hero.add(Hero("pawan kalyan","Ismart Shankar","$14M",R.drawable.pawan,"A south Indian Actor"))
        hero.add(Hero("Suriya  ","Singhum","$14M",R.drawable.suriya,"A south Indian Actor"))

        heroAdapter=HeroAdapter(hero)
        binding.heroRv.adapter=heroAdapter

        heroAdapter.onClick={
            val intent =Intent(this,DetailsActivity::class.java)
            intent.putExtra("name",it.heroName)
            intent.putExtra("title",it.filmTitle)
            intent.putExtra("fees",it.heroFees)
            intent.putExtra("about",it.abt)
            intent.putExtra("image",it.heroImg)
            startActivity(intent)
        }
        binding.addBtn.setOnClickListener {
            showHeroAddDialog()

        }
        val itemTouchHelper =ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                hero.removeAt(viewHolder.adapterPosition)
                heroAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.heroRv)

    }
    private fun showHeroAddDialog(){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.addhero_activity,null)
        val nameEt = dialogView.findViewById<EditText>(R.id.HeroNameEt)
        val titleEt = dialogView.findViewById<EditText>(R.id.MovieNameEt)
        val feesEt = dialogView.findViewById<EditText>(R.id.FeesEt)
        val heroAbt = dialogView.findViewById<EditText>(R.id.AboutEt)

       AlertDialog.Builder(this)
           .setTitle("Add hero")
           .setView(dialogView)
           .setPositiveButton("Add") { _, _ ->
               val name = nameEt.text.toString()
               val title = titleEt.text.toString()
               val fees = feesEt.text.toString()
               val abt = heroAbt.text.toString()
               val img = R.drawable.vijay
               hero.add(Hero(name,title,fees,img,abt))
               heroAdapter.notifyItemInserted(hero.size-1)
           }
           .setNegativeButton("Cancel",null)
           .show()

    }
}