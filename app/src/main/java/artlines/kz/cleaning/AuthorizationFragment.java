package artlines.kz.cleaning;

import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class AuthorizationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auth_fragment, container, false);
        ImageView back= (ImageView) view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        TextView registration= (TextView) view.findViewById(R.id.register);
        TextView call= (TextView) view.findViewById(R.id.call);
        call.setPaintFlags(call.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        registration.setPaintFlags(registration.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        return view;
    }
}
