package com.example.anirudh.djstrike;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Appliance> applianceList;
    ApplianceAdapter applianceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        initCollapsingToolbar();
        recyclerView = findViewById(R.id.recycler_view);

        applianceList = new ArrayList<>();

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(position ==0) {
                    Intent i = new Intent(view.getContext(), LightActivity.class);
                    startActivity(i);
                }
                if(position ==1) {
                    Intent i = new Intent(view.getContext(), Fan_activity.class);
                    startActivity(i);
                }
                if(position ==2) {
                    Intent i = new Intent(view.getContext(), tv_activity.class);
                    startActivity(i);
                }
                if(position ==3) {
                    Intent i = new Intent(view.getContext(), pc_activity.class);
                    startActivity(i);
                }
                if(position ==4) {
                    Intent i = new Intent(view.getContext(), ac_activity.class);
                    startActivity(i);
                }


            }
        };

        applianceAdapter = new ApplianceAdapter(this,applianceList,listener);
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(applianceAdapter);

        prepareAppliances();

        try {
            Glide.with(this).load(R.drawable.city).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void prepareAppliances(){
        int covers[] = new int[]{
                R.drawable.fbi,
                R.drawable.fen,
                R.drawable.tv1,
                R.drawable.pc1,
                R.drawable.acacac,
        };

        Appliance a = new Appliance("Lights", "4", covers[0]);
        applianceList.add(a);

        a = new Appliance("Fans", "3", covers[1]);
        applianceList.add(a);

        a = new Appliance("Television", "2", covers[2]);
        applianceList.add(a);

        a = new Appliance("Desktop PC", "1", covers[3]);
        applianceList.add(a);

        a = new Appliance("Air Conditioning", "1", covers[4]);
        applianceList.add(a);
        applianceAdapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}


