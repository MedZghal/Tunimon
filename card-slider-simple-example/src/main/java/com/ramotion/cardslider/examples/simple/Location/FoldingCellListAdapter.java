package com.ramotion.cardslider.examples.simple.Location;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ramotion.cardslider.examples.simple.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

import androidx.annotation.NonNull;

    /**
     * Simple example of ListAdapter for using with Folding Cell
     * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
     */
    @SuppressWarnings({"WeakerAccess", "unused"})
    public class FoldingCellListAdapter extends ArrayAdapter<Sites> {

        private HashSet<Integer> unfoldedIndexes = new HashSet<>();
        private View.OnClickListener defaultRequestBtnClickListener;

        public FoldingCellListAdapter(Context context, List<Sites> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            // get item for selected view
            Sites item = getItem(position);
            // if cell is exists - reuse it, if not - create the new one from resource
            FoldingCell cell = (FoldingCell) convertView;
            ViewHolder viewHolder;
            if (cell == null) {
                viewHolder = new ViewHolder();
                LayoutInflater vi = LayoutInflater.from(getContext());
                cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
                // binding view parts to view holder

                viewHolder.image= cell.findViewById(R.id.img);
                viewHolder.title_from_address= cell.findViewById(R.id.title_from_address);
                viewHolder.sortdesc= cell.findViewById(R.id.sortdesc);
                viewHolder.gouv= cell.findViewById(R.id.gouv);
                viewHolder.ville= cell.findViewById(R.id.ville);
                viewHolder.nom_desc= cell.findViewById(R.id.nom_desc);
                viewHolder.head_image= cell.findViewById(R.id.head_image);
                viewHolder.gouv_desc= cell.findViewById(R.id.gouv_desc);
                viewHolder.ville_desc = cell.findViewById(R.id.ville_desc);
                viewHolder.desc= cell.findViewById(R.id.desc);
                viewHolder.localiser= cell.findViewById(R.id.localiser);

                viewHolder.localiser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        String country = viewHolder.title_from_address.getText().toString();
                        Log.e("clicked",country);

                        if (country.equals("Médina de Tunis"))
                            intent.setData(Uri.parse("geo:36.7949999,10.0732369?q=Tunis, Médina"));
                        else
                            if (country.equals("Site archéologique de Carthage"))
                                intent.setData(Uri.parse("geo:36.8702624,10.3160516?q=Site archéologique de Carthage"));
                        else
                            if (country.equals("Amphithéâtre d'El Jem"))
                                intent.setData(Uri.parse("geo:35.2966772,10.7046842?q=Amphithéâtre El Djem, El Jem"));
                        else
                            if (country.equals("Parc national de l'Ichkeul"))
                                intent.setData(Uri.parse("geo:37.1598062,9.5960812?q=Lac Ichkeul"));
                        else
                            if (country.equals("Cité punique de Kerkouane et sa nécropole"))
                                intent.setData(Uri.parse("geo:36.9604709,11.0711717?q=Kerkouane"));
                        else
                            if (viewHolder.title_from_address.equals("Médina de Sousse"))
                                intent.setData(Uri.parse("geo:35.8248813,10.6334673?q=Médina de Sousse, Sousse"));
                        else
                            if (country.equals("Kairouan"))
                                intent.setData(Uri.parse("geo:35.6697142,10.0720715?q=OKBA mosquée, Kairouan"));
                        else
                            if (country.equals("Dougga"))
                                intent.setData(Uri.parse("geo:36.4232996,9.2143227?q=Dougga"));
                        else
                            intent.setData(Uri.parse("geo:36.8702624,10.3160516?q=Site archéologique de Carthage"));




                        getContext().startActivity(intent);
                    }
                });

                cell.setTag(viewHolder);
            } else {
                // for existing cell set valid valid state(without animation)
                if (unfoldedIndexes.contains(position)) {
                    cell.unfold(true);
                } else {
                    cell.fold(true);
                }
                viewHolder = (ViewHolder) cell.getTag();
            }

            if (null == item)
                return cell;

            // bind data from selected element to view through view holder
            viewHolder.image.setBackgroundResource(item.getImage());
            viewHolder.title_from_address.setText(item.getNom());
            viewHolder.sortdesc.setText(item.getShort_desc());
            viewHolder.gouv.setText(item.getGouv());
            viewHolder.ville.setText(item.getVille());
            viewHolder.nom_desc.setText(item.getNom());
            viewHolder.head_image.setImageResource(item.getImage());
            viewHolder.gouv_desc.setText(item.getGouv());
            viewHolder.ville_desc.setText(item.getVille());
            viewHolder.desc.setText(item.getDesc());

            // set custom btn handler for list item from that item
           // if (item.getRequestBtnClickListener() != null) {
             //   viewHolder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
           // } else {
                // (optionally) add "default" handler if no handler found in item
               // viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
           // }

            return cell;
        }

        // simple methods for register cell state changes
        public void registerToggle(int position) {
            if (unfoldedIndexes.contains(position))
                registerFold(position);
            else
                registerUnfold(position);
        }

        public void registerFold(int position) {
            unfoldedIndexes.remove(position);
        }

        public void registerUnfold(int position) {
            unfoldedIndexes.add(position);
        }

        public View.OnClickListener getDefaultRequestBtnClickListener() {
            return defaultRequestBtnClickListener;
        }

        public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
            this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
        }

        // View lookup cache
        private static class ViewHolder {
            RelativeLayout image;
            TextView title_from_address;
            TextView sortdesc;
            TextView gouv;
            TextView ville;
            TextView nom_desc;
            ImageView head_image;
            TextView gouv_desc;
            TextView ville_desc ;
            TextView desc;
            TextView localiser;
        }
    }
