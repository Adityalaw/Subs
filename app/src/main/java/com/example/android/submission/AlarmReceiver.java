package com.example.android.submission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
          int num = intent.getExtras().getInt("number");
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
//              Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//              if (alarmUri == null) {
//                  alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//              }
//              Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//              ringtone.play();
          if (day == Calendar.SUNDAY){
              //Play from Url
              String audioUrl = "http://www.all-birds.com/Sound/western%20bluebird.wav";
              MediaPlayer mediaPlayer = new MediaPlayer();
              mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
              try{
                  mediaPlayer.setDataSource(audioUrl);
                  mediaPlayer.prepare();
                  mediaPlayer.start();

              } catch (IOException e){
                  e.getStackTrace();
              }

          }

        else {
              int[] array = {R.raw.bingomarimba, R.raw.fantasy_alarm_clock, R.raw.magical_kingdom};
              Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
              if (alarmUri == null) {
                  alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
              }
              Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
                 ringtone.play();
          }
    }
}