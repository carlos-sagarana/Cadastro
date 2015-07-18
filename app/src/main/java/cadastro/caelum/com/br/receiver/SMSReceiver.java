package cadastro.caelum.com.br.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import cadastro.caelum.com.br.cadastrocaelum.R;
import cadastro.caelum.com.br.dao.AlunoDAO;
import cadastro.caelum.com.br.dao.DBHelper;

/**
 * Created by carloseduardo on 18/07/15.
 */
public class SMSReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] mensagens = (Object[]) intent.getExtras().get("pdus");
        byte[]  messagem = (byte[]) mensagens[0];
        SmsMessage sms = SmsMessage.createFromPdu(messagem);
        String number = sms.getDisplayOriginatingAddress();
        boolean isAluno = new AlunoDAO(new DBHelper(context)).checkNumber(number);

        if (isAluno) {
            MediaPlayer.create(context, R.raw.msg).start();
            Toast.makeText(context, "SMS de um aluno, cujo número é: " + number, Toast.LENGTH_LONG).show();
        }
    }
}
