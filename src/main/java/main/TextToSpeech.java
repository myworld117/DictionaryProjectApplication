package main;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    private final String VOICENAME = "kevin16";
    private Voice voice;

    public TextToSpeech() {
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        for (Voice v : voices) {
            System.out.println("Available Voice: " + v.getName());
        }
    }

    public void speak(String text) {
        if (voice != null) {
            voice.speak(text);
            System.out.println("Text spoken");
        } else {
            System.out.println("Voice is null");
        }
    }

}