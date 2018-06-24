package preferenciascorusuario.cursoandroid.dell.preferenciascorusuario;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button btnSalvar;
    private RadioGroup radioGroup;
    private RadioButton radioSelecionado;
    private RelativeLayout relativeLayout;

    private static final String ARQUIVO_PREFERENCIA = "arquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = (Button) findViewById(R.id.btn_salvar_id);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_id);
        relativeLayout = (RelativeLayout) findViewById(R.id.layout_id);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idSelecionado = radioGroup.getCheckedRadioButtonId();

                if (idSelecionado > 0) {
                    radioSelecionado = (RadioButton) findViewById(idSelecionado);

                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("corEscolhida", radioSelecionado.getText().toString());
                    editor.commit();

                    alterarCor(radioSelecionado.getText().toString());

                } else {
                    Toast.makeText(getApplicationContext(), "Selecione pelo menos uma cor", Toast.LENGTH_LONG).show();
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if (sharedPreferences.contains("corEscolhida")) {
            String corSalva = sharedPreferences.getString("corEscolhida", "Branco");
            alterarCor(corSalva);
            alterarRadioButton(corSalva);
        }
    }

    public void alterarCor(String cor) {
        if (cor.equals("Laranja")) {
            relativeLayout.setBackgroundColor(Color.parseColor("#ffce26"));
        } else if(cor.equals("Azul")) {
            relativeLayout.setBackgroundColor(Color.parseColor("#495b7c"));
        } else if(cor.equals("Verde")) {
            relativeLayout.setBackgroundColor(Color.parseColor("#009670"));
        } else {
            relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    public void alterarRadioButton(String cor) {
        if (cor.equals("Laranja")) {
            radioGroup.check(R.id.laranja_id);
        } else if(cor.equals("Azul")) {
            radioGroup.check(R.id.azul_id);
        } else if(cor.equals("Verde")) {
            radioGroup.check(R.id.verde_id);
        }
    }
}
