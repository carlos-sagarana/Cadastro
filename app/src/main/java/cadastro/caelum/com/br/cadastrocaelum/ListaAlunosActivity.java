package cadastro.caelum.com.br.cadastrocaelum;

import android.content.Intent;
import android.net.Uri;
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

import cadastro.caelum.com.br.adapter.AlunoAdapter;
import cadastro.caelum.com.br.converter.AlunoConverter;
import cadastro.caelum.com.br.dao.AlunoDAO;
import cadastro.caelum.com.br.dao.DBHelper;
import cadastro.caelum.com.br.extra.Extras;
import cadastro.caelum.com.br.modelo.Aluno;
import cadastro.caelum.com.br.network.WebClient;
import cadastro.caelum.com.br.task.EnviaAlunosTask;

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
                return false;

            case R.id.enviarAluno:
                new EnviaAlunosTask(this).execute();
                return false;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem ligar = menu.add(0, 0, 0, "Ligar");
        Intent intentLigar = new Intent(Intent.ACTION_DIAL);
        Uri numero = Uri.parse("tel:" + aluno.getTelefone());
        intentLigar.setData(numero);
        ligar.setIntent(intentLigar);

        MenuItem sms = menu.add(0, 1, 0, "Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:" + aluno.getTelefone()));
        intentSMS.putExtra("sms_body", "Mensagem");
        sms.setIntent(intentSMS);

        MenuItem mapa = menu.add(0, 2, 0, "Achar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + aluno.getEndereco()));
        mapa.setIntent(intentMapa);

        MenuItem site = menu.add(0, 3, 0, "Navegar no Site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        intentSite.setData(Uri.parse("http://" + aluno.getSite()));
        site.setIntent(intentSite);

        MenuItem deletar = menu.add(0, 4, 0, "Deletar");
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

        MenuItem email = menu.add(0, 5, 0, "Enviar E-mail");
        Intent intentEmail = new Intent(Intent.ACTION_SEND);
        intentEmail.setType("message/rfc822");
        intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[] {"carloseduardosx2015@gmail.com"});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Elogios do curso de android");
        intentEmail.putExtra(Intent.EXTRA_TEXT, "Este curso � �timo!!!");
        email.setIntent(intentEmail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        lista = (ListView) findViewById(R.id.lista_alunos);
        final List<Aluno> alunos = new AlunoDAO(new DBHelper(this)).getAlunos();
        AlunoAdapter adapter = new AlunoAdapter(alunos, this);
        lista.setAdapter(adapter);
    }
}
