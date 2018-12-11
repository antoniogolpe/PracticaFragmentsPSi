package psi1819.udc.es.lab02golpe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;


public class FragmOne extends Fragment {


    private onArticleSelectedListener mListener;
    private Button but_url;
    private Spinner sp_url;
    private SeekBar sb_f1;
    private EditText et_f1;
    private Button but_clear;

    public FragmOne() {
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
        return inflater.inflate(R.layout.fragment_fragm_one, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        but_url = (Button) getView().findViewById(R.id.but_url);
        but_url.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                mListener.onArticleSelected(sp_url.getSelectedItem().toString()); // fragment delivers data
            }
        });

        sp_url = (Spinner) getView().findViewById(R.id.sp_url);
        String []opciones={"","https://www.google.es/","https://m.ascodevida.com/"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, opciones);
        sp_url.setAdapter(adapter);

        et_f1 = (EditText) getView().findViewById(R.id.et_f1);

        sb_f1 = (SeekBar) getView().findViewById(R.id.sb_f1);
        sb_f1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(et_f1.length()!=0)
                    mListener.onSeekBarChange(et_f1.getText().toString(),i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        but_clear = (Button) getView().findViewById(R.id.but_clear);
        but_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                mListener.onClearSelected();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof  onArticleSelectedListener) {
            mListener = (onArticleSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface onArticleSelectedListener {
        // TODO: Update argument type and name
        void onArticleSelected(String name);
        void onClearSelected();
        void onSeekBarChange(String name, int value);
    }



}
