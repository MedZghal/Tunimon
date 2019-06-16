package com.ramotion.cardslider.examples.simple;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;
import com.ramotion.cardslider.examples.simple.cards.SliderAdapter;
import com.ramotion.cardslider.examples.simple.detection.tflite.Classifier;
import com.ramotion.cardslider.examples.simple.utils.DecodeBitmapTask;
import com.ramotion.cardslider.examples.simple.detection.tflite.Classifier;
import java.util.List;

import androidx.annotation.StyleRes;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

@SuppressLint("ValidFragment")
public class DescriptionFragment extends DialogFragment {


    private final int[] bourgibas = {R.drawable.bourgiba1, R.drawable.bourgiba2, R.drawable.bourgiba3, R.drawable.bourgiba4};
    private final int[] diwans = {R.drawable.diwan1, R.drawable.diwan2, R.drawable.diwan3, R.drawable.diwan4,R.drawable.diwan5};
    private final int[] avenues = {R.drawable.avenue1, R.drawable.avenue2, R.drawable.avenue3, R.drawable.avenue4,R.drawable.avenue5,R.drawable.avenue6};

    private final SliderAdapter sliderAdapterHB = new SliderAdapter(bourgibas, 20, new DescriptionFragment.OnCardClickListener());
    private final SliderAdapter sliderAdapterDW = new SliderAdapter(diwans, 20, new DescriptionFragment.OnCardClickListener());
    private final SliderAdapter sliderAdapterAV = new SliderAdapter(avenues, 20, new DescriptionFragment.OnCardClickListener());


    private int currentPosition;

    private CardSliderLayoutManager layoutManger;
    private RecyclerView recyclerView;
    TextView nomDetection ;
    TextView description,county ;

    String DetectionString="";

    @SuppressLint("ValidFragment")
    public DescriptionFragment(String results) {
        DetectionString=results;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_description, container, false);
        nomDetection = v.findViewById(R.id.ts_temperature);
        description = v.findViewById(R.id.description);
        county = v.findViewById(R.id.countryname);

        switch (DetectionString){
            case "avenue":
                nomDetection.setText("L'HORLOGE DE L'ABENUE");
                description.setText(getString(R.string.dscpav));
                county.setText("Tunis");
                initRecyclerView(v,sliderAdapterAV);
                break;
            case "diwan":
                nomDetection.setText("PORTE DIWAN");
                county.setText("Sfax");
                description.setText("  Bab Diwan (arabe : باب الديوان), aussi appelée Bab El Bhar (باب بحر soit « Porte de la mer »), est l'une des portes de la médina de Sfax, aménagée au milieu de la face sud des remparts entre Bab El Kasbah à l'ouest et Bab Borj Ennar à l'est" );
                initRecyclerView(v,sliderAdapterDW);
                break;
            case "bourgiba":
                nomDetection.setText("HBIB BOURGIBA");
                county.setText("Sfax");
                description.setText(getString(R.string.dscphb));
                initRecyclerView(v,sliderAdapterHB);
                break;
        }


        return  v;
    }

    private void initRecyclerView(View v,SliderAdapter sliderAdapter) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(sliderAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange();
                }
            }
        });

        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();

        new CardSnapHelper().attachToRecyclerView(recyclerView);
    }

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        int animH[] = new int[] {R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[] {R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        currentPosition = pos;
    }

    private class OnCardClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final CardSliderLayoutManager lm =  (CardSliderLayoutManager) recyclerView.getLayoutManager();

            if (lm.isSmoothScrolling()) {
                return;
            }

            final int activeCardPosition = lm.getActiveCardPosition();
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return;
            }

            final int clickedPosition = recyclerView.getChildAdapterPosition(view);
            if (clickedPosition == activeCardPosition) {
                final Intent intent = new Intent(getActivity(), DetailsActivity.class);

                switch (DetectionString){
                    case "avenue":
                        intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, avenues[activeCardPosition % avenues.length]);
                        break;
                    case "diwan":
                        intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, diwans[activeCardPosition % diwans.length]);
                        break;
                    case "bourgiba":
                        intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, bourgibas[activeCardPosition % bourgibas.length]);
                        break;
                }
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(getActivity(), sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }


}
