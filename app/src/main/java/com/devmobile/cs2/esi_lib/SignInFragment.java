package com.devmobile.cs2.esi_lib;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SignInFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        Button btn=(Button)rootView.findViewById(R.id.button);
        final Intent intent=new Intent(inflater.getContext(),com.devmobile.cs2.esi_lib.ListeLivres.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
		return rootView;
	}
}
