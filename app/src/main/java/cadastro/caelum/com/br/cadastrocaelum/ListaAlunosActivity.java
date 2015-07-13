package cadastro.caelum.com.br.cadastrocaelum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cadastro.caelum.com.br.dao.AlunoDAO;
import cadastro.caelum.com.br.dao.DBHelper;
import cadastro.caelum.com.br.extra.Extras;
import cadastro.caelum.com.br.modelo.Aluno;

/**
 * Created by carloseduardo on 10/07/15.
 */
public class ListaAlunosActivity extends ActionBarActivity {

    private ListView lista;
    private Aluno aluno;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_aluno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);
        lista = (ListView) findViewById(R.id.lista_alunos);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Intent alterar = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                Aluno alunoParaSerAlterado = (Aluno) adapter.getItemAtPosition(position);
                alterar.putExtra(Extras.ALUNO_SELECIONADO, alunoParaSerAlterado);
                startActivity(alterar);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                aluno = (Aluno) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.novo:
                startActivity(new Intent(this, FormularioActivity.class));
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem ligar = menu.add("Ligar");
        MenuItem sms = menu.add("Enviar SMS");
        MenuItem mapa = menu.add("Achar no Mapa");
        MenuItem site = menu.add("Navegar no Site");
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlunoDAO dao = new AlunoDAO(new DBHelper(ListaAlunosActivity.this));
                dao.deletar(aluno);
                dao.closeConnection();
                carregaLista();
                return false;
            }
        });

        MenuItem email = menu.add("Enviar E-mail");
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        lista = (ListView) findViewById(R.id.lista_alunos);
        final List<Aluno> alunos = new AlunoDAO(new DBHelper(this)).getAlunos();
        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(adapter);
    }
}
