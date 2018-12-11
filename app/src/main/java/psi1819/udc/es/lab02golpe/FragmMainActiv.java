package psi1819.udc.es.lab02golpe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmMainActiv extends AppCompatActivity  implements FragmOne.onArticleSelectedListener,
        FragmThree.OnFragmentInteractionListener {

    private FragmThree fragmThree;
    private Button exit;
    static final String NAME = "name";
    static final String VALUE = "value";
    static final String COUNTER = "counter";
    private int count_fragm_three=0;
    private boolean activo=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragm_main);

        exit = (Button) findViewById(R.id.but_exit);

        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(FragmMainActiv.this);
                dialogo.setTitle("Salir");
                final EditText et = new EditText(FragmMainActiv.this);
                et.setText("");
                dialogo.setView(et);
                dialogo.setCancelable(false);

                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {
                        FragmMainActiv.this.finish();
                    }
                });

                dialogo.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Log.d("Exit Cancel", et.getText().toString());
                        Toast notificacion= Toast.makeText(FragmMainActiv.this,et.getText().toString(),Toast.LENGTH_LONG);
                        notificacion.show();
                    }
                });

                dialogo.show();
            }
        });
    }

    @Override
    public void onArticleSelected(String name) {
        // Activity can call the public methods of all its fragments
        // (first check if the fragment exists !!)
        FragmTwo fragm_two = (FragmTwo) this.getSupportFragmentManager().findFragmentById(R.id.f2);
        fragm_two.load(name);
    }

    @Override
    public void onClearSelected(){
        if (fragmThree==null){
            Log.d("FragmThree","not exist");
            Toast notificacion= Toast.makeText(this,"FragmThree not exist",Toast.LENGTH_LONG);
            notificacion.show();
        }else{
            activo=false;
            getSupportFragmentManager().beginTransaction().remove(fragmThree).commit();
        }
    }

    @Override
    public void onSeekBarChange(String name,int value){

        if(!activo) {
            if (findViewById(R.id.linearLayout_fragm) != null) {
                if (fragmThree == null || !fragmThree.isVisible()) {
                    // Create a new Fragment to be placed in the activity layout
                    fragmThree = new FragmThree();

                    count_fragm_three++;

                    Bundle args = new Bundle();
                    args.putString(NAME, name);
                    args.putInt(VALUE, value);
                    args.putInt(COUNTER, count_fragm_three);

                    fragmThree.setArguments(args);
                    // Add the fragment to Activity root ViewGroup (with an id)
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.linearLayout_fragm, fragmThree).commit();
                } else {

                    fragmThree = new FragmThree();

                    Bundle args = new Bundle();
                    args.putString(NAME, name);
                    args.putInt(VALUE, value);
                    args.putInt(COUNTER, count_fragm_three);

                    fragmThree.setArguments(args);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.linearLayout_fragm, fragmThree);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();

                }
            }
        }

    }

    @Override
    public void onChecked(boolean b){
        activo=b;
    }
}
