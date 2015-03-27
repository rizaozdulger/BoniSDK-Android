package me.boni.sdk.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.boni.sdk.BoniBeaconListener;
import me.boni.sdk.BoniBeaconManager;
import me.boni.sdk.entities.BoniBeacon;
import me.boni.sdk.entities.BoniRegion;

public class MainActivity extends Activity {

    private static final String BONI_UUID = "538C5AB24DBA43BA53BE4EB041AD41B0";

    Context context;
    BeaconListAdapter adapter;
    BoniRegion region;
    List<BoniBeacon> beacons = new ArrayList<BoniBeacon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        ListView listView = (ListView)findViewById(R.id.listViewBeacon);
        adapter = new BeaconListAdapter(context, beacons);
        listView.setAdapter(adapter);

        region = new BoniRegion("test", BONI_UUID, null, null);
        BoniBeaconManager.getInstance(context).registerRangingListener(new BoniBeaconListener() {
            @Override
            public void onNearestRangedBeacon(BoniRegion boniRegion, BoniBeacon boniBeacon) {

            }

            @Override
            public void onRangedBeacons(BoniRegion boniRegion, final List<BoniBeacon> boniBeacons) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        beacons.clear();
                        beacons.addAll(boniBeacons);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (region != null) {
            BoniBeaconManager.getInstance(context).startRangingBeacons(region);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (region != null) {
            BoniBeaconManager.getInstance(context).stopRangingBeacons(region);
        }
    }

    private class BeaconListAdapter extends ArrayAdapter<BoniBeacon> {
        private final Context context;
        private final List<BoniBeacon> values;

        public BeaconListAdapter(Context context, List<BoniBeacon> values) {
            super(context, R.layout.list_item, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_item, parent, false);

            TextView tvName = (TextView)view.findViewById(R.id.tvName);
            TextView tvMajor = (TextView)view.findViewById(R.id.tvMajor);
            TextView tvMinor = (TextView)view.findViewById(R.id.tvMinor);
            TextView tvRssi = (TextView)view.findViewById(R.id.tvRssi);

            BoniBeacon boniBeacon = values.get(position);

            tvName.setText(boniBeacon.getDeviceName());
            tvMajor.setText(boniBeacon.getMajor());
            tvMinor.setText(boniBeacon.getMinor());
            tvRssi.setText(String.valueOf(boniBeacon.getRssi()));

            return view;
        }
    }
}
