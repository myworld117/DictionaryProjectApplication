package api;

import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.FileOutputStream;
public class VoiceRSS {
    private static final String apiKey = "ee1a861047db41e3aed6cca75554a826";
    public static void speak(String text, String language) throws Exception {
        VoiceProvider tts = new VoiceProvider(apiKey);
        VoiceParameters params = new VoiceParameters(text, language);
        params.setCodec(AudioCodec.MP3);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(-2);

        byte[] voice = tts.speech(params);

        String audioFilePath = "speak.mp3";
        FileOutputStream fos = new FileOutputStream(audioFilePath);
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();

        Media media = new Media(new File(audioFilePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}

