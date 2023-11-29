package api;

import java.io.IOException;

public interface FPTTextToSpeechAPI   {
    default  void getAudioUrl(String text) throws IOException {
    }

}
