package com.galo.mapas;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // tipo de mapa exibido
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        // localizando o parque ibirapuera
        LatLng ibirapuera = new LatLng(-23.587097, -46.657635);

        // Desenhando uma forma circular no mapa.
       /*CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(ibirapuera); // diz onde será o centro no circulo
        circleOptions.radius(500); //em metros
        //circleOptions.fillColor(Color.BLUE); // definindo uma cor
        circleOptions.strokeWidth(1); // especura da borda
        circleOptions.strokeColor(Color.GREEN);
        circleOptions.fillColor(Color.argb(50,255,153,0)); // definindo uma cor especifica
        mMap.addCircle(circleOptions);
        */

        // definindo uma forma aleatória a partir da latitude e longitude do map
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-23.586332, -46.658754));
        polygonOptions.add(new LatLng(-23.585615, -46.656662));
        polygonOptions.add(new LatLng(-23.587158, -46.657037));
        polygonOptions.strokeWidth(3); // espessura da borda
        polygonOptions.strokeColor(Color.BLUE);
        mMap.addPolygon(polygonOptions);

        // Adicionadno evento de clique no Mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                // Exibindo a msg na tela
                Toast.makeText(MapsActivity.this,
                        "Lat:" + latitude + " long:" + longitude,
                        Toast.LENGTH_LONG).show();

                // colocando o icone loja onde o usuário clicar
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Local")
                        .snippet("Descrição")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_loja)));
            }
        });

        mMap.addMarker(new MarkerOptions().position(ibirapuera).title("Parque Ibirapuera")
                // definindo outra cor para o icone
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                // adicionando icone personalizado
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.carro)));
        // 2.0 ate 21.0
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ibirapuera, 16));
    }
}