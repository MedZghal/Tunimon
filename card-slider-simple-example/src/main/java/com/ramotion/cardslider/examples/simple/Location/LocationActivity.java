package com.ramotion.cardslider.examples.simple.Location;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ramotion.cardslider.examples.simple.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {

    Sites Medina_tunis,archeologique,jem,ichkeul,cite_punique,Medina_sousse,kairouan,dougga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Medina_tunis= new Sites("Médina de Tunis",
                                "La médina de Tunis est une médina tunisienne, cœur historique de Tunis, inscrite depuis 1979 au patrimoine mondial de l'Unesco",
                                "Petite bourgade, Tunis devient l'une des villes les plus importantes du monde islamique sous le règne des Almohades et des Hafsides (XIIe-XVIe siècles). Elle compte quelque 700 monuments (palais, mosquées, mausolées, médersas, etc.) qui témoignent de ce riche passé historique.",
                                "Tunis",
                                "Tunis",
                                 R.drawable.tunis,
                                 R.drawable.map_jem);
        archeologique= new Sites("Site archéologique de Carthage",
                "Le site archéologique de Carthage est un site dispersé dans la ville actuelle de Carthage (Tunisie) et classé au patrimoine mondial de l'Unesco depuis 1979.",
                "Fondée au ixe siècle av. J.-C., la cité de Carthage établit un empire commercial sur le bassin méditerranéen et devient le siège d'une brillante civilisation. Au terme des trois guerres puniques, elle est détruite en 146 av. J.-C. avant de renaître de ses cendres sous l'impulsion de l'Empire romain.",
                "Tunis",
                "Tunis",
                R.drawable.archeologique,
                R.drawable.map_jem);
        jem= new Sites("Amphithéâtre d'El Jem",
                "un amphithéâtre romain situé dans l'actuelle ville tunisienne d'El Jem.",
                "L'amphithéâtre d'El Jem (arabe : مسرح الجم), aussi appelé Colisée de Thysdrus, est un amphithéâtre romain situé dans l'actuelle ville tunisienne d'El Jem, l'antique Thysdrus de la province romaine d'Afrique. ",
                "Mahdia",
                "El Jem",
                R.drawable.jem,
                R.drawable.map_jem);
        ichkeul= new Sites("Parc national de l'Ichkeul",
                "Le lac Ichkeul et les zones humides environnantes constituent un important relais pour des oiseaux migrateurs. Ceux-ci s'y arrêtent pour se nourrir et y nicher.",
                "Le parc national de l'Ichkeul (arabe : المحمية الوطنية إشكل) est un site naturel situé au nord de la Tunisie, plus précisément à 25 kilomètres au sud-est de Bizerte et à quinze kilomètres des villes de Menzel Bourguiba et Mateur, sur le territoire du gouvernorat de Bizerte. Il est géré par le ministère de l'Agriculture, plus précisément par la sous-direction de la chasse et des parcs nationaux rattachée à la direction générale des forêts.",
                "Bizerte",
                "Bizerte",
                R.drawable.ichkeul,
                R.drawable.map_jem);
        cite_punique= new Sites("Cité punique de Kerkouane et sa nécropole",
                "Kerkouane (arabe : كركوان) est un site antique tunisien situé sur la côte orientale de la péninsule du cap Bon, à six kilomètres au nord de Hammam Ghezèze.",
                "L'ancienne cité phénicienne a été abandonnée, sans doute pendant la Première guerre punique (vers 250 av. J.-C.). Elle offre de ce fait les seuls vestiges d'une ville phénico-punique connus à ce jour.",
                "Nabeul",
                "Kerkouane",
                R.drawable.kerkouane,
                R.drawable.map_jem);
        Medina_sousse= new Sites("Médina de Sousse",
                "La médina de Sousse est une médina tunisienne, cœur historique de Sousse, inscrite depuis le 9 décembre 19881 au patrimoine mondial de l'Unesco",
                "Sousse est un exemple d'une ville des premiers siècles de l'islam. Avec sa kasbah, ses remparts, sa médina et sa Grande Mosquée ou son ribat, elle constituait l'un des éléments d'un système de défense côtier.",
                "Sousse",
                "Sousse",
                R.drawable.soussemad,
                R.drawable.map_sousse);
        kairouan= new Sites("Kairouan",
                "Kairouan prospère sous les Aghlabides (ixe siècle).Elle reste considérée comme la première ville sainte du Maghreb. Son patrimoine architectural comprend notamment la Grande Mosquée et la mosquée des Trois Portes.",
                "Kairouan (arabe : قيروان) est une ville du centre de la Tunisie et le chef-lieu du gouvernorat du même nom.Fondée en 670, elle se situe à 150 kilomètres au sud-ouest de Tunis et cinquante kilomètres à l'ouest de Sousse. Peuplée de 139 070 habitants en 20141, elle est souvent désignée comme la quatrième ville sainte (ou sacrée) de l'islam et la première ville sainte du Maghreb",
                "Kairouan",
                "Kairouan",
                R.drawable.okba,
                R.drawable.kerkouane);
        dougga= new Sites("Dougga",
                "Dougga (دڨة ) ou Thugga est un site archéologique situé dans la délégation de Téboursouk au Nord-Ouest de la Tunisie.",
                "Construite sur une colline surplombant une plaine connue pour sa fertilité, la cité est d'abord la capitale d'un État libyco-punique. Après avoir prospéré sous les dominations romaine puis byzantine, elle décline inexorablement. Ses ruines témoignent des ressources dont disposait une petite ville située aux frontières de l'Empire romain",
                "Béja",
                "Dougga",
                R.drawable.dougga,
                R.drawable.map_jem);

// get our list view
        ListView theListView = findViewById(R.id.mainListView);

        // prepare elements to display
        ArrayList<Sites> items = new ArrayList<>();
        items.add(Medina_tunis);
        items.add(archeologique);
        items.add(jem);
        items.add(ichkeul);
        items.add(cite_punique);
        items.add(Medina_sousse);
        items.add(kairouan);
        items.add(dougga);

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set elements to adapter
        theListView.setAdapter(adapter);

        // set on click event listener to list view
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });

    }
}