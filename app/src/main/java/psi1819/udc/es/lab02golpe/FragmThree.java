package psi1819.udc.es.lab02golpe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class FragmThree extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TextView tv_f3_id;
    private TextView tv_f3;
    private ToggleButton tgbut_disable;
    static final String NAME = "name";
    static final String VALUE = "value";
    static final String COUNTER = "counter";

    public FragmThree() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragm_three, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        tv_f3 = (TextView) getView().findViewById(R.id.tv_f3);
        tv_f3_id = (TextView) getView().findViewById(R.id.tv_f3_id);
        if(args!=null){
            tv_f3.setText(args.getString(NAME));
            tv_f3.setTextSize(args.getInt(VALUE));
            tv_f3_id.setText(String.valueOf(args.getInt(COUNTER)));
        }
        tgbut_disable = (ToggleButton) getView().findViewById(R.id.tgbut_disable);
        tgbut_disable.setChecked(false);
        tgbut_disable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mListener.onChecked(b);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onChecked(boolean b);
    }
}
