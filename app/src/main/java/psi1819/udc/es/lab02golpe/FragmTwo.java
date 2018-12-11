package psi1819.udc.es.lab02golpe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class FragmTwo extends Fragment {

   // private OnFragmentInteractionListener mListener;
    WebView webView;

    public FragmTwo() {
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
        return inflater.inflate(R.layout.fragment_fragm_two, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "[FRAGMENT 2]: onResume()");
        webView = (WebView)getView().findViewById(R.id.webView);
        webView.setWebViewClient(new Callback()); // Book W-M Lee pag. 290-5
    }
    private class Callback extends WebViewClient {// Book W-M Lee pag. 290-5
        @Override
        public boolean shouldOverrideUrlLoading (WebView view, String url) {
            return false; }
    }
    public void load(String url){webView.loadUrl(url);}//clean if url is empty

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof  OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
     //   mListener = null;
    }

    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/

}
