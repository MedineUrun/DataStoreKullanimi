package com.example.datastorekullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.datastorekullanimi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appPref = AppPref(this)

        CoroutineScope(Dispatchers.Main).launch {
            //kayıt işlemi
            appPref.kayitAd("Ahmet")
            appPref.kayitYas(23)
            appPref.kayitBoy(1.78)
            appPref.kayitBekar(false)

            val liste = HashSet<String>()
            liste.add("Mehmet")
            liste.add("Zeynep")

            appPref.kayitListe(liste)

            //silme işlemi
            //appPref.silAd()

            //okuma işlemi
            val gelenAd = appPref.okuAd()
            val gelenYas = appPref.okuYas()
            val gelenBoy = appPref.okuBoy()
            val gelenBekar = appPref.okuBekar()

            Log.e("Gelen Ad", gelenAd)
            Log.e("Gelen Yaş", gelenYas.toString())
            Log.e("Gelen Boy", gelenBoy.toString())
            Log.e("Gelen Bekar", gelenBekar.toString())

            val gelenListe = appPref.okuListe()

            if (gelenListe != null){
                for (a in gelenListe){
                    Log.e("Gelen Arkadaş", a)
                }
            }

            //sayac uygulaması
            //en son değeri almak için okuma yapalım
            var gelenSayac = appPref.okuSayac()

            gelenSayac = gelenSayac + 1

            //bir sonraki seferde okumak için kayıt edeceğiz
            appPref.kayitSayac(gelenSayac)

            binding.textViewSayac.text = gelenSayac.toString()

        }
    }
}