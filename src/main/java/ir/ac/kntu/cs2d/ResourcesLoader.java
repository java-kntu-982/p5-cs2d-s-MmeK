package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Util.ResourceHelper;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ResourcesLoader {

    private Map<String, Image> images = new HashMap<>();
    private Map<String, AudioClip> audioClips = new HashMap<>();

    public ResourcesLoader() {
        images.put("Counter_Terrorist", new Image(Paths.get(ResourceHelper.IMAGES + "manBlue_gun.png").toUri().toString()));
        images.put("Terrorist", new Image(Paths.get(ResourceHelper.IMAGES + "manBrown_gun.png").toUri().toString()));

        audioClips.put("AK-47_Fire", new AudioClip(Paths.get(ResourceHelper.SOUNDS + "weapons/ak/ak47.wav").toUri().toString()));
        audioClips.put("AK-47_Reload", new AudioClip(Paths.get(ResourceHelper.SOUNDS + "weapons/ak/ak47_clipin.wav").toUri().toString()));

        audioClips.put("Glock_Fire", new AudioClip(Paths.get(ResourceHelper.SOUNDS + "weapons/glock/glock.wav").toUri().toString()));
        audioClips.put("Glock_Reload", new AudioClip(Paths.get(ResourceHelper.SOUNDS + "weapons/glock/glock_slideback.wav").toUri().toString()));
    }

    public Map<String, Image> getImages() {
        return images;
    }

    public Map<String, AudioClip> getAudioClips() {
        return audioClips;
    }
}
