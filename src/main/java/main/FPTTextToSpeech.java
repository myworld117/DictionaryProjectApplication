package main;

import api.FPTTextToSpeechAPI;
import javafx.scene.media.MediaPlayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import javafx.scene.media.Media;
public class FPTTextToSpeech implements FPTTextToSpeechAPI {

    public static void main(String[] args) throws IOException {
    }

    @Override
    public void getAudioUrl(String text) throws IOException {
        String url = "https://api.fpt.ai/hmi/tts/v5";
        String apiKey = "Ad90RZuhqIjBlQJyQVRoSneuWiJEGefr";
        String speed = ""; // Tốc độ phát âm
        String voice = "banmai"; // Giọng đọc

        // Tạo kết nối
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        // Thiết lập phương thức
        connection.setRequestMethod("POST");

        // Thiết lập tiêu đề
        connection.setRequestProperty("api-key", apiKey);
        connection.setRequestProperty("speed", speed);
        connection.setRequestProperty("voice", voice);

        // Kích hoạt chế độ gửi dữ liệu
        connection.setDoOutput(true);

        // Gửi dữ liệu
        try (OutputStream os = connection.getOutputStream()) {
            os.write(text.getBytes());
        }

        // Nhận phản hồi
        String audioUrl = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            audioUrl = br.readLine();
            audioUrl = audioUrl.substring(audioUrl.indexOf("async") + 8, audioUrl.indexOf("error") - 3);
        }

        Media media = new Media(audioUrl);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
